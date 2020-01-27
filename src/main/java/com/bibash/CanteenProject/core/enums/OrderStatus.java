package com.bibash.CanteenProject.core.enums;

public enum OrderStatus {
    READY("Ready"),PENDING("Pending"),DELIVERED("delivered");

    private final String value;

    OrderStatus(String orderStatus) {
        this.value=orderStatus;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (OrderStatus v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }
}
