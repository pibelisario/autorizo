// package caixa.beneficente.autorizo.config;

// import java.beans.Customizer;

// import org.apache.catalina.User;
// import
// org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecyrityConfig {

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .authorizeHttpRequests((requests) -> requests
// .requestMatchers("/").permitAll()
// .anyRequest().authenticated())
// .formLogin((form) -> form
// .loginPage("/login")
// .defaultSuccessUrl("/index")
// .permitAll())
// .logout((logout) -> logout.permitAll());

// return http.build();
// }

// }
