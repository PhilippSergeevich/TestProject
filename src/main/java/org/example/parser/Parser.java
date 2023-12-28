package org.example.parser;

import org.example.currency.CurrencyDTO;
import org.example.currency.CurrencyName;
import org.example.currency.KriptomatCurrency;

public interface Parser {

    CurrencyDTO parseJsonString(String strJson, String currencyName);

    void runParse(int period);

     String getApiUrl ();

}
