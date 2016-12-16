package show.ff.kasoale.it.ffshow.actities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.adapters.FilmCardAdapter;
import show.ff.kasoale.it.ffshow.adapters.FilmCardAdapterTopRated;
import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.FilmDetail;
import show.ff.kasoale.it.ffshow.beans.FilmDetailsList;

/**
 * Created by kasoale on 13/12/2016.
 */

public class FilmsTopRatedActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("FilmsActivity");
    private GridView filmListView;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated_films);
        ArrayList<FilmDetail> filmList = null;
        FilmDetailsList filmDetailsList = (FilmDetailsList) getIntent().getSerializableExtra("FilmDetailList");
        filmList = filmDetailsList.getFilmsDetails();

        logger.info("FilmsActivity Started");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_top_rated);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new FilmCardAdapterTopRated(filmList, this.getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
