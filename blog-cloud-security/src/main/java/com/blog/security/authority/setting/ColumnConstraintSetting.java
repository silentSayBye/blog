package com.blog.security.authority.setting;

import com.blog.security.authority.enums.Operation;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Date 2020/2/20 13:41
 * @Version 1.0
 **/
@Data
public class ColumnConstraintSetting implements ConstraintSetting {

    private String columnName;

    private Operation operation;

    private List<String> constraints = Lists.newArrayList();

    @Override
    public String getKind() {
        return this.columnName;
    }

}
