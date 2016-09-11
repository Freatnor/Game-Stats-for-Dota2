package com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchSequenceHistory;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/11/16.
 */
public class MatchSequenceResult {

    private int status;
    private List<SequenceMatch> matches;

    public MatchSequenceResult() {
    }

    public MatchSequenceResult(int status, List<SequenceMatch> matches) {
        this.status = status;
        this.matches = matches;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SequenceMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<SequenceMatch> matches) {
        this.matches = matches;
    }

    public long getFinalMatchId(){
        return matches.get(matches.size() - 1).getMatch_seq_num();
    }
}
