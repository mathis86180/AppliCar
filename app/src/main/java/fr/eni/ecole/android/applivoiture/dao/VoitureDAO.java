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
    private final static String QUERY_SELECT_ALL = "SELECT LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION," +
            " ETAT, MODELE, ID_AGENCE FROM VOITURE";
    private final static String QUERY_SELECT_LOUEES = "SELECT LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION," +
            " ETAT, MODELE, ID_AGENCE FROM VOITURE WHERE LOUE = 1";
    private final static String QUERY_SELECT_NON_LOUEES = "SELECT LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION," +
            " ETAT, MODELE, ID_AGENCE FROM VOITURE WHERE LOUE = 0";
    private final static String QUERY_FIND_ONE = "SELECT LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION, ETAT," +
            " MODELE, ID_AGENCE, IMAGE FROM VOITURE WHERE IMMATRICULATION = ?";
    private final static String QUERY_GET_ONE = "IMMATRICULATION = ?";
    private final static String QUERY_MIN_PRIX = "SELECT MIN(PRIX) AS PRIX FROM VOITURE";
    private final static String QUERY_MAX_PRIX = "SELECT MAX(PRIX) AS PRIX FROM VOITURE";

    public static String getQuerySelectLouees() {
        return QUERY_SELECT_LOUEES;
    }

    public static String getQuerySelectNonLouees() {
        return QUERY_SELECT_NON_LOUEES;
    }

    public VoitureDAO()
    {
    }

    public static List<Voiture> findVoitures(Context context, String query)
    {
        List<Voiture> maListe = new ArrayList<>();
        Cursor c = Database.getInstance(context).getDb().rawQuery(query, null);
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
        values.put("image", v.getImage());
        Database.getInstance(context).getDb().insert("voiture",null,values);
    }

    public static long update(Voiture v, Context context){
        ContentValues values = new ContentValues();
        String immatriculation = v.getImmatriculation();
        values.put("loue", v.getLoue());
        values.put("ville", v.getVille());
        values.put("campagne", v.getCampagne());
        values.put("etat", v.getEtat());
        values.put("marque", v.getMarque());
        values.put("modele", v.getModele());
        values.put("prix", v.getPrix_par_jour());
        values.put("id_agence", v.getAgence().getId());
        values.put("image", v.getImage());
        return Database.getInstance(context).getDb().update(TABLE_NAME, values, QUERY_GET_ONE, new String[] {immatriculation});
    }

    public static void delete(String immatriculation){
        String immatriculationS = immatriculation.toString();
        Database.getDb().delete(TABLE_NAME, QUERY_GET_ONE, new String[] {immatriculationS});
    }

    public static Voiture findOneById(String immatriculationQuery,Context context)
    {
        Voiture v = null;
        Cursor c = Database.getInstance(context).getDb().rawQuery(QUERY_FIND_ONE,new String[] {immatriculationQuery});
        if(c.getCount() > 0) {
            c.moveToFirst();
            String immatriculation = c.getString(c.getColumnIndex("immatriculation"));
            Integer campagne = c.getInt(c.getColumnIndex("campagne"));
            Integer ville = c.getInt(c.getColumnIndex("ville"));
            Integer loue = c.getInt(c.getColumnIndex("loue"));
            String etat = c.getString(c.getColumnIndex("etat"));
            String modele = c.getString(c.getColumnIndex("modele"));
            String marque = c.getString(c.getColumnIndex("marque"));
            Float prix = c.getFloat(c.getColumnIndex("prix"));
            String image = c.getString(c.getColumnIndex("image"));
            v = new Voiture(loue,ville,campagne,prix,immatriculation,etat,marque,modele,image);
        }
        return v;
    }

    public static float selectMin(Context context){
        float min = 0;

        Cursor c  = Database.getInstance(context).getDb().rawQuery(QUERY_MIN_PRIX, null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                min = c.getFloat(c.getColumnIndex("PRIX"));

                c.moveToNext();
            }
        }

        return min;
    }

    public static float selectMax(Context context){
        float max = 0;

        Cursor c  = Database.getInstance(context).getDb().rawQuery(QUERY_MAX_PRIX, null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {

                max = c.getFloat(c.getColumnIndex("PRIX"));

                c.moveToNext();
            }
        }

        return max;
    }

    public static List<Voiture> findRechercheVoitures(Context context, int villeRecherche, int campagneRecherche,
                                                      int minPrix, int maxPrix){

        List<Voiture> maListe = new ArrayList<>();

        Cursor c = Database.getInstance(context).getDb().rawQuery("SELECT LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE," +
                " IMMATRICULATION, ETAT, MODELE, ID_AGENCE FROM VOITURE WHERE CAMPAGNE = " + campagneRecherche + " " +
                " AND VILLE = " + villeRecherche + " AND PRIX BETWEEN " + minPrix + " AND " + maxPrix + "", null);

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

}
