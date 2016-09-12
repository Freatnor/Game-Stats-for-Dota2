package com.freatnor.game_stats_for_dota2.interfaces;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;

/**
 * Created by Jonathan Taylor on 9/12/16.
 */
public interface LatestMatchCallback {
    void onLatestMatchResponse(MatchDetail matchDetail);
}
