package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

    public Coordonnee updateCoord (long id, int lat, int lng){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_LAT, lat);
        values.put(MySQLiteHelper.COLUMN_LNG, lng);
        String[] whereArgs = { String.valueOf(id)};

        database.update(MySQLiteHelper.TABLE_COORDONNEES, values, "id=?", whereArgs );
    }

    private Coordonnee cursorToCoord(Cursor cursor) {
        Coordonnee coordonnee = new Coordonnee();
        coordonnee.setId(cursor.getLong(0));
        coordonnee.setLat(cursor.getInt(1));
        coordonnee.setLng(cursor.getInt(2));

        return coordonnee;
    }
}
