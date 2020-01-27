package com.bibash.CanteenProject.core.enums;

public enum  TransactionType {

    ORDER("Order"), TOPUP("Top-Up");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
