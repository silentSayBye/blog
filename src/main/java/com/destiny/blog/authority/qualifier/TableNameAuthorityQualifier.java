package com.destiny.blog.authority.qualifier;

import org.apache.commons.lang3.StringUtils;

/**
 * @Date 2020/2/20 13:57
 * @Version 1.0
 **/
public class TableNameAuthorityQualifier implements AuthorityQualifier {
    private String tableName;

    @Override
    public boolean qualifier(Object target) {
        if (StringUtils.isBlank(tableName)){
            return true;
        }
        return target instanceof String && tableName.equals(target);
    }
}
