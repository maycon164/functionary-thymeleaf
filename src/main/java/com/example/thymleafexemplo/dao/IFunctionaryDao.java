package com.example.thymleafexemplo.dao;

import com.example.thymleafexemplo.model.Functionary;

import java.util.List;

public interface IFunctionaryDao {

    public List<Functionary> findAll();
    public Functionary findById(long id);
    public Functionary update(long id, Functionary functionary);
    public Functionary delete(long id);
    public Functionary save(Functionary functionary);

}
