package sanitation.la.project.myapplication.helpers;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Totten on 4/5/2016.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sanitation.la.project.myapplication.formClass.Instantaneous;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "landfill";
    private static final String TABLE_INSTANTANEOUS = "InstantaneousData";

    private static final String KEY_INSTANTANEOUSDATAPK = "InstantaneousDataPK";
    private static final String KEY_SITEPK = "SitePK";
    private static final String KEY_EMPLOYEEPK = "EmployeePK";
    private static final String KEY_STARTTIME = "StartTime";
    private static final String KEY_FINISHTIME = "FinishTime";
    private static final String KEY_INSTRUMENTPK = "InstrumentPK";
    private static final String KEY_MAXCH = "maxCH";
    private static final String KEY_SITESAMPLINGPOINTPK = "SiteSamplingPointPK";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INSTANTANEOUS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_INSTANTANEOUS +
                "(" +
                KEY_INSTANTANEOUSDATAPK + " INTEGER PRIMARY KEY," + KEY_SITEPK + " INT," +
                KEY_EMPLOYEEPK + " INT," + KEY_STARTTIME + " DATETIME," + KEY_FINISHTIME + " " +
                "DATETIME," + KEY_INSTRUMENTPK + " INT," + KEY_MAXCH + " VARCHAR(45)," +
                KEY_SITESAMPLINGPOINTPK + " INT" + ")";
        db.execSQL(CREATE_INSTANTANEOUS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTANTANEOUS);
        onCreate(db);
    }

    // add
    public void addInstantaneous(Instantaneous instantaneous) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_INSTANTANEOUSDATAPK, instantaneous.getInstantaneousDataPK());
        values.put(KEY_SITEPK, instantaneous.getSitePK());
        values.put(KEY_EMPLOYEEPK, instantaneous.getEmployeePK());
        values.put(KEY_STARTTIME, instantaneous.getStartTime());
        values.put(KEY_FINISHTIME, instantaneous.getFinishTime());
        values.put(KEY_INSTRUMENTPK, instantaneous.getInstrumentPK());
        values.put(KEY_MAXCH, instantaneous.getMaxCH());
        values.put(KEY_SITESAMPLINGPOINTPK, instantaneous.getSiteSamplingPointPK());

        db.insert(TABLE_INSTANTANEOUS, null, values);
        db.close();
    }

    // single data - need fix
//    Instantaneous getInstantaneous(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_INSTANTANEOUS, new String[] { KEY_INSTANTANEOUSDATAPK,
//                        KEY_SITEPK, KEY_EMPLOYEEPK, KEY_STARTTIME, KEY_FINISHTIME,
//                        KEY_INSTRUMENTPK, KEY_MAXCH, KEY_SITESAMPLINGPOINTPK },
//                KEY_INSTANTANEOUSDATAPK + "=?", new String[] { String.valueOf(id)}, null, null, null);
//        if(cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        Instantaneous instantaneous = new Instantaneous(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString
//                (4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor
//                .getString(8));
//
//        return instantaneous;
//    }

    // all data
    public List<Instantaneous> getAllInstantaneous() {
        List<Instantaneous> instantaneousList = new ArrayList<Instantaneous>();

        String selectQuery = "SELECT * FROM " + TABLE_INSTANTANEOUS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Instantaneous instantaneous = new Instantaneous();

                instantaneous.setInstantaneousDataPK(Integer.parseInt(cursor.getString(0)));
                instantaneous.setSitePK(Integer.parseInt(cursor.getString(1)));
                instantaneous.setEmployeePK(Integer.parseInt(cursor.getString(2)));
                instantaneous.setStartTime(Integer.parseInt(cursor.getString(3)));
                instantaneous.setFinishTime(Integer.parseInt(cursor.getString(4)));
                instantaneous.setInstrumentPK(Integer.parseInt(cursor.getString(5)));
                instantaneous.setMaxCH(Double.parseDouble(cursor.getString(6)));
                instantaneous.setSiteSamplingPointPK(Integer.parseInt(cursor.getString(7)));

                instantaneousList.add(instantaneous);
            } while(cursor.moveToNext());
        }

        return instantaneousList;
    }

    // update single data
    public int updateInstantaneous(Instantaneous instantaneous) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INSTANTANEOUSDATAPK, instantaneous.getInstantaneousDataPK());
        values.put(KEY_SITEPK, instantaneous.getSitePK());
        values.put(KEY_EMPLOYEEPK, instantaneous.getEmployeePK());
        values.put(KEY_STARTTIME, instantaneous.getStartTime());
        values.put(KEY_FINISHTIME, instantaneous.getFinishTime());
        values.put(KEY_INSTRUMENTPK, instantaneous.getInstrumentPK());
        values.put(KEY_MAXCH, instantaneous.getMaxCH());
        values.put(KEY_SITESAMPLINGPOINTPK, instantaneous.getSiteSamplingPointPK());

        // update row
        return db.update(TABLE_INSTANTANEOUS, values, KEY_INSTANTANEOUSDATAPK + " = ?", new
                String[] { String.valueOf(instantaneous.getInstantaneousDataPK())});
    }

    // date single data
    public void deleteInstantaneous(Instantaneous instantaneous) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_INSTANTANEOUS, KEY_INSTANTANEOUSDATAPK + " = ?", new String[] { String
                .valueOf(instantaneous.getInstantaneousDataPK())});
        db.close();
    }

    // getting data count
    public int getInstantaneousCount() {
        String countQuery = "SELECT * FROM " + TABLE_INSTANTANEOUS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int c = cursor.getCount();
        cursor.close();

        // return count
        return c;
    }
}
