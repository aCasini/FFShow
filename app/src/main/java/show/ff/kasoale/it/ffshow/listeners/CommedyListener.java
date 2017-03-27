package show.ff.kasoale.it.ffshow.listeners;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.MainActivity;
import show.ff.kasoale.it.ffshow.SendFeedBackFFClientFilmsTopRated;
import show.ff.kasoale.it.ffshow.actities.FilmsTopRatedActivity;
import show.ff.kasoale.it.ffshow.beans.FilmDetail;
import show.ff.kasoale.it.ffshow.beans.FilmDetailsList;
import show.ff.kasoale.it.ffshow.catecories.SendFeedBackFFClientFilmsCommedy;
import show.ff.kasoale.it.ffshow.utils.Utilis;

/**
 * Created by kasoale on 20/03/2017.
 */

public class CommedyListener extends MainActivity implements View.OnClickListener {

    private static Logger logger = Logger.getLogger("CommedyListener");
    private ProgressDialog progressDialog;


    @Override
    public void onClick(View view) {
        logger.info("Clicked the Commedy Button");

        final Context context = view.getContext();

        progressDialog = ProgressDialog.show(context, "Loading Commedies", "Loading Commedy Films ...");
        progressDialog.show();

        final View finalView = view;

        Thread threadCommedyFilms = new Thread() {
            public void run() {
                SendFeedBackFFClientFilmsCommedy job = new SendFeedBackFFClientFilmsCommedy();

                try {
                    HashMap<String, String> map = new HashMap<>();
                    String asyncTaskResult = job.execute(map).get();
                    if (asyncTaskResult == null || asyncTaskResult.equals("")) {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.cancel();
                        }
                    }

                    //**
                    progressDialog.cancel();

                    FilmDetailsList filmDetailsList = Utilis.json2FilmDetailsList(asyncTaskResult);

                    ArrayList<FilmDetail> filmsTopRated = filmDetailsList.getFilmsDetails();
                    for (FilmDetail film : filmsTopRated) {
                        logger.info(film.toString());
                    }

                    logger.info("Changing activity to Commedy");
                    Intent intent = new Intent(context, FilmsTopRatedActivity.class);

                    intent.putExtra("FilmDetailList", (Serializable) filmDetailsList);
                    // set the values for the next activity
                    startActivity(intent);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        threadCommedyFilms.start();


        //view.findViewById()

    }


}
