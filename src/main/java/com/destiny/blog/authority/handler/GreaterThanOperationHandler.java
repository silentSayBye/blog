package com.destiny.blog.authority.handler;

import com.destiny.blog.authority.enums.Operation;
import com.destiny.blog.authority.setting.ConstraintSetting;

/**
 * @Date 2020/2/20 15:55
 * @Version 1.0
 **/
public class GreaterThanOperationHandler extends AbstractInterpretationHandler implements InterpretationHandler {
    @Override
    protected String handleSQLFramentGeneration(ConstraintSetting setting, Object operands) {
        return String.format(" %s > %s ",setting.getKind(),operands);
    }

    @Override
    protected OperandQuality getOperandQuality() {
        return OperandQuality.SINGLE;
    }

    @Override
    public Boolean supports(Operation operation) {
        return Operation.GREATER_THAN == operation;
    }
}
