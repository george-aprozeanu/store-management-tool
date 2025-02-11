package com.aprozeanu.smt.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringToListConverterTest {

    private final StringToListConverter converter = new StringToListConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        List<String> list = Arrays.asList("one", "two", "three");
        String expected = "one two three";
        String result = converter.convertToDatabaseColumn(list);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertToDatabaseColumn_Null() {
        String result = converter.convertToDatabaseColumn(null);
        assertEquals("", result);
    }

    @Test
    public void testConvertToEntityAttribute() {
        String joined = "one two three";
        List<String> expected = Arrays.asList("one", "two", "three");
        List<String> result = converter.convertToEntityAttribute(joined);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertToEntityAttribute_Null() {
        List<String> result = converter.convertToEntityAttribute(null);
        assertTrue(result.isEmpty());
    }
}
