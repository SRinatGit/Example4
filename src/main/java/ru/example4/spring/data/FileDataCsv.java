package ru.example4.spring.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileDataCsv {
    private String userName;
    private String firstName;
    private String surName;
    private String lastName;
    private String access_date;
    private String application;
    private Date validDate;
    private String fileName;

    public FileDataCsv() {
    }

    public FileDataCsv(String userName, String firstName, String surName, String lastName, String access_date, String application, Date validDate, String fileName) {
        this.userName = userName;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.access_date = access_date;
        this.application = application;
        this.validDate = validDate;
        this.fileName = fileName;
    }

    public String getFullName() {
        return this.getLastName() + " " + this.getFirstName() + " " + this.getSurName();
    }

    @Override
    public String toString() {
        return "FileDataCsv{" +
                "userName ='" + userName + '\'' +
                ", firstName ='" + firstName + '\'' +
                ", surName ='" + surName + '\'' +
                ", lastName ='" + lastName + '\'' +
                ", access_date ='" + access_date + '\'' +
                ", application ='" + application + '\'' +
                ", validDate =" + validDate +
                '}';
    }
}
