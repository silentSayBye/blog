package com.destiny.blog.authority.interpreter;

import com.destiny.blog.authority.explanation.Explanation;
import com.destiny.blog.authority.qualifier.AuthorityQualifier;
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
