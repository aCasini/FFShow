package show.ff.kasoale.it.ffshow.actities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import show.ff.kasoale.it.ffshow.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
