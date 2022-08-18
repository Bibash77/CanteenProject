package com.bibash.canteenproject.core.enums;

public enum  TransactionType {

    ORDER("Order"), TOPUP("Top-Up") , CANCEL("cancel");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
