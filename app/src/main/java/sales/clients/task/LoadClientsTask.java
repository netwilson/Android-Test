package sales.clients.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import sales.clients.dao.ClientsDao;
import sales.clients.services.ClientsServices;

public class LoadClientsTask extends AsyncTask<Object, Void, Void> {

    // 2- Add sync task when the APP is open

    @Override
    protected Void doInBackground(Object... objects) {
        Context context = (Context) objects[0];

        // call webservice state
        JSONArray data = ClientsServices.loadClients(0, 0, context);

        for (int i=0; i < data.length(); i++) {
            try {
                JSONObject client = data.getJSONObject(i);

                ClientsDao.saveClientsSync(context, client);
            }catch (Exception e){
                Log.e("SYNC", e.getLocalizedMessage());
            }
        }

        return null;
    }
}
