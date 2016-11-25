package show.ff.kasoale.it.ffshow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.beans.SerieTV;

/**
 * Created by kasoale on 25/11/2016.
 */

public class SeasonsListAdapter extends BaseAdapter{

    private Context mContext;
    private final SerieTV serieTV;
    private Logger logger = Logger.getLogger("SeasonsListAdapter");

    public SeasonsListAdapter(Context c, SerieTV serieTV) {
        super();
        mContext = c;
        this.serieTV = serieTV;
    }

    @Override
    public int getCount() {
        return serieTV.getSeasons().size();
    }

    @Override
    public Object getItem(int position) {
        return serieTV.getSeasons().get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        logger.info("Create the SerieGrid Activity");
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            String seasonName = serieTV.getSeasons().get(position).getName();
            String seasonID = serieTV.getSeasons().get(position).getSeasonID();
            String imageURL = serieTV.getSeasons().get(position).getImageSeason();

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_season, null);

            TextView textView = (TextView) grid.findViewById(R.id.season_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.season_image);

            textView.setText(seasonName + seasonID);
            Picasso.with(mContext).load(imageURL).into(imageView);


        } else {
            grid = (View) convertView;
        }

        return grid;
    }

}
