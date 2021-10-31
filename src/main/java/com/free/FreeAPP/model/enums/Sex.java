package com.free.FreeAPP.model.enums;

/**
 * @Date 2021/10/18   9:08
 */

public enum Sex {
    Male("M"),
    Female("W"),
    Confidentiality("S");

    private final String sex;

    private Sex(String sex) {
        this.sex = sex;
    }

    public String toString() {
        return this.sex;
    }
}