package show.ff.kasoale.it.ffshow.engine.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.AbstractRESTfulClient;

/**
 * Created by kasoale on 10/12/2016.
 */

public class FFClientSerieDetail extends AbstractRESTfulClient {

    private static Logger logger = Logger.getLogger("FFClientSerieDetail");
    private HashMap<String, String> queryParametersMap;

    private final String HTTP_URL = "https://ancient-tor-87034.herokuapp.com/serie/detail";

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
                jsonResponce = getStringFromInputStream(httpURLConnection.getInputStream());
                logger.info("Responce Message: "+jsonResponce);
                logger.info("Service Invoked");
            }

            super.closeHttpConnection();

            return jsonResponce;

        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.SEVERE, "Error Occured during the invocation webService");

            return null;
        }

    }

}
