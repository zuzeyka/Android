package com.zuzeyka.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private enum Player {X, O}

    private Player currentPlayer = Player.X;
    private Player[][] board = new Player[3][3];
    private Button[][] buttons = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setText("");
                buttons[i][j].setOnClickListener(new ClickListener(i, j));
                gridLayout.addView(buttons[i][j]);
            }
        }
    }

    class ClickListener implements View.OnClickListener {
        private int x;
        private int y;

        public ClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View v) {
            if (buttons[x][y].getText().toString().equals("")) {
                buttons[x][y].setText(currentPlayer.toString());
                board[x][y] = currentPlayer;
                if (isWinner(x, y)) {
                    Toast.makeText(getApplicationContext(), "Player " + currentPlayer + " wins!", Toast.LENGTH_SHORT).show();
                } else {
                    currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
                }
            }
        }
    }

    private boolean isWinner(int x, int y) {
        String player = currentPlayer.toString();

        if (buttons[x][0].getText().equals(player) &&
                buttons[x][1].getText().equals(player) &&
                buttons[x][2].getText().equals(player)) {
            return true;
        }

        if (buttons[0][y].getText().equals(player) &&
                buttons[1][y].getText().equals(player) &&
                buttons[2][y].getText().equals(player)) {
            return true;
        }

        if (buttons[0][0].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][2].getText().equals(player)) {
            return true;
        }

        if (buttons[0][2].getText().equals(player) &&
                buttons[1][1].getText().equals(player) &&
                buttons[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    }
}
