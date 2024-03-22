package com.netlify.lggdev.crudsistemamedico.model.security.user.entity;

import com.netlify.lggdev.crudsistemamedico.enums.TypePermission;
import com.netlify.lggdev.crudsistemamedico.model.security.permission.Permission;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails,Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_permission", referencedColumnName = "id")
    )
    private List<Permission> permissions;

    //Necessário mapear as permissões para que o Security consiga capturá-las
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission.getAuthority().equals("ADMIN")) {
                for (TypePermission typePermission: TypePermission.values()) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + typePermission.toString()));
                }
            }else{
                authorities.add(new SimpleGrantedAuthority("ROLE_" + permission.getAuthority()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
