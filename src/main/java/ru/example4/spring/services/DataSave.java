package ru.example4.spring.services;

import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example4.spring.annotation.LogTransformation;
import ru.example4.spring.data.FileDataCsv;
import ru.example4.spring.model.Logins;
import ru.example4.spring.model.Users;

import java.util.List;

@Setter
@Component
public class DataSave {
    @Autowired
    private UsersService usersService;
    @Autowired
    private LoginsService loginsService;

    private static final Logger logger = Logger.getLogger(DataSave.class);

    @LogTransformation
    public void save(List<FileDataCsv> listData) {
        logger.debug("            DataSave.save");
        for (FileDataCsv row : listData) {
            saveLogins(saveUsers(row), row);
        }
    }

    @LogTransformation
    private Users saveUsers(FileDataCsv row) {
        logger.debug("                DataSave.saveUsers");
        var user = new Users();

        if (usersService.findByUsername(row.getUserName()) == null) {
            user.setUsername(row.getUserName());
            user.setFio(row.getFullName());
            usersService.save(user);
        } else {
            user = usersService.findByUsername(row.getUserName());
        }
        return user;
    }

    @LogTransformation
    private void saveLogins(Users users, FileDataCsv row) {
        logger.debug("                DataSave.saveLogins");
        var login = new Logins();
        login.setApplication(row.getApplication());
        login.setAccess_date(row.getValidDate());
        login.setUser_id(users);
        loginsService.save(login);
    }
}
