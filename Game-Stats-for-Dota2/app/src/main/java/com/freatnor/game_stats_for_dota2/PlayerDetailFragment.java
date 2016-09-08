package com.freatnor.game_stats_for_dota2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by Jonathan Taylor on 9/7/16.
 */
public class PlayerDetailFragment extends Fragment{

    public static final int COMPETITIVE = 10;
    public static final int NORMAL = 20;

    private int mGameTypeFlag;

    //TODO change to the correct player object
    private Player mPlayer;

    private ImageView mPlayerIcon;

    private Button mFollowButton;

    private TextView mPlayerName;
    private TextView mPlayerID;
    private TextView mTotalGames;
    private TextView mTotalTime;
    private TextView mLastPlayed;
    private TextView mWins;
    private TextView mLosses;
    private TextView mWinPercentage;
    private TextView mKills;
    private TextView mDeaths;
    private TextView mAssists;

    //For the future
    private TextView mSoloMMR;
    private TextView mPartyMMR;

    public static PlayerDetailFragment getInstance(Player player, int gameTypeFlag){
        PlayerDetailFragment fragment = new PlayerDetailFragment();
        fragment.mPlayer = player;
        fragment.mGameTypeFlag = gameTypeFlag;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_player_detail, container, false);
        mPlayerIcon = (ImageView) fragmentView.findViewById(R.id.player_icon);

        mFollowButton = (Button) fragmentView.findViewById(R.id.follow_button);

        mPlayerName = (TextView) fragmentView.findViewById(R.id.player_name);
        mPlayerID = (TextView) fragmentView.findViewById(R.id.steam_id);
        mTotalGames = (TextView) fragmentView.findViewById(R.id.total_games);
        mTotalTime = (TextView) fragmentView.findViewById(R.id.total_time);
        mLastPlayed = (TextView) fragmentView.findViewById(R.id.last_played_time);
        mWins = (TextView) fragmentView.findViewById(R.id.player_career_wins);
        mLosses = (TextView) fragmentView.findViewById(R.id.player_career_losses);
        mWinPercentage = (TextView) fragmentView.findViewById(R.id.player_career_win_percentage);
        mKills = (TextView) fragmentView.findViewById(R.id.player_career_kills);
        mDeaths = (TextView) fragmentView.findViewById(R.id.player_career_deaths);
        mAssists = (TextView) fragmentView.findViewById(R.id.player_career_assists);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO bind all the data

        //Picasso load the player icon
        Picasso.with(this.getContext()).load(mPlayer.mImageUrl).into(mPlayerIcon);

        mPlayerName.setText(mPlayer.mName);
        mPlayerID.setText(mPlayer.mPlayerId + "");
        
    }
}
