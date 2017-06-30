package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import fr.eni.ecole.android.applivoiture.model.Client;
import fr.eni.ecole.android.applivoiture.model.Location;
import fr.eni.ecole.android.applivoiture.model.Voiture;

/**
 * Created by mseigle2016 on 28/06/2017.
 */

public class LocationDAO {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final String QUERY_FIND_LOCATION_NON_TERMINEES = "SELECT immatriculation " +
            "from location, voiture " +
            "where location.voiture_immatriculation = voiture.immatriculation " +
            "and location.date_fin >= CURRENT_DATE";
    private static final String QUERY_FIND_VOITURES_DISPONIBLES = "SELECT immatriculation " +
            "FROM voiture " +
            "WHERE immatriculation NOT IN( " +
            "SELECT voiture_immatriculation " +
            "FROM location, voiture " +
            "WHERE location.voiture_immatriculation = voiture.immatriculation " +
            "AND location.date_fin >= CURRENT_DATE)";

    public static String getQueryFindLocationNonTerminees() {
        return QUERY_FIND_LOCATION_NON_TERMINEES;
    }

    public static String getQueryFindVoituresDisponibles() {
        return QUERY_FIND_VOITURES_DISPONIBLES;
    }

    public static void insert(Location l, Context context){
        ContentValues values = new ContentValues();
        values.put("client_id",l.getClient().getId());
        values.put("voiture_immatriculation",l.getVoiture().getImmatriculation());
        values.put("date_debut", df.format(l.getDateDebut()));
        values.put("date_fin", df.format(l.getDateFin()));

        Database.getInstance(context).getDb().insert("location",null,values);

        System.out.println("Using a dateFormat date is : " + df.format(l.getDateDebut()));
        Log.e("DATE",df.format(l.getDateDebut()));
    }

    public static List<Voiture> findVoitureLouees(Context context, String query){
        List<Voiture> listVoiture = new ArrayList<>();
        Cursor c = Database.getInstance(context).getDb().rawQuery(query, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Voiture v = VoitureDAO.findOneById(c.getString(c.getColumnIndex("immatriculation")),context);
                listVoiture.add(v);
                c.moveToNext();
            }
        }
        return listVoiture;
    }
}
