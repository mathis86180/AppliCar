package fr.eni.ecole.android.applivoiture.model;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Gerant {

    private Integer id;
    private String nom;
    private String prenom;

    public Gerant(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
