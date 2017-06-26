package fr.eni.ecole.android.applivoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Gerant{

    private Integer id;
    private String nom;
    private String prenom;
    private String mail;

    public Gerant(Integer id, String nom, String prenom, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
