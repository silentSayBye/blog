package com.destiny.api.authority.interpreter;

import com.destiny.api.authority.explanation.Explanation;
import com.destiny.api.authority.setting.AuthoritySetting;
import com.destiny.api.authority.setting.ColumnConstraintSetting;
import com.destiny.api.authority.setting.MybatisAuthoritySetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Date 2020/2/20 20:07
 * @Version 1.0
 **/
@Slf4j
public class JDBCAuthorityInterpreter extends AbstractAuthorityInterpreter implements AuthorityInterpreter {

    public JDBCAuthorityInterpreter(Map<String, AuthoritySetting> authoritySetting) {
        super(authoritySetting);
    }

    @Override
    protected Map<String, Explanation> interpret(List<String> authorityCodes, StandardEvaluationContext context) {
        Map<String, Explanation> explanationMap = new HashMap<>();
        authorityCodes.parallelStream().forEach(authorityCode -> {
            AuthoritySetting authoritySetting = authoritySettingMap.get(authorityCode);
            MybatisAuthoritySetting mybatisAuthoritySetting = (MybatisAuthoritySetting) authoritySetting;
            log.info("mybatisAuthoritySetting is {}", mybatisAuthoritySetting);
            List<ColumnConstraintSetting> constraintSettingList = mybatisAuthoritySetting.getColumnConstraintSettings();
            constraintSettingList.forEach(columnConstraintSetting -> {
                unionConstraints(explanationMap, columnConstraintSetting, context);
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
