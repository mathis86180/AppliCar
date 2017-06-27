package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.eni.ecole.android.applivoiture.model.Agence;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class AgenceDAO {

    private final static String TABLE_NAME = "AGENCE";
    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM, ID_GERANT FROM AGENCE";
    private final static String QUERY_FIND_ONE = "SELECT ID, NOM, ID_GERANT FROM AGENCE WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";



    public AgenceDAO() {}

    public static void insert(Context context, Agence a)
    {
        ContentValues values = new ContentValues();
        values.put("nom", a.getNom());
        values.put("id_gerant", a.getGerant().getId());
        Database.getInstance(context).getDb().insert("agence",null,values);
    }
}
