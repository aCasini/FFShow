package show.ff.kasoale.it.ffshow.actities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.adapters.FilmCardAdapter;
import show.ff.kasoale.it.ffshow.adapters.FilmListAdapter;
import show.ff.kasoale.it.ffshow.beans.Film;

public class FilmsActivity extends AppCompatActivity{

    private static Logger logger = Logger.getLogger("FilmsActivity");
    private GridView filmListView;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        ArrayList<Film> filmList = (ArrayList<Film>) getIntent().getSerializableExtra("FilmsList");

        logger.info("FilmsActivity Started");

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewFilm);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new FilmCardAdapter(filmList, this.getApplicationContext());
        recyclerView.setAdapter(adapter);

        /*
        filmListView = (GridView) findViewById(R.id.filmListView);
        //FilmListAdapter filmListAdapter = new FilmListAdapter(getApplicationContext(), R.layout.activity_films, filmList);
        FilmListAdapter.FilmAdapterClickListener recListener = new FilmListAdapter.FilmAdapterClickListener() {
            @Override
            public void recyclerViewClick(String albumID) {
                logger.info("FILM : "+ albumID);
            }
        };
        FilmListAdapter filmListAdapter = new FilmListAdapter(getApplicationContext(), R.layout.grid_film, filmList, recListener);
*/
/**
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
**/

    }



}
