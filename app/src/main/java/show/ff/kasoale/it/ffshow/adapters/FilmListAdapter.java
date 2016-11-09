package show.ff.kasoale.it.ffshow.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.beans.Film;

/**
 * Created by kasoale on 06/11/2016.
 */

public class FilmListAdapter extends ArrayAdapter<Film>{

    private ArrayList<Film> films;
    private Context context;
    private int resource;

    private Logger logger = Logger.getLogger("FilmListAdapter");

    public ArrayList<Film> getFilms() {
        return films;
    }

    public FilmListAdapter(Context context, int resource, ArrayList<Film> films) {
        super(context, resource, films);
        this.films = films;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_films, null, true);

        }

        Film film = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.filmImage);
        Picasso.with(context).load(film.getImageUrl()).into(imageView);


        TextView textView = (TextView) convertView.findViewById(R.id.filmNameView);

        logger.info(film.getFilmName());
        textView.setText(film.getFilmName());

        return convertView;
    }
}
