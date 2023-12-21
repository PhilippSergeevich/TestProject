package org.example;

public enum Currency {
    ETHERIUM("eth"),
    BITKOIN("btc"),
    TETHER("usdt"),
    RIPPLE("xrp"),
    LITECOIN("ltc");


    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


