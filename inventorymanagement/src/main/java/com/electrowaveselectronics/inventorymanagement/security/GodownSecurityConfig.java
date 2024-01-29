package com.electrowaveselectronics.inventorymanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class GodownSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.builder()
                .username("user")
                .password(("{noop}test123"))
                .roles("EMPLOYEE")
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password(("{noop}test123"))
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(("{noop}test123"))
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, manager, admin);
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http.authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers(HttpMethod.GET, "/api/getAllGodown").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/getGodown/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, "/api/setGodown").hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/deleteGodown/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/getCapacity/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                            .requestMatchers(HttpMethod.PATCH, "/api/updateGodown/**").hasAnyRole("ADMIN", "MANAGER")
                            .requestMatchers(HttpMethod.GET, "/api/listProducts/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                            .requestMatchers(HttpMethod.PATCH, "/api/addProduct/**").hasAnyRole("ADMIN", "MANAGER")
                            .requestMatchers(HttpMethod.PATCH, "/api/setProduct/**").hasAnyRole("ADMIN", "MANAGER")
            );

            // Use HTTP Basic authentication
            http.httpBasic(Customizer.withDefaults());

            // disable Cross Site Request Forgery (CSRF)
            http.csrf(csrf -> csrf.disable());
            return http.build();
        }


}
