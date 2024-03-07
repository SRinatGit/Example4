package ru.example4.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.example4.spring.data.FileDataCsv;
import ru.example4.spring.model.Logins;
import ru.example4.spring.model.Users;
import ru.example4.spring.repo.LoginsRepo;
import ru.example4.spring.repo.UsersRepo;
import ru.example4.spring.services.DataSave;
import ru.example4.spring.services.LoginsService;
import ru.example4.spring.services.UsersService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DataSaveTest {
    @Mock
    UsersRepo usersRepo;
    @Mock
    LoginsRepo loginRepo;
    Users actualUser;
    Logins actualLogin;
    String format;
    FileDataCsv fileDataCsv;
    String userName;
    String lastName;
    String firstName;
    String surName;
    Date validDate;
    String fileName;
    String application;
    String access_date;

    UsersService usersService;
    LoginsService loginsService;
    DataSave dataSave = new DataSave();
    List<FileDataCsv> listData = new ArrayList<>();

    @BeforeEach
    void initVariables() {
        fileName = "in/testingData.csv";
        format = "yyyy-MM-dd";
        userName = "ivanovii";
        lastName = "Иванов";
        firstName = "Иван";
        surName = "Иванович";
        application = "WEB";
        access_date = "2024-02-13";
        try {
            var formatter = new SimpleDateFormat(format, Locale.ENGLISH);
            validDate = formatter.parse(access_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        fileDataCsv = new FileDataCsv(
                userName,
                firstName,
                surName,
                lastName,
                access_date,
                application,
                validDate,
                fileName);

        actualUser = new Users();
        actualUser.setId(1);
        actualUser.setUsername(userName);
        actualUser.setFio(lastName + " " + firstName + " " + surName);

        actualLogin = new Logins();
        actualLogin.setId(1);
        actualLogin.setUser_id(actualUser);
        actualLogin.setApplication("TERMINAL");
        actualLogin.setAccess_date(validDate);

        usersService = new UsersService(usersRepo);
        loginsService = new LoginsService(loginRepo);

        listData.add(fileDataCsv);
        dataSave.setUsersService(usersService);
        dataSave.setLoginsService(loginsService);
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("DataSave test - valid save data Users")
    void processUserTest() {
        when(usersRepo.save(any(Users.class))).thenReturn(actualUser);

        dataSave.save(listData);

        assertEquals(actualUser.getId(), 1);

        assertEquals(actualUser.getUsername(), userName);

        assertEquals(actualUser.getFio(), lastName + " " + firstName + " " + surName);
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("DataSave test - valid save data Logins")
    void processLoginTest() {
        when(loginRepo.save(any(Logins.class))).thenReturn(actualLogin);

        dataSave.save(listData);

        assertEquals(actualLogin.getId(), 1);

        assertEquals(actualLogin.getApplication(), "TERMINAL");

        assertEquals(actualLogin.getAccess_date(), validDate);
    }


}