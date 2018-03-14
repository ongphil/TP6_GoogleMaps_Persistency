package persistency.googlemap.tp6.android.tp6_googlemaps_persistency;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alex on 14/03/2018.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COORDONNEES = "coordonnees";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LNG = "lng";

    private static final String DATABASE_NAME = "coordonnees.db";
    private static final int DATABASE_VERSION = 1;

    //Commande pour créer la table
    private static final String DATABASE_CREATE = "create table " + TABLE_COORDONNEES
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_LAT + " integer, "
            + COLUMN_LNG + "integer);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "upgrading database from version " + oldVersion
        + "to " + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COORDONNEES);
        onCreate(sqLiteDatabase);
    }
}
