package br.com.challenge.euroIntegrate.autenticacao.security;

import br.com.challenge.euroIntegrate.autenticacao.filter.UsuarioFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UsuarioFilter usuarioFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/users/login", // Url que usaremos para fazer login
            "/h2-console/**",
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/users/test"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de cliente
    public static final String [] ENDPOINTS_CUSTOMER = {
            "/users/test/customer",

    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String [] ENDPOINTS_ADMIN = {
            "/users/test/administrator",
            "colaboradores/home",
            "colaboradores/perfil",
            "colaboradores/avatar"
    };
    // SEM O H2 PODE SER ESSE TALVEZ...
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(req -> {
//                            req.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll();
//                            req.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated();
//                            req.requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR"); // Repare que não é necessário colocar "ROLE" antes do nome, como fizemos na definição das roles
//                            req.requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER");
//                            req.anyRequest().denyAll();
//                }).addFilterBefore(usuarioFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

// PARA USO DO H2 TEM QUE SER ESSE CÓDIGO ABAIXO
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(req -> {
                    req.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll();
                    req.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated();
                    req.requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR");
                    req.requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER");
                    req.anyRequest().denyAll();
                })
                .headers(headers -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)) // Configuração para permitir o uso do console H2 em um iframe
                )
                .addFilterBefore(usuarioFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}