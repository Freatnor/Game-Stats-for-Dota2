package com.freatnor.game_stats_for_dota2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;
import com.freatnor.game_stats_for_dota2.interfaces.APICallback;
import com.freatnor.game_stats_for_dota2.interfaces.MatchCallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerCallback;
import com.freatnor.game_stats_for_dota2.presenters.SearchResultsRecyclerViewAdapter;
import com.freatnor.game_stats_for_dota2.utils.SteamAPIUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeActivity extends AppCompatActivity implements MatchCallback, PlayerCallback, APICallback{

    private static final String TAG = "HomeActivity";
    public static final String SHARED_FOLLOWED_LIST = "followed_list";

    private SearchResultsRecyclerViewAdapter mFollowedAdapter;
    private RecyclerView mFollowedRecyclerView;

    //search section
    private SearchResultsRecyclerViewAdapter mSearchAdapter;
    private RecyclerView mSearchRecyclerView;
    private LinearLayout mSearchLayout;
    private FloatingActionButton mSearchButton;
    private MenuItem mSearchMenuItem;

    private SearchView mSearchView;

    private SteamAPIUtility mUtility;

    private List<SteamPlayer> searchPlayers;
    private Map<Long, SteamPlayer> followedPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_container);

        mFollowedRecyclerView = (RecyclerView) findViewById(R.id.followed_players_recycler_view);
        mSearchRecyclerView = (RecyclerView) findViewById(R.id.searched_players_recycler_view);
        mSearchLayout = (LinearLayout) findViewById(R.id.searched_players_section);

        mSearchButton = (FloatingActionButton) findViewById(R.id.search_fab);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchMenuItem.expandActionView();
            }
        });

        searchPlayers = new LinkedList<>();
        followedPlayers = new HashMap<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSearchLayout.setVisibility(View.GONE);


        mUtility = SteamAPIUtility.getInstance(this);
        //test code
//        utility.getMatchHistoryForPlayer(players.get(0).mPlayerId, 0, this);
//        utility.getMatchDetail(2625097872L, this);
//        utility.getMatchSequenceByAccountId(10_000, 300);


        SharedPreferences sharedPrefs = getSharedPreferences(HomeActivity.SHARED_FOLLOWED_LIST, MODE_PRIVATE);
        Set<String> followedList = sharedPrefs.getStringSet(HomeActivity.SHARED_FOLLOWED_LIST, null);
        Log.d(TAG, "onResume: " + (followedList == null));
        if(followedList != null){
            long[] ids = new long[followedList.size()];
            Iterator<String> iter = followedList.iterator();
            int i = 0;
            while(iter.hasNext()){
                ids[i] = Long.parseLong(iter.next());
                Log.d(TAG, "onResume: item " + i + " is id " + ids[i]);
                i++;
            }
            mUtility.getPlayerById(ids, this, false);
        }


        mFollowedAdapter = new SearchResultsRecyclerViewAdapter(new ArrayList<SteamPlayer>(), this, this);
        mFollowedRecyclerView.setAdapter(mFollowedAdapter);
        mFollowedRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mSearchAdapter = new SearchResultsRecyclerViewAdapter(new ArrayList<SteamPlayer>(), this, this);
        mSearchRecyclerView.setAdapter(mSearchAdapter);
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.default_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchMenuItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) mSearchMenuItem.getActionView();

        ComponentName componentName = new ComponentName(this, this.getClass());
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        mSearchView.setQueryRefinementEnabled(true);

//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        processSearchIntent(intent);
    }

    private void processSearchIntent(Intent intent) {

        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            //clear the old search results
            searchPlayers.clear();
            mSearchAdapter.clearPlayers();
            mUtility.getPlayerByName(query, this);
            if(query.matches("^[0-9]*$")){
                //figure out if the id passed in is a 32 or 64 id
                long id64;
                long id32;
                long queryVal = Long.parseLong(query);
                if(mUtility.convert64IdTo32(Long.parseLong(query)) < 0){
                    id64 = mUtility.convert32IdTo64(queryVal);
                    id32 = queryVal;
                }
                else {
                    id32 = mUtility.convert64IdTo32(queryVal);
                    id64 = queryVal;
                }
                mUtility.getPlayerById(new long[]{id64, id32}, this, true);
            }
        }
    }

    @Override
    public void matchSelected(long matchId) {
        Intent intent = new Intent(HomeActivity.this, MatchDetailActivity.class);
        intent.putExtra(getString(R.string.match_id_key), matchId);
        startActivity(intent);
    }

    @Override
    public void playerSelected(long playerId) {
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
    public void onPlayerSearchComplete(SteamPlayer player, boolean forSearch) {
        if(forSearch) {
            if (player != null) {
                searchPlayers.add(player);
                mSearchAdapter.setPlayers(searchPlayers);
                mSearchAdapter.notifyDataSetChanged();

                if (mSearchLayout.getVisibility() == View.GONE) {
                    mSearchLayout.setVisibility(View.VISIBLE);
                }
            }
            //if the player returned is null check if there's no results and set the background to say there's no results
            else if (searchPlayers.size() < 1) {
                //TODO add something to turn on a "no results" view in search results
            }
        }
        //handle if it's the favorite calls
        else {
            if (player != null) {
                followedPlayers.put(player.getSteamid(), player);
                mFollowedAdapter.setPlayers(new ArrayList<SteamPlayer>(followedPlayers.values()));
                mFollowedAdapter.notifyDataSetChanged();
            }
        }
    }

}
