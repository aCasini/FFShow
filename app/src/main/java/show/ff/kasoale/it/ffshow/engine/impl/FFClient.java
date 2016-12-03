package show.ff.kasoale.it.ffshow.engine.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.AbstractRESTfulClient;

/**
 * Created by kasoale on 05/11/2016.
 */

public class FFClient extends AbstractRESTfulClient{

    private final String HTTP_URL = "https://ancient-tor-87034.herokuapp.com/films";
    private HashMap<String, String> queryParametersMap;

    private static Logger logger = Logger.getLogger("FFClient");

    public String invokeWS(Map<String, String> queryParametersMap){
        try {
            String wsURL = super.buildUrlConnection(HTTP_URL, queryParametersMap);
            URL url = new URL(wsURL);
            HttpURLConnection httpURLConnection = super.getHttpConnectio(url);
            String jsonResponce;

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            if(httpURLConnection.getResponseCode() != 200){
                throw new RuntimeException("Failed: HTTP Error code = "+httpURLConnection.getResponseCode());
            }else{
                //BufferedReader br = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
                jsonResponce = getStringFromInputStream(httpURLConnection.getInputStream());
                logger.info("Responce Message: "+jsonResponce);


                //logger.info("Output from Server .... \n");
                //while ((outputJson = br.readLine()) != null) {
                //    logger.info(outputJson);
                //}
                logger.info("Service Invoked");
            }

            //logger.log(Level.INFO, httpURLConnection.getResponseMessage());

            super.closeHttpConnection();

            return jsonResponce;

        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.SEVERE, "Error Occured during the invocation webService");

            return null;
        }
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

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
