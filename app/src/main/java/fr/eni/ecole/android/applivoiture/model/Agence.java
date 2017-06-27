package fr.eni.ecole.android.applivoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Agence implements Serializable, Parcelable{

    private Integer id;
    private String nom;
    private Gerant gerant;

    public Agence(Integer id, String nom, Gerant gerant) {
        this.id = id;
        this.nom = nom;
        this.gerant = gerant;
    }

    public Agence(String nom, Gerant gerant) {
        this.nom = nom;
        this.gerant = gerant;
    }

    protected Agence(Parcel in) {
        nom = in.readString();
        gerant = in.readParcelable(Gerant.class.getClassLoader());
    }

    public static final Creator<Agence> CREATOR = new Creator<Agence>() {
        @Override
        public Agence createFromParcel(Parcel in) {
            return new Agence(in);
        }

        @Override
        public Agence[] newArray(int size) {
            return new Agence[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeParcelable(gerant, i);
    }
}
