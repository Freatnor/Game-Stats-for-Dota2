package com.freatnor.game_stats_for_dota2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchPlayer;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;
import com.freatnor.game_stats_for_dota2.interfaces.APICallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerNameCallback;
import com.freatnor.game_stats_for_dota2.presenters.MatchDetailsPlayerRecyclerViewAdapter;
import com.freatnor.game_stats_for_dota2.utils.SteamAPIUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchDetailActivity extends AppCompatActivity implements APICallback, PlayerNameCallback{

    private static final String TAG = "MatchDetailActivity";

    private long mMatchId;

    private SteamAPIUtility mUtility;
    private MatchDetail mMatchDetail;

    private RecyclerView mRadiantRecyclerView;
    private RecyclerView mDireRecyclerView;

    private MatchDetailsPlayerRecyclerViewAdapter mRadiantAdapter;
    private MatchDetailsPlayerRecyclerViewAdapter mDireAdapter;

    private LinearLayout mDireOverview;
    private LinearLayout mRadiantOverview;
    private LinearLayout mVictoryBackground;

    private CardView mDireCard;
    private CardView mRadiantCard;

    private TextView mVictor;
    private TextView mLobbyType;
    private TextView mMode;
    private TextView mDuration;
    private TextView mMatchStart;

    //radiant overview
    private TextView mRadiantKills;
    private TextView mRadiantDeaths;
    private TextView mRadiantAssists;
    private TextView mRadiantTotalGold;

    //dire overview
    private TextView mDireKills;
    private TextView mDireDeaths;
    private TextView mDireAssists;
    private TextView mDireTotalGold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        mVictoryBackground = (LinearLayout) findViewById(R.id.victory_background);
        mVictor = (TextView) findViewById(R.id.victor);
        mLobbyType = (TextView) findViewById(R.id.lobby_type);
        mMode = (TextView) findViewById(R.id.game_mode);
        mDuration = (TextView) findViewById(R.id.match_duration);
        mMatchStart = (TextView) findViewById(R.id.match_start);

        mDireRecyclerView = (RecyclerView) findViewById(R.id.dire_match_detail_recycler);
        mRadiantRecyclerView = (RecyclerView) findViewById(R.id.radiant_match_detail_recycler);


        mDireCard = (CardView) findViewById(R.id.dire_card);
        mRadiantCard = (CardView) findViewById(R.id.radiant_card);

        mDireOverview = (LinearLayout) findViewById(R.id.dire_match_overview);
        mRadiantOverview = (LinearLayout) findViewById(R.id.radiant_match_overview);

        //TODO radiant and dire overview textviews
        
        mRadiantKills = (TextView) findViewById(R.id.radiant_kills);
        mRadiantDeaths = (TextView) findViewById(R.id.radiant_deaths);
        mRadiantAssists = (TextView) findViewById(R.id.radiant_assists);
        mRadiantTotalGold = (TextView) findViewById(R.id.radiant_total_gold);

        mDireKills = (TextView) findViewById(R.id.dire_kills);
        mDireDeaths = (TextView) findViewById(R.id.dire_deaths);
        mDireAssists = (TextView) findViewById(R.id.dire_assists);
        mDireTotalGold = (TextView) findViewById(R.id.dire_total_gold);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO get match details from API based on matchID from intent
        Intent intent = getIntent();
        mMatchId = intent.getLongExtra(getString(R.string.match_id_key), -1);
        ///...
        mUtility = SteamAPIUtility.getInstance(this);
        mUtility.getMatchDetail(mMatchId, this);


        //OnClicks for toggling open or closed the recycler views
        mDireOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDireCard.getVisibility() == View.VISIBLE){
                    mDireCard.setVisibility(View.GONE);
                }
                else {
                    mDireCard.setVisibility(View.VISIBLE);
                }
            }
        });

        mRadiantOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRadiantCard.getVisibility() == View.VISIBLE){
                    mRadiantCard.setVisibility(View.GONE);
                }
                else {
                    mRadiantCard.setVisibility(View.VISIBLE);
                }
            }
        });

        mRadiantAdapter = new MatchDetailsPlayerRecyclerViewAdapter(mUtility);
        mDireAdapter = new MatchDetailsPlayerRecyclerViewAdapter(mUtility);

        mDireRecyclerView.setAdapter(mRadiantAdapter);
        mRadiantRecyclerView.setAdapter(mDireAdapter);

        mDireRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRadiantRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onMatchHistoryResponse(List<HistoryMatch> matches) {

    }

    @Override
    public void onMatchDetailResponse(MatchDetail matchDetail) {
        mMatchDetail = matchDetail;
        List<MatchPlayer> radiantPlayers = new ArrayList<>();
        List<MatchPlayer> direPlayers = new ArrayList<>();

        for (int i = 0; i < mMatchDetail.getPlayers().size(); i++) {
            MatchPlayer player = mMatchDetail.getPlayers().get(i);
            Log.d(TAG, "onMatchDetailResponse: player slot = " + player.getPlayer_slot() +
                "and index = " + i);
            if(i < 5){
                radiantPlayers.add(player);
            }
            else {
                direPlayers.add(player);
            }
            mUtility.getPlayerName(player.getAccount_id(), i, this);
        }

        mRadiantAdapter.setPlayers(radiantPlayers);
        mRadiantAdapter.notifyDataSetChanged();

        mDireAdapter.setPlayers(direPlayers);
        mDireAdapter.notifyDataSetChanged();

        fillFields();
    }

    private void fillFields() {
        String victor = mMatchDetail.isRadiant_win()? "Radiant" : "Dire";
        mVictor.setText(String.format("%s Victory!", victor));
        
        mMode.setText(parseMode(mMatchDetail.getGame_mode()));
        mLobbyType.setText(parseLobby(mMatchDetail.getLobby_type()));
        mDuration.setText(String.format("%d Minutes and %d Seconds", mMatchDetail.getDuration() / 60,
                mMatchDetail.getDuration() % 60));

        //Finding the difference in time from the current and the start of match and showing it as a formatted date
        Date start = new Date(mMatchDetail.getStart_time());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        mMatchStart.setText(sdf.format(start));

        fillRadiantBoard();
        fillDireBoard();
    }

    private void fillDireBoard() {
        mRadiantKills.setText(calculateKills(mMatchDetail.getPlayers().subList(0, 4)) + "");
        mRadiantDeaths.setText(calculateDeaths(mMatchDetail.getPlayers().subList(0, 4))+ "");
        mRadiantAssists.setText(calculateAssists(mMatchDetail.getPlayers().subList(0, 4))+"");
        mRadiantTotalGold.setText(calculateTotalGold(mMatchDetail.getPlayers().subList(0, 4))+"");
    }

    private void fillRadiantBoard() {
        mDireKills.setText(calculateKills(mMatchDetail.getPlayers().subList(5, 9)) + "");
        mDireDeaths.setText(calculateDeaths(mMatchDetail.getPlayers().subList(5, 9))+ "");
        mDireAssists.setText(calculateAssists(mMatchDetail.getPlayers().subList(5, 9))+"");
        mDireTotalGold.setText(calculateTotalGold(mMatchDetail.getPlayers().subList(5, 9))+"");
    }

    private int calculateTotalGold(List<MatchPlayer> matchPlayers) {
        int totalGold = 0;
        for (int i = 0; i < matchPlayers.size(); i++) {
            totalGold += matchPlayers.get(i).getTotalGold();
        }
        return totalGold;
    }

    private int calculateAssists(List<MatchPlayer> matchPlayers) {
        int assists = 0;
        for (int i = 0; i < matchPlayers.size(); i++) {
            assists += matchPlayers.get(i).getAssists();
        }
        return assists;
    }

    private int calculateDeaths(List<MatchPlayer> matchPlayers) {
        int deaths = 0;
        for (int i = 0; i < matchPlayers.size(); i++) {
            deaths += matchPlayers.get(i).getDeaths();
        }
        return deaths;
    }

    private int calculateKills(List<MatchPlayer> matchPlayers) {
        int kills = 0;
        for (int i = 0; i < matchPlayers.size(); i++) {
            kills += matchPlayers.get(i).getKills();
        }
        return kills;
    }

    

    //perhaps useful to move to a helper class later
    private String parseLobby(int lobby_type) {
        switch (lobby_type){
            case 0:
                return "Public Matchmaking";
            case 1:
                return "Practise";
            case 2:
                return "Tournament";
            case 3:
                return "Tutorial";
            case 4:
                return "Co-op with bots.";
            case 5:
                return "Team Match";
            case 6:
                return "Solo Queue";
            case 7:
                return "Ranked";
            case 8:
                return "1v1 Mid";
            default:
                return "Invalid";
        }
    }

    //perhaps useful to move to a helper class later
    private String parseMode(int game_mode) {
        switch(game_mode) {
            case 1:
                return "All Pick";
            case 2:
                return "Captain's Mode";
            case 3:
                return "Random Draft";
            case 4:
                return "Single Draft";
            case 5:
                return "All Random";
            case 6:
                return "Intro";
            case 7:
                return "Diretide";
            case 8:
                return "Reverse Captain's Mode";
            case 9:
                return "The Greeviling";
            case 10:
                return "Tutorial";
            case 11:
                return "Mid Only";
            case 12:
                return "Least Played";
            case 13:
                return "New Player Pool";
            case 14:
                return "Compendium Matchmaking";
            case 15:
                return "Co-op vs Bots";
            case 16:
                return "Captains Draft";
            case 18:
                return "Ability Draft";
            case 20:
                return "All Random Deathmatch";
            case 21:
                return "1v1 Mid Only";
            case 22:
                return "Ranked Matchmaking";
            default:
                return "None";
        }

    }

    //useless here, perhaps callbacks should be refactored?
    @Override
    public void onPlayerSearchComplete(SteamPlayer player) {

    }


    @Override
    public void onPlayerNameResponse(String name, int index) {
        if(index < 5){
            mRadiantAdapter.setPlayerName(index, name);
            mRadiantAdapter.notifyItemChanged(index);
        }
        else {
            mDireAdapter.setPlayerName(index % 5, name);
            mDireAdapter.notifyItemChanged(index % 5);
        }
    }
}
