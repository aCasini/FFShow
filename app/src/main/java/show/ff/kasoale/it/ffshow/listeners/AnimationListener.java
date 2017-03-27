package show.ff.kasoale.it.ffshow.listeners;

import android.app.ProgressDialog;
import android.view.View;

import java.util.logging.Logger;

/**
 * Created by kasoale on 20/03/2017.
 */

public class AnimationListener implements View.OnClickListener {

    private static Logger logger = Logger.getLogger("AnimationListener");

    private ProgressDialog progressDialog;

    @Override
    public void onClick(View view) {
        logger.info("Clicked Animation Button");

        progressDialog = ProgressDialog.show(view.getContext(), "Loading Animations", "Loading Animation Films ...");
        progressDialog.show();
    }
}
