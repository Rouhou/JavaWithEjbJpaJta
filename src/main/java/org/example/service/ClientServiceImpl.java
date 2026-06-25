package org.example.service;

import jakarta.ejb.EJB;
import org.example.dao.IClientDao;
import org.example.entities.Client;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class ClientServiceImpl {

    @EJB
    private IClientDao clientDao;

    public Client creerClient(Client client) {
        return clientDao.save(client);
    }

    public Client consulterClient(Long id) {
        return clientDao.findById(id);
    }

    public List<Client> listerClients() {
        return clientDao.findAll();
    }

    public void modifierClient(Client client){
        clientDao.update(client);
    }

    public void supprimerClient(Long id) {
        clientDao.delete(id);
    }
}