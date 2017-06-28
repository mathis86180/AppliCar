package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import fr.eni.ecole.android.applivoiture.model.Client;

/**
 * Created by mseigle2016 on 28/06/2017.
 */

public class ClientDAO {

    private static final String QUERY_FIND_BY_ID = "SELECT ID, NOM, PRENOM, ADRESSE, VILLE FROM CLIENT WHERE ID = ?";

    public static void insert(Client c, Context context){
        ContentValues values = new ContentValues();
        values.put("nom",c.getNom());
        values.put("prenom",c.getPrenom());
        values.put("adresse",c.getAdresse());
        values.put("ville",c.getVille());
        Database.getInstance(context).getDb().insert("client",null,values);
    }

    public static Client findOneById(Integer id,Context context) {
        String ids = id.toString();
        Client cl = null;
        Cursor c = Database.getInstance(context).getDb().rawQuery(QUERY_FIND_BY_ID, new String[]{ids});
        if (c.getCount() > 0) {
            c.moveToFirst();
            Integer idClient = c.getInt(c.getColumnIndex("id"));
            String nom = c.getString(c.getColumnIndex("nom"));
            String prenom = c.getString(c.getColumnIndex("prenom"));
            String adresse = c.getString(c.getColumnIndex("adresse"));
            String ville = c.getString(c.getColumnIndex("ville"));

            cl = new Client(idClient, nom, prenom, adresse, ville);
        }
        return cl;
    }
}
