package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOperation;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypeOperation type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Compte compte;

    public Operation() {
    }

    public Operation(Long id, LocalDate dateOperation, Double montant, TypeOperation type, Compte compte) {
        this.id = id;
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.type = type;
        this.compte = compte;
    }

    public Long getId() { return id; }

    public LocalDate getDateOperation() { return dateOperation; }

    public Double getMontant() { return montant; }

    public TypeOperation getType() { return type; }

    public Compte getCompte() { return compte; }


    public void setId(Long id) { this.id = id; }

    public void setDateOperation(LocalDate dateOperation) { this.dateOperation = dateOperation; }

    public void setMontant(Double montant) { this.montant = montant; }

    public void setType(TypeOperation type) { this.type = type; }

    public void setCompte(Compte compte) { this.compte = compte; }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", dateOperation=" + dateOperation +
                ", montant=" + montant +
                ", type=" + type +
                ", compte=" + compte +
                '}';
    }

    public boolean estDepot() {
        return type == TypeOperation.DEPOT;
    }

    public boolean estRetrait() {
        return type == TypeOperation.RETRAIT;
    }

    public boolean montantValide() {
        return montant != null && montant > 0;
    }


}