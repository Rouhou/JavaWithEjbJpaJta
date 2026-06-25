package org.example.dao;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import org.example.entities.Client;

import java.util.List;

public interface IGenericDao<T, ID> {

    T save(T entity);

    T findById(ID id);

    List<T> findAll();

    void update(T entity);

    void delete(ID id);
}