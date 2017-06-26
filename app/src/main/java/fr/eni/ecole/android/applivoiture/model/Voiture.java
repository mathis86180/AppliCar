package fr.eni.ecole.android.applivoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Voiture implements Serializable{

    private Integer id;
    private int loue;
    private int ville;
    private int campagne;
    private Float prix_par_jour;
    private String immatriculation;
    private String etat;
    private String marque;
    private String modele;
    private Agence agence;

    public Voiture(Integer id, int loue, int ville, int campagne, Float prix_par_jour, String immatriculation,
                   String etat, String marque, String modele, Agence agence) {
        this.id = id;
        this.loue = loue;
        this.ville = ville;
        this.campagne = campagne;
        this.prix_par_jour = prix_par_jour;
        this.immatriculation = immatriculation;
        this.etat = etat;
        this.marque = marque;
        this.modele = modele;
        this.agence = agence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLoue() {
        return loue;
    }

    public void setLoue(int loue) {
        this.loue = loue;
    }

    public int getVille() {
        return ville;
    }

    public void setVille(int ville) {
        this.ville = ville;
    }

    public int getCampagne() {
        return campagne;
    }

    public void setCampagne(int campagne) {
        this.campagne = campagne;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Float getPrix_par_jour() {
        return prix_par_jour;
    }

    public void setPrix_par_jour(Float prix_par_jour) {
        this.prix_par_jour = prix_par_jour;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
