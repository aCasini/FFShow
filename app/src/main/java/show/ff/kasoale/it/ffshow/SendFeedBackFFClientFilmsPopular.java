package show.ff.kasoale.it.ffshow;

import android.os.AsyncTask;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.impl.FFClientFilmsPopular;
import show.ff.kasoale.it.ffshow.engine.impl.FFClientFilmsTopRated;

/**
 * Created by kasoale on 22/12/2016.
 */

public class SendFeedBackFFClientFilmsPopular extends AsyncTask<HashMap<String,String>, Void, String> {

    private static Logger logger = Logger.getLogger("SendFeedBackFFClientFilmsPopular");
    private ObjectMapper mapper;

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        logger.info("Do In backgroud");

        HashMap<String, String> queryParamsMap = hashMaps[0];

        FFClientFilmsPopular ffClientFilmsPopular = new FFClientFilmsPopular();
        logger.info("Invoking url ...");

        String outJson = ffClientFilmsPopular.invokeWS(queryParamsMap);

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
