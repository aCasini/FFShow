package show.ff.kasoale.it.ffshow.actities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.adapters.EpisodeListAdapter;
import show.ff.kasoale.it.ffshow.beans.Episode;
import show.ff.kasoale.it.ffshow.beans.Season;
import show.ff.kasoale.it.ffshow.beans.SerieTV;

/**
 * Created by kasoale on 27/11/2016.
 */

public class SeasonActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("SeasonActivity");
    private ListView episodesListView;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_episode);

        Season season = (Season) getIntent().getSerializableExtra("Season");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(season.getName());

        ImageView episodeImage = (ImageView) findViewById(R.id.season_image_for_episodes);
        String episodeImageURL = season.getImageSeason();
        Picasso.with(getApplicationContext()).load(episodeImageURL).into(episodeImage);

        episodesListView = (ListView) findViewById(R.id.episodes_list);
        EpisodeListAdapter episodeListAdapter = new EpisodeListAdapter(getApplicationContext(), R.layout.grid_episode, season.getEpisodes());

        episodesListView.setAdapter(episodeListAdapter);

        episodesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Episode episode = (Episode) adapterView.getItemAtPosition(i);
                logger.info("You clicked on ... ");
                logger.info(episode.toString());

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setDataAndType(Uri.parse(episode.getStreamingURL()), "video/*");

                startActivity(Intent.createChooser(intent, "Complete action using"));
            }
        });


    }
}
