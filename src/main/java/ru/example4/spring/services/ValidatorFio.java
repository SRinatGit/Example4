package ru.example4.spring.services;

public class ValidatorFio implements ValidateInterface {
    private final String original;
    private final String value;

    public ValidatorFio(String original) {
        this.original = original;
        this.value = getValidName(this.original);
    }
    public String getValue() {
        return value;
    }

    @Override
    public boolean validate() {
        if (!getValidName(this.original).equals(this.value)) {
            return false;
        }
        return true;
    }

    private String getValidName(String original) {
        return original.trim().toLowerCase().substring(0, 1).toUpperCase() +
                original.trim().toLowerCase().substring(1);
    }
}
