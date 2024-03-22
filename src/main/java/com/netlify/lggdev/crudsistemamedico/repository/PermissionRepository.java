package com.netlify.lggdev.crudsistemamedico.repository;

import com.netlify.lggdev.crudsistemamedico.enums.TypePermission;
import com.netlify.lggdev.crudsistemamedico.model.security.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    @Query("SELECT p FROM Permission p where p.typePermission =:typePermission")
    Permission findByTypePermission(@Param("typePermission") TypePermission typePermission);
}
