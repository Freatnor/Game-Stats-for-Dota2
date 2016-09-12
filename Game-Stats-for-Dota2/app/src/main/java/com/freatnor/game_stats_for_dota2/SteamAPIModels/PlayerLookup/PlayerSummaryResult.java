package com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/12/16.
 */
public class PlayerSummaryResult {

    List<SteamPlayer> players;

    public PlayerSummaryResult() {
    }

    public List<SteamPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<SteamPlayer> players) {
        this.players = players;
    }


    public class PlayerSummaryResultContainer{
        private PlayerSummaryResult response;

        public PlayerSummaryResultContainer() {
        }

        public PlayerSummaryResult getResponse() {
            return response;
        }

        public void setResponse(PlayerSummaryResult response) {
            this.response = response;
        }
    }
}
