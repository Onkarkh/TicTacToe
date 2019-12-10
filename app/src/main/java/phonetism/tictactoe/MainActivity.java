package phonetism.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Tic-Tac-Teo";
    private int currentPlayer = 0;
    private int[] gameState = {19, 19, 19, 19, 19, 19, 19, 19, 19};
    private int[][] winPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private Boolean Active = true;
    private GridLayout gridLayout;
    private String status;
    private int checker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridlayout);
    }

    public void getCounter(View view) {
        ImageView counter= (ImageView) view;
        int tapper = Integer.parseInt(counter.getTag().toString());
        if (gameState[tapper] == 19 && Active) {
            gameState[tapper] = currentPlayer;
            counter.setTranslationY(-1500);
            if (currentPlayer == 0) {
                counter.setPadding(3,3,3,3);
                counter.setImageResource(R.drawable.ic_cross_blue_128dp);
                currentPlayer = 1;
            } else {
                counter.setImageResource(R.drawable.ic_circle_red_128dp);
                currentPlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] wp : winPosition) {
                if (gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]] == gameState[wp[2]] &&
                        gameState[wp[0]] != 19) {

                    Active = false;

                    if (currentPlayer == 1) {
                        status = "Cross is the Winner!";
                        openDialog(status);
                    } else {
                        status = "Circle is the Winner!";
                        openDialog(status);
                    }
                }
            }
            
            for (int i = 0; i < gameState.length; i++) {

                if (gameState[i] == 19) {
                    checker++;
                    Log.d(TAG, "getCounter: "+checker);
                }
            }
            if (checker == 0) {
                status = "Game Tie!";
               openDialog(status);
            }
            checker = 0;
        }
    }

    private void openDialog(String win) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(win);
        builder.setCancelable(true);

        builder.setNegativeButton(
                "Quit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                });

        builder.setPositiveButton(
                "New Game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        for (int i = 0; i < gridLayout.getChildCount(); i++) {
                            ImageView counter = (ImageView) gridLayout.getChildAt(i);
                            counter.setImageDrawable(null);
                            initGame();
                        }
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void initGame() {
        currentPlayer = 0;
        checker = 0;
        Active = true;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 19;
        }
    }
}
