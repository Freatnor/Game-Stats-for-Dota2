package com.freatnor.game_stats_for_dota2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerDetailCallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerDetailRequest;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerFollowedListener;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Jonathan Taylor on 9/7/16.
 */
public class PlayerDetailFragment extends Fragment implements PlayerDetailCallback{

    private static final String TAG = "PlayerDetailFragment";

    public static final int COMPETITIVE = 10;
    public static final int NORMAL = 20;

    private int mGameTypeFlag;

    private PlayerFollowedListener mListener;
    private PlayerDetailRequest mPlayerDetailRequest;


    private SteamPlayer mPlayer;
    private List<HistoryMatch> mMatches;

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


    public static PlayerDetailFragment getInstance(int gameTypeFlag, PlayerFollowedListener listener, PlayerDetailRequest request){
        PlayerDetailFragment fragment = new PlayerDetailFragment();
        fragment.mGameTypeFlag = gameTypeFlag;
        fragment.mListener = listener;
        fragment.mPlayerDetailRequest = request;
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

        mPlayerDetailRequest.requestPlayerInfo(this);


        mFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onPlayerFollowed(mPlayer.getSteamid());
            }
        });
    }

    @Override
    public void onPlayerReturned(SteamPlayer player) {
        //Picasso load the player icon
        mPlayer = player;

        Log.d(TAG, "onViewCreated: player image url " + mPlayer.getAvatarFull());
        Picasso.with(this.getContext()).load(mPlayer.getAvatarmedium().replace("https", "http").replace("medium", "full")).into(mPlayerIcon);

        mPlayerName.setText(mPlayer.getPersonaname());
        mPlayerID.setText(String.valueOf(mPlayer.getSteamid()));
    }

    @Override
    public void onMatchHistoryReturned(List<HistoryMatch> matches) {
        mMatches = matches;

    }
}
