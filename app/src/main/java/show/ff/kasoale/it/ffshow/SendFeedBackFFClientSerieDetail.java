package show.ff.kasoale.it.ffshow;

import android.os.AsyncTask;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.impl.FFClientFilmDetail;
import show.ff.kasoale.it.ffshow.engine.impl.FFClientSerieDetail;

/**
 * Created by kasoale on 10/12/2016.
 */

public class SendFeedBackFFClientSerieDetail extends AsyncTask<HashMap<String,String>, Void, String> {

    private static Logger logger = Logger.getLogger("SendFeedBackFFClientSerieDetail");
    private ObjectMapper mapper;

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        logger.info("Do In backgroud");

        HashMap<String, String> queryParamsMap = hashMaps[0];

        FFClientSerieDetail ffClientSerieDetial = new FFClientSerieDetail();
        logger.info("Invoking url ...");

        String outJson = ffClientSerieDetial.invokeWS(queryParamsMap);

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
