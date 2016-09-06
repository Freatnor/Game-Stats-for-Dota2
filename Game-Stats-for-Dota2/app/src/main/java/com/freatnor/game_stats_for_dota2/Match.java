package com.freatnor.game_stats_for_dota2;

public class Match{
    public boolean mIsWin;
    public String mHeroPortraitUrl;
    public String mHeroName;
    public String mItemIconUrl;
    public int mDuration;
    public int mKills;
    public int mDeaths;
    public int mAssists;

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
