package org.example.parser;

import org.example.currency.CurrencyDTO;

public interface ParserJson extends Parser {
    CurrencyDTO parseJsonString(String strJson, String currencyName);

    String getApiUrl ();
}
