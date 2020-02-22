package com.destiny.api.authority.handler;

import com.destiny.api.authority.enums.Operation;
import com.destiny.api.authority.explanation.Explanation;
import com.destiny.api.authority.setting.ConstraintSetting;

import org.springframework.expression.spel.support.StandardEvaluationContext;

public interface InterpretationHandler {

    Explanation interpret(ConstraintSetting constraintSetting, StandardEvaluationContext context);

    Boolean supports(Operation operation);
}
