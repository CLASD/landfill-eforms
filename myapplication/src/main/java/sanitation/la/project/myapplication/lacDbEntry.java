package sanitation.la.project.myapplication;

import android.provider.BaseColumns;

/**
 * Created by saul on 2/2/16.
 */
public final class lacDbEntry {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public lacDbEntry() {}

    /* Inner class that defines the table contents */
    public static abstract class mEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "dat";
        public static final String COLUMN_NAME_NULLABLE = "NILL";
    }
}