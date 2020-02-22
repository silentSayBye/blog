package com.destiny.api.authority.interpreter;


import com.destiny.api.authority.explanation.Explanation;
import com.destiny.api.authority.setting.AuthoritySetting;
import com.destiny.api.authority.setting.FieldConstraintSetting;
import com.destiny.api.authority.setting.HibernateAuthoritySetting;
import com.google.common.collect.Maps;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Date 2020/2/20 19:16
 * @Version 1.0
 **/
public class HibernateAuthorityInterpreter extends AbstractAuthorityInterpreter implements AuthorityInterpreter {

    public HibernateAuthorityInterpreter(Map<String, AuthoritySetting> authoritySettingMap) {
        super(authoritySettingMap);
    }

    @Override
    protected Map<String, Explanation> interpret(List<String> authorityCodes, StandardEvaluationContext context) {
        Map<String, Explanation> explanationMap = Maps.newHashMap();
        authorityCodes.forEach(authorityCode ->{
            AuthoritySetting authoritySetting = this.authoritySettingMap.get(authorityCode);
            List<FieldConstraintSetting> constraintSettings = ((HibernateAuthoritySetting) authoritySetting).getFieldConstraintSettings();
            constraintSettings.forEach(constraintSetting ->{
                unionConstraints(explanationMap, constraintSetting, context);
            });
        });
        return explanationMap;
    }

    @Override
    protected String convertExplanationToText(Map<String, Explanation> explanationMap) {
        List<String> sqlSegments = explanationMap.values().stream().map(explanation -> explanation.getSqlFragment())
                .collect(Collectors.toList());
        return String.join(" and ", sqlSegments);
    }
}
