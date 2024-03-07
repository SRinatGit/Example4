package ru.example4.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "logins")
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "access_date")
    Date access_date;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Users user_id;

    @Column(name = "application")
    String application;

    @Override
    public String toString() {
        return "Logins{" +
                "id= " + id +
                ", access_date=" + access_date +
                ", user_id= " + user_id +
                ", application= '" + application + '\'' +
                '}';
    }
}
