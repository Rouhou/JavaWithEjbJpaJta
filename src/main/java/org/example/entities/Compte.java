package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCompte;
    private Double solde;
    private LocalDate dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Compte() {
    }

    public Compte(Long id, String numeroCompte, Double solde, LocalDate dateCreation, Client client) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.client = client;
    }


    public Long getId() {
        return id;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public Double getSolde() {
        return solde;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Client getClient() {
        return client;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", numeroCompte='" + numeroCompte + '\'' +
                ", solde=" + solde +
                ", dateCreation=" + dateCreation +
                ", client=" + client +
                '}';
    }


}