package com.freatnor.game_stats_for_dota2.SteamAPIModels.Item;

/**
 * Created by Jonathan Taylor on 9/12/16.
 */
public class Item {

    private int id;
    private String name;
    private int cost;
    private boolean secret_shop;
    private boolean side_shop;
    private boolean recipe;
    private String localized_name;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isSecret_shop() {
        return secret_shop;
    }

    public void setSecret_shop(boolean secret_shop) {
        this.secret_shop = secret_shop;
    }

    public boolean isSide_shop() {
        return side_shop;
    }

    public void setSide_shop(boolean side_shop) {
        this.side_shop = side_shop;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }

    public String getLocalized_name() {
        return localized_name;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }
}
