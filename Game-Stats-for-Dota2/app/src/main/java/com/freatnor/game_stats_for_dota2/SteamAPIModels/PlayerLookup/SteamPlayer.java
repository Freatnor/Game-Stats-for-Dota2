package com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;

/**
 * Created by Jonathan Taylor on 9/12/16.
 */
public class SteamPlayer {

    private long steamid;
    private int communityvisibilitystate;
    private int profilestate;
    private String personaname;
    private long lastlogoff;
    private String profileurl;
    private String avatar;
    private String avatarmedium;
    private String avatarFull;
    private int personastate;
    private String realname;
    private String primaryclanid;
    private long timecreated;
    private int personaflags;

    //For use in search results
    private MatchDetail mLatestMatch;
    private boolean mUnableToSeeMatches = false;

    public SteamPlayer() {
    }

    public long getSteamid() {
        return steamid;
    }

    public void setSteamid(long steamid) {
        this.steamid = steamid;
    }

    public int getCommunityvisibilitystate() {
        return communityvisibilitystate;
    }

    public void setCommunityvisibilitystate(int communityvisibilitystate) {
        this.communityvisibilitystate = communityvisibilitystate;
    }

    public int getProfilestate() {
        return profilestate;
    }

    public void setProfilestate(int profilestate) {
        this.profilestate = profilestate;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public long getLastlogoff() {
        return lastlogoff;
    }

    public void setLastlogoff(long lastlogoff) {
        this.lastlogoff = lastlogoff;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarmedium() {
        return avatarmedium;
    }

    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    public String getAvatarFull() {
        return avatarFull;
    }

    public void setAvatarFull(String avatarFull) {
        this.avatarFull = avatarFull;
    }

    public int getPersonastate() {
        return personastate;
    }

    public void setPersonastate(int personastate) {
        this.personastate = personastate;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPrimaryclanid() {
        return primaryclanid;
    }

    public void setPrimaryclanid(String primaryclanid) {
        this.primaryclanid = primaryclanid;
    }

    public long getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(long timecreated) {
        this.timecreated = timecreated;
    }

    public int getPersonaflags() {
        return personaflags;
    }

    public void setPersonaflags(int personaflags) {
        this.personaflags = personaflags;
    }

    public MatchDetail getLatestMatch() {
        return mLatestMatch;
    }

    public void setLatestMatch(MatchDetail latestMatch) {
        mLatestMatch = latestMatch;
    }

    public boolean isUnableToSeeMatches() {
        return mUnableToSeeMatches;
    }

    public void setUnableToSeeMatches(boolean unableToSeeMatches) {
        mUnableToSeeMatches = unableToSeeMatches;
    }
}
