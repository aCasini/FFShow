package show.ff.kasoale.it.ffshow;

import android.os.AsyncTask;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.impl.FFClient;

/**
 * Created by kasoale on 05/11/2016.
 */

public class SendFeedBackFFClient extends AsyncTask<HashMap<String,String>, Void, String>{

    private static Logger logger = Logger.getLogger("SendFeedBackFFClient");
    private ObjectMapper mapper;

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        logger.info("Do In backgroud");

        HashMap<String, String> queryParamsMap = hashMaps[0];

        FFClient ffClient = new FFClient();
        logger.info("Invoking url ...");

        String outJson = ffClient.invokeWS(queryParamsMap);

        logger.info("**** "+outJson);

        if(outJson == null || outJson.trim().equals("[]")) {
            return null;
        }

        return outJson;
    }

    @Override
    protected void onPostExecute(String message) {
        //process message
    }
}
