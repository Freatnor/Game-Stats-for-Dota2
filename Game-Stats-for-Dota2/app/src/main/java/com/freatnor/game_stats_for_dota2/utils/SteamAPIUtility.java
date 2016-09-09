package com.freatnor.game_stats_for_dota2.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.freatnor.game_stats_for_dota2.R;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistoryResults;

/**
 * Created by Jonathan Taylor on 9/8/16.
 */
public class SteamAPIUtility  {

    //URL constants
    //Used for all but specific user lookups
    public static final String STEAM_API_BASE_URL = "https://api.steampowered.com/IDOTA2Match_570/";

    //key parameter prefix
    public static final String STEAM_API_KEY_PARAMETER = "key=";

    //methods for steamapi for dota
    public static final String GET_MATCHES = "GetMatchHistory/V001/";
    public static final String GET_MATCH_DETAILS = "GetMatchDetails/V001/";
    public static final String GET_GAME_ITEMS = "GetGameItems/v1";
    public static final String GET_HEROES = "GetHeroes/v1";
    public static final String GET_ITEMS = "GetGameItems/v1";

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

    public SteamAPIUtility getInstance(Context context){
        if(sInstance == null){
            sInstance = new SteamAPIUtility(context);
        }
        return sInstance;
    }

    public MatchHistoryResults getMatchHistoryForPlayer(long account_id){
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
                        Log.d("Error.Response", response);
                    }
                }
        );

// add it to the RequestQueue
        mRequestQueue.add(getRequest);
    }


}
