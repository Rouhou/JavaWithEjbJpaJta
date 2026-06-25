package org.example.dao;

import jakarta.ejb.Local;
import org.example.entities.Operation;

@Local
public interface IOperationDao extends IGenericDao<Operation, Long> {
}