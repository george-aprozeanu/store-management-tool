package com.aprozeanu.smt.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringToListConverter implements AttributeConverter<List<String>, String> {

    static String separator = " ";

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        if (list == null) return "";
        return String.join(separator, list);
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        if (joined == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(joined.split(separator)));
    }
}
