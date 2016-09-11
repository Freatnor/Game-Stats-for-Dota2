package com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class MatchPlayer {
    private long account_id;
    private int player_slot;
    private int hero_id;

    public MatchPlayer() {
    }

    public MatchPlayer(long account_id, int player_slot, int hero_id) {
        this.account_id = account_id;
        this.player_slot = player_slot;
        this.hero_id = hero_id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public int getPlayer_slot() {
        return player_slot;
    }

    public void setPlayer_slot(int player_slot) {
        this.player_slot = player_slot;
    }

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }
}
