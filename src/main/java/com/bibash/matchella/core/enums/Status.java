package com.bibash.matchella.core.enums;


public enum Status {
    INACTIVE("Inactive"), ACTIVE("Active"), DELETED("Deleted");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
