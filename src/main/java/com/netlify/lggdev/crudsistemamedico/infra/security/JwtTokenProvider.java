package com.netlify.lggdev.crudsistemamedico.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.netlify.lggdev.crudsistemamedico.model.security.user.entity.User;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.InvalidJwtAuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Logger;

@Service
public class JwtTokenProvider {
    private Logger logger = Logger.getLogger(JwtTokenProvider.class.getName());

    //Lendo a secret-key do application.yml
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    public String generateAccessToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey); //Definindo a secret para o algorithm encriptografar
            return JWT.create()
                    .withIssuer("sistema-medico-api-security") //Definindo o sistema que gerou o Token
                    .withSubject(user.getUsername()) //Definindo o dono do Token
                    .withClaim("Full Name", user.getFullName()) //Definindo uma Claim para passar no Token como informção
                    .withExpiresAt(this.getExpireTime()) //Definindo tempo de expiração do Token
                    .sign(algorithm); //Finalizando o Token
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String getSubjectAndVerifyToken(String jwtToken) throws InvalidJwtAuthenticationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey); //Definindo a secret para o algorithm desencriptografar
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("sistema-medico-api-security")
                    // reusable verifier instance
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new InvalidJwtAuthenticationException("JWT Token inválido ou expirado!");
        }
    }

    private Instant getExpireTime(){
        //Definindo o tempo de expiração do token em 90 minutos passando a zona do fuso horário do Brasil
        return LocalDateTime.now().plusMinutes(90).toInstant(ZoneOffset.of("-03:00"));
    }


}
