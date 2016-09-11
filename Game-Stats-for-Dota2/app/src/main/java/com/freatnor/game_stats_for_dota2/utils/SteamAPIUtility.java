package com.freatnor.game_stats_for_dota2.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.freatnor.game_stats_for_dota2.R;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.MatchHistoryResults;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchSequenceHistory.MatchSequence;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchSequenceHistory.MatchSequenceResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Taylor on 9/8/16.
 */
public class SteamAPIUtility  {
    private static final String TAG = "SteamAPIUtility";

    //URL constants
    //Used for all but specific user lookups
    public static final String STEAM_API_BASE_URL = "https://api.steampowered.com/IDOTA2Match_570/";

    //key parameter prefix
    public static final String STEAM_API_KEY_PARAMETER = "key=";

    //methods for steamapi for dota
    public static final String GET_MATCHES = "GetMatchHistory/V001/";
    public static final String GET_MATCHES_BY_SEQUENCE = "GetMatchHistoryBySequenceNum/v1";
    public static final String GET_MATCH_DETAILS = "GetMatchDetails/V001/";
    public static final String GET_GAME_ITEMS = "GetGameItems/v1";
    public static final String GET_HEROES = "GetHeroes/v1";
    public static final String GET_ITEMS = "GetGameItems/v1";

    //extra parameters
    public static final String MULTIPLAYER_MATCH = "min_players=10";
    public static final String DATE_MAX = "date_max=";
    public static final String START_AT_MATCH_ID = "start_at_match_id=";
    public static final String HERO_ID = "hero_id=";
    public static final String MATCHES_REQEUSTED_NUMBER = "matches_requested=";
    public static final String MATCH_ID = "match_id=";
    public static final String ACCOUNT_ID = "account_id=";

    public static final String START_AT_MATCH_SEQ_NUM = "start_at_match_seq_num=";

    //Used for user specific lookups
    public static final String STEAM_USER_API_BASE_URL = "http://api.steampowered.com/ISteamUser/";

    //methods for steam user api
    public static final String USER_PROFILE = "GetPlayerSummaries/v0002/";
    public static final String SEARCH_VANITY_URL = "ResolveVanityURL/v0001/";


    //Special urls for hero, ability, item images
    public static final String ITEM_IMAGE_URL = "http://cdn.dota2.com/apps/dota2/images/items/%s_lg.png";
    public static final String HERO_BASE_URL = "http://cdn.dota2.com/apps/dota2/images/heroes/";
    public static final String HERO_LARGE_IMAGE_SUFFIX = "_lg.png";
    public static final String HERO_FULL_HORIZONTAL_SUFFIX = "_full.png";

    private RequestQueue mRequestQueue;
    private Context mContext;

    //Singleton stuff
    private static SteamAPIUtility sInstance;

    private SteamAPIUtility(Context context){
        mContext = context.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public static SteamAPIUtility getInstance(Context context){
        if(sInstance == null){
            sInstance = new SteamAPIUtility(context);
        }
        return sInstance;
    }

    //method to get the list of matches for a player. Pass in 0 to get the default number of matches
    public MatchHistoryResults getMatchHistoryForPlayer(final long account_id, final int num_results,){
        String url = STEAM_API_BASE_URL + GET_MATCHES + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key) +
                "&" + ACCOUNT_ID + account_id;
        if(num_results > 0){
            url += "&" + MATCHES_REQEUSTED_NUMBER + num_results;
        }
        final List<HistoryMatch> matchList = new ArrayList<>();
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        Gson gson = new Gson();
                        MatchHistoryResults.MatchHistory results = gson.fromJson(response.toString(), MatchHistoryResults.MatchHistory.class);
                        matchList.addAll(results.getResult().getMatches());
                        Log.d(TAG, "onResponse: " + results.getResult().getNum_results());

                        if(results.getResult().hasMorePagedResults()){
                            getMatchHistoryForPlayer(account_id, num_results, results.getResult().getLastMatchId() - 1)
                        }
                        else{
                            //TODO return the results in a callback
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, " getMatchHistoryForPlayer Error.Response");
                        error.printStackTrace();
                    }
                }
        );

// add it to the RequestQueue
        mRequestQueue.add(getRequest);
        return null;
    }

    //TODO helper method to iterate through the matches
    public MatchHistoryResults getMatchHistoryForPlayer(final long account_id, final int num_results, long latest_match_id){
        String url = STEAM_API_BASE_URL + GET_MATCHES + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key) +
                "&" + ACCOUNT_ID + account_id;
        if(num_results > 0){
            url += "&" + MATCHES_REQEUSTED_NUMBER + num_results;
        }
        if(latest_match_id < 1){
            //return the results so far;
        }
        final List<HistoryMatch> matchList = new ArrayList<>();
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        Gson gson = new Gson();
                        MatchHistoryResults.MatchHistory results = gson.fromJson(response.toString(), MatchHistoryResults.MatchHistory.class);
                        matchList.addAll(results.getResult().getMatches());
                        Log.d(TAG, "onResponse: " + results.getResult().getNum_results());

                        if(results.getResult().hasMorePagedResults()){
                            getMatchHistoryForPlayer(account_id, num_results, results.getResult().getLastMatchId() - 1);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, " getMatchHistoryForPlayer Error.Response");
                        error.printStackTrace();
                    }
                }
        );

// add it to the RequestQueue
        mRequestQueue.add(getRequest);
        return null;
    }


    public MatchDetail getMatchDetail(long match_id){
        String url = STEAM_API_BASE_URL + GET_MATCH_DETAILS + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key) +
                "&match_id=" + match_id;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, " getMatchDetail Error.Response");
                        error.printStackTrace();
                    }
                }
        );

// add it to the RequestQueue
        mRequestQueue.add(getRequest);
        return null;
    }



    //Lookup all matches ever by match sequence
    public MatchSequenceResult getMatchSequenceByAccountId(long starting_seq_id, int matches_requested){
        String url = STEAM_API_BASE_URL + GET_MATCHES_BY_SEQUENCE + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key);
        if(starting_seq_id > 0){
            url += "&" + START_AT_MATCH_SEQ_NUM + starting_seq_id;
        }
        if(matches_requested > 0){
            url += "&" + MATCHES_REQEUSTED_NUMBER + matches_requested;
        }
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        Gson gson = new Gson();
                        MatchSequence sequence = gson.fromJson(response.toString(), MatchSequence.class);
                        MatchSequenceResult result = sequence.getResult();
                        Log.d(TAG, "onResponse: Match Sequence matches num " + result.getMatches().size());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, " getMatchDetail Error.Response");
                        error.printStackTrace();
                    }
                }
        );

// add it to the RequestQueue
        mRequestQueue.add(getRequest);
        return null;
    }
}
