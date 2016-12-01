package show.ff.kasoale.it.ffshow.actities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.MainActivity;
import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.adapters.SeasonsListAdapter;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.Season;
import show.ff.kasoale.it.ffshow.beans.SerieTV;
import show.ff.kasoale.it.ffshow.popups.PopupSerieDetails;
import show.ff.kasoale.it.ffshow.utils.Utilis;

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

                changeActivityEpisodes(view, season);

            }
        });

    }

    public void changeActivityEpisodes(View view, Season season){
        Intent intent = new Intent(this, SeasonActivity.class);
        intent.putExtra("Season", (Serializable) season);

        startActivity(intent);
    }

    public void showDetails(View view) {
        // Open a DialogPop regarding the Serie TV information
        Context context = view.getContext();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle("Details Serie");

        // set dialog message
        alertDialogBuilder
                .setMessage(Html.fromHtml((Utilis.getInfoSerie())))
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
        //startActivity(new Intent(SerieTvActivity.this, PopupSerieDetails.class));
    }
}