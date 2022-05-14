package com.goopnigoop.hibcheck.enums;

public enum Currency {
    USD, EUR;

    @Override
    public String toString() {
        return this.name();
    }
}
