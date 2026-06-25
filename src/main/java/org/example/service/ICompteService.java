package org.example.service;

import jakarta.ejb.Local;
import org.example.entities.Compte;

import java.util.List;

@Local
public interface ICompteService {

    Long creerCompte(Compte compte);

    Compte consulterCompte(Long id);

    List<Compte> listerComptes();

    void modifierCompte(Compte compte);

    void supprimerCompte(Long id);
}