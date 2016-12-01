package show.ff.kasoale.it.ffshow;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.actities.FilmsActivity;
import show.ff.kasoale.it.ffshow.actities.SerieTvActivity;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.SerieTV;
import show.ff.kasoale.it.ffshow.popups.PopupSerieDetails;
import show.ff.kasoale.it.ffshow.utils.Utilis;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static Logger logger = Logger.getLogger("MainActivity");
    private String searchMode;

    FloatingActionButton fabPlus, fabFilms, fabSeries;
    Animation fabOpen, fabClose, fabClock, fabAntiClock;
    TextView serieTV_desc, film_desc;
    ProgressDialog progressDialog;
    boolean isOpen = false;

    String result;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fabPlus = (FloatingActionButton) findViewById(R.id.fab);
        fabFilms = (FloatingActionButton) findViewById(R.id.fab_films);
        fabSeries = (FloatingActionButton) findViewById(R.id.fab_series);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabClock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlock);

        serieTV_desc = (TextView) findViewById(R.id.serieTV_desc);
        film_desc = (TextView) findViewById(R.id.film_desc);


        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fabFilms.startAnimation(fabClose);
                    fabSeries.startAnimation(fabClose);
                    fabPlus.startAnimation(fabAntiClock);
                    fabFilms.setClickable(false);
                    fabSeries.setClickable(false);

                    serieTV_desc.startAnimation(fabClose);
                    serieTV_desc.setVisibility(View.INVISIBLE);
                    film_desc.setVisibility(View.INVISIBLE);
                    film_desc.startAnimation(fabClose);
                    isOpen = false;
                }else{
                    fabFilms.startAnimation(fabOpen);
                    fabSeries.startAnimation(fabOpen);
                    fabPlus.startAnimation(fabClock);
                    fabFilms.setClickable(true);
                    fabSeries.setClickable(true);

                    serieTV_desc.setVisibility(View.VISIBLE);
                    film_desc.setVisibility(View.VISIBLE);
                    film_desc.startAnimation(fabOpen);
                    serieTV_desc.startAnimation(fabOpen);
                    isOpen = true;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void setSearchFilms(View view){
        TextView searchingMode = (TextView) findViewById(R.id.searchMode);
        searchingMode.setVisibility(View.VISIBLE);
        searchingMode.setText("Search wiil be do on Films Storage ... ");
        setSearchMode("FILMS");

        fabFilms.startAnimation(fabClose);
        fabSeries.startAnimation(fabClose);
        fabPlus.startAnimation(fabAntiClock);
        fabFilms.setClickable(false);
        fabSeries.setClickable(false);

        serieTV_desc.startAnimation(fabClose);
        serieTV_desc.setVisibility(View.INVISIBLE);
        film_desc.setVisibility(View.INVISIBLE);
        film_desc.startAnimation(fabClose);
        isOpen = false;
    }

    public void setSearchSeries(View view){
        TextView searchingMode = (TextView) findViewById(R.id.searchMode);
        searchingMode.setVisibility(View.VISIBLE);
        searchingMode.setText("Search wiil be do on Series TV Storage ... ");
        setSearchMode("SERIES");

        fabFilms.startAnimation(fabClose);
        fabSeries.startAnimation(fabClose);
        fabPlus.startAnimation(fabAntiClock);
        fabFilms.setClickable(false);
        fabSeries.setClickable(false);

        serieTV_desc.startAnimation(fabClose);
        serieTV_desc.setVisibility(View.INVISIBLE);
        film_desc.setVisibility(View.INVISIBLE);
        film_desc.startAnimation(fabClose);
        isOpen = false;
    }


    public void searchStreamingMedia(View view){
        final View viewFinal = view;

        if(getSearchMode() == null
                || getSearchMode().equals("")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            // set title
            alertDialogBuilder.setTitle("Info Message");
            // set dialog message
            alertDialogBuilder
                    .setMessage("To start set a search mode, thanks !")
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
        }else if(getSearchMode().equals("FILMS")){
            EditText filmText = (EditText) findViewById(R.id.filmText);
            String filmName = filmText.getText().toString();

            if(filmName == null || filmName.equals("")){
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
                return;
            }

            progressDialog = ProgressDialog.show(this, "Loading FILMs", "Loading the Films ...");
            progressDialog.show();


            Thread thread = new Thread(){
                public void run() {
                    logger.info("Start the searching... for series");
                    result = searchFilms(viewFinal);
                    progressDialog.cancel();

                    if(result == null){
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                logger.info("Ops, no films found");
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(viewFinal.getContext());

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
                            }
                        });
                    }

                }
            };
            thread.start();

        }else{
            EditText serieText = (EditText) findViewById(R.id.filmText);
            String serieName = serieText.getText().toString();

            if(serieName == null || serieName.equals("")){
                logger.info("ERROR: insert a value for the TV Serie");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Info Message");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Insert a TV Serie name to start the vision!")
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


            progressDialog = ProgressDialog.show(this, "Loading TV Series", "Loading the TV Series ...");
            progressDialog.show();

            Thread thread = new Thread(){
                public void run() {
                    logger.info("Start the searching... for series");
                    result = searchSerie(viewFinal);
                    progressDialog.cancel();

                    if(result == null){
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                logger.info("Ops, no TV Series found");
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                                // set title
                                alertDialogBuilder.setTitle("Info Message");

                                // set dialog message
                                alertDialogBuilder
                                        .setMessage("Ops, no TV Series found !")
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
                        });
                    }

                }
            };
            thread.start();
        }
    }

    private String searchSerie(View view){
        logger.info("Call the webservice for Serie");
        EditText serieText = (EditText) findViewById(R.id.filmText);

        String serieName = serieText.getText().toString();

        if(serieName != null && !serieName.equals("")){
            logger.info("Looking Serie for ... " + serieName);

            HashMap<String, String> queryParamsMap = new HashMap<>();
            queryParamsMap.put("serieName", serieName.replace(" ", "+"));

            //Invoke the webService
            SendFeedBackSerieFFClient jobSerie = new SendFeedBackSerieFFClient();


            try{
                String asyncTaskResult = jobSerie.execute(queryParamsMap).get();
                if(asyncTaskResult == null || asyncTaskResult.equals("")){
                    if(progressDialog != null && progressDialog.isShowing()){
                        progressDialog.cancel();
                    }
                    return null;
                }
                logger.info("ASYNC SERIE TV RESULT: "+asyncTaskResult);

                SerieTV serieTV = Utilis.json2SerieTV(asyncTaskResult);

                logger.info(serieTV.toString());

                changeActivitySerie(view, serieTV);

                return "Serie Found";

            }catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }

        }else{
            return null;
        }
    }


    private String searchFilms(View view){
        //Call the webservice in order to find the Film
        logger.info("Call the webSerive");
        EditText filmText = (EditText) findViewById(R.id.filmText);

        String filmName = filmText.getText().toString();
        if(filmName != null && !filmName.equals("")){
            logger.info("Looking Film for ... "+ filmName);

            HashMap<String, String> queryParamsMap = new HashMap<>();
            queryParamsMap.put("filmName", filmName.replace(" ", "+"));

            //Invoke the webService
            SendFeedBackFFClient job = new SendFeedBackFFClient();

            try {
                String asyncTaskResult = job.execute(queryParamsMap).get();
                if(asyncTaskResult == null || asyncTaskResult.equals("")){
                    if(progressDialog != null && progressDialog.isShowing()){
                        progressDialog.cancel();
                    }
                    return null;

                }
                logger.info("ASYNC RESULT: "+asyncTaskResult);

                Film[] filmsList = Utilis.json2FilmList(asyncTaskResult);

                for (Film film: filmsList) {
                    logger.info(film.toString());
                }

                changeActivity(view, filmsList);

                return "Films Found";

            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return null;
            }

        }else{
            return null;
        }

    }

    private void changeActivity(View view, Film[] filmList){
        logger.info("Changing activity");
        Intent intent = new Intent(this, FilmsActivity.class);
        List<Film> films = Arrays.asList(filmList);
        intent.putExtra("FilmsList", (Serializable) films);
        // set the values for the next activity
        startActivity(intent);
    }

    private void changeActivitySerie(View view, SerieTV serieTV){
        logger.info("Changing activity Serie");
        Intent intent = new Intent(this, SerieTvActivity.class);
        //List<Film> films = Arrays.asList(filmList);
        intent.putExtra("SerieTV", (Serializable) serieTV);
        // set the values for the next activity
        String infoSerie = serieTV.getTitoloOriginale() + "\n"
                + serieTV.getGenere() + "\n"
                + serieTV.getAnno() + "\n"
                + serieTV.getNazione() + "\n"
                + serieTV.getIdeatore() + "\n"
                + serieTV.getProduzione() + "\n"
                + serieTV.getCast();
        infoSerie = infoSerie.replace("<b>", "");
        infoSerie = infoSerie.replace("</b>", "");

        Utilis.setInfoSerie(infoSerie);
        startActivity(intent);
    }

    /**
     * ADDED for the NAVIGATION Menu
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            logger.info("Set the Settings for FFShow");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set title
            alertDialogBuilder.setTitle("Information");

            // set dialog message
            alertDialogBuilder
                    .setMessage(Html.fromHtml("The Stream Pockey App is an easy way to see:\n<br>"
                            + " - <b><font color=#18ffff>TV Series</b>\n<br>"
                            + " - <b><font color=#18ffff>Films</b>\n\n<br><br>"
                            + "See all TV Series and Fils where and where you want.<br>"
                            + "How ? it is simple, use the Streaming functionality Pocket App.\n<br><br>"
                            + "Install a Player like \n<br> - <b><font color=#18ffff>VLC</b> \n<br> - <b><font color=#18ffff>FLV Player</b> \n<br> in order to watch the movies.\n\n<br><br>"
                            +"Enjoy :-)"))
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
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            // Set up the input
            final EditText suggest = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            suggest.setInputType(InputType.TYPE_CLASS_TEXT);
            suggest.setHint("Your Suggestion here ");
            suggest.setLines(5);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            // set title
            alertDialogBuilder.setTitle("Suggest Somethings!");

            // set dialog message
            alertDialogBuilder
                    .setCancelable(true)
                    .setView(suggest)
                    .setPositiveButton("Send",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            String to = "kasoale@gmail.com";
                            String subject = "Stream Pocket Suggestion";
                            String message = suggest.getText().toString();

                            if(message != null && !message.equals("")) {
                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                                //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                                //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                                email.putExtra(Intent.EXTRA_TEXT, message);

                                //need this to prompts email client only
                                email.setType("message/rfc822");

                                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                            }

                            dialog.cancel();
                        }
                    });


            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setSearchMode(String searchMode){
        this.searchMode = searchMode;
    }

    public String getSearchMode(){
        return this.searchMode;
    }


}

