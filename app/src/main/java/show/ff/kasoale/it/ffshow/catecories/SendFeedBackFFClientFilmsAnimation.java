package show.ff.kasoale.it.ffshow.catecories;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.engine.impl.categories.FFClientFilmsAnimation;

/**
 * Created by kasoale on 21/03/2017.
 */

public class SendFeedBackFFClientFilmsAnimation extends AsyncTask<HashMap<String,String>, Void, String> {

    private static Logger logger = Logger.getLogger("SendFeedBackFFClientFilmsAnimation");

    @Override
    protected String doInBackground(HashMap<String, String>... hashMaps) {
        logger.info("Do In backgroud >> calls the Category Commedy Films");

        HashMap<String, String> queryParamsMap = hashMaps[0];

        FFClientFilmsAnimation ffClientFilmsAnimation = new FFClientFilmsAnimation();
        logger.info("Invoking url ...");

        String outJson = ffClientFilmsAnimation.invokeWS(queryParamsMap);

        if(outJson == null || outJson.trim().equals("[]")) {
            return null;
        }

        return outJson;
    }
}
