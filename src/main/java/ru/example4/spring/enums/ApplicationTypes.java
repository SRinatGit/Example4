package ru.example4.spring.enums;

public enum ApplicationTypes {
    WEB("web"),
    MOBILE("mobile"),
    OTHER("other");

    private final String name;

    ApplicationTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
