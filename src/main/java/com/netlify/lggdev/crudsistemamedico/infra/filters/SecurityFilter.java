package com.netlify.lggdev.crudsistemamedico.infra.filters;

import com.netlify.lggdev.crudsistemamedico.infra.security.JwtTokenProvider;
import com.netlify.lggdev.crudsistemamedico.repository.UserRepository;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.InvalidJwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
* Filtros são uma forma de interceptar requisições, feitos à priori no Java EE, portanto serve para qualquer aplicação java,
* ou seja, bastava implementar a interface Filter, no entanto, usaremos o filtro de forma a aproveitar o Spring e suas
* funcionalidades, fazendo com que nossa classe extenda o 'OncePerRequestFilter' um classe do Spring que implementa o Filter
*/
@Component //Para sinalizar ao Spring que ele precisa gerenciar a classe
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    //DoFilterInternal intercepta todas as requisições 1 vez

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String tokenJWT = getAccessToken(request);


            if (tokenJWT != null) {
                //Valida o token
                var subject = this.jwtTokenProvider.getSubjectAndVerifyToken(tokenJWT);

                //Captura o subject como usuario
                var user = this.userRepository.findByUserName(subject);

                //Se não existir no banco de dados
                if (user == null){
                    throw new InvalidJwtAuthenticationException("Usuário não encontrado no banco de dados!");
                }

                //Captura um authentication
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                //Força uma authorization no Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (InvalidJwtAuthenticationException e) {
            throw new RuntimeException(e);
        }

        //Necessário chamar este metodo para que o fluxo da requisição continue (caso não implemente, a aplicação irá
        //ficar parada aqui).
        filterChain.doFilter(request,response);
    }
    private String getAccessToken(HttpServletRequest request) throws InvalidJwtAuthenticationException {

        //Captura o cabeçalho Authorization que é onde se passa o token
        var authorizationHeader = request.getHeader("Authorization");

        //Verifica se o cabeçalho não é diferente de nulo para poder remover o prefixo
        if (authorizationHeader != null){
            //Remove o prefixo Bearer
            return authorizationHeader.replace("Bearer ", "");
        }

        //Se o cabecalho for nulo
        return null;


    }
}
