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

import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchDetail;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchPlayer;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchHistory.HistoryMatch;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;
import com.freatnor.game_stats_for_dota2.interfaces.APICallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerDetailCallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerDetailRequest;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerFollowedListener;
import com.freatnor.game_stats_for_dota2.utils.SteamAPIUtility;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Jonathan Taylor on 9/7/16.
 */
public class PlayerDetailFragment extends Fragment implements PlayerDetailCallback, APICallback{

    private static final String TAG = "PlayerDetailFragment";

    public static final int COMPETITIVE = 10;
    public static final int NORMAL = 20;

    private int mGameTypeFlag;

    private PlayerFollowedListener mListener;
    private PlayerDetailRequest mPlayerDetailRequest;


    private SteamPlayer mPlayer;
    private List<HistoryMatch> mMatches;
    private long id32;

    //int fields
    private long totalTime;
    private int wins;
    private int losses;
    private int kills;
    private int deaths;
    private int assists;
    private double winPercentage;

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
    private SteamAPIUtility mUtility;


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

        mUtility = SteamAPIUtility.getInstance(this.getContext());

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
        id32 = mUtility.convert64IdTo32(mPlayer.getSteamid());

        Log.d(TAG, "onViewCreated: player image url " + mPlayer.getAvatarFull());
        Picasso.with(this.getContext()).load(mPlayer.getAvatarmedium().replace("https", "http").replace("medium", "full")).into(mPlayerIcon);

        mPlayerName.setText(mPlayer.getPersonaname());
        mPlayerID.setText(String.valueOf(mPlayer.getSteamid()));
    }

    @Override
    public void onMatchHistoryReturned(List<HistoryMatch> matches) {
        mMatches = matches;
        mTotalGames.setText(String.valueOf(mMatches.size()));
        for (int i = 0; i < mMatches.size(); i++) {
            HistoryMatch match = mMatches.get(i);
            mUtility.getMatchDetail(match.getMatch_id(), this);
        }

    }

    @Override
    public void onMatchHistoryResponse(List<HistoryMatch> matches) {
        //nothing
    }

    @Override
    public void onMatchDetailResponse(MatchDetail matchDetail) {
        Log.d(TAG, "onMatchDetailResponse: for match " + matchDetail.getMatch_id());
        totalTime += matchDetail.getDuration();
        mTotalTime.setText(String.valueOf(totalTime));

        MatchPlayer matchPlayer = null;
        if(matchDetail != null) {
            for (int i = 0; i < matchDetail.getPlayers().size(); i++) {
                long tempId = matchDetail.getPlayers().get(i).getAccount_id();
                if (tempId == id32) {
                    matchPlayer = matchDetail.getPlayers().get(i);
                }
            }
        }

        if(matchPlayer != null) {
            kills += matchPlayer.getKills();
            deaths += matchPlayer.getDeaths();
            assists += matchPlayer.getAssists();
            if(matchDetail.isRadiant_win() && matchPlayer.getPlayer_slot() < 5){
                wins++;
            }
            else{
                losses++;
            }
            winPercentage = wins / losses;

            mKills.setText(String.valueOf(kills));
            mDeaths.setText(String.valueOf(deaths));
            mAssists.setText(String.valueOf(assists));
            mWins.setText(String.valueOf(wins));
            mLosses.setText(String.valueOf(losses));
            mWinPercentage.setText(String.valueOf(winPercentage));
        }

    }

    @Override
    public void onPlayerSearchComplete(SteamPlayer player, boolean forSearch) {
        //nothing
    }
}
