package ru.example4.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example4.spring.model.Logins;
import ru.example4.spring.repo.LoginsRepo;

@Service
public class LoginsService {
    private final LoginsRepo loginsRepo;

    @Autowired
    public LoginsService(LoginsRepo loginRepo) {
        this.loginsRepo = loginRepo;
    }

    public Iterable<Logins> findAll() {
        return loginsRepo.findAll();
    }

    public void save(Logins logins) {
        loginsRepo.save(logins);
    }
}
