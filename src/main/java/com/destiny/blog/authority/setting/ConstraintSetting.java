package com.destiny.blog.authority.setting;

import com.destiny.blog.authority.enums.Operation;

import java.util.List;

public interface ConstraintSetting {

    String getKind();

    Operation getOperation();

    List<String> getConstraints();
}
