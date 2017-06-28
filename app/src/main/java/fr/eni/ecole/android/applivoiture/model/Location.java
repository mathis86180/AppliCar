package fr.eni.ecole.android.applivoiture.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mseigle2016 on 28/06/2017.
 */

public class Location implements Serializable {

    private Voiture voiture;
    private Client client;
    private Date dateDebut;
    private Date dateFin;

    public Location(Voiture voiture, Client client, Date dateDebut, Date dateFin) {
        this.voiture = voiture;
        this.client = client;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
