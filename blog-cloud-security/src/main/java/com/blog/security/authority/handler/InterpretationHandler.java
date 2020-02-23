package com.blog.security.authority.handler;

import com.blog.security.authority.enums.Operation;
import com.blog.security.authority.explanation.Explanation;
import com.blog.security.authority.setting.ConstraintSetting;

import org.springframework.expression.spel.support.StandardEvaluationContext;

public interface InterpretationHandler {

    Explanation interpret(ConstraintSetting constraintSetting, StandardEvaluationContext context);

    Boolean supports(Operation operation);
}
