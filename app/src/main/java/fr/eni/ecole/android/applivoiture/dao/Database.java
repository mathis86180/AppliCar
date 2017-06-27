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
    private static final String TBL_GERANT = "gerant";
    private static final String TBL_AGENCE = "agence";
    private static final String TBL_VOITURE = "voiture";

    // table column name
    private static final String GERANT_ID = "id";
    private static final String GERANT_NOM = "nom";
    private static final String GERANT_PRENOM = "prenom";
    private static final String GERANT_MAIL = "mail";
    private static final String GERANT_PASSWORD = "password";

    private static final String AGENCE_ID = "id";
    private static final String AGENCE_NOM = "nom";
    private static final String AGENCE_GERANT_ID = "id_gerant";

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

    // requetes
    private final static String QUERY_DELETE_TABLE_GERANT = "DROP TABLE IF EXISTS " + TBL_GERANT;
    private final static String QUERY_DELETE_TABLE_AGENCE = "DROP TABLE IF EXISTS " + TBL_AGENCE;
    private final static String QUERY_DELETE_TABLE_VOITURE = "DROP TABLE IF EXISTS " + TBL_VOITURE;

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

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_GERANT + " (" +
                GERANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                GERANT_NOM + " VARCHAR," +
                GERANT_PRENOM + " VARCHAR," +
                GERANT_MAIL + " VARCHAR," +
                GERANT_PASSWORD + " VARCHAR" +
                "); ");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_AGENCE + " ( " +
                AGENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AGENCE_NOM + " VARCHAR, " +
                AGENCE_GERANT_ID + " INTEGER, " +
                "FOREIGN KEY (" + AGENCE_GERANT_ID + ") REFERENCES " + TBL_GERANT + "(" + GERANT_ID + ")" +
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
            db.execSQL(QUERY_DELETE_TABLE_GERANT);
            db.execSQL(QUERY_DELETE_TABLE_AGENCE);
            db.execSQL(QUERY_DELETE_TABLE_VOITURE);
            onCreate(db);
        }
    }

    // Ajouter méthode select
    /*public static select (String table) {

        return ;
    }*/

}
