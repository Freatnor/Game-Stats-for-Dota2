package com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchSequenceHistory;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/11/16.
 */
public class SequenceMatch {

    private boolean radiant_win;
    private int duration;
    private int pre_game_duration;
    private long start_time;
    private long match_id;
    private long match_seq_num;
    private int tower_status_radiant;
    private int tower_status_dire;
    private int barracks_status_radiant;
    private int barracks_status_dire;
    private int cluster;
    private int first_blood_time;
    private int lobby_type;
    private int human_players;
    private long leagueid;
    private long positive_votes;
    private long negative_votes;
    private int game_mode;
    private int engine;
    private int radiant_score;
    private int dire_score;

    private List<SequencePlayer> players;

    public SequenceMatch() {
    }

    public boolean isRadiant_win() {
        return radiant_win;
    }

    public void setRadiant_win(boolean radiant_win) {
        this.radiant_win = radiant_win;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPre_game_duration() {
        return pre_game_duration;
    }

    public void setPre_game_duration(int pre_game_duration) {
        this.pre_game_duration = pre_game_duration;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(long match_id) {
        this.match_id = match_id;
    }

    public long getMatch_seq_num() {
        return match_seq_num;
    }

    public void setMatch_seq_num(long match_seq_num) {
        this.match_seq_num = match_seq_num;
    }

    public int getTower_status_radiant() {
        return tower_status_radiant;
    }

    public void setTower_status_radiant(int tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
    }

    public int getTower_status_dire() {
        return tower_status_dire;
    }

    public void setTower_status_dire(int tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
    }

    public int getBarracks_status_radiant() {
        return barracks_status_radiant;
    }

    public void setBarracks_status_radiant(int barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
    }

    public int getBarracks_status_dire() {
        return barracks_status_dire;
    }

    public void setBarracks_status_dire(int barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getFirst_blood_time() {
        return first_blood_time;
    }

    public void setFirst_blood_time(int first_blood_time) {
        this.first_blood_time = first_blood_time;
    }

    public int getLobby_type() {
        return lobby_type;
    }

    public void setLobby_type(int lobby_type) {
        this.lobby_type = lobby_type;
    }

    public int getHuman_players() {
        return human_players;
    }

    public void setHuman_players(int human_players) {
        this.human_players = human_players;
    }

    public long getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(long leagueid) {
        this.leagueid = leagueid;
    }

    public long getPositive_votes() {
        return positive_votes;
    }

    public void setPositive_votes(long positive_votes) {
        this.positive_votes = positive_votes;
    }

    public long getNegative_votes() {
        return negative_votes;
    }

    public void setNegative_votes(long negative_votes) {
        this.negative_votes = negative_votes;
    }

    public int getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(int game_mode) {
        this.game_mode = game_mode;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getRadiant_score() {
        return radiant_score;
    }

    public void setRadiant_score(int radiant_score) {
        this.radiant_score = radiant_score;
    }

    public int getDire_score() {
        return dire_score;
    }

    public void setDire_score(int dire_score) {
        this.dire_score = dire_score;
    }

    public List<SequencePlayer> getPlayers() {
        return players;
    }


    //get the total gold
    public void setPlayers(List<SequencePlayer> players) {
        this.players = players;
    }
}
