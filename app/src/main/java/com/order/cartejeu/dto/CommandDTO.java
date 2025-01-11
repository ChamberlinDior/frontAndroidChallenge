package com.order.cartejeu.dto;

public class CommandDTO {

    private Long clientId;
    private String typeColis;
    private Double poids; // En kilogrammes
    private Double dimensions; // En mètre cube
    private String adresseDepart;
    private String adresseArrivee;
    private String instructionsSpecifiques;
    private String statutCommande; // Statut de la commande en tant que String
    private String dateLivraisonPrevue; // Format ISO 8601, attendu par le backend

    public CommandDTO() {}

    // Getters et Setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public String getDateLivraisonPrevue() {
        return dateLivraisonPrevue;
    }

    public void setDateLivraisonPrevue(String dateLivraisonPrevue) {
        this.dateLivraisonPrevue = dateLivraisonPrevue;
    }

    @Override
    public String toString() {
        return "CommandDTO{" +
                "clientId=" + clientId +
                ", typeColis='" + typeColis + '\'' +
                ", poids=" + poids +
                ", dimensions=" + dimensions +
                ", adresseDepart='" + adresseDepart + '\'' +
                ", adresseArrivee='" + adresseArrivee + '\'' +
                ", instructionsSpecifiques='" + instructionsSpecifiques + '\'' +
                ", statutCommande='" + statutCommande + '\'' +
                ", dateLivraisonPrevue='" + dateLivraisonPrevue + '\'' +
                '}';
    }
}
