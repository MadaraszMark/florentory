package hu.florentory.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // ha REST-et használsz, különösen POST-hoz
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // minden kérés engedélyezve
        return http.build();
    }
}
