package ru.example4.spring.services;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidatorDate implements ValidateInterface {
    private final String original;
    private final Date value;
    private final String format;

    private static final Logger logger = Logger.getLogger(ValidatorDate.class);

    public ValidatorDate(String original, String format) {
        this.original = original;
        this.format = format;
        this.value = getDateFromString();
    }

    public Date getValue() {
        return value;
    }

    @Override
    public boolean validate() {
        return original != null && !original.isEmpty();
    }

    private Date getDateFromString() {
        if (this.original.isEmpty() || this.format.isEmpty()) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(this.format);
        try {
            return formatter.parse(this.original.trim());
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}