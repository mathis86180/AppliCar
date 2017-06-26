package fr.eni.ecole.android.applivoiture.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class GerantDAO {
    public final static String DATABASE_NAME = "AppliCar.db";
    public final static Integer DATABASE_VERSION = 1;
    private final static String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            "GERANT( " +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NOM TEXT, " +
            "PRENOM TEXT)";
    private final static String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS GERANT";
    private final static String TABLE_NAME = "GERANT";
    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM, PRENOM FROM GERANT";
    private final static String QUERY_FIND_ONE = "SELECT ID, NOM, PRENOM FROM GERANT WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public GerantDAO(Context context)
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
}
