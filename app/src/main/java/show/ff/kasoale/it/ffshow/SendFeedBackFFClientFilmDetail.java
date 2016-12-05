package show.ff.kasoale.it.ffshow;

import android.os.AsyncTask;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.impl.FFClient;
import show.ff.kasoale.it.ffshow.engine.impl.FFClientFilmDetail;

/**
 * Created by acasini on 05/12/16.
 */

public class SendFeedBackFFClientFilmDetail extends AsyncTask<HashMap<String,String>, Void, String> {

    private static Logger logger = Logger.getLogger("SendFeedBackFFClientFilmDetail");
    private ObjectMapper mapper;

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        logger.info("Do In backgroud");

        HashMap<String, String> queryParamsMap = hashMaps[0];

        FFClientFilmDetail ffClientFilmDetial = new FFClientFilmDetail();
        logger.info("Invoking url ...");

        String outJson = ffClientFilmDetial.invokeWS(queryParamsMap);

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
