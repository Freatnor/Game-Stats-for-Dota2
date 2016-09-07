package com.freatnor.game_stats_for_dota2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.freatnor.game_stats_for_dota2.presenters.MatchDetailsPlayerRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MatchDetailActivity extends AppCompatActivity {

    private int mMatchId;

    private RecyclerView mRadiantRecyclerView;
    private RecyclerView mDireRecyclerView;

    private CardView mDireCard;
    private CardView mRadiantCard;

    private TextView mVictor;
    private TextView mSkillBracket;
    private TextView mLobbyType;
    private TextView mMode;
    private TextView mDuration;
    private TextView mMatchStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        mVictor = (TextView) findViewById(R.id.victor);
        mSkillBracket = (TextView) findViewById(R.id.skill_bracket);
        mLobbyType = (TextView) findViewById(R.id.lobby_type);
        mMode = (TextView) findViewById(R.id.game_mode);
        mDuration = (TextView) findViewById(R.id.match_duration);
        mMatchStart = (TextView) findViewById(R.id.match_start);

        mDireRecyclerView = (RecyclerView) findViewById(R.id.dire_match_detail_recycler);
        mRadiantRecyclerView = (RecyclerView) findViewById(R.id.radiant_match_detail_recycler);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO get match details from API based on matchID from intent
        Intent intent = getIntent();
        mMatchId = intent.getIntExtra("MatchId", -1);
        ///...

        //Dummy Data
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/0e/0e93c226aba775646e58357f188cbb687bc6a030_full.jpg",
                "Freatnor", 1445123794, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/juggernaut_lg.png", "Juggernaut",
                "http://cdn.dota2.com/apps/dota2/images/items/phase_boots_lg.png", 2777, 8, 4,11)));
        players.add(new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/fd/fdcf3b4cecda9b90cff20ab35c58374d35fb517c_medium.jpg",
                "RME", 1445123794, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/slark_lg.png", "Slark",
                "http://cdn.dota2.com/apps/dota2/images/items/invis_sword_lg.png", 3478, 3, 1, 24)));

        mSkillBracket.setText("High");
        mMode.setText("All Pick");
        mLobbyType.setText("Solo Queue");
        mDuration.setText(String.format("%d Minutes and %d Seconds", 2747 / 60, 2747 % 60));
        //Finding the difference in time from the current and the start of match and showing it as a formatted date
        Date start = new Date(1473122825);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        mMatchStart.setText(sdf.format(start));

        //setting dummy data in the

        mDireRecyclerView.setAdapter(new MatchDetailsPlayerRecyclerViewAdapter(players, MatchDetailsPlayerRecyclerViewAdapter.DIRE));
        mRadiantRecyclerView.setAdapter(new MatchDetailsPlayerRecyclerViewAdapter(players, MatchDetailsPlayerRecyclerViewAdapter.RADIANT));

        mDireRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRadiantRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
