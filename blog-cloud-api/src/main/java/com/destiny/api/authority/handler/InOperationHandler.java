package com.destiny.api.authority.handler;

import com.destiny.api.authority.enums.Operation;
import com.destiny.api.authority.setting.ConstraintSetting;

import java.util.List;

/**
 * @Date 2020/2/20 16:01
 * @Version 1.0
 **/
public class InOperationHandler extends AbstractInterpretationHandler implements InterpretationHandler {
    @Override
    protected String handleSQLFramentGeneration(ConstraintSetting setting, Object operands) {
        List operandList = (List)operands;
        return String.format(" %s in ( %s )", setting.getKind(),String.join(", ",operandList));
    }

    @Override
    protected OperandQuality getOperandQuality() {
        return OperandQuality.COLLECTION;
    }

    @Override
    public Boolean supports(Operation operation) {
        return Operation.IN == operation;
    }
}
