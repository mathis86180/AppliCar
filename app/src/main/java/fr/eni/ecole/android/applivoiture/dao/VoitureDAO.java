package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.android.applivoiture.R;
import fr.eni.ecole.android.applivoiture.model.Agence;
import fr.eni.ecole.android.applivoiture.model.Gerant;
import fr.eni.ecole.android.applivoiture.model.Voiture;

/**
 * Created by afillon2016 on 26/06/2017.
 */

public class VoitureDAO {

    private final static String TABLE_NAME = "VOITURE";
    private final static String QUERY_SELECT_ALL = "SELECT ID, LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION, ETAT," +
            " MODELE, ID_AGENCE FROM VOITURE";
    private final static String QUERY_FIND_ONE = "SELECT ID, LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION, ETAT," +
            " MODELE, ID_AGENCE FROM VOITURE WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";




    public VoitureDAO()
    {
    }

    public List<Voiture> findVoitureLouees(Context context)
    {
        List<Voiture> maListe = new ArrayList<>();
        Cursor c = Database.getInstance(context).getDb().rawQuery(QUERY_SELECT_ALL, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                String immatriculation = c.getString(c.getColumnIndex("immatriculation"));
                Integer campagne = c.getInt(c.getColumnIndex("campagne"));
                Integer ville = c.getInt(c.getColumnIndex("ville"));
                Integer loue = c.getInt(c.getColumnIndex("loue"));
                String etat = c.getString(c.getColumnIndex("etat"));
                String modele = c.getString(c.getColumnIndex("modele"));
                String marque = c.getString(c.getColumnIndex("marque"));
                Float prix = c.getFloat(c.getColumnIndex("prix"));

                Voiture v = new Voiture(loue,ville,campagne,prix,immatriculation,etat,marque,modele);
                maListe.add(v);
                c.moveToNext();
            }
        }
        return maListe;
    }


    public static void insert(Voiture v, Context context)
    {
        ContentValues values = new ContentValues();
        values.put("immatriculation", v.getImmatriculation());
        values.put("loue", v.getLoue());
        values.put("ville", v.getVille());
        values.put("campagne", v.getCampagne());
        values.put("etat", v.getEtat());
        values.put("marque", v.getMarque());
        values.put("modele", v.getModele());
        values.put("prix", v.getPrix_par_jour());
        values.put("id_agence", v.getAgence().getId());
        Database.getInstance(context).getDb().insert("voiture",null,values);
    }

}
