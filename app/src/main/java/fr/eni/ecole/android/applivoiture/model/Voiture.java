package fr.eni.ecole.android.applivoiture.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Voiture implements Serializable, Parcelable {

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
    private String image;

    public Voiture(int loue, int ville, int campagne, Float prix_par_jour, String immatriculation, String etat,
                   String marque, String modele) {
        this.loue = loue;
        this.ville = ville;
        this.campagne = campagne;
        this.prix_par_jour = prix_par_jour;
        this.immatriculation = immatriculation;
        this.etat = etat;
        this.marque = marque;
        this.modele = modele;
    }

    public Voiture(int loue, int ville, int campagne, Float prix_par_jour, String immatriculation, String etat,
                   String marque, String modele, Agence agence) {
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

    public Voiture(int loue, int ville, int campagne, Float prix_par_jour, String immatriculation, String etat,
                   String marque, String modele, Agence agence, String image) {
        this.loue = loue;
        this.ville = ville;
        this.campagne = campagne;
        this.prix_par_jour = prix_par_jour;
        this.immatriculation = immatriculation;
        this.etat = etat;
        this.marque = marque;
        this.modele = modele;
        this.agence = agence;
        this.image = image;
    }

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

    protected Voiture(Parcel in) {
        loue = in.readInt();
        ville = in.readInt();
        campagne = in.readInt();
        immatriculation = in.readString();
        etat = in.readString();
        marque = in.readString();
        modele = in.readString();
    }

    public static final Creator<Voiture> CREATOR = new Creator<Voiture>() {
        @Override
        public Voiture createFromParcel(Parcel in) {
            return new Voiture(in);
        }

        @Override
        public Voiture[] newArray(int size) {
            return new Voiture[size];
        }
    };

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(loue);
        parcel.writeInt(ville);
        parcel.writeInt(campagne);
        parcel.writeString(immatriculation);
        parcel.writeString(etat);
        parcel.writeString(marque);
        parcel.writeString(modele);
        parcel.writeDouble(prix_par_jour);
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", loue=" + loue +
                ", ville=" + ville +
                ", campagne=" + campagne +
                ", prix_par_jour=" + prix_par_jour +
                ", immatriculation='" + immatriculation + '\'' +
                ", etat='" + etat + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", agence=" + agence +
                ", image=" + image +
                '}';
    }
}
