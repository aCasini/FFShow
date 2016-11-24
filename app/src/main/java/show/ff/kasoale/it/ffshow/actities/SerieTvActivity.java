package show.ff.kasoale.it.ffshow.actities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.R;

/**
 * Created by kasoale on 24/11/2016.
 */

public class SerieTvActivity extends AppCompatActivity {

    private static Logger logger = Logger.getLogger("SerieTvActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_serie);

    }
}
