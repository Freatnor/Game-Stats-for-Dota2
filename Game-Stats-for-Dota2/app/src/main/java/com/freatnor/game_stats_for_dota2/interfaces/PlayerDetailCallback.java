package com.freatnor.game_stats_for_dota2.interfaces;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/13/16.
 */
public interface PlayerDetailCallback {
    void onPlayerReturned(SteamPlayer player);
    void onMatchHistoryReturned(List<HistoryMatch> matches);

}
