package com.freatnor.game_stats_for_dota2.SteamAPIModels.Hero;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/12/16.
 */
public class HeroResult {

    private int status;
    private int count;

    private List<Hero> heroes;

    public HeroResult() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    //container JSON class
    public class HeroResultContainer{

        private HeroResult result;

        public HeroResultContainer() {
        }

        public HeroResult getResult() {
            return result;
        }

        public void setResult(HeroResult result) {
            this.result = result;
        }
    }
}
