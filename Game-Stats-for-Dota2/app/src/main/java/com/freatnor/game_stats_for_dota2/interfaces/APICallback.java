package com.freatnor.game_stats_for_dota2.interfaces;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;

import java.util.List;

/**
 * Interface for returns from the API calls
 * Created by Jonathan Taylor on 9/11/16.
 */
public interface APICallback {
    void onMatchHistoryResponse(List<HistoryMatch> matches);
    void onMatchDetailResponse(MatchDetail matchDetail);
    //null in player means no match
    void onPlayerSearchComplete(SteamPlayer player);
}
