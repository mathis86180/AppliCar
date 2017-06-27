package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.eni.ecole.android.applivoiture.model.Gerant;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class GerantDAO {
    public final static Integer DATABASE_VERSION = 1;

    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM, PRENOM, MAIL FROM GERANT";
    private final static String QUERY_FIND_ONE = "SELECT ID, NOM, PRENOM, MAIL FROM GERANT WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public GerantDAO() {}

    public static void insert(Context context, Gerant g)
    {
        ContentValues values = new ContentValues();
        values.put("nom", "test");
        values.put("prenom", "test");
        values.put("mail", "test@test.fr");
        Database.getInstance(context).getDb().insert("gerant",null,values);
    }

}
