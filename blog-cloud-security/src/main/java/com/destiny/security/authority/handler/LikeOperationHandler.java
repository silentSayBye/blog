package com.destiny.security.authority.handler;

import com.destiny.security.authority.enums.Operation;
import com.destiny.security.authority.setting.ConstraintSetting;
import com.destiny.security.authority.setting.ConstraintSetting;

/**
 * @Date 2020/2/20 16:21
 * @Version 1.0
 **/
public class LikeOperationHandler extends AbstractInterpretationHandler implements InterpretationHandler {
    @Override
    protected String handleSQLFramentGeneration(ConstraintSetting setting, Object operands) {
        return String.format(" %s like %s ",setting.getKind(),operands);
    }

    @Override
    protected OperandQuality getOperandQuality() {
        return OperandQuality.SINGLE;
    }

    @Override
    public Boolean supports(Operation operation) {
        return Operation.LIKE == operation;
    }
}
