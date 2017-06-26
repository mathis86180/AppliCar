package fr.eni.ecole.android.applivoiture.model;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Voiture {

    private Integer id;
    private String nom;
    private Boolean loue;
    private Boolean ville;
    private Boolean campagne;
    private String marque;
    private String modele;
    private Agence agence;

    public Voiture(Integer id, String nom, Boolean loue, Boolean ville, Boolean campagne, String marque, String modele, Agence agence) {
        this.id = id;
        this.nom = nom;
        this.loue = loue;
        this.ville = ville;
        this.campagne = campagne;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getLoue() {
        return loue;
    }

    public void setLoue(Boolean loue) {
        this.loue = loue;
    }

    public Boolean getVille() {
        return ville;
    }

    public void setVille(Boolean ville) {
        this.ville = ville;
    }

    public Boolean getCampagne() {
        return campagne;
    }

    public void setCampagne(Boolean campagne) {
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
}
