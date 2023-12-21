package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitflyerCryptoCurrency {
    private String productCode;
    private double price;

    @JsonProperty("product_code")
    public String getProductCode() {
        return productCode;
    }

    @JsonProperty("product_code")
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice() {
        this.price = price;
    }
}
