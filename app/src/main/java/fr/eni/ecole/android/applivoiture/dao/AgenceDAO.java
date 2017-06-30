package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.eni.ecole.android.applivoiture.model.Agence;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class AgenceDAO {

    private final static String TABLE_NAME = "AGENCE";
    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM FROM AGENCE";
    private final static String QUERY_FIND_ONE = "SELECT ID, NOM FROM AGENCE WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";

    public AgenceDAO() {}

    public static void insert(Context context, Agence a)
    {
        ContentValues values = new ContentValues();
        values.put("nom", a.getNom());
        Database.getInstance(context).getDb().insert(TABLE_NAME,null,values);
    }

    public static Agence findOneById(Integer idQuery, Context context){
        Agence a = null;
        String idS = idQuery.toString();
        Cursor c = Database.getInstance(context).getDb().rawQuery(QUERY_FIND_ONE,new String[] {idS});
        if(c.getCount() > 0) {
            c.moveToFirst();
            Integer id = c.getInt(c.getColumnIndex("id"));
            String nom = c.getString(c.getColumnIndex("nom"));
            a = new Agence(id, nom);
        }

        return a;
    }
}
