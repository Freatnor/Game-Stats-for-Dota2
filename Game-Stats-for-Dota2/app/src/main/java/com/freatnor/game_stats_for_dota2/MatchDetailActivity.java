package com.freatnor.game_stats_for_dota2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchPlayer;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;
import com.freatnor.game_stats_for_dota2.interfaces.APICallback;
import com.freatnor.game_stats_for_dota2.presenters.MatchDetailsPlayerRecyclerViewAdapter;
import com.freatnor.game_stats_for_dota2.utils.SteamAPIUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchDetailActivity extends AppCompatActivity implements APICallback{

    private long mMatchId;

    private SteamAPIUtility mUtility;
    private MatchDetail mMatchDetail;

    private RecyclerView mRadiantRecyclerView;
    private RecyclerView mDireRecyclerView;

    private LinearLayout mDireOverview;
    private LinearLayout mRadiantOverview;

    private CardView mDireCard;
    private CardView mRadiantCard;

    private TextView mVictor;
    private TextView mSkillBracket;
    private TextView mLobbyType;
    private TextView mMode;
    private TextView mDuration;
    private TextView mMatchStart;

    //radiant overview
    private TextView mRadiantKills;
    private TextView mRadiantDeaths;
    private TextView mRadiantAssits;
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

        mVictor = (TextView) findViewById(R.id.victor);
        mSkillBracket = (TextView) findViewById(R.id.skill_bracket);
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

        //Dummy Data
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/0e/0e93c226aba775646e58357f188cbb687bc6a030_full.jpg",
                "Freatnor", 1445123794, 76561198029057889l, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/juggernaut_lg.png", "Juggernaut",
                "http://cdn.dota2.com/apps/dota2/images/items/phase_boots_lg.png", 2777, 8, 4,11)));
        players.add(new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/fd/fdcf3b4cecda9b90cff20ab35c58374d35fb517c_medium.jpg",
                "RME", 1445123794, 76561197972714345l, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/slark_lg.png", "Slark",
                "http://cdn.dota2.com/apps/dota2/images/items/invis_sword_lg.png", 3478, 3, 1, 24)));

        mSkillBracket.setText("High");
        mMode.setText("All Pick");
        mLobbyType.setText("Solo Queue");
        mDuration.setText(String.format("%d Minutes and %d Seconds", 2747 / 60, 2747 % 60));
        //Finding the difference in time from the current and the start of match and showing it as a formatted date
        Date start = new Date(1473122825);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        mMatchStart.setText(sdf.format(start));

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

        mDireRecyclerView.setAdapter(new MatchDetailsPlayerRecyclerViewAdapter(players, MatchDetailsPlayerRecyclerViewAdapter.DIRE));
        mRadiantRecyclerView.setAdapter(new MatchDetailsPlayerRecyclerViewAdapter(players, MatchDetailsPlayerRecyclerViewAdapter.RADIANT));

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
        radiantPlayers.add(mMatchDetail.getPlayers().get(0));
        radiantPlayers.add(mMatchDetail.getPlayers().get(1));
        radiantPlayers.add(mMatchDetail.getPlayers().get(2));
        radiantPlayers.add(mMatchDetail.getPlayers().get(3));
        radiantPlayers.add(mMatchDetail.getPlayers().get(4));

        List<MatchPlayer> direPlayers = new ArrayList<>();
        direPlayers.add(mMatchDetail.getPlayers().get(5));
        direPlayers.add(mMatchDetail.getPlayers().get(6));
        direPlayers.add(mMatchDetail.getPlayers().get(7));
        direPlayers.add(mMatchDetail.getPlayers().get(8));
        direPlayers.add(mMatchDetail.getPlayers().get(9));

        mDireRecyclerView.setAdapter(new MatchDetailsPlayerRecyclerViewAdapter(direPlayers, MatchDetailsPlayerRecyclerViewAdapter.DIRE));
        mRadiantRecyclerView.setAdapter(new MatchDetailsPlayerRecyclerViewAdapter(radiantPlayers, MatchDetailsPlayerRecyclerViewAdapter.RADIANT));

        mDireRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRadiantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onPlayerSearchComplete(SteamPlayer player) {

    }
}
