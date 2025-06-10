package fr.diginamic.spring_security.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class CookieController {


    @GetMapping("/get-cookie")
    public ResponseEntity<String> getCookie() {
        String cookieName = "myCookie";
        String cookieValue = "cookieValue123";

        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, cookieValue)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofSeconds(3600))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                .body("Cookie posé avec succès");
    }
}
