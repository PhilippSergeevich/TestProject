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

//        BankirosHtmlParser bankirosHtmlParser = new BankirosHtmlParser();
//        bankirosHtmlParser.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\util\\BankirosCurrency.csv"));
//        GoogleHtmlParser googleHtmlParser = new GoogleHtmlParser();
//        googleHtmlParser.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\util\\GoogleCurrency.csv"));

        Runnable r1 = () -> {
            BankirosHtmlParser bankirosHtmlParser1 = new BankirosHtmlParser();
            bankirosHtmlParser1.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\util\\BankirosCurrency.csv"));
        };
        Runnable r2 = () -> {
            BitflyerParser bitflyerParser = new BitflyerParser();
            bitflyerParser.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\CryptoCurrencyBitflyer.csv"));
        };

        Runnable r3 = () -> {
            KriptomatParser kriptomatParser = new KriptomatParser();
            kriptomatParser.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\CryptoCurrency.csv"));
        };
        Runnable r4 = () -> {
            GoogleHtmlParser googleHtmlParser = new GoogleHtmlParser();
            googleHtmlParser.runParse(10, Path.of("C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\main\\java\\org\\example\\util\\GoogleCurrency.csv"));
        };

        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
        new Thread(r4).start();

    }

}


