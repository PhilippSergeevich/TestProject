package org.example;

import org.example.parser.BitflyerParser;
import org.example.parser.KriptomatParser;
import org.example.parser.Parser;

public class Main {


    public static void main(String[] args) {

        BitflyerParser bitflyerParser = new BitflyerParser();
        bitflyerParser.runParse(10);
        KriptomatParser kriptomatParser = new KriptomatParser();
        kriptomatParser.runParse(10);

    }
}


