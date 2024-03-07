package ru.example4.spring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.example4.spring.model.Users;

public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
