package fr.eni.ecole.android.applivoiture.model;

import java.io.Serializable;

/**
 * Created by mseigle2016 on 28/06/2017.
 */

public class Client implements Serializable {

    private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String mail;

    public Client(Integer id, String nom, String prenom, String adresse, String ville, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.mail = mail;
    }

    public Client(String nom, String prenom, String adresse, String ville, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.mail = mail;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
