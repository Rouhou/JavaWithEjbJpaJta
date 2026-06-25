package org.example.dao;

import jakarta.ejb.Local;
import org.example.entities.Client;

@Local
public interface IClientDao extends IGenericDao<Client, Long> {
}