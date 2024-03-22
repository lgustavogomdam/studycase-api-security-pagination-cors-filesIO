package com.netlify.lggdev.crudsistemamedico.model.security.permission;

import com.netlify.lggdev.crudsistemamedico.enums.TypePermission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Permission implements GrantedAuthority,Serializable {
    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_permission", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePermission typePermission;


    @Override
    public String getAuthority() {
        return this.typePermission.toString();
    }
}


