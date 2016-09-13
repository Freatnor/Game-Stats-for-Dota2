package com.freatnor.game_stats_for_dota2.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.freatnor.game_stats_for_dota2.Player;
import com.freatnor.game_stats_for_dota2.PlayerDetailFragment;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerDetailRequest;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerFollowedListener;

/**
 * Created by Jonathan Taylor on 9/7/16.
 */
public class PlayerDetailPagerAdapter extends FragmentPagerAdapter {


    private String[] sections;

    private PlayerFollowedListener mListener;
    private PlayerDetailRequest mPlayerDetailRequest;

    public PlayerDetailPagerAdapter(FragmentManager fm, PlayerFollowedListener listener, PlayerDetailRequest request) {
        super(fm);
        sections = new String[]{"Competitive"};//, "Normal"};
        mListener = listener;
        mPlayerDetailRequest = request;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PlayerDetailFragment.getInstance(PlayerDetailFragment.COMPETITIVE, mListener,
                        mPlayerDetailRequest);
            case 1:
                return PlayerDetailFragment.getInstance(PlayerDetailFragment.NORMAL, mListener,
                        mPlayerDetailRequest);
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
