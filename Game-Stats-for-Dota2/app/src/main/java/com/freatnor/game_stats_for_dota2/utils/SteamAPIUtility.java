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
import com.freatnor.game_stats_for_dota2.SteamAPIModels.Hero.Hero;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.Hero.HeroResult;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.Item.Item;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.Item.ItemResult;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.MatchHistoryResults;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchSequenceHistory.MatchSequence;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchSequenceHistory.MatchSequenceResult;
import com.freatnor.game_stats_for_dota2.interfaces.APICallback;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static final String LANGUAGE = "language=";

    //Used for user specific lookups
    public static final String STEAM_USER_API_BASE_URL = "http://api.steampowered.com/ISteamUser/";

    //methods for steam user api
    public static final String USER_PROFILE = "GetPlayerSummaries/v0002/";
    public static final String SEARCH_VANITY_URL = "ResolveVanityURL/v0001/";


    //Special urls for hero, ability, item images
    public static final String ITEM_IMAGE_URL = "http://cdn.dota2.com/apps/dota2/images/items/%s_lg.png";
    public static final String HERO_BASE_URL = "http://cdn.dota2.com/apps/dota2/images/heroes/";
    public static final String LARGE_IMAGE_SUFFIX = "_lg.png";
    public static final String HERO_FULL_HORIZONTAL_SUFFIX = "_full.png";

    private RequestQueue mRequestQueue;
    private Context mContext;

    //reference maps for the heroes and items (stay up to date with the API)
    private Map<Integer, Hero> mHeroMap;
    private Map<Integer, Item> mItemMap;

    //Singleton stuff
    private static SteamAPIUtility sInstance;

    private SteamAPIUtility(Context context){
        mContext = context.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(mContext);

        //initialize the hero and item maps
        getHeroes();
        getItems();
    }


    public static SteamAPIUtility getInstance(Context context){
        if(sInstance == null){
            sInstance = new SteamAPIUtility(context);
        }
        return sInstance;
    }


    private void getItems() {
        String url = STEAM_API_BASE_URL + GET_ITEMS + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key)
                + "?" + LANGUAGE + "en";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        Gson gson = new Gson();
                        ItemResult.ItemResultContainer container = gson.fromJson(response.toString(),
                                ItemResult.ItemResultContainer.class);
                        List<Item> items = container.getResult().getItems();
                        for (int i = 0; i < items.size(); i++) {
                            mItemMap.put(items.get(i).getId(), items.get(i));
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, " getMatchDetail Error.Response - Unable to get Items List");
                        error.printStackTrace();
                    }
                }
        );

// add it to the RequestQueue
        mRequestQueue.add(getRequest);
    }

    private void getHeroes() {
        String url = STEAM_API_BASE_URL + GET_HEROES + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key)
                + "?" + LANGUAGE + "en";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        Gson gson = new Gson();
                        HeroResult.HeroResultContainer container = gson.fromJson(response.toString(),
                                HeroResult.HeroResultContainer.class);
                        List<Hero> heroes = container.getResult().getHeroes();
                        for (int i = 0; i < heroes.size(); i++) {
                            mHeroMap.put(heroes.get(i).getId(), heroes.get(i));
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, " getMatchDetail Error.Response - Unable to get Items List");
                        error.printStackTrace();
                    }
                }
        );

        mRequestQueue.add(getRequest);
    }

    public String getHeroImageUrl(int id){
        String heroName = mHeroMap.get(id).getName().replace("npc_dota_hero_", "");
        return HERO_BASE_URL + heroName + LARGE_IMAGE_SUFFIX;
    }

    public String getHeroName(int id){
        return mHeroMap.get(id).getLocalized_name();
    }

    public String getItemImageUrl(int id){
        String itemName = mItemMap.get(id).getName().replace("item_", "");
        return ITEM_IMAGE_URL + itemName + LARGE_IMAGE_SUFFIX;
    }

    public String getItemName(int id){
        return mItemMap.get(id).getLocalized_name();
    }



    //method to get the list of matches for a player. Pass in 0 to get the default number of matches
    public void getMatchHistoryForPlayer(final long account_id, final int num_results, final APICallback callback){
        String url = STEAM_API_BASE_URL + GET_MATCHES + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key) +
                "&" + ACCOUNT_ID + convert64IdTo32(account_id);
        if(num_results > 0){
            url += "&" + MATCHES_REQEUSTED_NUMBER + num_results;
        }
        Log.d(TAG, "getMatchHistoryForPlayer: request url = " + url);
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
                            getMatchHistoryForPlayer(account_id, num_results, results.getResult().getLastMatchId() - 1, 0, callback);
                        }
                        else{
                            //TODO return the results in a callback
                            callback.onMatchHistoryResponse(matchList);
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
    }

    //TODO helper method to iterate through the matches
    //Looks like the date_max field isn't working...
    public void getMatchHistoryForPlayer(final long account_id, final int num_results,
                                                        long latest_match_id, final long max_unix_timestamp, final APICallback callback){
        String url = STEAM_API_BASE_URL + GET_MATCHES + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key) +
                "&" + ACCOUNT_ID + convert64IdTo32(account_id);
        if(num_results > 0){
            url += "&" + MATCHES_REQEUSTED_NUMBER + num_results;
        }
        if(latest_match_id < 1){
            //return the results so far;
        }
        else{
            url += "&" + START_AT_MATCH_ID + latest_match_id;
        }
        //if there's a timestamp include it to back in time!
        if(max_unix_timestamp > 0){
            url += "&" + DATE_MAX + max_unix_timestamp;
        }
        Log.d(TAG, "getMatchHistoryForPlayer: request url = " + url);
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
                            getMatchHistoryForPlayer(account_id, num_results, results.getResult().getLastMatchId() - 1, max_unix_timestamp, callback);
                        }
                        //if there were no results from the last call return what we have
                        else if(results.getResult().getTotal_results() < 500 && results.getResult().getResults_remaining() < 1){
                            callback.onMatchHistoryResponse(matchList);
                        }
                        //continue to earlier dates by taking one less than the last date and one less than the last match id
                        else{
                            getMatchHistoryForPlayer(account_id, num_results, results.getResult().getLastMatchId() - 1, results.getResult().getLastMatchTimestamp() - 1, callback);
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
    }

    //method to return a match's details
    public void getMatchDetail(long match_id, final APICallback callback){
        String url = STEAM_API_BASE_URL + GET_MATCH_DETAILS + "?" + STEAM_API_KEY_PARAMETER + mContext.getResources().getString(R.string.steam_api_key) +
                "&match_id=" + match_id;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        Gson gson = new Gson();
                        MatchDetail.MatchDetailContainer container = gson.fromJson(response.toString(),
                                MatchDetail.MatchDetailContainer.class);
                        callback.onMatchDetailResponse(container.getResult());
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




    //util method to convert the 64 bit ID to 32
    public long convert64IdTo32(long id){
        return id - 76561197960265728L;
    }
}
