package com.netlify.lggdev.crudsistemamedico.controller;

import com.netlify.lggdev.crudsistemamedico.model.generic.GenericMapper;
import com.netlify.lggdev.crudsistemamedico.repository.GenericRepository;
import com.netlify.lggdev.crudsistemamedico.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class GenericAPIController< K,
                                            E,
                                            M,
                                            MP extends GenericMapper<E,M>,
                                            R extends GenericRepository<E,K>,
                                            S extends GenericService<K,E,M,MP,R>>{
    private S service;

    @Autowired
    public GenericAPIController(S service) {
        this.service = service;
    }

    @GetMapping()
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Page<List<M>>> list(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "12") Integer limit,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ){

        //Definindo o tipo de ordenação e chamando o metodo de ordenação pelas propriedades definidas
        var sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        var pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

        return new ResponseEntity<Page<List<M>>>(this.service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<M> findById(@PathVariable K id){
        try{
            M model = service.findById(id);
            return new ResponseEntity<M>(model, HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Elemento não encontrado!");
        }
    }

    @PostMapping()
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<M> create(@RequestBody @Valid M model){

        var createdModel = service.create(model);

        return new ResponseEntity<M>(createdModel, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<M> update(@RequestBody @Valid M model){


        var updatedModel = service.update(model);

        return new ResponseEntity<M>(updatedModel, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> delete(@PathVariable K id){
        boolean isDeleted = service.delete(id);
        return isDeleted ?
                new ResponseEntity<>("Elemento deletado com sucesso!", HttpStatusCode.valueOf(204)) :
                new ResponseEntity<>("Elemento não encontrado!", HttpStatusCode.valueOf(404));
    }
}
