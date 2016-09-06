package com.freatnor.game_stats_for_dota2;

/**
 * Created by Jonathan Taylor on 9/1/16.
 * dummy class for use in the search results adapter
 */
public class Player {
    String mImageUrl;
    String mName;
    int mLastPlayed;

    Match mLastPlayedMatch;

    int mPlayerId;

    public Player(String mImageUrl, String mName, int mLastPlayed, Match mLastPlayedMatch) {
        this.mImageUrl = mImageUrl;
        this.mName = mName;
        this.mLastPlayed = mLastPlayed;
        this.mLastPlayedMatch = mLastPlayedMatch;
    }
}

class Match{
    boolean mIsWin;
    String mHeroPortraitUrl;
    String mHeroName;
    String mItemIconUrl;
    int mDuration;
    int mKills;
    int mDeaths;
    int mAssists;

    int mMatchId;

    public Match(boolean isWin, String heroPortraitUrl, String heroName, String itemIconUrl, int duration, int kills, int deaths, int assists) {
        mIsWin = isWin;
        mHeroPortraitUrl = heroPortraitUrl;
        mHeroName = heroName;
        mItemIconUrl = itemIconUrl;
        mDuration = duration;
        mKills = kills;
        mDeaths = deaths;
        mAssists = assists;
    }
}
