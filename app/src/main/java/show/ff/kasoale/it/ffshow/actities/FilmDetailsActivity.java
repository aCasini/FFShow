package show.ff.kasoale.it.ffshow.actities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.FilmDetail;
import show.ff.kasoale.it.ffshow.beans.Season;

/**
 * Created by acasini on 05/12/16.
 */

public class FilmDetailsActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("FilmDetailsActivity");
    private String IMG_URL_BASE = "https://image.tmdb.org/t/p/w500";

    private ImageView posterImage;
    private Toolbar toolbar;
    private TextView filmGenereTxt, filmOverviewTxt, filmOriginalTitleTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        //init component
        filmGenereTxt = (TextView) findViewById(R.id.film_genere);
        filmGenereTxt.setText("Action, Horror");
        filmOverviewTxt = (TextView) findViewById(R.id.film_overview);
        filmOriginalTitleTxt = (TextView) findViewById(R.id.film_original_title);


        Film film = (Film) getIntent().getSerializableExtra("Film");
        FilmDetail filmDetail = (FilmDetail) getIntent().getSerializableExtra("FilmDetail");

        if(filmDetail == null){
            //TODO: open popu no info available
            logger.info("Film Details is NULL");
        }

        logger.info("*******");
        logger.info(filmDetail.toString());
        logger.info("*******");

        String imagePosterFull = IMG_URL_BASE + filmDetail.getPosterPath();
        posterImage = (ImageView) findViewById(R.id.poster_image);

        Picasso.with(this.getApplicationContext()).load(imagePosterFull).into(posterImage);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(filmDetail.getTitle());

        filmOverviewTxt.setText(filmDetail.getOverview());
        filmOriginalTitleTxt.setText(filmDetail.getOriginalTitle());




    }
}
