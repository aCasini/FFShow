package show.ff.kasoale.it.ffshow;

import android.os.AsyncTask;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.impl.FFClient;
import show.ff.kasoale.it.ffshow.engine.impl.FFClientSerie;

/**
 * Created by kasoale on 24/11/2016.
 */

public class SendFeedBackSerieFFClient extends AsyncTask<HashMap<String,String>, Void, String> {

    private static Logger logger = Logger.getLogger("SendFeedBackSerieFFClient");
    private ObjectMapper mapper;

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        logger.info("Do In backgroud");

        HashMap<String, String> queryParamsMap = hashMaps[0];

        FFClientSerie ffClientSerie = new FFClientSerie();
        logger.info("Invoking url ...");

        String outJson = ffClientSerie.invokeWS(queryParamsMap);

        logger.info("**** "+outJson);

        if(outJson.trim().equals("[]")){
            return null;
        }

        return outJson;
    }

    @Override
    protected void onPostExecute(String message) {
        //process message
    }

}
