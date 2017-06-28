package fr.eni.ecole.android.applivoiture.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.eni.ecole.android.applivoiture.model.Gerant;

/**
 * Created by mseigle2016 on 26/06/2017.
 */

public class GerantDAO {
    public final static Integer DATABASE_VERSION = 1;

    private final static String QUERY_SELECT_ALL = "SELECT ID, NOM, PRENOM, MAIL FROM GERANT";
    private final static String QUERY_FIND_ONE_BY_ID = "SELECT ID, NOM, PRENOM, MAIL FROM GERANT WHERE ID = ?";
    private final static String QUERY_GET_ONE = "ID = ?";
    private static final String QUERY_FIND_BY_MAIL = "SELECT ID, NOM, PRENOM, MAIL, PASSWORD FROM GERANT " +
            "WHERE MAIL = ? ";
    private static final String QUERY_FIND_BY_MAIL_MDP = "SELECT ID, NOM, PRENOM, MAIL, PASSWORD FROM GERANT " +
            "WHERE MAIL = ? " +
            "AND PASSWORD = ?";

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public GerantDAO() {}

    public static void insert(Context context, Gerant g)
    {
        ContentValues values = new ContentValues();
        values.put("nom", g.getNom());
        values.put("prenom", g.getPrenom());
        values.put("mail", g.getMail());
        values.put("password", g.getPassword());
        values.put("id_agence",g.getAgence().getId());
        Database.getInstance(context).getDb().insert("gerant",null,values);
    }

    public static Gerant findByMail(String mailQuery, Context context) {
        Gerant g = null;
        Cursor c = Database.getInstance(context).getDb().rawQuery(QUERY_FIND_BY_MAIL, new String[]{mailQuery});
        if (c.getCount() > 0) {
            c.moveToFirst();

            Integer id = c.getInt(c.getColumnIndex("id"));
            String nom = c.getString(c.getColumnIndex("nom"));
            String prenom = c.getString(c.getColumnIndex("prenom"));
            String mail = c.getString(c.getColumnIndex("mail"));
            String password = c.getString(c.getColumnIndex("password"));

            g = new Gerant(id,nom,prenom,mail,password);
        }
        return g;
    }

    public static Gerant findByMailMdp(String mailQuery, String mdp,Context context) {
        Gerant g = null;
        Cursor c = Database.getInstance(context).getDb().rawQuery(QUERY_FIND_BY_MAIL_MDP, new String[]{mailQuery, mdp});
        if (c.getCount() > 0) {
            c.moveToFirst();

            Integer id = c.getInt(c.getColumnIndex("id"));
            String nom = c.getString(c.getColumnIndex("nom"));
            String prenom = c.getString(c.getColumnIndex("prenom"));
            String mail = c.getString(c.getColumnIndex("mail"));
            String password = c.getString(c.getColumnIndex("password"));

            g = new Gerant(id,nom,prenom,mail,password);
        }
        return g;
    }

    public void insert(Gerant g,Context context)
    {
        ContentValues values = new ContentValues();
        values.put("NOM", g.getNom());
        values.put("PRENOM", g.getPrenom());
        values.put("MAIL", g.getMail());
        values.put("PASSWORD", g.getPassword());
        values.put("id_agence", g.getAgence().getId());
        Database.getInstance(context).getDb().insert("gerant", null, values);
    }
}
