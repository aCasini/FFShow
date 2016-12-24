package show.ff.kasoale.it.ffshow.engine.impl;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.utils.Utilis;

/**
 * Created by kasoale on 22/12/2016.
 */

public class RetrievalSteamingURL extends AsyncTask<String, Void, String> {

    private static Logger logger = Logger.getLogger("RetrievalSteamingURL");

    @Override
    protected String doInBackground(String... filmName) {
        try {

            String urlREST = "https://ancient-tor-87034.herokuapp.com/film?filmName="+filmName[0].replace(" ","+");
            URL url = new URL(urlREST);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            logger.info("URL: "+urlREST);
            logger.info("Response Code: "+conn.getResponseCode());

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            String jsonResponce = getStringFromInputStream(conn.getInputStream());

            Film film = Utilis.json2Film(jsonResponce);

            conn.disconnect();

            if(film != null){
                return film.getStreamingUrl();
            }
            return null;

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }

    }

    // convert InputStream to String
    protected static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
