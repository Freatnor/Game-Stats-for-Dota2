package com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class HistoryMatch {
    private long match_id;
    private long match_sequence_number;
    private long start_time;
    private int lobby_type;
    private List<HistoryPlayer> players;

    private long radiant_team_id;
    private long dire_team_id;

    public HistoryMatch() {
    }

    public HistoryMatch(long match_id, long match_sequence_number, long start_time, int lobby_type, List<HistoryPlayer> players, long radiant_team_id, long dire_team_id) {
        this.match_id = match_id;
        this.match_sequence_number = match_sequence_number;
        this.start_time = start_time;
        this.lobby_type = lobby_type;
        this.players = players;
        this.radiant_team_id = radiant_team_id;
        this.dire_team_id = dire_team_id;
    }

    public long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(long match_id) {
        this.match_id = match_id;
    }

    public long getMatch_sequence_number() {
        return match_sequence_number;
    }

    public void setMatch_sequence_number(long match_sequence_number) {
        this.match_sequence_number = match_sequence_number;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public int getLobby_type() {
        return lobby_type;
    }

    public void setLobby_type(int lobby_type) {
        this.lobby_type = lobby_type;
    }

    public List<HistoryPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<HistoryPlayer> players) {
        this.players = players;
    }

    public long getRadiant_team_id() {
        return radiant_team_id;
    }

    public void setRadiant_team_id(long radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
    }

    public long getDire_team_id() {
        return dire_team_id;
    }

    public void setDire_team_id(long dire_team_id) {
        this.dire_team_id = dire_team_id;
    }
}
