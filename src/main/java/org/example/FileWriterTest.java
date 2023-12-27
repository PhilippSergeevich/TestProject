package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

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
}
