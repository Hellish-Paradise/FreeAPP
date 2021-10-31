package com.free.FreeAPP.model.enums;

/**
 * @Date 2021/10/18   9:07
 */

public enum Status {
    Normal(1),
    Exceptions(0),
    Forbidden(3);

    private final Integer status;

    private Status(Integer status) {
        this.status = status;
    }

    public String toString() {
        return String.valueOf(this.status);
    }
}
