package com.example.ticketguru;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


     private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.disable()) 
          .authorizeHttpRequests(auth -> auth
              .requestMatchers(
                "/api/tapahtumat/ADMIN/**", 
                "/api/kayttajat/ADMIN/**",
                "/api/jarjestajat/ADMIN/**",
                "/api/postinumerot/ADMIN/**",
                "/api/myynnit/ADMIN/**",
                "/api/myyntirivit/ADMIN/**",
                "/api/lipputyypit/ADMIN/**"
            ).hasAuthority("ADMIN")
              .requestMatchers("/api/**").authenticated()
          )
          .httpBasic(Customizer.withDefaults()); 
        return http.build();
    }

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}

