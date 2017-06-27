package fr.eni.ecole.android.applivoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Gerant implements Serializable, Parcelable{

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
    public Gerant(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    protected Gerant(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        mail = in.readString();
    }

    public static final Creator<Gerant> CREATOR = new Creator<Gerant>() {
        @Override
        public Gerant createFromParcel(Parcel in) {
            return new Gerant(in);
        }

        @Override
        public Gerant[] newArray(int size) {
            return new Gerant[size];
        }
    };

    protected Gerant(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        mail = in.readString();
    }

    public static final Creator<Gerant> CREATOR = new Creator<Gerant>() {
        @Override
        public Gerant createFromParcel(Parcel in) {
            return new Gerant(in);
        }

        @Override
        public Gerant[] newArray(int size) {
            return new Gerant[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(mail);
    }
}
