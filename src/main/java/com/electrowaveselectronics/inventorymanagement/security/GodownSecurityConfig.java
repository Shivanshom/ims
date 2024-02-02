//package com.electrowaveselectronics.inventorymanagement.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class GodownSecurityConfig {
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(("{noop}test123"))
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails manager = User.builder()
//                .username("manager")
//                .password(("{noop}test123"))
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(("{noop}test123"))
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, manager, admin);
//    }
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//            http.authorizeHttpRequests(configurer ->
//                    configurer
//                            .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
//                            .requestMatchers(HttpMethod.POST, "/api/logout").permitAll()
//                            .requestMatchers(HttpMethod.GET, "/api/getAllGodown").hasRole("admin")
//                            .requestMatchers(HttpMethod.GET, "/api/getGodown/**").hasAnyRole("admin", "godownhead")
//                            .requestMatchers(HttpMethod.POST, "/api/setGodown").hasRole("admin")
//                            .requestMatchers(HttpMethod.POST, "/api/createGodown").hasAnyRole("admin", "godownhead")
//                            .requestMatchers(HttpMethod.DELETE, "/api/deleteGodown/**").hasRole("admin")
//                            .requestMatchers(HttpMethod.GET, "/api/getCapacity/**").hasAnyRole("admin", "godownhead")
//                            .requestMatchers(HttpMethod.PATCH, "/api/updateGodown/**").hasAnyRole("admin", "godownhead")
//                            .requestMatchers(HttpMethod.GET, "/api/listProducts/**").hasAnyRole("admin", "godownhead")
//                            .requestMatchers(HttpMethod.POST, "/api/addProduct").hasAnyRole("admin", "godownhead")
//                            .requestMatchers(HttpMethod.PATCH, "/api/updateProduct/**").hasAnyRole("admin", "godownhead")
//                            .requestMatchers(HttpMethod.GET, "/api/findGodownsByAddress/**").hasAnyRole("admin", "godownhead")
//            );
//
//            // Use HTTP Basic authentication
//            http.httpBasic(Customizer.withDefaults());
//
//            // disable Cross Site Request Forgery (CSRF)
//            http.csrf(csrf -> csrf.disable());
//            return http.build();
//        }
//
//
//}
