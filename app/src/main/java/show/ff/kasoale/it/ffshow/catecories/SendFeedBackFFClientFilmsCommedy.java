package show.ff.kasoale.it.ffshow.catecories;

import android.os.AsyncTask;


import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.impl.categories.FFClientFilmsCommedy;

/**
 * Created by kasoale on 20/03/2017.
 */

public class SendFeedBackFFClientFilmsCommedy extends AsyncTask<HashMap<String,String>, Void, String> {

    private static Logger logger = Logger.getLogger("SendFeedBackFFClientFilmsCommedy");

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        logger.info("Do In backgroud >> calls the Category Commedy Films");

        HashMap<String, String> queryParamsMap = hashMaps[0];

        FFClientFilmsCommedy ffClientFilmsCommedy = new FFClientFilmsCommedy();
        logger.info("Invoking url ...");

        String outJson = ffClientFilmsCommedy.invokeWS(queryParamsMap);

        if(outJson == null || outJson.trim().equals("[]")) {
            return null;
        }

        return outJson;
    }
}
