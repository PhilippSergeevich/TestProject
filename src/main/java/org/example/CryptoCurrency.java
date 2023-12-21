package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class CryptoCurrency {
    private String name;
    private String code;
    private String state;
    private String ticker;
    private String price;
    private double change;

    private double[] weeklyPriceHistory;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("ticker")
    public String getTicker() {
        return ticker;
    }

    @JsonProperty("ticker")
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("change")
    public double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(double change) {
        this.change = change;
    }

    @JsonProperty("weekly_price_history")
    public double[] getWeeklyPriceHistory() {
        return weeklyPriceHistory;
    }

    @JsonProperty("weekly_price_history")
    public void setWeeklyPriceHistory(double[] weeklyPriceHistory) {
        this.weeklyPriceHistory = weeklyPriceHistory;
    }


    @Override
    public String toString() {
        return "CryptoCurrency{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", state='" + state + '\'' +
                ", ticker='" + ticker + '\'' +
                ", price='" + price + '\'' +
                ", change=" + change +
                ", weeklyPriceHistory=" + Arrays.toString(weeklyPriceHistory) +
                '}';
    }
}
