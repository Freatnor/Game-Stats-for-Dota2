package com.freatnor.game_stats_for_dota2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MatchDetailActivity extends AppCompatActivity {

    private int mMatchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO get match details from API based on matchID from intent
        Intent intent = getIntent();
        mMatchId = intent.getIntExtra("MatchId", -1);
        ///...


    }
}
