package com.freatnor.game_stats_for_dota2.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.freatnor.game_stats_for_dota2.Player;
import com.freatnor.game_stats_for_dota2.PlayerDetailFragment;

/**
 * Created by Jonathan Taylor on 9/7/16.
 */
public class PlayerDetailPagerAdapter extends FragmentPagerAdapter {

    //TODO change to correct Player type
    private Player mPlayer;
    private String[] sections;

    public PlayerDetailPagerAdapter(FragmentManager fm, Player player) {
        super(fm);
        mPlayer = player;
        sections = new String[]{"Competitive", "Normal"};
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PlayerDetailFragment.getInstance(mPlayer, PlayerDetailFragment.COMPETITIVE);
            case 1:
                return PlayerDetailFragment.getInstance(mPlayer, PlayerDetailFragment.NORMAL);
        }
        return null;
    }

    @Override
    public int getCount() {
        return sections.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sections[position];
    }
}
