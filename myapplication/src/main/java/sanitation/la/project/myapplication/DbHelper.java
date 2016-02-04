package sanitation.la.project.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by saul on 2/2/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    public final String TAG = getClass().getSimpleName();

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lacdemo.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + lacDbEntry.mEntry.TABLE_NAME + " (" +
                    lacDbEntry.mEntry._ID + " INTEGER PRIMARY KEY," +
                    lacDbEntry.mEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    lacDbEntry.mEntry.COLUMN_NAME_CONTENT + TEXT_TYPE + COMMA_SEP +
                    lacDbEntry.mEntry.COLUMN_NAME_TITLE + TEXT_TYPE  + ") ";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + lacDbEntry.mEntry.TABLE_NAME;



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating Db");
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
