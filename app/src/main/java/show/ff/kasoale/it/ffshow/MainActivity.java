package show.ff.kasoale.it.ffshow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.actities.FilmsActivity;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.engine.impl.FFClient;
import show.ff.kasoale.it.ffshow.utils.Utilis;

public class MainActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("MainActivity");
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchFilms(View view){
        //Call the webservice in order to find the Film
        logger.info("Call the webSerive");
        EditText filmText = (EditText) findViewById(R.id.filmText);

        String filmName = filmText.getText().toString();
        if(filmName != null && !filmName.equals("")){
            logger.info("Looking for ... "+ filmName);

            HashMap<String, String> queryParamsMap = new HashMap<>();
            queryParamsMap.put("filmName", filmName.replace(" ", "+"));

            //Invoke the webService
            SendFeedBackFFClient job = new SendFeedBackFFClient();

            try {
                String asyncTaskResult = job.execute(queryParamsMap).get();
                if(asyncTaskResult == null || asyncTaskResult.equals("")){
                    logger.info("Ops, no films found");
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("Info Message");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Ops, no films found !")
                            .setCancelable(false)
                            .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });


                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                    return;
                }
                logger.info("ASYNC RESULT: "+asyncTaskResult);

                Film[] filmsList = Utilis.json2FilmList(asyncTaskResult);

                for (Film film: filmsList) {
                    logger.info(film.toString());
                }

                changeActivity(view, filmsList);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }else{
            logger.info("ERROR: insert a value for the film");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set title
            alertDialogBuilder.setTitle("Info Message");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Insert a Film name to start the vision!")
                    .setCancelable(false)
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });


            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }

    }

    private void changeActivity(View view, Film[] filmList){
        logger.info("Changing activity");
        Intent intent = new Intent(this, FilmsActivity.class);
        List<Film> films = Arrays.asList(filmList);
        intent.putExtra("FilmsList", (Serializable) films);
        //TODO: set the values for the next activity
        startActivity(intent);
    }
}

