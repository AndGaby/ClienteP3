package br.usjt.arqdesis.clientep3.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Anderson on 14/10/2016.
 */
public class ClienteRequester {
    OkHttpClient client = new OkHttpClient();
    ArrayList<Cliente> clientes;

    public Cliente[] getClientes(String url) throws IOException{
        clientes = new ArrayList<>();
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        String strJson = response.body().string();

        try {
            JSONArray jsonArray = new JSONArray(strJson);
            JSONObject jsonObject;
            for(int i = 0; i <jsonArray.length(); i++){
                jsonObject = (JSONObject) jsonArray.get(i);
                int id = jsonObject.getInt("id");
                String nome = jsonObject.getString("nome");
                String fone = jsonObject.getString("fone");
                String email = jsonObject.getString("email");

                clientes.add(new Cliente(id, nome, fone, email));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return clientes.toArray(new Cliente[0]);
    }

    public Bitmap getFoto(String url){
        Bitmap img = null;
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        InputStream is = response.body().byteStream();
        img = BitmapFactory.decodeStream(is);
        is.close();;

        return img;
    }
}
