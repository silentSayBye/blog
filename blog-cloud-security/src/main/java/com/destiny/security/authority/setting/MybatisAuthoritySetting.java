package com.destiny.security.authority.setting;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

/**
 * @Date 2020/2/20 13:47
 * @Version 1.0
 **/
@Data
public class MybatisAuthoritySetting implements AuthoritySetting {

    private String parentCode;

    private String authorityCode;

    private String tableName;

    private List<ColumnConstraintSetting> columnConstraintSettings = Lists.newArrayList();
}
