package com.destiny.api.authority.interpreter;

import com.destiny.api.authority.explanation.Explanation;
import com.destiny.api.authority.qualifier.AuthorityQualifier;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Map;

/**
 * @Date 2020/2/20 16:23
 * @Version 1.0
 **/
public interface AuthorityInterpreter {

    Map<String, Explanation> interpret(List<String> authority, AuthorityQualifier qualifier, StandardEvaluationContext context);

    String interpretAsText(List<String> authority, AuthorityQualifier qualifier, StandardEvaluationContext context);


}
