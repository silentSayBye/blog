package com.blog.security.authority.setting;

import com.blog.security.authority.enums.Operation;

import java.util.List;

public interface ConstraintSetting {

    String getKind();

    Operation getOperation();

    List<String> getConstraints();
}
