package ru.example4.spring.services;

import ru.example4.spring.enums.ApplicationTypes;

public class ValidatorApplication implements ValidateInterface {
    private final String original;
    private final String value;

    public ValidatorApplication(String original) {
        this.original = original;
        this.value = getValidApplicationType(this.original);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean validate() {
        return getValidApplicationType(this.original).equals(this.value);
    }

    private String getValidApplicationType(String original) {
        String value;
        if (original.toLowerCase().trim().equals(ApplicationTypes.WEB.toString())) {
            return ApplicationTypes.WEB.toString();
        }
        if (original.toLowerCase().trim().equals(ApplicationTypes.MOBILE.toString())) {
            return ApplicationTypes.MOBILE.toString();
        }
        value = ApplicationTypes.OTHER.toString() + " " + original;

        return value;
    }
}
