package com.destiny.api.authority.handler;

import com.destiny.api.authority.enums.Operation;
import com.destiny.api.authority.setting.ConstraintSetting;

/**
 * @Date 2020/2/20 15:51
 * @Version 1.0
 **/
public class EqualOperationHandler extends AbstractInterpretationHandler implements InterpretationHandler {
    @Override
    protected String handleSQLFramentGeneration(ConstraintSetting setting, Object operands) {
        return String.format(" %s = %s", setting.getKind(),operands);
    }

    @Override
    protected OperandQuality getOperandQuality() {
        return OperandQuality.SINGLE;
    }

    @Override
    public Boolean supports(Operation operation) {
        return Operation.EQUAL == operation;
    }
}
