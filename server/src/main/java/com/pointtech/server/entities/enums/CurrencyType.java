package com.pointtech.server.entities.enums;

public enum CurrencyType {
    USD(0),
    EUR(1),
    GBP(2),
    CAD(3),
    BRL(4);

    private int code;

    private CurrencyType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CurrencyType valueOf(int code) {
        for (CurrencyType value : CurrencyType.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid CurrencyType code");
    }

}
