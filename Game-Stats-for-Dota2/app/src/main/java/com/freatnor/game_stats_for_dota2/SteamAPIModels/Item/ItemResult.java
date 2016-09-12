package com.freatnor.game_stats_for_dota2.SteamAPIModels.Item;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/12/16.
 */
public class ItemResult {

    private int status;

    private List<Item> items;

    public ItemResult() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    //Container JSON class
    public class ItemResultContainer{
        private ItemResult result;

        public ItemResultContainer() {
        }

        public ItemResult getResult() {
            return result;
        }

        public void setResult(ItemResult result) {
            this.result = result;
        }
    }
}
