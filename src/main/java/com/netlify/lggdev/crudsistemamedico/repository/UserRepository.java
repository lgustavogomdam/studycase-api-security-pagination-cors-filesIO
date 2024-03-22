package com.netlify.lggdev.crudsistemamedico.repository;

import com.netlify.lggdev.crudsistemamedico.model.security.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User,Long>{

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    User findByUserName(@Param("userName") String userName);
}
