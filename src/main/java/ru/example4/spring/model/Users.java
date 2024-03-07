package ru.example4.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "username")
    String username;

    @Column(name = "fio")
    String fio;

    @Override
    public String toString() {
        return "Users{" +
                "id= " + id +
                ", login= '" + username + '\'' +
                ", fio= '" + fio + '\'' +
                '}';
    }
}
