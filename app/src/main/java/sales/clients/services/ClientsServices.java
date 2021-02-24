package sales.clients.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import system.Constants;
import system.soap.MarshalDouble;

public class ClientsServices {

    /**
     * get clients list
     * @param seller
     * @param timestamp
     * @param context
     * @return
     */
    public static JSONArray loadClients(int seller, double timestamp, Context context){
        // config manager
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String serverIp = settings.getString("server_ip", Constants.SERVER_IP);

        // Config method, namespace and action from webservices
        final String wsMethod = "loadClients";
        String namespace = "http://services.clients.sales/";
        String accionSoap = namespace + wsMethod;
        String url = "http://" + serverIp + ":8080/WebServicesSales/Clients";
        JSONArray result = new JSONArray();

        try {
            // Do Soap Request
            SoapObject request = new SoapObject(namespace, wsMethod);
            request.addProperty("seller", seller);
            request.addProperty("timestamp", timestamp);

            // complete message
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.implicitTypes = true;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            envelope.setOutputSoapObject(request);

            // create transport
            HttpTransportSE transport = new HttpTransportSE(url);
            transport.call(accionSoap, envelope);

            // parse result as JSON
            SoapPrimitive resultMessage = (SoapPrimitive) envelope.getResponse();
            
            result = new JSONArray(resultMessage.toString());

        } catch (Exception e) {
            Log.e("ERROR", e.toString());
        }

        return result;
    }

}
