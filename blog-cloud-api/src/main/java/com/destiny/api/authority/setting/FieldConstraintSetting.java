package com.destiny.api.authority.setting;

import com.destiny.api.authority.enums.Operation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/2/20 13:30
 * @Version 1.0
 **/

@Data
public class FieldConstraintSetting implements ConstraintSetting{

    private String fieldName;

    private Operation operation;

    private List<String> constraints = new ArrayList<>();

    @Override
    public String getKind() {
        return this.fieldName;
    }
}
