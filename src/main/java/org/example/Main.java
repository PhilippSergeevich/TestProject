package org.example;

import org.example.parser.BankirosHtmlParser;
import org.example.parser.BitflyerParser;
import org.example.parser.GoogleHtmlParser;
import org.example.parser.KriptomatParser;

import java.nio.file.Path;

public class Main {


    public static void main(String[] args) {

//        BitflyerParser bitflyerParser = new BitflyerParser();
//        bitflyerParser.runParse(10,Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\CryptoCurrencyBitflyer.csv"));
//        KriptomatParser kriptomatParser = new KriptomatParser();
//        kriptomatParser.runParse(10,Path.of("C:\\\\Users\\\\citru\\\\IdeaProjects\\\\TestProject\\\\src\\\\main\\\\java\\\\org\\\\example\\\\CryptoCurrency.csv\""));

        BankirosHtmlParser bankirosHtmlParser = new BankirosHtmlParser();
        bankirosHtmlParser.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\util\\BankirosCurrency.csv"));
//        GoogleHtmlParser googleHtmlParser = new GoogleHtmlParser();
//        googleHtmlParser.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\util\\GoogleCurrency.csv"));

    }
}


