package org.example;

import org.example.entities.Client;
import org.example.entities.Compte;
import org.example.entities.Operation;
import org.example.service.IClientService;
import org.example.service.ICompteService;
import org.example.service.IOperationService;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import jakarta.ejb.EJB;
import java.util.List;
import java.time.LocalDate;

@RunWith(Arquillian.class)
public class BanqueIntegrationTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "banque-test.war")
                .addPackages(true, "org.example.service")
                .addPackages(true, "org.example.dao")
                .addPackages(true, "org.example.entities")
                .addAsResource("META-INF/persistence.xml");
    }

    @EJB
    private IClientService clientService;

    @EJB
    private ICompteService compteService;

    @EJB
    private IOperationService operationService;


    @Test
    @InSequence(1)
    public void resetDatabase() {
        for (Operation op : operationService.listerOperation()) {
            operationService.supprimerOperation(op.getId());
        }

        for (Compte c : compteService.listerComptes()) {
            compteService.supprimerCompte(c.getId());
        }

        for (Client c : clientService.listerClients()) {
            clientService.supprimerClient(c.getId());
        }
    }

    @Test
    @InSequence(2)
    public void createClients() {
        clientService.creerClient(new Client(null, "Issa", "Diagne", "770000001", LocalDate.of(1990, 1, 10)));
        clientService.creerClient(new Client(null, "Ignace", "Diatta", "770000002", LocalDate.of(1992, 2, 15)));
        clientService.creerClient(new Client(null, "Jean", "Yowane", "770000003", LocalDate.of(1988, 5, 20)));
        clientService.creerClient(new Client(null, "Samba", "Suare", "770000004", LocalDate.of(1995, 7, 12)));
        clientService.creerClient(new Client(null, "Ibrahima", "Ndiaye", "770000005", LocalDate.of(1991, 11, 30)));
    }

    @Test
    @InSequence(3)
    public void createComptes() {
        List<Client> clients = clientService.listerClients();

        for (Client c : clients) {
            Compte cp = new Compte(
                    null,
                    "CPT-" + c.getId(),
                    100000.0,
                    LocalDate.now(),
                    c
            );
            compteService.creerCompte(cp);
        }
    }

    @Test
    @InSequence(4)
    public void testDepots() {
        List<Compte> comptes = compteService.listerComptes();

        for (Compte c : comptes) {
            operationService.depot(c.getId(), 50000);
        }
    }

    @Test
    @InSequence(5)
    public void testRetraits() {
        List<Compte> comptes = compteService.listerComptes();

        for (Compte c : comptes) {
            operationService.retrait(c.getId(), 20000);
        }
    }

    @Test
    @InSequence(6)
    public void testVirements() {
        List<Compte> comptes = compteService.listerComptes();

        if (comptes.size() >= 2) {
            operationService.virement(
                    comptes.get(0).getId(),
                    comptes.get(1).getId(),
                    10000
            );
        }
    }

    @Test
    @InSequence(7)
    public void checkResult() {
        List<Compte> comptes = compteService.listerComptes();

        for (Compte c : comptes) {
            System.out.println(
                    "Compte: " + c.getNumeroCompte()
                            + " | Solde: " + c.getSolde()
            );
        }
    }
}