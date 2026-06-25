package org.example.dao;

import jakarta.ejb.Stateless;
import org.example.entities.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ClientDaoImpl implements IClientDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Client save(Client client) {
        em.persist(client);
        return client;
    }

    @Override
    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    @Override
    public List<Client> findAll() {
        return em.createQuery("SELECT c FROM Client c", Client.class)
                .getResultList();
    }

    @Override
    public void update (Client client) {
        em.merge(client);
    }

    @Override
    public void delete(Long id) {
        Client c = em.find(Client.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}