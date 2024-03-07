package ru.example4.spring.services;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.example4.spring.annotation.LogTransformation;
import ru.example4.spring.data.FileDataCsv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Component
public class FileDataCsvService {
    private List<FileDataCsv> listData;
    @Value("${upload.path}")
    private String path;
    @Value("${scaner.delim}")
    private char delim;

    @Value("${scaner.format.date}")
    private String formatDate;

    @Autowired
    private DataSave dataSave;

    private static final Logger logger = Logger.getLogger(FileDataCsvService.class);
    //нам интересны только файлы с расширением .csv
    public ArrayList<File> getFileAll(String pathCsv) {
        File folder = new File(pathCsv);
        String name;
        String[] pr;
        File[] allFiles = folder.listFiles();
        ArrayList<File> filesList = new ArrayList<>();

        if (allFiles != null) {
            for (File file : allFiles) {
                name = file.toString();
                pr = name.split("\\.");
                if (pr[pr.length - 1].equals("csv")) {
                    filesList.add(file);
                }
            }
        } else {
            logger.debug("Файлы в каталоге'" + pathCsv + "' не найдены");
            return null;
        }

        return filesList;
    }

    @LogTransformation
    @Bean
    public void parseFile() {
        logger.debug(" init parse...");

        ArrayList<File> list = getFileAll(path);
        List<FileDataCsv> csvObjectAll = new ArrayList<>();

        if (list.isEmpty()) {
            logger.debug("    каталог " + path + " не содержит файлы имеющие расширение .csv");
            this.listData = csvObjectAll;
        }

        logger.debug("    каталог " + path + " содержит " + list.size() + " файла  имеющих расширение .csv");

        for (File file : list) {
            String nameFile = file.toString();
            logger.debug("    parsing file " + nameFile);

            try (CSVReader reader =
                         new CSVReaderBuilder(new FileReader(nameFile)).
                                 withSkipLines(1) //считываем залоговки
                                 .withCSVParser(new CSVParserBuilder().withSeparator(delim).build())
                                 .build()) {

                csvObjectAll.addAll(getDataAllRow(reader, nameFile));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.listData = csvObjectAll;
    }

    @LogTransformation
    @Bean
    public void getValidRows() {
        logger.debug(" getValidRows... size = " + this.listData.size());
        if (!this.listData.isEmpty()) {
            Iterator<FileDataCsv> iterator = this.listData.iterator();
            while (iterator.hasNext()) {
                FileDataCsv e = iterator.next();
                if (e.getValidDate() == null) {
                    logger.warn("Файл " + e.getFileName() + ", пользователь " + e.getFullName() + ", значение даты : " + e.getAccess_date());
                    iterator.remove();
                }
            }
        }
    }

    @LogTransformation
    @Bean
    public void getSaveValidData() {
        logger.debug(" getSaveValidData... size = " + this.listData.size());
        if (!this.listData.isEmpty()) {
            dataSave.save(this.listData);
        }
    }

    @LogTransformation
    public List<FileDataCsv> getDataAllRow(CSVReader reader, String fileName) throws IOException {
        return reader.readAll().stream().map(data -> {

            FileDataCsv csvObject = new FileDataCsv();
            csvObject.setUserName(data[0]);
            csvObject.setLastName(new ValidatorFio(data[1]).getValue());
            csvObject.setFirstName(new ValidatorFio(data[2]).getValue());
            csvObject.setSurName(new ValidatorFio(data[3]).getValue());
            csvObject.setValidDate(new ValidatorDate(data[4], formatDate).getValue());
            csvObject.setAccess_date(data[4]);
            csvObject.setFileName(fileName);
            csvObject.setApplication(new ValidatorApplication(data[5]).getValue());

            return csvObject;
        }).toList();
    }
}
