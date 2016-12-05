package show.ff.kasoale.it.ffshow.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kasoale on 05/11/2016.
 */

public abstract class AbstractRESTfulClient implements RESTfulClient{

    private HttpURLConnection httpURLConnection = null;
    private Logger logger = Logger.getLogger("AbstractRESTfulClient");

    @Override
    public HttpURLConnection getHttpConnectio(URL url) {
        if(httpURLConnection == null && url != null){
            try {
                logger.info("Create a new HTTP Connection");
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }catch (Exception e){
                logger.log(Level.SEVERE, "ERROR: not able to instantiate a new HTTP Connection");
                e.printStackTrace();
            }
        }else{
            logger.log(Level.SEVERE, "ERROR: URL can be null");
        }

        return httpURLConnection;
    }

    @Override
    public void closeHttpConnection() {
        if(httpURLConnection != null){
            httpURLConnection.disconnect();
        }
    }

    @Override
    public String buildUrlConnection(String baseUrl, Map<String, String> queryParameters) {
        Set<String> keySet = queryParameters.keySet();

        if(baseUrl == null){
            logger.log(Level.SEVERE, "Impossible building the URL request, baseUrl is NULL");
            return null;
        }

        int i = 0;
        int size = keySet.size()-1;
        baseUrl += "?";

        for (String key: keySet) {
            if(i < size) {
                baseUrl += key + "=" + queryParameters.get(key) + "&";
            }else{
                baseUrl += key + "=" + queryParameters.get(key);
            }
        }

        logger.log(Level.INFO, "URL: "+baseUrl);
        return baseUrl;
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
