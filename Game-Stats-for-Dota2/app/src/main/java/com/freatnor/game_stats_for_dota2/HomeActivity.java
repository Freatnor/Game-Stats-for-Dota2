package com.freatnor.game_stats_for_dota2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.interfaces.APICallback;
import com.freatnor.game_stats_for_dota2.interfaces.MatchCallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerCallback;
import com.freatnor.game_stats_for_dota2.presenters.SearchResultsRecyclerViewAdapter;
import com.freatnor.game_stats_for_dota2.utils.SteamAPIUtility;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements MatchCallback, PlayerCallback, APICallback{

    private static final String TAG = "HomeActivity";

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
                "Freatnor", 1445123794, 76561198029057889L, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/juggernaut_lg.png", "Juggernaut",
                "http://cdn.dota2.com/apps/dota2/images/items/phase_boots_lg.png", 2777, 8, 4,11)));
        players.add(new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/fd/fdcf3b4cecda9b90cff20ab35c58374d35fb517c_medium.jpg",
                "RME", 1445123794, 76561197972714345L, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/slark_lg.png", "Slark",
                "http://cdn.dota2.com/apps/dota2/images/items/invis_sword_lg.png", 3478, 3, 1, 24)));

        SteamAPIUtility utility = SteamAPIUtility.getInstance(this);
        utility.getMatchHistoryForPlayer(players.get(0).mPlayerId, 0, this);
        utility.getMatchDetail(2625097872L, this);
        utility.getMatchSequenceByAccountId(10_000, 300);


        mAdapter = new SearchResultsRecyclerViewAdapter(players, this, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void matchSelected(long matchId) {
        Intent intent = new Intent(HomeActivity.this, MatchDetailActivity.class);
        intent.putExtra(getString(R.string.match_id_key), matchId);
        startActivity(intent);
    }

    @Override
    public void playerSelected(int playerId) {
        Intent intent = new Intent(HomeActivity.this, PlayerDetailActivity.class);
        intent.putExtra(getString(R.string.player_id_key), playerId);
        startActivity(intent);
    }

    @Override
    public void onMatchHistoryResponse(List<HistoryMatch> matches) {
        Log.d(TAG, "onMatchHistoryResponse: matches returned " + matches.size());
    }

    @Override
    public void onMatchDetailResponse(MatchDetail matchDetail) {
        Log.d(TAG, "onMatchDetailResponse: player = " + matchDetail.getHuman_players() + " and is radiant win = " +
            matchDetail.isRadiant_win());
    }

    @Override
    public void onPlayerSearchComplete() {

    }
}
