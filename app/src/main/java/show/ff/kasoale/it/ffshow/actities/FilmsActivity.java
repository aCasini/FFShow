package show.ff.kasoale.it.ffshow.actities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.adapters.FilmListAdapter;
import show.ff.kasoale.it.ffshow.beans.Film;

public class FilmsActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("FilmsActivity");
    private ListView filmListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        ArrayList<Film> filmList = (ArrayList<Film>) getIntent().getSerializableExtra("FilmsList");

        logger.info("FilmsActivity Started");

        filmListView = (ListView) findViewById(R.id.filmListView);
        //FilmListAdapter filmListAdapter = new FilmListAdapter(getApplicationContext(), R.layout.activity_films, filmList);
        FilmListAdapter filmListAdapter = new FilmListAdapter(getApplicationContext(), R.layout.grid_film, filmList);

        filmListView.setAdapter(filmListAdapter);


        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Film film = (Film) adapterView.getItemAtPosition(i);
                logger.info("You clicked on ... ");
                logger.info(film.toString());

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setDataAndType(Uri.parse(film.getStreamingUrl()), "video/*");

                startActivity(Intent.createChooser(intent, "Complete action using"));
            }
        });


    }
}
