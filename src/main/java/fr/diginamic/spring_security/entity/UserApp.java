package fr.diginamic.spring_security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    public UserApp() {
    }

    public UserApp(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

