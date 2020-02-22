package com.destiny.api.authority.setting;

import com.destiny.api.authority.enums.Operation;

import java.util.List;

public interface ConstraintSetting {

    String getKind();

    Operation getOperation();

    List<String> getConstraints();
}
