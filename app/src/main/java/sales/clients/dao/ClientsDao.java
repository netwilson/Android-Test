package sales.clients.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sales.clients.transport.Clients;

/**
 * Android Dao
 *
 * http://www.sgoliver.net/blog/bases-de-datos-en-android-i-primeros-pasos/
 * http://www.sgoliver.net/blog/bases-de-datos-en-android-ii-insertaractualizareliminar/
 * http://www.sgoliver.net/blog/bases-de-datos-en-android-iii-consultarrecuperar-registros/
 */
public class ClientsDao {

    // 1- Add field recived from WS JSON "cli_notes" to the table and its load and save query

    /**
     * save client from webservices
     * @param context
     * @param clients
     * @throws JSONException
     */
    public static void saveClientsSync(Context context, JSONObject clients) throws JSONException {
        // get writable access
        ClientsDbHelper dbClients = new ClientsDbHelper(context);
        SQLiteDatabase db = dbClients.getWritableDatabase();

        if(db != null){
            // user UPSERT to update sync
            Object[] args = new Object[] {
                    clients.getInt(ClientsContract.ClientsEntry.ID),
                    clients.getString(ClientsContract.ClientsEntry.CODE),
                    clients.getString(ClientsContract.ClientsEntry.NAME),
                    clients.getString(ClientsContract.ClientsEntry.FANTASY_NAME),
                    clients.getString(ClientsContract.ClientsEntry.LOCATION),
                    clients.getString(ClientsContract.ClientsEntry.POSTAL_CODE),
                    clients.getString(ClientsContract.ClientsEntry.ADDRESS),
                    clients.getString(ClientsContract.ClientsEntry.PHONE),
                    clients.getString(ClientsContract.ClientsEntry.MAIL),
                    clients.getDouble(ClientsContract.ClientsEntry.REGULAR_DISCOUNT),
                    clients.getInt(ClientsContract.ClientsEntry.STATE),
                    clients.getDouble(ClientsContract.ClientsEntry.TIMESTAMP)};

            db.execSQL("INSERT OR REPLACE INTO " + ClientsContract.ClientsEntry.TABLE_NAME + " ("
                            + ClientsContract.ClientsEntry.ID + ","
                            + ClientsContract.ClientsEntry.CODE + ","
                            + ClientsContract.ClientsEntry.NAME + ","
                            + ClientsContract.ClientsEntry.FANTASY_NAME + ","
                            + ClientsContract.ClientsEntry.LOCATION + ","
                            + ClientsContract.ClientsEntry.POSTAL_CODE + ","
                            + ClientsContract.ClientsEntry.ADDRESS + ","
                            + ClientsContract.ClientsEntry.PHONE + ","
                            + ClientsContract.ClientsEntry.MAIL + ","
                            + ClientsContract.ClientsEntry.REGULAR_DISCOUNT + ","
                            + ClientsContract.ClientsEntry.STATE + ","
                            + ClientsContract.ClientsEntry.TIMESTAMP
                            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    , args);
            db.close();
        }
    }

    /**
     * load clients
     * @param context
     * @param client
     * @return
     */
    public static Clients loadClient(Context context, Clients client){
        // get read access
        ClientsDbHelper dbClients = new ClientsDbHelper(context);
        SQLiteDatabase db = dbClients.getReadableDatabase();

        String[] args = new String[] {String.valueOf(client.getId())};
        Cursor c = db.rawQuery("SELECT "
                + ClientsContract.ClientsEntry.ID + ","
                + ClientsContract.ClientsEntry.CODE + ","
                + ClientsContract.ClientsEntry.NAME + ","
                + ClientsContract.ClientsEntry.FANTASY_NAME + ","
                + ClientsContract.ClientsEntry.LOCATION + ","
                + ClientsContract.ClientsEntry.POSTAL_CODE + ","
                + ClientsContract.ClientsEntry.ADDRESS + ","
                + ClientsContract.ClientsEntry.PHONE + ","
                + ClientsContract.ClientsEntry.MAIL + ","
                + ClientsContract.ClientsEntry.REGULAR_DISCOUNT + ","
                + ClientsContract.ClientsEntry.STATE + ","
                + ClientsContract.ClientsEntry.TIMESTAMP + " FROM "
                + ClientsContract.ClientsEntry.TABLE_NAME + " WHERE "
                + ClientsContract.ClientsEntry.ID + " = ? ORDER BY "
                + ClientsContract.ClientsEntry.CODE
            , args);

        // the we complete the data using the cursor
        if (c.moveToFirst()) {
            do {
                client.setId(c.getInt(0));
                client.setCode(c.getString(1));
                client.setName(c.getString(2));
                client.setFantasyName(c.getString(3));
                client.setLocation(c.getString(4));
                client.setPostalCode(c.getString(5));
                client.setAddress(c.getString(6));
                client.setPhone(c.getString(7));
                client.setMail(c.getString(8));
                client.setRegularDiscount(c.getDouble(9));
                client.setState(c.getInt(10));
                client.setTimestamp(c.getDouble(10));

            } while(c.moveToNext());
        }

        c.close();
        db.close();

        return client;
    }

    /**
     * load clients list
     * @param context
     * @return
     */
    public static List<Clients> loadClientsList(Context context){
        // get read access
        ClientsDbHelper dbClients = new ClientsDbHelper(context);
        SQLiteDatabase db = dbClients.getReadableDatabase();
        List<Clients> clientsList = new ArrayList<>();

        String[] args = new String[] {};
        Cursor c = db.rawQuery("SELECT "
                        + ClientsContract.ClientsEntry.ID + ","
                        + ClientsContract.ClientsEntry.CODE + ","
                        + ClientsContract.ClientsEntry.NAME + ","
                        + ClientsContract.ClientsEntry.FANTASY_NAME + ","
                        + ClientsContract.ClientsEntry.LOCATION + ","
                        + ClientsContract.ClientsEntry.POSTAL_CODE + ","
                        + ClientsContract.ClientsEntry.ADDRESS + ","
                        + ClientsContract.ClientsEntry.PHONE + ","
                        + ClientsContract.ClientsEntry.MAIL + ","
                        + ClientsContract.ClientsEntry.REGULAR_DISCOUNT + ","
                        + ClientsContract.ClientsEntry.STATE + ","
                        + ClientsContract.ClientsEntry.TIMESTAMP + " FROM "
                        + ClientsContract.ClientsEntry.TABLE_NAME + " ORDER BY "
                        + ClientsContract.ClientsEntry.CODE
                , args);

        // the we complete the data using the cursor
        if (c.moveToFirst()) {
            do {
                Clients client = new Clients();
                client.setId(c.getInt(0));
                client.setCode(c.getString(1));
                client.setName(c.getString(2));
                client.setFantasyName(c.getString(3));
                client.setLocation(c.getString(4));
                client.setPostalCode(c.getString(5));
                client.setAddress(c.getString(6));
                client.setPhone(c.getString(7));
                client.setMail(c.getString(8));
                client.setRegularDiscount(c.getDouble(9));
                client.setState(c.getInt(10));
                client.setTimestamp(c.getDouble(10));

                clientsList.add(client);
            } while(c.moveToNext());
        }

        c.close();
        db.close();

        return clientsList;
    }

}
