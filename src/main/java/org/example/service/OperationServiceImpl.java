package org.example.service;

import jakarta.ejb.EJB;
import org.example.dao.*;
import org.example.entities.Compte;
import org.example.entities.Operation;
import org.example.entities.TypeOperation;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class OperationServiceImpl implements IOperationService {

    @EJB
    private ICompteDao compteDao;

    @EJB
    private IOperationDao operationDao;

    public void depot(Long compteId, double montant) {

        Compte compte = compteDao.findById(compteId);

        compte.setSolde(compte.getSolde() + montant);

        Operation op = new Operation();
        op.setCompte(compte);
        op.setMontant(montant);
        op.setType(TypeOperation.DEPOT);
        op.setDateOperation(LocalDate.now());

        operationDao.save(op);
    }

    public void retrait(Long compteId, double montant) {

        Compte compte = compteDao.findById(compteId);

        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant");
        }

        compte.setSolde(compte.getSolde() - montant);

        Operation op = new Operation();
        op.setCompte(compte);
        op.setMontant(montant);
        op.setType(TypeOperation.RETRAIT);
        op.setDateOperation(LocalDate.now());

        operationDao.save(op);
    }

    public void virement(Long sourceId, Long destinationId, double montant) {

        Compte source = compteDao.findById(sourceId);
        Compte destination = compteDao.findById(destinationId);

        if (source.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant");
        }

        // débit source
        source.setSolde(source.getSolde() - montant);

        Operation op1 = new Operation();
        op1.setCompte(source);
        op1.setMontant(montant);
        op1.setType(TypeOperation.RETRAIT);
        op1.setDateOperation(LocalDate.now());

        // crédit destination
        destination.setSolde(destination.getSolde() + montant);

        Operation op2 = new Operation();
        op2.setCompte(destination);
        op2.setMontant(montant);
        op2.setType(TypeOperation.DEPOT);
        op2.setDateOperation(LocalDate.now());

        operationDao.save(op1);
        operationDao.save(op2);
    }

    public List<Operation> listerOperation(){
        return operationDao.findAll();
    }

    public void supprimerOperation(Long id){
        operationDao.delete(id);
    }
}