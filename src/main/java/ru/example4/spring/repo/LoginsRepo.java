package ru.example4.spring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.example4.spring.model.Logins;

public interface LoginsRepo extends CrudRepository<Logins, Long> {
}
