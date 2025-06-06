package fr.diginamic.spring_security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/private")
    public String privatePage() throws Exception{
        return "private";
    }

    @GetMapping("/public")
    public String publicPage() throws Exception{
        return "public";
    }

    @GetMapping("/auth")
    @ResponseBody
    public String authPage(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return "Bonzoir " + username;
    }

}
