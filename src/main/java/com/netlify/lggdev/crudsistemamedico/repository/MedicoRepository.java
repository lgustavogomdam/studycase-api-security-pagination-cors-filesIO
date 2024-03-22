package com.netlify.lggdev.crudsistemamedico.repository;

import com.netlify.lggdev.crudsistemamedico.model.medico.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends GenericRepository<MedicoEntity,Long> {

    //%AND%
    //Para quando precisar fazer uma consulta buscando pelo nome TODO: definir no controller tambem um novo endpoint
    @Query("SELECT m FROM Medico m WHERE m.nome LIKE LOWER(CONCAT ('%',:nome,'%'))")
    Page<MedicoEntity> findMedicoEntitiesByName(@Param("nome") String nome, Pageable pageable);
}
