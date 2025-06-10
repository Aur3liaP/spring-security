package fr.diginamic.spring_security.entity;

import lombok.Data;

@Data
public class UserApp {
    private String username;
    private String password;

    public UserApp() {
    }

    public UserApp(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

