package org.example.service;

import jakarta.ejb.EJB;
import org.example.dao.ICompteDao;
import org.example.entities.Compte;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class CompteServiceImpl {

    @EJB
    private ICompteDao compteDao;

    public Compte creerCompte(Compte compte) {
        return compteDao.save(compte);
    }

    public Compte consulterCompte(Long id) {
        return compteDao.findById(id);
    }

    public List<Compte> listerComptes() {
        return compteDao.findAll();
    }

    public void modifierCompte(Compte compte){
        compteDao.update(compte);
    }

    public void supprimerCompte(Long id) {
        compteDao.delete(id);
    }

}