package fr.diginamic.spring_security.controller;


import fr.diginamic.spring_security.entity.UserApp;
import fr.diginamic.spring_security.repository.UserAppRepository;
import fr.diginamic.spring_security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user-app")
public class UserAppController {

    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping()
    @ResponseBody
    public String userApp() throws Exception {
        return userAppRepository.findAll().toString();
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserApp userApp) throws Exception {
        customUserDetailsService.createUser(
                userApp.getEmail(),
                userApp.getPassword()
        );
        return "redirect:/login";
    }
}
