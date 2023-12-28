package org.example.currency;

public enum CurrencyName {
    ETHERIUM("eth"),
    BITKOIN("btc"),
    TETHER("usdt"),
    RIPPLE("xrp"),
    LITECOIN("ltc");


    private String name;

    CurrencyName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}


