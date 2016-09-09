package com.freatnor.game_stats_for_dota2;

/**
 * Created by Jonathan Taylor on 9/1/16.
 * dummy class for use in the search results adapter
 */
public class Player {
    public String mImageUrl;
    public String mName;
    public int mLastPlayed;

    public Match mLastPlayedMatch;

    long mPlayerId;

    public Player(String mImageUrl, String mName, int mLastPlayed, long playerId, Match mLastPlayedMatch) {
        this.mImageUrl = mImageUrl;
        this.mName = mName;
        this.mLastPlayed = mLastPlayed;
        this.mLastPlayedMatch = mLastPlayedMatch;
        this.mPlayerId = playerId;
    }
}

