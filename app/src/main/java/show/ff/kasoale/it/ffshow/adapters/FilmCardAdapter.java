package show.ff.kasoale.it.ffshow.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.beans.Film;

/**
 * Created by kasoale on 04/12/2016.
 */

public class FilmCardAdapter extends RecyclerView.Adapter<FilmCardAdapter.FilmViewHolder> {

    private ArrayList<Film> films = new ArrayList<>();
    private Context context;
    private static Logger logger = Logger.getLogger("FilmCardAdapter");

    public FilmCardAdapter(ArrayList<Film> films, Context context){
        this.films = films;
        this.context = context;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_film,parent,false);
        FilmViewHolder filmViewHolder = new FilmViewHolder(view, context, films);
        //context = view.getContext();

        return filmViewHolder;
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film =  films.get(position);

        Picasso.with(context).load(film.getImageUrl()).into(holder.imageFilm);
        holder.filmTitle.setText(film.getFilmName());

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageFilm;
        TextView filmTitle;
        ArrayList<Film> films = new ArrayList<>();
        Context context;

        public FilmViewHolder(View view, Context context, ArrayList<Film> films){
            super(view);
            view.setOnClickListener(this);
            imageFilm = (ImageView)view.findViewById(R.id.filmImage);
            filmTitle = (TextView)view.findViewById(R.id.filmNameView);
            this.films = films;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Film film = films.get(position);
            logger.info(film.toString());

            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setDataAndType(Uri.parse(film.getStreamingUrl()), "video/*");
            context.startActivity(intent);
//            startActivity(Intent.createChooser(intent, "Complete action using"));
        }
    }
}
