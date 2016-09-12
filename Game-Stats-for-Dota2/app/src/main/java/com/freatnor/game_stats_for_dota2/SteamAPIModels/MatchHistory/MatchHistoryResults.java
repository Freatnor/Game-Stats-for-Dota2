package com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class MatchHistoryResults {
    private String statusDetail;
    private int num_results;
    private int total_results;
    private int results_remaining;
    private List<HistoryMatch> matches;

    public MatchHistoryResults() {
    }

    public MatchHistoryResults(int result, String statusDetail, int num_results, int total_results, int results_remaining, List<HistoryMatch> matches) {
        this.statusDetail = statusDetail;
        this.num_results = num_results;
        this.total_results = total_results;
        this.results_remaining = results_remaining;
        this.matches = matches;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getResults_remaining() {
        return results_remaining;
    }

    public void setResults_remaining(int results_remaining) {
        this.results_remaining = results_remaining;
    }

    public List<HistoryMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<HistoryMatch> matches) {
        this.matches = matches;
    }

    public boolean hasMorePagedResults(){
        return results_remaining > 0;
    }

    public long getLastMatchId(){
        return matches.get(matches.size() - 1).getMatch_id();
    }

    public long getLastMatchTimestamp(){
        return matches.get(matches.size() - 1).getStart_time();
    }

    //parent wrapping object
    public class MatchHistory{
        private MatchHistoryResults result;

        public MatchHistory() {
        }

        public MatchHistory(MatchHistoryResults result) {
            this.result = result;
        }

        public MatchHistoryResults getResult() {
            return result;
        }

        public void setResult(MatchHistoryResults result) {
            this.result = result;
        }
    }
}
