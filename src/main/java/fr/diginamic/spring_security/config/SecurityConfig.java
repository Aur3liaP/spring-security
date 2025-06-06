package fr.diginamic.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailService() {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("1234"))
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.httpBasic(Customizer.withDefaults());  // Rend toutes le pages publiques


//        http.httpBasic(Customizer.withDefaults())      // Limite l'accès public aux routes indiquées
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/hello/public").permitAll()
//                        .anyRequest().authenticated()
//                );


//        http.httpBasic(Customizer.withDefaults())       // Désactive protection CSRF
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/hello/public").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf(AbstractHttpConfigurer::disable);

                http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/hello/public", "/login", "/h2-console/**", "/register", "/article/all", "/user-app/register","/css/**").permitAll()  // Ne pas oublier les pages mdp perdu / register /modifs mdp
                        .requestMatchers("/hello/auth", "/article/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/hello/private", true)
                        .failureUrl("/hello/public")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
//                        .permitAll()
                )
                .headers(headers -> headers     // pour pouvoir lancer h2 console
                        .frameOptions(frameOptions -> frameOptions
                        .sameOrigin()
                ));



        return http.build();

    }

}
