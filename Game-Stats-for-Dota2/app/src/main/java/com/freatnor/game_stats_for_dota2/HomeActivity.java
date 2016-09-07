package com.freatnor.game_stats_for_dota2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.freatnor.game_stats_for_dota2.presenters.SearchResultsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private SearchResultsRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.followed_players_recycler_view);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //Test Array
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/0e/0e93c226aba775646e58357f188cbb687bc6a030_full.jpg",
                "Freatnor", 1445123794, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/juggernaut_lg.png", "Juggernaut",
                "http://cdn.dota2.com/apps/dota2/images/items/phase_boots_lg.png", 2777, 8, 4,11)));
        players.add(new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/fd/fdcf3b4cecda9b90cff20ab35c58374d35fb517c_medium.jpg",
                "RME", 1445123794, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/slark_lg.png", "Slark",
                "http://cdn.dota2.com/apps/dota2/images/items/invis_sword_lg.png", 3478, 3, 1, 24)));


        mAdapter = new SearchResultsRecyclerViewAdapter(players);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
