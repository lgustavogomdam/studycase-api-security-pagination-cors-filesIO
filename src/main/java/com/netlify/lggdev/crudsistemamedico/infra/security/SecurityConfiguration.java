package com.netlify.lggdev.crudsistemamedico.infra.security;

import com.netlify.lggdev.crudsistemamedico.enums.TypePermission;
import com.netlify.lggdev.crudsistemamedico.infra.filters.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    //Define uma cadeia de filtros de segurança para configurar os acessos e autorizações
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf -> csrf.disable()) //Desabilita o csrf pois é redundante
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Define a sessão como Stateless para NÃO salvar o estado de autorizações e autenticações na API
                .authorizeHttpRequests(authorize -> authorize //Define as autorizações para requisições
                        .requestMatchers(HttpMethod.POST, "/api/login", "/api/register").permitAll() //Permite todos no endpoint de login
                        .requestMatchers("/swagger-ui.html","/swagger-ui/**", "/v3/api-docs/**").permitAll() //Permite todos no endpoint de login
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole(TypePermission.ADMIN.toString()) //Permite somente ADMIN enviar requisições POST e DELETE
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(TypePermission.ADMIN.toString()) //Permite USERs e ADMINs para todas requisições GET
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole(TypePermission.USER.toString()) //Permite USERs e ADMINs para todas requisições GET
                        .requestMatchers(HttpMethod.GET, "/api/**").hasRole(TypePermission.USER.toString()) //Permite USERs e ADMINs para todas requisições GET
                        .anyRequest().authenticated() //Todas as requisições precisam de autenticação
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //Chamo o meu filtro antes do filtro do Spring para que não sobreponha minhas validações
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

}
