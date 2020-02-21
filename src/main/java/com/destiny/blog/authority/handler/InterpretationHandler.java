package com.destiny.blog.authority.handler;

import com.destiny.blog.authority.enums.Operation;
import com.destiny.blog.authority.explanation.Explanation;
import com.destiny.blog.authority.setting.ConstraintSetting;

import org.springframework.expression.spel.support.StandardEvaluationContext;

public interface InterpretationHandler {

    Explanation interpret(ConstraintSetting constraintSetting, StandardEvaluationContext context);

    Boolean supports(Operation operation);
}
