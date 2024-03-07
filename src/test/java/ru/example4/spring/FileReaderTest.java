package ru.example4.spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.example4.spring.services.DataSave;
import ru.example4.spring.services.FileDataCsvService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTest {
    File testFile;
    FileDataCsvService fileDataCsvService;

    @BeforeEach
    public void createCsv() throws Exception {
        String path = "./";
        testFile = new File(path + "testData.csv");

        fileDataCsvService = new FileDataCsvService();

        fileDataCsvService.setPath(path);
        fileDataCsvService.setDelim(';');
        fileDataCsvService.setFormatDate("yyyy-MM-dd");
        fileDataCsvService.setDataSave(new DataSave());

        try (FileWriter fileWriter = new FileWriter(testFile)) {
            fileWriter.write("username;lastName;firstName;surName;access_date;application"
                    + System.lineSeparator()
                    + "user777;Ивановcкий;Иванидзе;Ивановичовский;2024-03-01;mobile");
        } catch (IOException ex) {
            throw new Exception("Data is not written.");
        }
    }

    @AfterEach
    public void deleteCsv() {
        testFile.delete();
    }

    @Test
    @DisplayName("FileDataCsvService test - reader file csv")
    public void readDataPositiveTest() throws Exception {
        fileDataCsvService.parseFile();

        var countRows = fileDataCsvService.getListData().size();

        assertEquals(countRows, 1);
    }
}