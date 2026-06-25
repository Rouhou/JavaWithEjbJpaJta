package org.example.dao;

import jakarta.ejb.Stateless;
import org.example.entities.Compte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class CompteDaoImpl implements ICompteDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Compte save(Compte compte) {
        em.persist(compte);
        return compte;
    }

    @Override
    public Compte findById(Long id) {
        return em.find(Compte.class, id);
    }

    @Override
    public List<Compte> findAll() {
        return em.createQuery("SELECT c FROM Compte c", Compte.class)
                .getResultList();
    }

    @Override
    public void update (Compte compte) {
        em.merge(compte);
    }

    @Override
    public void delete(Long id) {
        Compte c = em.find(Compte.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}