package com.destiny.security.authority.exception;

/**
 * @Date 2020/2/20 19:35
 * @Version 1.0
 **/
public class NotSupportedConstraintSetting extends RuntimeException {
    private String operation;

    private static final String MESSAGE = "Not support this operation %s";

    public NotSupportedConstraintSetting(String operation) {
        this.operation = operation;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, operation);
    }
}
