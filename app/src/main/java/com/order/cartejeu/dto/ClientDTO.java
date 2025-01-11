package com.order.cartejeu.dto;

import java.util.List;

public class ClientDTO {
    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private String pseudo;
    private String statutCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    // Autres champs nécessaires uniquement pour l'affichage
    private String adresseExpedition;
    private String adresseDestination;
    private List<String> historiqueCommandes;

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getStatutCompte() {
        return statutCompte;
    }

    public void setStatutCompte(String statutCompte) {
        this.statutCompte = statutCompte;
    }

    public String getAdresseExpedition() {
        return adresseExpedition;
    }

    public void setAdresseExpedition(String adresseExpedition) {
        this.adresseExpedition = adresseExpedition;
    }

    public String getAdresseDestination() {
        return adresseDestination;
    }

    public void setAdresseDestination(String adresseDestination) {
        this.adresseDestination = adresseDestination;
    }

    public List<String> getHistoriqueCommandes() {
        return historiqueCommandes;
    }

    public void setHistoriqueCommandes(List<String> historiqueCommandes) {
        this.historiqueCommandes = historiqueCommandes;
    }
}
