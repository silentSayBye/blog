package com.destiny.api.authority.handler;

import com.destiny.api.authority.enums.Operation;
import com.destiny.api.authority.setting.ConstraintSetting;

import java.util.List;


/**
 * @Date 2020/2/20 15:43
 * @Version 1.0
 **/
public class BetweenOperationHandler extends AbstractInterpretationHandler implements InterpretationHandler {
    @Override
    protected String handleSQLFramentGeneration(ConstraintSetting setting, Object operands) {
        List<Object> operandList =(List<Object>)operands;
        return String.format(" %s between and %s ",setting.getKind(),operandList.get(0),operandList.get(1));
    }

    @Override
    protected OperandQuality getOperandQuality() {
        return OperandQuality.COUPLE;
    }

    @Override
    public Boolean supports(Operation operation) {
        return Operation.AND == operation;
    }
}
