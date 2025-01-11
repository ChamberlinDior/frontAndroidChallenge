package com.order.cartejeu.model;

import java.util.List;

public class Client {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private String pseudo;
    private String motDePasse;
    private String statutCompte;
    private String adresseExpedition;
    private String adresseDestination;
    private String typeColis;
    private String instructionsSpeciales;
    private String declarationHonneur;
    private List<String> historiqueCommandes;
    private List<String> suiviColis;
    private String methodePaiement;
    private String parrainages;
    private Double noteMoyenne;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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

    public String getTypeColis() {
        return typeColis;
    }

    public void setTypeColis(String typeColis) {
        this.typeColis = typeColis;
    }

    public String getInstructionsSpeciales() {
        return instructionsSpeciales;
    }

    public void setInstructionsSpeciales(String instructionsSpeciales) {
        this.instructionsSpeciales = instructionsSpeciales;
    }

    public String getDeclarationHonneur() {
        return declarationHonneur;
    }

    public void setDeclarationHonneur(String declarationHonneur) {
        this.declarationHonneur = declarationHonneur;
    }

    public List<String> getHistoriqueCommandes() {
        return historiqueCommandes;
    }

    public void setHistoriqueCommandes(List<String> historiqueCommandes) {
        this.historiqueCommandes = historiqueCommandes;
    }

    public List<String> getSuiviColis() {
        return suiviColis;
    }

    public void setSuiviColis(List<String> suiviColis) {
        this.suiviColis = suiviColis;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }

    public String getParrainages() {
        return parrainages;
    }

    public void setParrainages(String parrainages) {
        this.parrainages = parrainages;
    }

    public Double getNoteMoyenne() {
        return noteMoyenne;
    }

    public void setNoteMoyenne(Double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }
}
