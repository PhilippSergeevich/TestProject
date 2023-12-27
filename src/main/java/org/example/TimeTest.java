package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {

    @Test
    public void testFormatData() {
        String expected = "2023-12-27 15:30:00";
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 27, 15, 30);
        String actualTime = Time.formatData(dateTime);
        assertEquals(expected, actualTime);
    }

    @Test
    public void testFormatDataWithNull() {
        String expected = null;
        String actual = Time.formatData(null);
        assertEquals(expected, actual);
    }
}

