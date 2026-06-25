package org.example.dao;

import jakarta.ejb.Local;
import org.example.entities.Compte;

@Local
public interface ICompteDao extends IGenericDao<Compte, Long> {
}