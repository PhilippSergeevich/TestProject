package org.example.parser;

import org.example.currency.CurrencyDTO;

import java.nio.file.Path;

public interface Parser {

    void runParse(int period, Path path);
}
