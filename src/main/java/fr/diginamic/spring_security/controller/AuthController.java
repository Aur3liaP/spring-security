package fr.diginamic.spring_security.controller;

import fr.diginamic.spring_security.entity.UserApp;
import fr.diginamic.spring_security.repository.UserAppRepository;
import fr.diginamic.spring_security.service.JwtAuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtAuthentificationService jwtAuthentificationService;

    @Autowired
    private UserAppRepository userAppRepository;

    @PostMapping("/register")
    public UserApp registerUser(@RequestBody UserApp user) {
        return userAppRepository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserApp user) {
        if (jwtAuthentificationService.authenticate(user.getUsername(), user.getPassword())) {
            return jwtAuthentificationService.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @GetMapping("/verify-jwt/{jwt}")
    public boolean verifyJwt(@PathVariable String jwt) {
        return jwtAuthentificationService.verifyToken(jwt);
    }

}
