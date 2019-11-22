package com.bibash.CanteenProject.core.enums;

public enum RoleType {
    ADMIN("admin"), STUDENT("student"),PARENT("parent"),KITCHENER("kitchener"),ACCOUNTANT("accountant");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static RoleType getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (RoleType v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }

}
