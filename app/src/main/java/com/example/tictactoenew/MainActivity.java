package com.example.tictactoenew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            gameReset(view);
            return;
        }

        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;

            ImageView overlayImageView = findViewById(getResources().getIdentifier("overlay" + (tappedImage + 1), "id", getPackageName()));
            if (activePlayer == 0) {
                overlayImageView.setImageResource(R.drawable.union);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - Tap to play");
            } else {
                overlayImageView.setImageResource(R.drawable.ellipse_1);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - Tap to play");
            }
            overlayImageView.setVisibility(View.VISIBLE);

            for (int i = 0; i < winPositions.length; i++) {
                int[] winPosition = winPositions[i];
                if (gameState[winPosition[0]] == gameState[winPosition[1]]
                        && gameState[winPosition[1]] == gameState[winPosition[2]]
                        && gameState[winPosition[0]] != 2) {

                    String winnerStr;
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "X has won!";
                    } else {
                        winnerStr = "O has won!";
                    }

                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                    Button restartButton = findViewById(R.id.restartButton);
                    restartButton.setVisibility(View.VISIBLE);

                    // Check if the win position matches 0, 4, or 8
                    if (i == 0) { // 0, 4, 8
                        ImageView lineImageView = findViewById(R.id.line1);
                        lineImageView.setVisibility(View.VISIBLE);
                    } else if (i == 1) {
                        ImageView lineImageView = findViewById(R.id.line2);
                        lineImageView.setVisibility(View.VISIBLE);

                    } else if (i == 2) {
                        ImageView lineImageView = findViewById(R.id.line3);
                        lineImageView.setVisibility(View.VISIBLE);

                    } else if (i == 3) {
                        ImageView lineImageView = findViewById(R.id.line4);
                        lineImageView.setVisibility(View.VISIBLE);

                    } else if (i == 4) {
                        ImageView lineImageView = findViewById(R.id.line5);
                        lineImageView.setVisibility(View.VISIBLE);

                    } else if (i == 5) {
                        ImageView lineImageView = findViewById(R.id.line6);
                        lineImageView.setVisibility(View.VISIBLE);

                    } else if (i == 7) {
                        ImageView lineImageView = findViewById(R.id.dig1);
                        lineImageView.setVisibility(View.VISIBLE);

                    } else if (i == 6) {
                        ImageView lineImageView = findViewById(R.id.dig2);
                        lineImageView.setVisibility(View.VISIBLE);

                    }

                    return;
                }
            }

            boolean draw = true;
            for (int cell : gameState) {
                if (cell == 2) {
                    draw = false;
                    break;
                }
            }

            if (draw) {
                gameActive = false;
                TextView status = findViewById(R.id.status);
                status.setText("It's a draw!");
                Button restartButton = findViewById(R.id.restartButton);
                restartButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.img1)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img2)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img3)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img4)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img5)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img6)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img7)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img8)).setImageResource(R.drawable.rectangle_1__1_);
        ((ImageView) findViewById(R.id.img9)).setImageResource(R.drawable.rectangle_1__1_);

        for (int i = 1; i <= 9; i++) {
            ImageView overlayImageView = findViewById(getResources().getIdentifier("overlay" + i, "id", getPackageName()));
            overlayImageView.setVisibility(View.INVISIBLE);
        }

        findViewById(R.id.dig1).setVisibility(View.GONE);
        findViewById(R.id.dig2).setVisibility(View.GONE);
        findViewById(R.id.line1).setVisibility(View.GONE);
        findViewById(R.id.line2).setVisibility(View.GONE);
        findViewById(R.id.line3).setVisibility(View.GONE);
        findViewById(R.id.line4).setVisibility(View.GONE);
        findViewById(R.id.line5).setVisibility(View.GONE);
        findViewById(R.id.line6).setVisibility(View.GONE);

        TextView status = findViewById(R.id.status);
        status.setText("X's turn - Tap to play");

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setVisibility(View.GONE);
    }
}