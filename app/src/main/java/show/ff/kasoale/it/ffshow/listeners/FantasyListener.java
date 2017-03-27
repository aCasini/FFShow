package show.ff.kasoale.it.ffshow.listeners;

import android.app.ProgressDialog;
import android.view.View;

import java.util.logging.Logger;

/**
 * Created by kasoale on 20/03/2017.
 */

public class FantasyListener implements View.OnClickListener {

    private static Logger logger = Logger.getLogger("FantasyListener");

    private ProgressDialog progressDialog;

    @Override
    public void onClick(View view) {
        logger.info("Clicked the Fantasy Button");

        progressDialog = ProgressDialog.show(view.getContext(), "Loading Fantay", "Loading  Films ...");
        progressDialog.show();
    }
}
