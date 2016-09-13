package com.freatnor.game_stats_for_dota2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;
import com.freatnor.game_stats_for_dota2.interfaces.APICallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerDetailRequest;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerFollowedListener;
import com.freatnor.game_stats_for_dota2.presenters.PlayerDetailPagerAdapter;
import com.freatnor.game_stats_for_dota2.utils.SteamAPIUtility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerDetailActivity extends AppCompatActivity implements APICallback, PlayerFollowedListener,
        PlayerDetailRequest{

    //TODO change to correct player + history type
    private SteamPlayer mPlayer;

    private PlayerDetailPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private FragmentManager mManager;

    private SteamAPIUtility mUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        mViewPager = (ViewPager) findViewById(R.id.player_detail_fragment_container);
        mTabLayout = (TabLayout) findViewById(R.id.player_detail_pager_tabs);

        //Set up the Fragment Manager
        mManager = getSupportFragmentManager();

        mUtility = SteamAPIUtility.getInstance(this);

        //Get the player account_id to use for the API calls...
        Intent intent = getIntent();
        long accountId = intent.getLongExtra(getString(R.string.player_id_key), -1);
        //TODO get the actual player object from the API with the account_id


        //Dummy Data
        //TODO delete this
//        mPlayer = new Player("https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/0e/0e93c226aba775646e58357f188cbb687bc6a030_full.jpg",
//                "Freatnor", 1445123794, 76561198029057889l, new Match(false, "http://cdn.dota2.com/apps/dota2/images/heroes/juggernaut_lg.png", "Juggernaut",
//                "http://cdn.dota2.com/apps/dota2/images/items/phase_boots_lg.png", 2777, 8, 4,11));

        mPagerAdapter = new PlayerDetailPagerAdapter(mManager, mPlayer, this);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    @Override
    public void onMatchHistoryResponse(List<HistoryMatch> matches) {

    }

    @Override
    public void onMatchDetailResponse(MatchDetail matchDetail) {

    }

    @Override
    public void onPlayerSearchComplete(SteamPlayer player, boolean forSearch) {
        mManager.getFragments().get(0).
    }

    @Override
    public void requestPlayerInfo() {

    }

    @Override
    public void onPlayerFollowed(long steamid) {
        SharedPreferences sharedPrefs = getSharedPreferences(HomeActivity.SHARED_FOLLOWED_LIST, MODE_PRIVATE);
        Set<String> followedList = sharedPrefs.getStringSet(HomeActivity.SHARED_FOLLOWED_LIST, null);
        if(followedList != null){
            Set<String> newList = new HashSet<>(followedList);
            newList.add(String.valueOf(steamid));
            SharedPreferences.Editor e = sharedPrefs.edit();
            e.putStringSet(HomeActivity.SHARED_FOLLOWED_LIST, newList);
            e.commit();
        }
    }
}
