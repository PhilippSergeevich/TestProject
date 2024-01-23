package org.example;

import org.example.util.FileWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileWriterTest {
    private FileWriter fileWriter;
    private final String testFilePath = "testFile.txt";


    @BeforeEach
    public void setUp() {
        fileWriter = new FileWriter(testFilePath);
    }

    @Test
    public void testFileCreation() {
        assertTrue(Files.exists(Paths.get(testFilePath)));
    }

    @Test
    public void testCreateFileWhichNotExist() {
        String path = "C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\test\\java\\org\\example\\TestFile.csv";
        Path pathToFile = Path.of(path);
        if (Files.exists(pathToFile)) {
            try {
                Files.delete(pathToFile);
                System.out.println("Файл уже был создан со страрыми значениями - удален");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FileWriter fileWriter2 = new FileWriter(path);
        assertTrue(Files.exists(pathToFile));
        System.out.println("Файл создан");
    }

    @Test
    public void testAppendNewLine() {
        String path = "C:\\Users\\citru\\IdeaProjects\\TestProject\\src\\test\\java\\org\\example\\TestFile2.csv";
        Path pathToFilwe = Path.of(path);
        String checkStr = "4444,1515,1512";
        FileWriter fileWriter2 = new FileWriter(pathToFilwe, "BTC,ETH,STH");
        assertTrue(Files.exists(Path.of(path)));
        fileWriter2.appendNewLine(checkStr);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String str = lines.get(lines.size() - 1);
        assertTrue(str.equals(checkStr));
    }
}
