package com.guilhermeonizio.AgendamentoConsultas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desabilita CSRF (necessário para o H2 Console)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // Permite acesso ao H2 Console
                        .requestMatchers("/consultas/**").authenticated() // Exige autenticação para /consultas
                        .requestMatchers("/medicos/**").authenticated()   // Exige autenticação para /medicos
                        .requestMatchers("/pacientes/**").authenticated() // Exige autenticação para /pacientes
                        .anyRequest().permitAll() // Permite acesso sem autenticação a outros endpoints
                )
                .headers().frameOptions().disable() // Permite o uso de iframes (necessário para o H2 Console)
                .and()
                .httpBasic(); // Usa autenticação básica (usuário e senha)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Cria usuários em memória (para fins de demonstração)
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usa BCrypt para codificar senhas
    }
}
