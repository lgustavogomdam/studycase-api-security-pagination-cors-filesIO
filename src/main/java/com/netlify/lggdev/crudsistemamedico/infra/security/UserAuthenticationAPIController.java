package com.netlify.lggdev.crudsistemamedico.infra.security;

import com.netlify.lggdev.crudsistemamedico.model.security.token.JwtTokenDTO;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.InvalidJwtAuthenticationException;
import com.netlify.lggdev.crudsistemamedico.model.security.user.dto.RegisterOrAuthenticationUserDTO;
import com.netlify.lggdev.crudsistemamedico.model.security.user.dto.RegisterAuthorityUserDTO;
import com.netlify.lggdev.crudsistemamedico.model.security.user.entity.User;
import com.netlify.lggdev.crudsistemamedico.model.security.user.mapper.RegisterOrAuthenticationUserDTOMapper;
import com.netlify.lggdev.crudsistemamedico.model.security.user.mapper.RegisterAuthorityUserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class UserAuthenticationAPIController {
    private Logger logger = Logger.getLogger(UserAuthenticationService.class.getName());

    @Autowired
    private RegisterAuthorityUserMapper registerAuthorityUserMapper;
    @Autowired
    private RegisterOrAuthenticationUserDTOMapper registerOrAuthenticationUserDTOMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> login(@RequestBody @Valid RegisterOrAuthenticationUserDTO registerOrAuthenticationUserDTO) throws InvalidJwtAuthenticationException {

        //Encriptando usuario para autenticar, já que na autenticação sempre se encripta a senha para comparar
        var encriptLoginPassword = new UsernamePasswordAuthenticationToken(registerOrAuthenticationUserDTO.username(), registerOrAuthenticationUserDTO.password());

        //Realizando a autenticação, o Spring identifica o UserService pela implementação da interface
        var auth = authenticationManager.authenticate(encriptLoginPassword);

        //Gerando o token de acesso passando 'auth' que representa um usuário autenticado
        var jwtTokenAccess = new JwtTokenDTO(this.jwtTokenProvider.generateAccessToken((User) auth.getPrincipal()));

        //Retornando o token como response
        return new ResponseEntity<JwtTokenDTO>(jwtTokenAccess, HttpStatusCode.valueOf(200));

    }

    @PostMapping("/register/authority")
    public ResponseEntity<String> registerAuthority(@RequestBody @Valid RegisterAuthorityUserDTO registerAuthorityUserDTO) throws InvalidJwtAuthenticationException {

        var newAuthorityUser = this.userAuthenticationService.save(registerAuthorityUserMapper.toUser(registerAuthorityUserDTO));
        //Retornando o token como response
        return new ResponseEntity<String>("Usuário criado com sucesso!", HttpStatusCode.valueOf(200));
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterOrAuthenticationUserDTO> register(@RequestBody @Valid RegisterOrAuthenticationUserDTO registerOrAuthenticationUserDTO) throws InvalidJwtAuthenticationException {

        var newRegisterUser = this.userAuthenticationService.save(registerOrAuthenticationUserDTOMapper.toUser(registerOrAuthenticationUserDTO));
        //Retornando o token como response
        return new ResponseEntity<RegisterOrAuthenticationUserDTO>(registerOrAuthenticationUserDTO, HttpStatusCode.valueOf(200));
    }
}
