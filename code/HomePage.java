package com.example.theSpartan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * HomePage implements the first activity that will be shown in the application
 * saves and loads information to shared preferences
 * sets the textViews' text and implements functions that are used by the buttons
 * implements the menu
 */
public class HomePage extends Activity {
    private EditText userID;
    private SharedPreferences spUserID, spBestScores;
    private SharedPreferences.Editor editBestScores;
    private String id;
    private TextView leaderboard, provideID;

    /**
     * onCreate creates everything and displays it to the screen
     */
    @SuppressLint("CommitPrefEdits")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = findViewById(R.id.input_id);
        userID.setBackgroundColor(getResources().getColor(R.color.gray));
        leaderboard = findViewById(R.id.txt_leaderboard);
        leaderboard.setBackgroundColor(getResources().getColor(R.color.gray));
        provideID = findViewById(R.id.txt_id);
        provideID.setBackgroundColor(getResources().getColor(R.color.gray));

        spUserID = getSharedPreferences("UserID", Context.MODE_PRIVATE);
        spBestScores = getSharedPreferences("BestScores", Context.MODE_PRIVATE);
        editBestScores = spBestScores.edit();
    }

    /**
     *creates the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * implements each item of the menu
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_contact_us:
                displayMessage(getResources().getString(R.string.menu_contact_us_msg));
                return true;
            case R.id.menu_about:
                displayMessage(getResources().getString(R.string.menu_about_msg));
                return true;
            case R.id.menu_general:
                displayMessage(getResources().getString(R.string.menu_general_msg));
                return true;
            case R.id.menu_points:
                displayMessage(getResources().getString(R.string.menu_points_msg));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * used by the menu items to display message to the user by an AlertDialog
     * @param msg - the String to be displayed
     */
    private void displayMessage(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * proceed to the next activity
     */
    public void gotoLevelSelector(View v) {
        id = userID.getText().toString(); // get user's id
        if (id.equals("")) { // id can not contain no characters
            Toast.makeText(this, "Please Provide An ID", Toast.LENGTH_SHORT).show();
        } else if (id.length() < 3) { // id must contain at least 3 characters
            Toast.makeText(this, "Your ID Must Contain At Least 3 Character", Toast.LENGTH_SHORT).show();
        } else {
            editBestScores.putString("current", id).apply(); // save id for later use
            Intent intent = new Intent(this, LevelSelector.class);
            startActivity(intent); // proceed to the next activity
        }
    }

    /**
     * shows to the user his high score by getting its id
     */
    @SuppressLint("SetTextI18n")
    public void viewMyScore(View v) {
        id = userID.getText().toString(); // get user's id
        if (id.equals("")) {
            Toast.makeText(this, "Please Provide An ID", Toast.LENGTH_SHORT).show();
        } else if (id.length() < 3) {
            Toast.makeText(this, "Your ID Must Contain At Least 3 Character", Toast.LENGTH_SHORT).show();
        } else {
            int score = spUserID.getInt(id, 0); // get user's high score
            leaderboard.setText(getResources().getString(R.string.your_id) + id + "\n\n" + getResources().getString(R.string.best_score) + score);
        }
    }

    /**
     * displays the three best scores taken from shared preferences
     */
    @SuppressLint("SetTextI18n")
    public void viewBestScores(View view) {
        // id of the best scores
        String firstID = spBestScores.getString("first", "");
        String secondID = spBestScores.getString("second", "");
        String thirdID = spBestScores.getString("third", "");
        // score of best scores
        int firstScore = spUserID.getInt(firstID, 0);
        int secondScore = spUserID.getInt(secondID, 0);
        int thirdScore = spUserID.getInt(thirdID, 0);

        leaderboard.setText("1) " + getResources().getString(R.string.player) + firstID + "\n" +
                            getResources().getString(R.string.score) + firstScore + "\n\n"
                        +"2) " + getResources().getString(R.string.player) + secondID + "\n" +
                        getResources().getString(R.string.score) + secondScore + "\n\n"
                        +"3) " + getResources().getString(R.string.player) + thirdID + "\n" +
                        getResources().getString(R.string.score) + thirdScore + "\n\n"
                );
    }
}
