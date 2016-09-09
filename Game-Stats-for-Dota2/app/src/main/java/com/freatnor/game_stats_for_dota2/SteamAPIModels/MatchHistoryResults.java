package com.freatnor.game_stats_for_dota2.SteamAPIModels;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class MatchHistoryResults {
    private int result;
    private String statusDetail;
    private int num_results;
    private int total_results;
    private int results_remaining;
    private List<HistoryMatch> matches;

    public MatchHistoryResults(int result, String statusDetail, int num_results, int total_results, int results_remaining, List<HistoryMatch> matches) {
        this.result = result;
        this.statusDetail = statusDetail;
        this.num_results = num_results;
        this.total_results = total_results;
        this.results_remaining = results_remaining;
        this.matches = matches;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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
}
