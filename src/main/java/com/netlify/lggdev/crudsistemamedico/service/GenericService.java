package com.netlify.lggdev.crudsistemamedico.service;

import com.netlify.lggdev.crudsistemamedico.model.generic.GenericMapper;
import com.netlify.lggdev.crudsistemamedico.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class GenericService <K,E,M,MP extends GenericMapper<E,M>, R extends GenericRepository<E,K>>{
    
    R repository;
    MP mapper;

    @Autowired
    public GenericService(R repository, MP mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    protected abstract void saveValidation(E entity);

    public Page<List<M>> findAll(Pageable pageable){

        Page entityPage = this.repository.findAll(pageable);

        var modelPage = entityPage.map(p -> this.mapper.toModel((E)p));
        
        return modelPage;
    }

    public M findById(K id){
        
        var model = this.mapper.toModel(repository.findById(id).orElse(null));
        
        return model;
    }

    public M create(M modelSubmited){

        var newEntity = this.mapper.toEntity(modelSubmited);
        saveValidation(newEntity);
        var createdModel = this.mapper.toModel(repository.save(newEntity));

        return createdModel;
    }

    public M update(M oldModel){
        
        var entity = this.mapper.toEntity(oldModel);
        saveValidation(entity);
        var updatedModel = this.mapper.toModel(repository.saveAndFlush(entity));
        
        return updatedModel;
    }

    public boolean delete(K id){

        E entity = repository.findById(id).orElse(null);
        if (entity == null)
            return false;
        repository.delete(entity);
        return true;
    }
}
