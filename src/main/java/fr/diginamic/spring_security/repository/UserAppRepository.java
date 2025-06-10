package fr.diginamic.spring_security.repository;

import fr.diginamic.spring_security.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    UserApp findByUsernameAndPassword(String username, String password);
}
