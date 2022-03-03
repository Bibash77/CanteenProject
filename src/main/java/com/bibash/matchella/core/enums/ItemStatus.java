package com.bibash.matchella.core.enums;

public enum ItemStatus {

    INSTOCK("in stock"), OUTOFSTOCK("out of stock");

    private final String value;

    ItemStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static ItemStatus getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (ItemStatus v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }
}
