package com.destiny.security.authority.handler;

import com.blog.security.authority.explanation.Explanation;
import com.blog.security.authority.setting.ConstraintSetting;
import com.destiny.security.authority.explanation.Explanation;
import com.destiny.security.authority.setting.ConstraintSetting;
import com.google.common.base.Preconditions;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date 2020/2/20 14:18
 * @Version 1.0
 **/
public abstract class AbstractInterpretationHandler implements InterpretationHandler{

    protected ExpressionParser expressionParser = new SpelExpressionParser();

    protected static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Explanation interpret(ConstraintSetting constraintSetting, StandardEvaluationContext context) {
        check(constraintSetting);

        List<Object> operandList = constraintSetting.getConstraints().stream()
                .map(constraint -> expressionParser.parseExpression(constraint).getValue(context))
                .collect(Collectors.toList());

        Object operands = getOperandQuality().picker.pick(operandList);
        return Explanation.builder()
                .kind(constraintSetting.getKind())
                .operands(operands)
                .operation(constraintSetting.getOperation())
                .sqlFragment(handleSQLFramentGeneration(constraintSetting,preProcessOperands(operands)))
                .build();
    }

    protected abstract String handleSQLFramentGeneration(ConstraintSetting setting, Object operands);

    private Object preProcessOperands(Object operands){
        if (operands instanceof List){
            List<Object> operandList = (List<Object>)operands;
            return operandList.stream()
                    .map(this::processOperandType)
                    .collect(Collectors.toList());
        }
        return processOperandType(operands);

    }

    private Object processOperandType(Object operand){
        Preconditions.checkNotNull(operand);
        if (operand instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
            return stringSqlParameter(sdf.format(operand));
        }else if (operand instanceof Integer){
            return operand;
        }else if (operand instanceof Double){
            return operand;
        }else if (operand instanceof BigDecimal){
            return operand;
        }else if (operand instanceof Long){
            return operand;
        }
        return stringSqlParameter(operand.toString());
    }
    private String stringSqlParameter(String parameter){
        return String.format("'%s'",parameter);
    }



    protected void check(ConstraintSetting constraintSetting){
        getOperandQuality().checker.check(constraintSetting);
    }

    protected abstract OperandQuality getOperandQuality();


    enum OperandQuality{

        SINGLE(new Checker() {
            @Override
            public void check(ConstraintSetting constraintSetting) {
                Preconditions.checkArgument(!CollectionUtils.isEmpty(constraintSetting.getConstraints())
                                && constraintSetting.getConstraints().size() == 1,
                        String.format("Invalid column constraints setting %s",
                                String.join(",", constraintSetting.getConstraints())));
            }
        }, new Picker() {
            @Override
            public Object pick(List<Object> operands) {
                return operands.get(0);
            }
        }),
        COUPLE(new Checker() {
            @Override
            public void check(ConstraintSetting constraintSetting) {
                Preconditions.checkArgument(!CollectionUtils.isEmpty(constraintSetting.getConstraints())
                                && constraintSetting.getConstraints().size() == 2,
                        String.format("Invalid column constraints setting %s",
                                String.join(",", constraintSetting.getConstraints())));
            }
        }, new Picker() {
            @Override
            public Object pick(List<Object> operands) {
                return operands.subList(0,2);
            }
        }),
        COLLECTION(new Checker() {
            @Override
            public void check(ConstraintSetting constraintSetting) {
                Preconditions.checkArgument(!CollectionUtils.isEmpty(constraintSetting.getConstraints()),
                        String.format("Invalid column constraints setting %s",
                                String.join(",", constraintSetting.getConstraints())));
            }
        }, new Picker() {
            @Override
            public Object pick(List<Object> operands) {
                return operands;
            }
        });

        private OperandQuality(Checker checker,Picker picker){
            this.checker = checker;
            this.picker = picker;
        }

        private Checker checker;

        private Picker picker;

        interface Checker{
            void check(ConstraintSetting constraintSetting);
        }

        interface Picker{
            Object pick(List<Object> operands);
        }
    }
}
