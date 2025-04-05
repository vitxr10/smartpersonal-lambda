package br.com.vitxr.smartpersonal.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convert(LocalDate object) {
        return object != null ? object.format(formatter) : null;
    }

    @Override
    public LocalDate unconvert(String object) {
        return object != null ? LocalDate.parse(object, formatter) : null;
    }
}
