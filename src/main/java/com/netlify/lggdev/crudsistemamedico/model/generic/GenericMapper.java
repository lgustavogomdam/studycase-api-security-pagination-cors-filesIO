package com.netlify.lggdev.crudsistemamedico.model.generic;

import java.util.List;


public interface GenericMapper<E,M> {

    public E toEntity(M model);

    public M toModel(E entity);

    public List<E> toEntityList(List<M> modelList);

    public List<M> toModelList(List<E> modelList);
}
