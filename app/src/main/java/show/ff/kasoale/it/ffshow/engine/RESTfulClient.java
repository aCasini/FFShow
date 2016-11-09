package show.ff.kasoale.it.ffshow.engine;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kasoale on 05/11/2016.
 */

public interface RESTfulClient {

    public HttpURLConnection getHttpConnectio(URL url);

    public String buildUrlConnection(String baseUrl, Map<String, String> queryParameters);

    public void closeHttpConnection();
}
