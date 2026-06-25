package org.example.dao;

import jakarta.ejb.Stateless;
import org.example.entities.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class OperationDaoImpl implements IOperationDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Operation save(Operation operation) {
        em.persist(operation);
        return operation;
    }

    @Override
    public Operation findById(Long id) {
        return em.find(Operation.class, id);
    }

    @Override
    public List<Operation> findAll() {
        return em.createQuery("SELECT o FROM Operation o", Operation.class)
                .getResultList();
    }

    @Override
    public void update (Operation operation) {
        em.merge(operation);
    }

    @Override
    public void delete(Long id) {
        Operation o = em.find(Operation.class, id);
        if (o != null) {
            em.remove(o);
        }
    }
}