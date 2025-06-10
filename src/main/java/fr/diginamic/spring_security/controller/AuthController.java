package fr.diginamic.spring_security.controller;

import fr.diginamic.spring_security.service.JwtAuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtAuthentificationService jwtAuthentificationService;

    @GetMapping("/create-jwt")
    public String createJwt() {
        return jwtAuthentificationService.generateToken();
    }

    @GetMapping("/get-jwt")
    public String getJwt() {
        return "Voici le JWT : " + jwtAuthentificationService.generateToken();
    }

}
