package com.netlify.lggdev.crudsistemamedico.infra.security;

import com.netlify.lggdev.crudsistemamedico.enums.TypePermission;
import com.netlify.lggdev.crudsistemamedico.infra.exceptions.my_exceptions.ElementAlreadyRegisteredException;
import com.netlify.lggdev.crudsistemamedico.model.security.permission.Permission;
import com.netlify.lggdev.crudsistemamedico.model.security.user.entity.User;
import com.netlify.lggdev.crudsistemamedico.repository.PermissionRepository;
import com.netlify.lggdev.crudsistemamedico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserAuthenticationService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user){
        saveValidation(user);
        prepareToSave(user);
        return this.repository.save(user);
    }

    private void prepareToSave(User user) {
        if (user.getPermissions() == null) {
            user.setPermissions(new ArrayList<Permission>(
                    Arrays.asList(this.permissionRepository.findByTypePermission(TypePermission.USER))));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
    }

    private void saveValidation(User user) {

        var userRepository = this.repository.findByUserName(user.getUsername());
        if (userRepository != null)
            throw new ElementAlreadyRegisteredException("Username já cadastrado no banco de dados!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.repository.findByUserName(username);
        if (user != null)
            return user;
        else
            throw new UsernameNotFoundException("Usuário não encontrado!");
    }
}
