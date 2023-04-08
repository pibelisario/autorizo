// package caixa.beneficente.autorizo.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// // @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class WebSecurityConfig {

//     @Autowired
//     UserDetailsServiceImpl userDetailsServiceImpl;

//     protected void configure(HttpSecurity http) throws Exception {
//         http
//                 .httpBasic()
//                 .and()
//                 .authorizeHttpRequests()
//                 .anyRequest().authenticated();
//     }

//     protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//         auth.userDetailsService(userDetailsServiceImpl)
//             .passwordEncoder(passwordEncoder());
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }

// }
