package com.example.thymleafexemplo.dao;

import com.example.thymleafexemplo.model.Functionary;

import java.util.List;

public interface IFunctionaryDao {

    public List<Functionary> findAll();
    public Functionary findById(long id);
    public boolean update(long id, Functionary functionary);
    public boolean deleteById(long id);
    public Functionary insert(Functionary functionary);

}
