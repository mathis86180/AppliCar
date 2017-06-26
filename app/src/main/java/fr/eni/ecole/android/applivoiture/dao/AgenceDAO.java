package fr.eni.ecole.android.applivoiture.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class AgenceDAO {
    public final static String DATABASE_NAME = "AppliCar.db";
    public final static Integer DATABASE_VERSION = 1;
    private final static String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            "AGENCE( " +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NOM TEXT, " +
            "ID_GERANT INTEGER, " +
            "FOREIGN KEY (ID_GERANT) REFERENCES GERANT(ID))";
    private final static String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS AGENCE";
    private final static String TABLE_NAME = "AGENCE";
    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM, ID_GERANT FROM AGENCE";
    private final static String QUERY_FIND_ONE = "SELECT ID, NOM, ID_GERANT FROM AGENCE WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public AgenceDAO(Context context)
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
