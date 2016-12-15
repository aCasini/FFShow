package show.ff.kasoale.it.ffshow.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.SendFeedBackFFClientFilmDetail;
import show.ff.kasoale.it.ffshow.actities.FilmDetailsActivity;
import show.ff.kasoale.it.ffshow.beans.FilmDetail;
import show.ff.kasoale.it.ffshow.utils.Utilis;

/**
 * Created by kasoale on 13/12/2016.
 */

public class FilmCardAdapterTopRated extends RecyclerView.Adapter<FilmCardAdapterTopRated.FilmViewHolder> {

    private ArrayList<FilmDetail> films = new ArrayList<>();
    private Context context;
    private static Logger logger = Logger.getLogger("FilmCardAdapterTopRated");
    private String IMG_URL_BASE = "https://image.tmdb.org/t/p/w500";

    public FilmCardAdapterTopRated(ArrayList<FilmDetail> films, Context context){
        this.films = films;
        this.context = context;
    }

    @Override
    public FilmCardAdapterTopRated.FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_rated_film_row,parent,false);
        FilmCardAdapterTopRated.FilmViewHolder filmViewHolder = new FilmCardAdapterTopRated.FilmViewHolder(view, context, films);
        //context = view.getContext();

        return filmViewHolder;
    }

    @Override
    public void onBindViewHolder(FilmCardAdapterTopRated.FilmViewHolder holder, int position) {
        FilmDetail film =  films.get(position);

        Picasso.with(context).load(IMG_URL_BASE + film.getPosterPath()).into(holder.imageFilm);
        holder.filmTitle.setText(film.getTitle());

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        ImageView imageFilm;
        TextView filmTitle;
        ArrayList<FilmDetail> films = new ArrayList<>();
        Context context;

        public FilmViewHolder(View view, Context context, ArrayList<FilmDetail> films){
            super(view);
            view.setOnClickListener(this);
            imageFilm = (ImageView)view.findViewById(R.id.filmImage_toprated);
            filmTitle = (TextView)view.findViewById(R.id.filmName_toprated);
            this.films = films;
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            FilmDetail film = films.get(position);
            logger.info("Clicked on FILM: "+film.getTitle());

            HashMap<String, String> mapQueryParam = new HashMap<String,String>();
            mapQueryParam.put("filmName", film.getTitle().replace(" ","+"));

            SendFeedBackFFClientFilmDetail jobFilmDetail = new SendFeedBackFFClientFilmDetail();

            try{
                String asyncTaskResult = jobFilmDetail.execute(mapQueryParam).get();
                logger.info("ASYNC FILM DETAIL: \n"+asyncTaskResult);
                FilmDetail filmDetail = Utilis.json2FilmDetail(asyncTaskResult);

                Intent intent = new Intent(context, FilmDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("FilmDetail", (Serializable) filmDetail);
                intent.putExtra("Film", (Serializable) film);

                context.startActivity(intent);


            }catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }



            /**
             Intent intent = new Intent(Intent.ACTION_VIEW);

             intent.setDataAndType(Uri.parse(film.getStreamingUrl()), "video/*");
             context.startActivity(intent);
             **/
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            FilmDetail film = films.get(position);
            logger.info("Clicked onLong --> FILM: "+film.getTitle());

            return false;
        }
    }

}
