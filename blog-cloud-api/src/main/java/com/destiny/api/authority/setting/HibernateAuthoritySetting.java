package com.destiny.api.authority.setting;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Date 2020/2/20 13:43
 * @Version 1.0
 **/
@Data
public class HibernateAuthoritySetting implements AuthoritySetting {
    private String parentCode;

    private String authorityCode;

    private Class entityClass;

    private List<FieldConstraintSetting> fieldConstraintSettings = Lists.newArrayList();
}
