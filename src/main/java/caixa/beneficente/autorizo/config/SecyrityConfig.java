package caixa.beneficente.autorizo.config;

import java.beans.Customizer;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecyrityConfig {

    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // // return http
    // // .authorizeHttpRequests(
    // // authorizeConfig -> {
    // // // authorizeConfig.requestMatchers("/pesquisar").permitAll();
    // // authorizeConfig.requestMatchers("/buscarCpf").permitAll();
    // // authorizeConfig.anyRequest().authenticated();
    // // })
    // // .build();
    // http.authorizeHttpRequests()
    // .requestMatchers("/login").permitAll()
    // .anyRequest().authenticated();

    // http.formLogin(login -> login
    // .loginPage("/login")
    // .defaultSuccessUrl("/pesquisar")
    // .permitAll());

    // return http.build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login/login")
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
