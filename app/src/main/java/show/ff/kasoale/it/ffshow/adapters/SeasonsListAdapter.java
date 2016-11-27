package show.ff.kasoale.it.ffshow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.beans.Season;
import show.ff.kasoale.it.ffshow.beans.SerieTV;

/**
 * Created b
y kasoale on 25/11/2016.
        */
public class SeasonsListAdapter extends ArrayAdapter<Season>{

    private Context mContext;
    private SerieTV serieTV;
    private ArrayList<Season> seasons;
    private int resource;
    private Logger logger = Logger.getLogger("SeasonsListAdapter");


    public SeasonsListAdapter(Context context, int resource, SerieTV serieTV, ArrayList<Season> seasons) {
        super(context, resource, seasons);
        this.mContext = context;
        this.resource = resource;
        this.seasons = seasons;
        this.serieTV = serieTV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_season, null);
            View popUpView = inflater.inflate(R.layout.popup_serie_details, null);
        }

        String seasonName       = seasons.get(position).getName();
        String seasonID         = seasons.get(position).getSeasonID();
        String imageURL         = seasons.get(position).getImageSeason();
        TextView textView       = (TextView) convertView.findViewById(R.id.season_text);
        ImageView imageView     = (ImageView) convertView.findViewById(R.id.season_image);

        textView.setText(seasonName + " - " +seasonID);
        Picasso.with(mContext).load(imageURL).into(imageView);

        convertView.setTag(convertView);

        return convertView;
    }


}
