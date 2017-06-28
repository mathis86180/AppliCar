package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import fr.eni.ecole.android.applivoiture.model.Location;

/**
 * Created by mseigle2016 on 28/06/2017.
 */

public class LocationDAO {

    private static final String QUERY_FIND_LOCATION_NON_TERMINEES = "";

    public static void insert(Location l, Context context){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
        ContentValues values = new ContentValues();
        values.put("client_id",l.getClient().getId());
        values.put("voiture_immatriculation",l.getVoiture().getImmatriculation());
        values.put("date_debut", df.format(l.getDateDebut()));
        values.put("date_fin", df.format(l.getDateFin()));

        Database.getInstance(context).getDb().insert("location",null,values);

        System.out.println("Using a dateFormat date is : " + df.format(l.getDateDebut()));
        Log.e("DATE",df.format(l.getDateDebut()));
    }
}
