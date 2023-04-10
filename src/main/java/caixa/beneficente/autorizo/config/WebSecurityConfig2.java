package caixa.beneficente.autorizo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig2 {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.authorizeHttpRequests()
        // .requestMatchers("/login/login").permitAll()
        // .requestMatchers("/css/**").permitAll()
        // .requestMatchers("/img/**").permitAll()
        // .requestMatchers("/js/**").permitAll()
        // .anyRequest().authenticated();
        // return http.build();

        // http.authorizeHttpRequests()
        // .anyRequest().permitAll();
        // return http.build();

        // return http
        // .authorizeHttpRequests(
        // authorizeConfig -> {
        // authorizeConfig.requestMatchers("/logout").permitAll();
        // authorizeConfig.requestMatchers("compras/{id}").hasRole("ADMIN");
        // authorizeConfig.requestMatchers("/logout").permitAll();
        // authorizeConfig.anyRequest().authenticated();
        // })
        // .formLogin(Customizer.withDefaults())
        // .build();

        // return http
        // .authorizeHttpRequests(
        // authorizeConfig -> {
        // authorizeConfig.requestMatchers("/logout").permitAll();
        // authorizeConfig.requestMatchers("/login").permitAll();
        // authorizeConfig.requestMatchers("compras/{id}").hasRole("ADMIN");
        // authorizeConfig.anyRequest().authenticated();
        // })
        // .formLogin((form) -> form
        // .loginPage("/login").permitAll()
        // .defaultSuccessUrl("/pesquisar"))
        // .logout((logout) -> logout.permitAll())
        // .build();

        http.authorizeHttpRequests(
                authorizeConfig -> {
                    authorizeConfig.requestMatchers("/logout").permitAll();
                    authorizeConfig.requestMatchers("/login").permitAll();
                    authorizeConfig.requestMatchers("compras/{id}").hasRole("ADMIN");
                    authorizeConfig.anyRequest().authenticated();
                });

        http.formLogin((form) -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/pesquisar"));

        http.logout((logout) -> logout.permitAll());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
