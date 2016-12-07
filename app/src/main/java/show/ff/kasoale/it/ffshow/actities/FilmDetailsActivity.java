package show.ff.kasoale.it.ffshow.actities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.FilmDetail;
import show.ff.kasoale.it.ffshow.beans.Season;
import show.ff.kasoale.it.ffshow.utils.Utilis;

/**
 * Created by acasini on 05/12/16.
 */

public class FilmDetailsActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("FilmDetailsActivity");
    private String IMG_URL_BASE = "https://image.tmdb.org/t/p/w500";

    private ImageView posterImage;
    private Toolbar toolbar;
    private TextView filmGenereTxt, filmOverviewTxt, filmOriginalTitleTxt, filmReleadeDateTxt,
            filmAdultTxt, filmMediaVotoTxt;

    private String streamingURL;

    public String getStreamingURL() {
        return streamingURL;
    }

    public void setStreamingURL(String streamingURL) {
        this.streamingURL = streamingURL;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        //init component
        filmGenereTxt = (TextView) findViewById(R.id.film_genere);
        filmOverviewTxt = (TextView) findViewById(R.id.film_overview);
        filmOriginalTitleTxt = (TextView) findViewById(R.id.film_original_title);
        filmReleadeDateTxt = (TextView) findViewById(R.id.film_release_date);
        filmAdultTxt = (TextView) findViewById(R.id.film_adult);
        filmMediaVotoTxt = (TextView) findViewById(R.id.film_media_voto);


        Film film = (Film) getIntent().getSerializableExtra("Film");
        setStreamingURL(film.getStreamingUrl());
        FilmDetail filmDetail = (FilmDetail) getIntent().getSerializableExtra("FilmDetail");

        String generi = "";
        for (int genereID : filmDetail.getGenere()) {
            generi = generi + Utilis.genereMap.get(genereID) + " - ";
        }
        generi = generi.substring(0,generi.length() - 2);

        if(filmDetail == null){
            logger.info("Film Details is NULL");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Info Message");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Film Details not available, you can only watch the movie")
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

        logger.info("*******");
        logger.info(filmDetail.toString());
        logger.info("*******");

        String imagePosterFull = IMG_URL_BASE + filmDetail.getPosterPath();
        posterImage = (ImageView) findViewById(R.id.poster_image);

        Picasso.with(this.getApplicationContext()).load(imagePosterFull).into(posterImage);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(filmDetail.getTitle());

        filmGenereTxt.setText(generi);
        filmOverviewTxt.setText(filmDetail.getOverview());
        filmOriginalTitleTxt.setText(filmDetail.getOriginalTitle());
        filmReleadeDateTxt.setText(filmDetail.getReleaseDate());

        if(filmDetail.isAdult()){
            filmAdultTxt.setText("Visione consigliata ad un pubblico adulto o accompagnato");
        }else{
            filmAdultTxt.setText("Visione per tutta la famiglia");
        }

        filmMediaVotoTxt.setText(String.valueOf(filmDetail.getVoteAverage()));

    }

    public void playMoview(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setDataAndType(Uri.parse(getStreamingURL()), "video/*");
        view.getContext().startActivity(intent);
    }
}
