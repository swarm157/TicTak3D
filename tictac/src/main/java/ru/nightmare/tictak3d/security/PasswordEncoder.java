package ru.nightmare.tictak3d.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public StandardPasswordEncoder getPasswordEncoder(){
        return new StandardPasswordEncoder();
    }
}
