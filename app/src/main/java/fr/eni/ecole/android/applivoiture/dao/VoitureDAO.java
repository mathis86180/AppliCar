package fr.eni.ecole.android.applivoiture.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.android.applivoiture.model.Voiture;

/**
 * Created by afillon2016 on 26/06/2017.
 */

public class VoitureDAO {
    public final static String DATABASE_NAME = "AppliCar.db";
    public final static Integer DATABASE_VERSION = 1;
    private final static String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            "VOITURE( " +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NOM TEXT, " +
            "LOUE INTEGER, " +
            "VILLE INTEGER, " +
            "CAMPAGNE INTEGER, " +
            "PRIX INTEGER, " +
            "IMMATRICULATION TEXT, " +
            "ETAT TEXT, " +
            "MARQUE TEXT, " +
            "MODELE TEXT, " +
            "ID_AGENCE INTEGER, " +
            "FOREIGN KEY (ID_AGENCE) REFERENCES AGENCE(ID))";

    private final static String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS VOITURE";
    private final static String TABLE_NAME = "VOITURE";
    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM, LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION, ETAT," +
            " MODELE, ID_AGENCE FROM VOITURE";
    private final static String QUERY_FIND_ONE = "SELECT ID, NOM, LOUE, VILLE, CAMPAGNE, PRIX,  MARQUE, IMMATRICULATION, ETAT," +
            " MODELE, ID_AGENCE FROM VOITURE WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public VoitureDAO(Context context)
    {
        helper = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(QUERY_CREATE_TABLE);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                if (oldVersion < newVersion) {
                    db.execSQL(QUERY_DELETE_TABLE);
                    onCreate(db);
                }
            }
        };
        db = helper.getWritableDatabase();
    }

    public static List<Voiture> listeVoitures = new ArrayList<Voiture>();

    public static List<Voiture> getVoitures() {
        return listeVoitures;
    }

    public static void setVoitures(List<Voiture> listeVoitures) {
        VoitureDAO.listeVoitures = listeVoitures;
    }

    public static List<Voiture> initList(){

        if (listeVoitures.isEmpty()){
            //listeVoitures.add(new Voiture(1, "test", 0, 1, 0, 20.50F, "ABC089", "bien", "RENAUD", "Espace", 1));

            // TODO : Plus de jeux d'essai

        /*listeVoitures = null;
        listeVoitures = new ArrayList<Voiture>();
        Cursor c = db.rawQuery(QUERY_SELECT_ALL, null);
        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                Integer id = c.getInt(c.getColumnIndex("Id"));
                String nom = c.getString(c.getColumnIndex("Nom"));
                Integer loue = c.getInt(c.getColumnIndex("Loue"));
                Integer ville = c.getInt(c.getColumnIndex("Ville"));
                Integer campagne = c.getInt(c.getColumnIndex("Campagne"));
                Integer prix_par_jour = c.getInt(c.getColumnIndex("Prix"));
                String immatriculation = c.getString(c.getColumnIndex("Immatriculation"));
                String etat = c.getString(c.getColumnIndex("Etat"));
                String marque = c.getString(c.getColumnIndex("Marque"));
                String modele = c.getString(c.getColumnIndex("Modele"));
                Integer agenceId = c.getInt(c.getColumnIndex("Agence"));

                // TODO : faire puis appeller findByIdAgence

                Voiture voiture = new Voiture(id, nom, loue, ville, campagne, prix_par_jour, immatriculation, etat, marque, modele,
                        agence);
                listeVoitures.add(voiture);
                c.moveToNext();
            }
        }
        c.close();
        db.close(); */
        }
        return listeVoitures;
    }
}
