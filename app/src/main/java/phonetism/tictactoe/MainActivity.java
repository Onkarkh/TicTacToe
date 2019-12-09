package phonetism.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int currentPlayer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getCounter(View view) {
        ImageView counter= (ImageView) view;
        counter.setTranslationY(-1500);
        if(currentPlayer==0) {
            counter.setImageResource(R.drawable.ic_cross_black_110dp);
            currentPlayer = 1;
        } else {
            counter.setImageResource(R.drawable.ic_circle_black_110dp);
            currentPlayer = 0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
    }
}
