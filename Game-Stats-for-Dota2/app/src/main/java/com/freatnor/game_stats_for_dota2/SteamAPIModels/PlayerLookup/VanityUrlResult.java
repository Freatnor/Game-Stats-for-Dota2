package com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup;

/**
 * Created by Jonathan Taylor on 9/12/16.
 */
public class VanityUrlResult {

    //VanityURL IDs are 64 (need to be converted to 32)
    private long steamid;
    private int success;

    public VanityUrlResult() {
    }

    public long getSteamid() {
        return steamid;
    }

    public void setSteamid(long steamid) {
        this.steamid = steamid;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public boolean noMatch(){
        return success == 42;
    }

    public class VanityUrlResultContainer{
        private VanityUrlResult result;

        public VanityUrlResultContainer() {
        }

        public VanityUrlResult getResult() {
            return result;
        }

        public void setResult(VanityUrlResult result) {
            this.result = result;
        }
    }
}
