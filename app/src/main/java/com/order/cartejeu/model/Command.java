package com.order.cartejeu.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Command {

    private Long id; // ID unique de la commande
    private Long clientId; // ID du client associé à la commande
    private Long livreurId; // ID du livreur (peut être null si non assigné)
    private String typeColis; // Type du colis (ex : électronique, vêtements)
    private Double poids; // Poids du colis en kilogrammes
    private Double dimensions; // Dimensions en mètre cube
    private String adresseDepart; // Adresse de départ
    private String adresseArrivee; // Adresse d'arrivée
    private String instructionsSpecifiques; // Instructions spécifiques pour la livraison
    private String statutCommande; // Statut de la commande (ex : EN_ATTENTE, LIVRE)
    private Date dateCreation; // Date de création de la commande
    private Date dateLivraisonPrevue; // Date de livraison prévue

    // Constructeur par défaut
    public Command() {
        this.dateCreation = new Date(); // Initialise par défaut la date de création à la date actuelle
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(Long livreurId) {
        this.livreurId = livreurId;
    }

    public String getTypeColis() {
        return typeColis;
    }

    public void setTypeColis(String typeColis) {
        this.typeColis = typeColis;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getDimensions() {
        return dimensions;
    }

    public void setDimensions(Double dimensions) {
        this.dimensions = dimensions;
    }

    public String getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public String getAdresseArrivee() {
        return adresseArrivee;
    }

    public void setAdresseArrivee(String adresseArrivee) {
        this.adresseArrivee = adresseArrivee;
    }

    public String getInstructionsSpecifiques() {
        return instructionsSpecifiques;
    }

    public void setInstructionsSpecifiques(String instructionsSpecifiques) {
        this.instructionsSpecifiques = instructionsSpecifiques;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateLivraisonPrevue() {
        return dateLivraisonPrevue;
    }

    public void setDateLivraisonPrevue(Date dateLivraisonPrevue) {
        this.dateLivraisonPrevue = dateLivraisonPrevue;
    }

    // Méthode pour formater la date de livraison prévue
    public String getFormattedDateLivraisonPrevue() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateLivraisonPrevue != null ? dateFormat.format(dateLivraisonPrevue) : "Non définie";
    }

    // Méthode pour afficher un résumé de la commande
    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", livreurId=" + livreurId +
                ", typeColis='" + typeColis + '\'' +
                ", poids=" + poids +
                ", dimensions=" + dimensions +
                ", adresseDepart='" + adresseDepart + '\'' +
                ", adresseArrivee='" + adresseArrivee + '\'' +
                ", instructionsSpecifiques='" + instructionsSpecifiques + '\'' +
                ", statutCommande='" + statutCommande + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateLivraisonPrevue=" + getFormattedDateLivraisonPrevue() +
                '}';
    }
}
