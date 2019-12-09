package phonetism.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int currentPlayer = 0;
    private int[] gameState = {19, 19, 19, 19, 19, 19, 19, 19, 19};
    private int[][] winPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getCounter(View view) {
        ImageView counter= (ImageView) view;
        int tapper = Integer.parseInt(counter.getTag().toString());
        gameState[tapper] = currentPlayer;
        counter.setTranslationY(-1500);
        if(currentPlayer==0) {
            counter.setImageResource(R.drawable.ic_cross_black_110dp);
            currentPlayer = 1;
        } else {
            counter.setImageResource(R.drawable.ic_circle_black_110dp);
            currentPlayer = 0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for (int[] wp : winPosition) {
            if (gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]] == gameState[wp[2]] &&
                    gameState[wp[0]] != 19) {
                String win = "";
                if (currentPlayer == 1) {
                    win = "Cross";
                } else {
                    win = "Circle";
                }
                Toast.makeText(this, "Winner is "+win, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
