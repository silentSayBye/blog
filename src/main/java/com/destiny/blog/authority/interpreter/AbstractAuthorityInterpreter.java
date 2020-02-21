package com.destiny.blog.authority.interpreter;

import com.destiny.blog.authority.exception.InvalidAuthorityCodeException;
import com.destiny.blog.authority.exception.NotSupportedConstraintSetting;
import com.destiny.blog.authority.explanation.Explanation;
import com.destiny.blog.authority.explanation.OrExplanation;
import com.destiny.blog.authority.handler.*;
import com.destiny.blog.authority.qualifier.AuthorityQualifier;
import com.destiny.blog.authority.setting.AuthoritySetting;
import com.destiny.blog.authority.setting.ConstraintSetting;
import com.destiny.blog.authority.setting.HibernateAuthoritySetting;
import com.destiny.blog.authority.setting.MybatisAuthoritySetting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Date 2020/2/20 16:38
 * @Version 1.0
 **/
@Slf4j
@Data
public abstract class AbstractAuthorityInterpreter implements AuthorityInterpreter {

    protected List<InterpretationHandler> interpretationHandlers = Lists.newArrayList();

    protected Map<String, AuthoritySetting> authoritySettingMap = Maps.newHashMap();

    public AbstractAuthorityInterpreter(Map<String, AuthoritySetting> authoritySetting){
        List<InterpretationHandler> interpretationHandlers = Lists.newArrayList();
        interpretationHandlers.add(new BetweenOperationHandler());
        interpretationHandlers.add(new EqualOperationHandler());
        interpretationHandlers.add(new GreaterOrEqualOperationHandler());
        interpretationHandlers.add(new GreaterThanOperationHandler());
        interpretationHandlers.add(new InOperationHandler());
        interpretationHandlers.add(new LessOrEqualOperationHandler());
        interpretationHandlers.add(new LessThanOperationHandler());
        interpretationHandlers.add(new LikeOperationHandler());
        interpretationHandlers.add(new NotEqualOperationHandler());
        this.interpretationHandlers = interpretationHandlers;
        this.authoritySettingMap = authoritySetting;
    }

    Comparator<Explanation> comparator = new Comparator<Explanation>() {
        @Override
        public int compare(Explanation o1, Explanation o2) {
            Preconditions.checkNotNull(o1.getOperands(),"Operand of comparing o1 should not be null");
            Preconditions.checkNotNull(o2.getOperands(),"Operand of comparing o2 should not be null");
            Preconditions.checkArgument((o1.getOperands() instanceof Number || o1.getOperands() instanceof Date || o1.getOperands() instanceof BigDecimal)
                && (o2.getOperands() instanceof Number || o2.getOperands() instanceof Date || o2.getOperands() instanceof BigDecimal)
                && o1.getClass() == o2.getClass(),
                    String.format("Comparing operand type should be Number, Date or BigDecimal and should be same,actual operand type is o1.operand[%s], o2.operands[%s]",
                            o1.getOperands().getClass(),o2.getOperands().getClass()));
            if (o1.getOperands() instanceof Integer){
                return (Integer)o1.getOperands() - (Integer)o2.getOperands();
            }else if (o1.getOperands() instanceof Long){
                return ((Long)o1.getOperands()).compareTo((Long)o2.getOperands());
            }else if (o1.getOperands() instanceof Date){
                return ((Date)o1.getOperands()).compareTo((Date)o2.getOperands());
            }else{
                throw new IllegalStateException("Can not do comparision for value of type other than Integer, Long, Date or BigDecimal");
            }
        }
    };

    @Override
    public Map<String, Explanation> interpret(List<String> authorityCodes, AuthorityQualifier qualifier, StandardEvaluationContext context) {
        if (CollectionUtils.isEmpty(authorityCodes)){
            return new HashMap<>();
        }
        log.info("authorityCodes is [{}]",authorityCodes);
        classifyAuthorityCode(authorityCodes,qualifier);
        return interpret(authorityCodes,context);
    }

    @Override
    public String interpretAsText(List<String> authority, AuthorityQualifier qualifier, StandardEvaluationContext context) {
        return convertExplanationToText(interpret(authority,qualifier,context));
    }
    protected void unionConstraints(Map<String,Explanation> explanationMap, ConstraintSetting constraintSetting,
                                    StandardEvaluationContext context){

        Explanation explanation = interpretExplanation(constraintSetting, context);
        if (explanationMap.containsKey(explanation.getKind())){
            Explanation existing = explanationMap.get(explanation.getKind());
            unionConstraints(explanationMap,existing,explanation);
        }else{
            explanationMap.put(explanation.getKind(),explanation);
        }

    }

    private Explanation interpretExplanation(ConstraintSetting constraintSetting,StandardEvaluationContext context){
        for(InterpretationHandler handler:interpretationHandlers){
            if (handler.supports(constraintSetting.getOperation())){
               return handler.interpret(constraintSetting,context);
            }
        }
        throw new NotSupportedConstraintSetting(constraintSetting.getOperation().toString());
    }

    private void unionConstraints(Map<String,Explanation> explanationMap, Explanation existing, Explanation explanation){
        if (existing.getOperation() == explanation.getOperation()){
            unionSameOperationConstraints(explanationMap, existing, explanation);
        }else {
            unionDifferentOperationConstraints();
        }
    }

    private void unionSameOperationConstraints(Map<String,Explanation> explanationMap, Explanation existing, Explanation newOne){
        switch (existing.getOperation()){
            case EQUAL:
            case NOT_EQUAL:
                OrExplanation orExplanation = new OrExplanation(existing,newOne);
                explanationMap.put(existing.getKind(),orExplanation);
                break;
            case BETWEEN:
                throw new IllegalStateException("BETWEEN union not supported yet");
            case LESS_THAN:
            case LESS_OR_EQUAL:
                explanationMap.put(existing.getKind(),min(existing,newOne));
                break;
            case GREATER_THAN:
            case GREATER_OR_EQUAL:
                explanationMap.put(existing.getKind(),max(existing,newOne));
                break;
            case IN:
                Preconditions.checkArgument(existing.getOperands() instanceof List, "operand of IN operation should be list");
                Preconditions.checkArgument(newOne.getOperands() instanceof List, "operand of IN operation should be list");
                ((List)existing.getOperands()).addAll((List)newOne.getOperands());
                explanationMap.put(existing.getKind(),existing);
                break;
            default:
                throw new IllegalStateException(String.format("this Operation %s is not supported yet",existing.getOperation()));
        }
    }

    private Explanation min(Explanation existing, Explanation newOne){
        return Arrays.asList(existing,newOne).stream().min(comparator).get();
    }

    private Explanation max(Explanation existing, Explanation newOne){
        return Arrays.asList(existing,newOne).stream().max(comparator).get();
    }

    private void unionDifferentOperationConstraints() {
        throw new IllegalStateException("Different operation union not supported yet");
    }

    protected abstract Map<String, Explanation> interpret(List<String> authorityCodes, StandardEvaluationContext context);

    protected abstract String convertExplanationToText(Map<String, Explanation> explanationMap);

    private ClassificationResult classifyAuthorityCode(List<String> authorityCodes, AuthorityQualifier qualifier){
        ClassificationResult classificationResult = new ClassificationResult();

        authorityCodes.stream()
                .forEach(authorityCode -> {
                            AuthoritySetting authoritySetting = this.authoritySettingMap.get(authorityCode);
                            if (authoritySetting == null){
                                throw new InvalidAuthorityCodeException(authorityCode);
                            }else{
                                Object resource = null;
                                if (authoritySetting instanceof MybatisAuthoritySetting){
                                    resource = ((MybatisAuthoritySetting) authoritySetting).getTableName();
                                }else if (authoritySetting instanceof HibernateAuthoritySetting){
                                    resource = ((HibernateAuthoritySetting) authoritySetting).getEntityClass();
                                }
                                if (qualifier != null && !qualifier.qualifier(resource)){
                                    classificationResult.addInvalidCode(authoritySetting.getAuthorityCode());
                                }
                            }
                        });
        authorityCodes.removeAll(classificationResult.getInvalidCodes());
        authorityCodes.forEach(
                authorityCode ->{
                    if (hasOverrideAuthorityCode(authorityCode,authorityCodes,qualifier)){
                        classificationResult.addOverrideCode(authorityCode);
                    }
                }
        );
        authorityCodes.removeAll(classificationResult.getOverrideCodes());
        return classificationResult;
    }

    private Boolean hasOverrideAuthorityCode(String authorityCode, List<String> authorityCodes, AuthorityQualifier qualifier){
        AuthoritySetting authoritySetting = this.authoritySettingMap.get(authorityCode);
        String parentCode = authoritySetting.getParentCode();
        if (StringUtils.isBlank(parentCode)){
            return false;
        }

        if (!authorityCodes.contains(parentCode)){
            return false;
        }
        AuthoritySetting parentAuthoritySetting = this.authoritySettingMap.get(parentCode);
        if (parentAuthoritySetting == null){
            throw new InvalidAuthorityCodeException(parentCode);
        }
        return true;
    }

    @Data
    class ClassificationResult{
        private List<String> invalidCodes = Lists.newArrayList();
        private List<String> overrideCodes = Lists.newArrayList();

        public void addInvalidCode(String code){
            invalidCodes.add(code);
        }
        public void addOverrideCode(String code){
            overrideCodes.add(code);
        }
    }
}
