package com.example.projetmobile2;
public class Restaurant {

    public int id;
    public String nomRestaurant;
    public String adresseRestaurant;
    public String qualitePlats;
    public String qualiteService;
    public float prixMoyen;
    public int nbEtoiles;

    public Restaurant(int id, String nomRestaurant, String adresseRestaurant, String qualitePlats, String qualiteService, float prixMoyen, int nbEtoiles) {
        this.id = id;
        this.nomRestaurant = nomRestaurant;
        this.adresseRestaurant = adresseRestaurant;
        this.qualitePlats = qualitePlats;
        this.qualiteService = qualiteService;
        this.prixMoyen = prixMoyen;
        this.nbEtoiles = nbEtoiles;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", nomRestaurant='" + nomRestaurant + '\'' +
                ", adresseRestaurant='" + adresseRestaurant + '\'' +
                ", qualitePlats='" + qualitePlats + '\'' +
                ", qualiteService='" + qualiteService + '\'' +
                ", prixMoyen=" + prixMoyen +
                ", nbEtoiles=" + nbEtoiles +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public String getAdresseRestaurant() {
        return adresseRestaurant;
    }

    public void setAdresseRestaurant(String adresseRestaurant) {
        this.adresseRestaurant = adresseRestaurant;
    }

    public String getQualitePlats() {
        return qualitePlats;
    }

    public void setQualitePlats(String qualitePlats) {
        this.qualitePlats = qualitePlats;
    }

    public String getQualiteService() {
        return qualiteService;
    }

    public void setQualiteService(String qualiteService) {
        this.qualiteService = qualiteService;
    }

    public float getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(float prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }
}

