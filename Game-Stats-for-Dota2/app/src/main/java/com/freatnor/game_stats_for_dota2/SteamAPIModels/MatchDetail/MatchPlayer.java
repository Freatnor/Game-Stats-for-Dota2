package com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class MatchPlayer {
    private long account_id;
    private int player_slot;
    private int hero_id;
    private int item_0;
    private int item_1;
    private int item_2;
    private int item_3;
    private int item_4;
    private int item_5;
    private int kills;
    private int deaths;
    private int assists;
    private int leaver_status;
    private int last_hits;
    private int denies;
    private int gold_per_minute;
    private int xp_per_minute;
    private int level;
    private int hero_damage;
    private int tower_damage;
    private int hero_healing;
    private int gold;
    private int gold_spent;
    private int scaled_hero_damage;
    private int scaled_tower_damage;
    private int scaled_hero_healing;

    private String playerName;

    private List<AbilityUpgrade> ability_upgrades;

    public MatchPlayer() {
        //priming the value
        playerName = "";
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

    public int getItem_0() {
        return item_0;
    }

    public void setItem_0(int item_0) {
        this.item_0 = item_0;
    }

    public int getItem_1() {
        return item_1;
    }

    public void setItem_1(int item_1) {
        this.item_1 = item_1;
    }

    public int getItem_2() {
        return item_2;
    }

    public void setItem_2(int item_2) {
        this.item_2 = item_2;
    }

    public int getItem_3() {
        return item_3;
    }

    public void setItem_3(int item_3) {
        this.item_3 = item_3;
    }

    public int getItem_4() {
        return item_4;
    }

    public void setItem_4(int item_4) {
        this.item_4 = item_4;
    }

    public int getItem_5() {
        return item_5;
    }

    public void setItem_5(int item_5) {
        this.item_5 = item_5;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getLeaver_status() {
        return leaver_status;
    }

    public void setLeaver_status(int leaver_status) {
        this.leaver_status = leaver_status;
    }

    public int getLast_hits() {
        return last_hits;
    }

    public void setLast_hits(int last_hits) {
        this.last_hits = last_hits;
    }

    public int getDenies() {
        return denies;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public int getGold_per_minute() {
        return gold_per_minute;
    }

    public void setGold_per_minute(int gold_per_minute) {
        this.gold_per_minute = gold_per_minute;
    }

    public int getXp_per_minute() {
        return xp_per_minute;
    }

    public void setXp_per_minute(int xp_per_minute) {
        this.xp_per_minute = xp_per_minute;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHero_damage() {
        return hero_damage;
    }

    public void setHero_damage(int hero_damage) {
        this.hero_damage = hero_damage;
    }

    public int getTower_damage() {
        return tower_damage;
    }

    public void setTower_damage(int tower_damage) {
        this.tower_damage = tower_damage;
    }

    public int getHero_healing() {
        return hero_healing;
    }

    public void setHero_healing(int hero_healing) {
        this.hero_healing = hero_healing;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold_spent() {
        return gold_spent;
    }

    public void setGold_spent(int gold_spent) {
        this.gold_spent = gold_spent;
    }

    public int getScaled_hero_damage() {
        return scaled_hero_damage;
    }

    public void setScaled_hero_damage(int scaled_hero_damage) {
        this.scaled_hero_damage = scaled_hero_damage;
    }

    public int getScaled_tower_damage() {
        return scaled_tower_damage;
    }

    public void setScaled_tower_damage(int scaled_tower_damage) {
        this.scaled_tower_damage = scaled_tower_damage;
    }

    public int getScaled_hero_healing() {
        return scaled_hero_healing;
    }

    public void setScaled_hero_healing(int scaled_hero_healing) {
        this.scaled_hero_healing = scaled_hero_healing;
    }

    public List<AbilityUpgrade> getAbility_upgrades() {
        return ability_upgrades;
    }

    public void setAbility_upgrades(List<AbilityUpgrade> ability_upgrades) {
        this.ability_upgrades = ability_upgrades;
    }

    public int getTotalGold(){
        return gold + gold_spent;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

