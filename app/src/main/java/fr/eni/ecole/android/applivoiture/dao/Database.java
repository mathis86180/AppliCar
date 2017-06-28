package fr.eni.ecole.android.applivoiture.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class Database extends SQLiteOpenHelper {
    public final static Integer DATABASE_VERSION = 1;
    // database name
    private static final String DB_NAME = "Applicar.db";

    // table name
    private static final String TBL_CLIENT = "client";
    private static final String TBL_GERANT = "gerant";
    private static final String TBL_AGENCE = "agence";
    private static final String TBL_VOITURE = "voiture";
    private static final String TBL_LOCATION = "location";

    // table column name
    private static final String CLIENT_ID = "id";
    private static final String CLIENT_NOM = "nom";
    private static final String CLIENT_PRENOM = "prenom";
    private static final String CLIENT_ADRESSE = "adresse";
    private static final String CLIENT_VILLE = "ville";

    private static final String GERANT_ID = "id";
    private static final String GERANT_NOM = "nom";
    private static final String GERANT_PRENOM = "prenom";
    private static final String GERANT_MAIL = "mail";
    private static final String GERANT_PASSWORD = "password";
    private static final String GERANT_AGENCE_ID = "id_agence";

    private static final String AGENCE_ID = "id";
    private static final String AGENCE_NOM = "nom";

    private static final String VOITURE_IMMATRICULATION = "immatriculation";
    private static final String VOITURE_VILLE = "ville";
    private static final String VOITURE_CAMPAGNE = "campagne";
    private static final String VOITURE_LOUE = "loue";
    private static final String VOITURE_PRIX = "prix";
    private static final String VOITURE_ETAT = "etat";
    private static final String VOITURE_MARQUE = "marque";
    private static final String VOITURE_MODELE = "modele";
    private static final String VOITURE_AGENCE_ID = "id_agence";
    private static final String VOITURE_IMAGE = "image";

    private static final String LOCATION_VOITURE_IMMATRICULATION = "voiture_immatriculation";
    private static final String LOCATION_CLIENT_ID = "client_id";
    private static final String LOCATION_DATE_DEBUT = "date_debut";
    private static final String LOCATION_DATE_FIN = "date_fin";

    // requetes
    private static final String QUERY_DELETE_TABLE_CLIENT = "DROP TABLE IF EXISTS " + TBL_CLIENT;
    private final static String QUERY_DELETE_TABLE_GERANT = "DROP TABLE IF EXISTS " + TBL_GERANT;
    private final static String QUERY_DELETE_TABLE_AGENCE = "DROP TABLE IF EXISTS " + TBL_AGENCE;
    private final static String QUERY_DELETE_TABLE_VOITURE = "DROP TABLE IF EXISTS " + TBL_VOITURE;
    private static final String QUERY_DELETE_TABLE_LOCATION = "DROP TABLE IF EXISTS " + TBL_LOCATION;

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDb() {
        return db;
    }

    public static void setDb(SQLiteDatabase db) {
        Database.db = db;
    }

    //Database.getInstance(AddActivity.this).addOrUpdateMusic(musiques.getContentValues());
    private static Database mInstance = null;

    public static Database getInstance(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(
                    "Context is null ! Databases can't be initialized with null context");
        }

        if (mInstance == null) {
            //Log.e("database", "mInstance == null");
            mInstance = new Database(context.getApplicationContext());
            open();
        }

        return mInstance;
    }

    private Database(Context c) {
        super(c, DB_NAME, null, 1); // +1 version to upgrade database
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("onCreate", "Database update");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_CLIENT + " ( " +
                CLIENT_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLIENT_NOM + " VARCHAR, " +
                CLIENT_PRENOM + " VARCHAR, " +
                CLIENT_ADRESSE + " VARCHAR, " +
                CLIENT_VILLE + " VARCHAR)"
        );
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_AGENCE + " ( " +
                AGENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AGENCE_NOM + " VARCHAR, " +
                "); ");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_GERANT + " (" +
                GERANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                GERANT_NOM + " VARCHAR," +
                GERANT_PRENOM + " VARCHAR," +
                GERANT_MAIL + " VARCHAR," +
                GERANT_PASSWORD + " VARCHAR" +
                GERANT_AGENCE_ID + " INTEGER" +
                "FOREIGN KEY (" + GERANT_AGENCE_ID + ") REFERENCES " + TBL_AGENCE + "(" + AGENCE_ID + ")" +
                "); ");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_VOITURE + " (" +
                VOITURE_IMMATRICULATION + " VARCHAR PRIMARY KEY," +
                VOITURE_CAMPAGNE + " INTEGER," +
                VOITURE_VILLE + " INTEGER," +
                VOITURE_LOUE + " INTEGER," +
                VOITURE_ETAT + " VARCHAR," +
                VOITURE_MARQUE + " VARCHAR," +
                VOITURE_MODELE + " VARCHAR," +
                VOITURE_PRIX + " FLOAT," +
                VOITURE_AGENCE_ID + " INTEGER," +
                VOITURE_IMAGE + " VARCHAR," +
                "FOREIGN KEY (" + VOITURE_AGENCE_ID + ") REFERENCES " + TBL_AGENCE + "(" + AGENCE_ID + ")" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_LOCATION + " ( " +
                LOCATION_CLIENT_ID + " INTEGER NOT NULL, " +
                LOCATION_VOITURE_IMMATRICULATION + " VARCHAR NOT NULL, " +
                LOCATION_DATE_DEBUT + " DATE, " +
                LOCATION_DATE_FIN + " DATE, " +
                "FOREIGN KEY (" + LOCATION_CLIENT_ID + ") REFERENCES " + TBL_CLIENT + "(" + CLIENT_ID + "), " +
                "FOREIGN KEY (" + LOCATION_VOITURE_IMMATRICULATION + ") REFERENCES " + TBL_VOITURE + "(" + VOITURE_IMMATRICULATION + "), " +
                "PRIMARY KEY (" + LOCATION_CLIENT_ID + ", " + VOITURE_IMMATRICULATION + "))");
    }

    private static void open() {

        if (db == null) {
            db = mInstance.getWritableDatabase();
        }
    }

    public synchronized void closeConnecion() {
        if (mInstance != null) {
            mInstance.close();
            db.close();
            mInstance = null;
            db = null;
        }
    }

    @Override
    public synchronized void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL(QUERY_DELETE_TABLE_LOCATION);
            db.execSQL(QUERY_DELETE_TABLE_GERANT);
            db.execSQL(QUERY_DELETE_TABLE_VOITURE);
            db.execSQL(QUERY_DELETE_TABLE_AGENCE);
            db.execSQL(QUERY_DELETE_TABLE_CLIENT);
            onCreate(db);
        }
    }

    // Ajouter méthode select
    /*public static select (String table) {

        return ;
    }*/

}
