package show.ff.kasoale.it.ffshow.actities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.adapters.SeasonsListAdapter;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.Season;
import show.ff.kasoale.it.ffshow.beans.SerieTV;

/**
 * Created by kasoale on 24/11/2016.
 */

public class SerieTvActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("SerieTvActivity");

    private GridView seasonsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_serie);

        SerieTV serieTV = (SerieTV) getIntent().getSerializableExtra("SerieTV");

        ImageView serieTVImage = (ImageView) findViewById(R.id.serieTV_image);
        String serieTVImageURL = serieTV.getImageURL();
        if(serieTVImageURL == null){
            serieTVImageURL = serieTV.getSeasons().get(0).getImageSeason();
        }
        Picasso.with(getApplicationContext()).load(serieTVImageURL).into(serieTVImage);

        seasonsListView = (GridView) findViewById(R.id.seasonsList);
        SeasonsListAdapter adapter = new SeasonsListAdapter(getApplicationContext(), serieTV);

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
}
