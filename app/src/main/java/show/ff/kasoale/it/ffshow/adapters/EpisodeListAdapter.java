package show.ff.kasoale.it.ffshow.adapters;

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

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.beans.Episode;
import show.ff.kasoale.it.ffshow.beans.Film;

/**
 * Created by kasoale on 27/11/2016.
 */

public class EpisodeListAdapter extends ArrayAdapter<Episode> {

    private ArrayList<Episode> episodes;
    private Context context;

    public EpisodeListAdapter(Context context, int resource, ArrayList<Episode> episodes) {
        super(context, resource, episodes);
        this.episodes = episodes;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_episode, null);
        }

        String episodeDescription = episodes.get(position).getDescription();
        String epidodeID = episodes.get(position).getEpisodeID();
        String episodeTitle = episodes.get(position).getTitle();
        String episodeImageUrl = episodes.get(position).getImageEpisode();

        TextView description = (TextView) convertView.findViewById(R.id.episode_description);
        TextView episodeID = (TextView) convertView.findViewById(R.id.episode_id);
        ImageView episodeImage = (ImageView) convertView.findViewById(R.id.episode_image);

        description.setText(episodeDescription);
        episodeID.setText(epidodeID + " - " + episodeTitle);
        Picasso.with(context).load(episodeImageUrl).into(episodeImage);

        convertView.setTag(convertView);
        
        return convertView;
    }


}
