package org.example.service;

import jakarta.ejb.Local;
import org.example.entities.Client;

import java.util.List;

@Local
public interface IClientService {

    Client creerClient(Client client);

    Client consulterClient(Long id);

    List<Client> listerClients();

    void modifierClient(Client client);

    void supprimerClient(Long id);
}