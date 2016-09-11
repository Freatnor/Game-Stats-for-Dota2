package com.freatnor.game_stats_for_dota2.interfaces;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;

import java.util.List;

/**
 * Interface for returns from the API calls
 * Created by Jonathan Taylor on 9/11/16.
 */
public interface APICallback {
    void onMatchHistoryResponse(List<HistoryMatch> matches);
    void onMatchDetailResponse(MatchDetail matchDetail);
    //TODO finish player search API callback
    void onPlayerSearchComplete();
}
