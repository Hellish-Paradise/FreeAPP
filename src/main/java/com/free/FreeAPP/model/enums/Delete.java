package com.free.FreeAPP.model.enums;

/**
 * @Date 2021/10/18   9:08
 */

public enum Delete {
    NO("N"),
    YES("Y");

    private final String delete;

    private Delete(String delete) {
        this.delete = delete;
    }

    public String toString() {
        return this.delete;
    }
}
