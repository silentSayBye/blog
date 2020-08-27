package com.destiny.security.authority.setting;

import com.destiny.security.authority.enums.Operation;

import java.util.List;

public interface ConstraintSetting {

    String getKind();

    Operation getOperation();

    List<String> getConstraints();
}
