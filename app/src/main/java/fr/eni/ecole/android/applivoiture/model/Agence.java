package fr.eni.ecole.android.applivoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Agence{

    private Integer id;
    private String nom;
    private Gerant gerant;

    public Agence(Integer id, String nom, Gerant gerant) {
        this.id = id;
        this.nom = nom;
        this.gerant = gerant;
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

    public Gerant getGerant() {
        return gerant;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }

}
