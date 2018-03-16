package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 14/03/2018.
 */

public class CoordonneesDataSource {

    //Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
                                    MySQLiteHelper.COLUMN_LAT,
                                    MySQLiteHelper.COLUMN_LNG};

    public CoordonneesDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Coordonnee createCoord (int lat, int lng) {
        ContentValues values = new ContentValues();
        values.put (MySQLiteHelper.COLUMN_LAT, lat);
        values.put(MySQLiteHelper.COLUMN_LNG, lng);

        long insertId = database.insert(MySQLiteHelper.TABLE_COORDONNEES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COORDONNEES, allColumns, MySQLiteHelper.COLUMN_ID
        + " = " + insertId, null, null ,null ,null);
        cursor.moveToFirst();
        Coordonnee newCoordonnee = cursorToCoord(cursor);
        cursor.close();

        return newCoordonnee;
    }

    public boolean isDBEmpty()
    {
        boolean isEmpty = true;
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + MySQLiteHelper.TABLE_COORDONNEES, null);
        if (cursor != null && cursor.moveToFirst()) {
            isEmpty = (cursor.getInt (0) == 0);
        }
        cursor.close();
        return isEmpty;
    }

    public Coordonnee updateCoord (long id, int lat, int lng){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_LAT, lat);
        values.put(MySQLiteHelper.COLUMN_LNG, lng);
        //String[] whereArgs = { String.valueOf(id)};

        database.update(MySQLiteHelper.TABLE_COORDONNEES, values, "_id=" + id, null );

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COORDONNEES, allColumns, MySQLiteHelper.COLUMN_ID
                + " = " + id, null, null ,null ,null);
        cursor.moveToFirst();
        Coordonnee updateCoordonnee = cursorToCoord(cursor);
        cursor.close();

        return updateCoordonnee;
    }

    public List<Coordonnee> getAllCoordonnees() {
        List<Coordonnee> coordonnees = new ArrayList<Coordonnee>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COORDONNEES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Coordonnee coordonnee = cursorToCoord(cursor);
            coordonnees.add(coordonnee);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return coordonnees;
    }

    public Coordonnee getCoord(long id) {
        Coordonnee coords = null;

        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM " + MySQLiteHelper.TABLE_COORDONNEES + " WHERE _id=" + id, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            coords = cursorToCoord(cursor);
        }

        cursor.close();
        return coords;
    }

    public void deleteAll()
    {
        database.delete(MySQLiteHelper.TABLE_COORDONNEES, null, null);
    }

    private Coordonnee cursorToCoord(Cursor cursor) {
        Coordonnee coordonnee = new Coordonnee();
        coordonnee.setId(cursor.getLong(0));
        coordonnee.setLat(cursor.getInt(1));
        coordonnee.setLng(cursor.getInt(2));

        return coordonnee;
    }
}
