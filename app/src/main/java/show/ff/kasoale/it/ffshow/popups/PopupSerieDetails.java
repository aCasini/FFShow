package show.ff.kasoale.it.ffshow.popups;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import show.ff.kasoale.it.ffshow.R;
import show.ff.kasoale.it.ffshow.utils.Utilis;

/**
 * Created by kasoale on 26/11/2016.
 */

public class PopupSerieDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_serie_details);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        ((TextView)findViewById(R.id.serie_info)).setText(Utilis.getInfoSerie());

        getWindow().setLayout((int) (width*.7), (int) (height*.6));

    }
}
