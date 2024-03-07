package ru.example4.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example4.spring.model.Users;
import ru.example4.spring.repo.UsersRepo;

@Service
public class UsersService {
    private final UsersRepo usersRepo;

    @Autowired
    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Iterable<Users> findAll() {
        return usersRepo.findAll();
    }

    public void save(Users user) {
        usersRepo.save(user);
    }

    public Users findByUsername(String username) {
        return usersRepo.findByUsername(username);
    }
}
