package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitflyerCryptoCurrency {
    private String productCode;
    private double price;
    private String type;
    private String priceStr;
    private double change;
    private String changeStr;

    private String pair;
    private double[] priceHistories;

    @JsonProperty("pair")

    public String getPair() {
        return pair;
    }

    @JsonProperty("pair")

    public void setPair(String pair) {
        this.pair = pair;
    }


    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("price_str")
    public String getPriceStr() {
        return priceStr;
    }

    @JsonProperty("price_str")
    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    @JsonProperty("change")
    public double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(double change) {
        this.change = change;
    }

    @JsonProperty("change_str")
    public String getChangeStr() {
        return changeStr;
    }

    @JsonProperty("change_str")
    public void setChangeStr(String changeStr) {
        this.changeStr = changeStr;
    }

    @JsonProperty("price_histories")
    public double[] getPriceHistories() {
        return priceHistories;
    }

    @JsonProperty("price_histories")
    public void setPriceHistories(double[] priceHistories) {
        this.priceHistories = priceHistories;
    }


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
    public void setPrice(double price) {
        this.price = price;
    }
}
