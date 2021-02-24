package sales.clients.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "clients.db";

    public ClientsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create sqlite database
        sqLiteDatabase.execSQL("CREATE TABLE " + ClientsContract.ClientsEntry.TABLE_NAME + " ("
                + ClientsContract.ClientsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ClientsContract.ClientsEntry.ID + " INTEGER NOT NULL,"
                + ClientsContract.ClientsEntry.CODE + " TEXT,"
                + ClientsContract.ClientsEntry.NAME + " TEXT,"
                + ClientsContract.ClientsEntry.FANTASY_NAME + " TEXT,"
                + ClientsContract.ClientsEntry.LOCATION + " TEXT,"
                + ClientsContract.ClientsEntry.POSTAL_CODE + " TEXT,"
                + ClientsContract.ClientsEntry.ADDRESS + " TEXT,"
                + ClientsContract.ClientsEntry.PHONE + " TEXT,"
                + ClientsContract.ClientsEntry.MAIL + " TEXT,"
                + ClientsContract.ClientsEntry.REGULAR_DISCOUNT + " DOUBLE NOT NULL,"
                + ClientsContract.ClientsEntry.STATE + " INTEGER NOT NULL,"
                + ClientsContract.ClientsEntry.TIMESTAMP + " DOUBLE NOT NULL,"
                + "UNIQUE (" + ClientsContract.ClientsEntry.ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // delete current table before update
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ClientsContract.ClientsEntry.TABLE_NAME);

        // recreate table
        sqLiteDatabase.execSQL("CREATE TABLE " + ClientsContract.ClientsEntry.TABLE_NAME + " ("
                + ClientsContract.ClientsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ClientsContract.ClientsEntry.ID + " INTEGER NOT NULL,"
                + ClientsContract.ClientsEntry.CODE + " TEXT,"
                + ClientsContract.ClientsEntry.NAME + " TEXT,"
                + ClientsContract.ClientsEntry.FANTASY_NAME + " TEXT,"
                + ClientsContract.ClientsEntry.LOCATION + " TEXT,"
                + ClientsContract.ClientsEntry.POSTAL_CODE + " TEXT,"
                + ClientsContract.ClientsEntry.ADDRESS + " TEXT,"
                + ClientsContract.ClientsEntry.PHONE + " TEXT,"
                + ClientsContract.ClientsEntry.MAIL + " TEXT,"
                + ClientsContract.ClientsEntry.REGULAR_DISCOUNT + " DOUBLE NOT NULL,"
                + ClientsContract.ClientsEntry.STATE + " INTEGER NOT NULL,"
                + ClientsContract.ClientsEntry.TIMESTAMP + " DOUBLE NOT NULL,"
                + "UNIQUE (" + ClientsContract.ClientsEntry.ID + "))");
    }
}
