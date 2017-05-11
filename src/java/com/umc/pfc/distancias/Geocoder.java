package com.umc.pfc.distancias;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.umc.pfc.entidades.Coordenada;
import com.umc.pfc.entidades.Endereco;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author danigpam
 */
public class Geocoder {

    //Link para solicitar API KEY:
    //https://developers.google.com/maps/documentation/javascript/geocoding
    private static final String API_KEY = "";

    public Coordenada getCoordenada(Endereco endereco) {
        HttpURLConnection connection = null;
        URL serverAddress = null;

        try {
            serverAddress = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" +URLEncoder.encode(endereco.toString(), "UTF-8") + "&key=" + API_KEY);
            connection = null;

            //Set up the initial connection
            connection = (HttpURLConnection) serverAddress.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);

            connection.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }
            String responseText = sb.toString();
            
            JsonElement jelement = new JsonParser().parse(responseText);
            JsonObject  jobject = jelement.getAsJsonObject();
            JsonArray results = jobject.getAsJsonArray("results");
            JsonObject endereco1 = results.get(0).getAsJsonObject();
            JsonObject geometry = endereco1.get("geometry").getAsJsonObject();
            JsonObject location = geometry.get("location").getAsJsonObject();

            String lat = location.get("lat").toString();
            String lng = location.get("lng").toString();
            
            Coordenada coordenada = new Coordenada(Double.parseDouble(lat), Double.parseDouble(lng));
            return coordenada;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
