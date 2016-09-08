package com.freatnor.game_stats_for_dota2.utils;

import android.content.Context;

import com.android.volley.RequestQueue;

/**
 * Created by Jonathan Taylor on 9/8/16.
 */
public class SteamAPIUtility  {

    private RequestQueue mRequestQueue;
    private Context mContext =

    //Singleton stuff
    private static SteamAPIUtility sInstance;

    private SteamAPIUtility(Context context){
        mContext = context.getApplicationContext();
    }

    public SteamAPIUtility getInstance(Context context){
        if(sInstance == null){
            sInstance = new SteamAPIUtility(context);
        }
        return sInstance;
    }



}
