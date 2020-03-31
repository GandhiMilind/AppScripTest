package com.example.appscriptgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.appscriptgame.Adapter.History_Adapter;
import com.example.appscriptgame.DatabasePack.DataBaseHelper;

import java.util.ArrayList;

public class Game_History extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__history);
        init();
    }

    private void init(){
        RecyclerView gamehistory = findViewById(R.id.gamehistory);
        History_Adapter historyAdapter = new History_Adapter(this, new DataBaseHelper(this).getData());
        gamehistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        gamehistory.setAdapter(historyAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Game_History.this,MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }
}
