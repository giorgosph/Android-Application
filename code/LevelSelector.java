package com.example.theSpartan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * LevelSelector let the user choose a game and start playing
 * displays the current score of each level and saves it to shared preferences
 */
public class LevelSelector extends Activity {
    private int highScore, totalScore;
    private String id;
    private  SharedPreferences spBestScores, spUserID;
    private SharedPreferences.Editor editUserID, editBestScores;
    private Intent intent;

    /**
     * creates and displays everything
     * get information from other classes and shared preferences
     * set new values to shared preferences if there is a need
     * enables/disables buttons
     */
    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_selector);

        // (int)To save the temporary score of each level until the user wins all the levels
        SharedPreferences spTempScore = getSharedPreferences("TemporaryScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editTempScore = spTempScore.edit();
        // (boolean)To keep the current level enabled until the user wins all the levels
        SharedPreferences spLevel = getSharedPreferences("Level", Context.MODE_PRIVATE);
        SharedPreferences.Editor editLevel = spLevel.edit();
        // (int)To save each users personal high score after completing all the levels(the key is the user's id)
        spUserID = getSharedPreferences("UserID", Context.MODE_PRIVATE);
        editUserID = spUserID.edit();
        // (String)To save the ids of the users with the 3 best high scores
        spBestScores = getSharedPreferences("BestScores", Context.MODE_PRIVATE);
        editBestScores = spBestScores.edit();

        intent = getIntent();
        // after the user ends a level checks for win or loss
        boolean win1 = intent.getBooleanExtra("win1", false);
        boolean win2 = intent.getBooleanExtra("win2", false);
        boolean win3 = intent.getBooleanExtra("win3", false);
        // if the user win it takes his score
        int score1 = intent.getIntExtra("score1", 0);
        int score2 = intent.getIntExtra("score2", 0);
        int score3 = intent.getIntExtra("score3", 0);
        // assign its id
        id = spBestScores.getString("current", "");
        // assign its current high score
        highScore = spUserID.getInt(id, 0);

        Button btnLevel2 = findViewById(R.id.btn_level2);
        Button btnLevel3 = findViewById(R.id.btn_level3);
        TextView txtScore1 = findViewById(R.id.txt_score1);
        txtScore1.setBackgroundColor(getResources().getColor(R.color.gray));
        TextView txtScore2 = findViewById(R.id.txt_score2);
        txtScore2.setBackgroundColor(getResources().getColor(R.color.gray));
        TextView txtScore3 = findViewById(R.id.txt_score3);
        txtScore3.setBackgroundColor(getResources().getColor(R.color.gray));
        TextView txtTotalScore = findViewById(R.id.txt_total_score);
        txtTotalScore.setBackgroundColor(getResources().getColor(R.color.gray));


        btnLevel2.setEnabled(false);
        btnLevel3.setEnabled(false);

        String ID1 = id + "1";
        String ID2 = id + "2";
        String ID3 = id + "3";
        // save the score of each level for each user
        int keepScore1 = spTempScore.getInt(ID1, 0);
        int keepScore2 = spTempScore.getInt(ID2, 0);

        if(win3){ // win level 3
            totalScore = keepScore1 + keepScore2 + score3; // calculate total score
            editLevel.putBoolean(ID2, false).apply(); // reset (disable)
            editLevel.putBoolean(ID3, false).apply();
            txtScore3.setText(getString(R.string.score) + score3);
            txtTotalScore.setText(getString(R.string.total_score) + totalScore);
            checkHighScores();
        }

        boolean keep2 = spLevel.getBoolean(ID2, false);
        boolean keep3 = spLevel.getBoolean(ID3, false);

        if(win1 || keep2){ // level 1 has been won
            btnLevel2.setEnabled(true); // enable next level
            editLevel.putBoolean(ID2, true).apply(); // keep it enabled
            if(score1 != 0) {
                editTempScore.putInt(ID1, score1).apply();
                txtScore1.setText(getString(R.string.score) + score1);
            }else
                txtScore1.setText(getString(R.string.score) + keepScore1);
        }
        if(win2 || keep3){ // level 2 has been won
            btnLevel3.setEnabled(true); // enable next level
            editLevel.putBoolean(ID3, true).apply();  // keep it enabled
            if(score2 != 0) {
                editTempScore.putInt(ID2, score2).apply();
                txtScore2.setText(getString(R.string.score) + score2);
            }else
                txtScore2.setText(getString(R.string.score) + keepScore2);
        }
    }

    /**
     * checks if the new score is higher than the high score to update it
     * checks if the new high score is higher than the 3 best scores to update positions
     */
    private void checkHighScores() {
        if (totalScore > highScore) {
            editUserID.putInt(id, totalScore).apply();
        }
        // get 3 best id
        String firstID = spBestScores.getString("first", "");
        String secondID = spBestScores.getString("second", "");
        String thirdID = spBestScores.getString("third", "");
        // get their scores
        int firstScore = spUserID.getInt(firstID, 0);
        int secondScore = spUserID.getInt(secondID, 0);
        int thirdScore = spUserID.getInt(thirdID, 0);

        if (totalScore > thirdScore) {
            if (totalScore > secondScore) {
                if (totalScore > firstScore) {
                    editBestScores.putString("first", id).apply();
                    editBestScores.putString("second", firstID).apply();
                    editBestScores.putString("third", secondID).apply();
                }else{
                    editBestScores.putString("second", id).apply();
                    editBestScores.putString("third", secondID).apply();
                }
            }else{
                editBestScores.putString("third", id).apply();
            }
        }
    }

    /**
     * proceed to level 1
     */
    public void gotoLevel1(View view) {
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("lvl", 1); // inform with level number
        startActivity(intent);
    }

    /**
     * proceed to level 2
     */
    public void gotoLevel2(View view) {
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("lvl", 2);
        startActivity(intent);
    }

    /**
     * proceed to level 3
     */
    public void gotoLevel3(View view) {
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("lvl", 3);
        startActivity(intent);
    }
}
