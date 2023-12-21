package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileWriter {


    private Path path;

    public FileWriter(String pathToFile) {
        this(Path.of(pathToFile));
    }

    public FileWriter(Path pathToFile) {
        this.path = pathToFile;
        createFileIfNotExist(pathToFile);
    }

    private void createFileIfNotExist(Path pathToFile) {
        if (!Files.exists(pathToFile)) {
            try {
                Files.createFile(path);
                String headerTable = "Data-Time,eth,btc,usdt,xrp,ltc" + "\n";
                Files.writeString(path, headerTable, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new IllegalArgumentException("Ошибка чтения пути к файлу");
            }
        }
    }

    public void appendNewLine(String currentPriceWithData) {
        try {
            Files.writeString(path, currentPriceWithData + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

