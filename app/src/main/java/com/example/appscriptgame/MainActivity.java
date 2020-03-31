package com.example.appscriptgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appscriptgame.DatabasePack.DataBaseHelper;
import com.example.appscriptgame.ModelClass.GameData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private String tag = getClass().getSimpleName();

    //variable get name view
    private EditText playername;
    private Button nextclick;
    private LinearLayout nameview;


    //variable related questions
    private LinearLayout qusansview;
    public static final String questions[] = {"Who is the best cricketer in the world?", "What are the colors in the Indian national flag? Select all:"};
    public static final String options[][] = {{"Sachin Tendulkar", "Virat Kolli", "Adam Gilchirst", "Jacques Kallis"}, {"White", "Yellow", "Orange", "Green"}};
    private TextView qustext;
    private TextView option1, option2, option3, option4;
    private Button nextquestionclick;

    private int currentquestion = -1;
    private ArrayList<ArrayList<Integer>> ansure = new ArrayList<>();

    private String username, time;
    private GameData gamedata;


    //variable related to summery view
    private LinearLayout summeryView;
    private TextView summeryText;
    private Button historyclick, finishclick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        init();
    }

    private void init() {

        nameview = findViewById(R.id.nameview);
        playername = findViewById(R.id.playername);
        nextclick = findViewById(R.id.nextclick);

        nextclick.setOnClickListener(this);

        qustext = findViewById(R.id.qustext);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextquestionclick = findViewById(R.id.nextquestionclick);
        qusansview = findViewById(R.id.qusansview);

        nextquestionclick.setOnClickListener(this);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        summeryView = findViewById(R.id.summeryView);
        summeryText = findViewById(R.id.summerytext);
        historyclick = findViewById(R.id.historyclick);
        finishclick = findViewById(R.id.finishclick);

        historyclick.setOnClickListener(this);
        finishclick.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.nextclick:
                username = playername.getText().toString().trim();
                if (!username.equals("") && username.length() >= 2 && username.matches("^[A-Za-z]+$")) {
                    time = getCurrentTime();
                    startQuestions();

                } else {
                    Toast.makeText(context, "Please enter valid name.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.nextquestionclick:
                if (ansure.size() != currentquestion && ansure.get(currentquestion).size() > 0) {
                    startQuestions();
                }
                else {
                    Toast.makeText(context, "Please select any answer.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.option1:
                if (currentquestion == 1) {
                    if (ansure.size() == currentquestion) { //checking that array list contain the data or not.
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(0);
                        ansure.add(currentquestion, ans);
                        option1.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                    } else if (ansure.get(currentquestion).contains(0)) {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.remove((Integer) 0); // Remove id already selected item select once again.
                        ansure.set(currentquestion, ans);
                        option1.setTextColor(context.getResources().getColor(R.color.black));

                    } else {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.add(0);
                        ansure.set(currentquestion, ans);
                        option1.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    }
                } else {
                    setallunselect();
                    if (ansure.size() > currentquestion) { //checking that array list contain the data or not.
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(0);
                        ansure.set(currentquestion, ans);
                    } else {

                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(0);
                        ansure.add(currentquestion, ans);
                    }
                    option1.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                }
                break;

            case R.id.option2:

                if (currentquestion == 1) {
                    if (ansure.size() == currentquestion) {
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(1);
                        ansure.add(currentquestion, ans);
                        option2.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                    } else if (ansure.get(currentquestion).contains(1)) {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.remove((Integer) 1);
                        ansure.set(currentquestion, ans);
                        option2.setTextColor(context.getResources().getColor(R.color.black));

                    } else {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.add(1);
                        ansure.set(currentquestion, ans);
                        option2.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    }
                } else {
                    setallunselect();

                    if (ansure.size() > currentquestion) {
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(1);
                        ansure.set(currentquestion, ans);
                    } else {

                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(1);
                        ansure.add(currentquestion, ans);
                    }
                    option2.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                }


                break;
            case R.id.option3:
                if (currentquestion == 1) {
                    if (ansure.size() == currentquestion) {
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(2);
                        ansure.add(currentquestion, ans);
                        option3.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                    } else if (ansure.get(currentquestion).contains(2)) {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.remove((Integer) 2);
                        ansure.set(currentquestion, ans);
                        option3.setTextColor(context.getResources().getColor(R.color.black));

                    } else {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.add(2);
                        ansure.set(currentquestion, ans);
                        option3.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    }
                } else {
                    setallunselect();
                    if (ansure.size() > currentquestion) {
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(2);
                        ansure.set(currentquestion, ans);
                    } else {

                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(2);
                        ansure.add(currentquestion, ans);
                    }
                    option3.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                }

                break;
            case R.id.option4:

                if (currentquestion == 1) {
                    if (ansure.size() == currentquestion) {
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(3);
                        ansure.add(currentquestion, ans);
                        option4.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                    } else if (ansure.get(currentquestion).contains(3)) {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.remove((Integer) 3);
                        ansure.set(currentquestion, ans);
                        option4.setTextColor(context.getResources().getColor(R.color.black));

                    } else {
                        ArrayList<Integer> ans = ansure.get(currentquestion);
                        ans.add(3);
                        ansure.set(currentquestion, ans);
                        option4.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    }
                } else {
                    setallunselect();

                    if (ansure.size() > currentquestion) {
                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(3);
                        ansure.set(currentquestion, ans);
                    } else {

                        ArrayList<Integer> ans = new ArrayList<>();
                        ans.add(3);
                        ansure.add(currentquestion, ans);
                    }
                    option4.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                }

                break;

            case R.id.historyclick:

                // Call Game history on history click.
                Intent intent = new Intent(MainActivity.this, Game_History.class);
                startActivity(intent);
                finish();
                break;

            case R.id.finishclick:
                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }

    }


    /**
     * Function to call on Next Click...
     *
     * */
    private void startQuestions() {

        nameview.setVisibility(View.GONE);
        qusansview.setVisibility(View.VISIBLE);
        setallunselect();
        currentquestion++;
        if (currentquestion < questions.length) {
            qustext.setText(questions[currentquestion]);
            option1.setText(options[currentquestion][0]);
            option2.setText(options[currentquestion][1]);
            option3.setText(options[currentquestion][2]);
            option4.setText(options[currentquestion][3]);
        } else {
            gamedata = new GameData();
            gamedata.setName(username);
            gamedata.setTime(time);
            gamedata.setAns1(options[0][ansure.get(0).get(0)]);
            String an2 = "";
            for (int j = 0; j < ansure.get(1).size(); j++) {
                if(an2.length()>0) {
                    an2 = an2 +", "+ options[1][ansure.get(1).get(j)];
                }else{
                    an2 = options[1][ansure.get(1).get(j)];
                }
            }
            gamedata.setAns2(an2);
            setsummery(gamedata);
        }

    }


    /**
     * Set nuselection..
     * */
    private void setallunselect() {
        option1.setTextColor(context.getResources().getColor(R.color.black));
        option2.setTextColor(context.getResources().getColor(R.color.black));
        option3.setTextColor(context.getResources().getColor(R.color.black));
        option4.setTextColor(context.getResources().getColor(R.color.black));
    }


    /**
     * Function for Summary Data...
     * */

    private void setsummery(GameData gameData) {

        summeryView.setVisibility(View.VISIBLE);
        qusansview.setVisibility(View.GONE);
        String string = "Hello " + gameData.getName() + "\n\nHere are the answer selected:\n\n1.)"
                + questions[0] + "\n\n Answer: " + gameData.getAns1() + " \n\n2.)" + questions[1] + "\n\n  Answer:"
                + gameData.getAns2();
        summeryText.setText(string);


        //Save the Data to Database...
        DataBaseHelper db = new DataBaseHelper(context);
        db.addData(gameData);
        db.close();

    }


    /**
     * Methode to get the current time..
     */

    private String getCurrentTime() {
        SimpleDateFormat outputFormatAmPm = new SimpleDateFormat("dd MMM KK:mm a");
        return outputFormatAmPm.format(new Date());
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }
}
