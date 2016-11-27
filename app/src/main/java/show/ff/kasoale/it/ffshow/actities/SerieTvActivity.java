package show.ff.kasoale.it.ffshow.actities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.MainActivity;
import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.adapters.SeasonsListAdapter;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.Season;
import show.ff.kasoale.it.ffshow.beans.SerieTV;
import show.ff.kasoale.it.ffshow.popups.PopupSerieDetails;

/**
 * Created by kasoale on 24/11/2016.
 */

public class SerieTvActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("SerieTvActivity");

    private ListView seasonsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_serie);

        SerieTV serieTV = (SerieTV) getIntent().getSerializableExtra("SerieTV");

        ImageView serieTVImage = (ImageView) findViewById(R.id.serieTV_image);
        String serieTVImageURL = serieTV.getImageURL();
        if (serieTVImageURL == null) {
            serieTVImageURL = serieTV.getSeasons().get(0).getImageSeason();
        }
        Picasso.with(getApplicationContext()).load(serieTVImageURL).into(serieTVImage);

        seasonsListView = (ListView) findViewById(R.id.seasonsList);
        //new SeasonsListAdapter(getApplicationContext(), R.layout.grid_season, serieTV, serieTV.getSeasons());
        SeasonsListAdapter adapter = new SeasonsListAdapter(getApplicationContext(), R.layout.grid_season, serieTV, serieTV.getSeasons());

        seasonsListView.setAdapter(adapter);

        seasonsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Season season = (Season) adapterView.getItemAtPosition(i);
                logger.info("You clicked on ... ");
                logger.info(season.toString());

                //TODO: more details
            }
        });

    }

    public void showDetails(View view) {
        startActivity(new Intent(SerieTvActivity.this, PopupSerieDetails.class));
    }
}