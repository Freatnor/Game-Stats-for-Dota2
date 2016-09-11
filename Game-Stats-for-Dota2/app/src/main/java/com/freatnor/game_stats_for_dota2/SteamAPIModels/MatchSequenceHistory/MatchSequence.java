package com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchSequenceHistory;

/**
 * Created by Jonathan Taylor on 9/11/16.
 */
public class MatchSequence {
    private MatchSequenceResult result;

    public MatchSequence() {
    }

    public MatchSequenceResult getResult() {
        return result;
    }

    public void setResult(MatchSequenceResult result) {
        this.result = result;
    }
}
