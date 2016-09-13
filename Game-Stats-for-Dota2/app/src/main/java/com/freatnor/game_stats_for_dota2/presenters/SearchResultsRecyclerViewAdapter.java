package com.freatnor.game_stats_for_dota2.presenters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freatnor.game_stats_for_dota2.R;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchDetail.MatchPlayer;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.PlayerLookup.SteamPlayer;
import com.freatnor.game_stats_for_dota2.interfaces.MatchCallback;
import com.freatnor.game_stats_for_dota2.interfaces.PlayerCallback;
import com.freatnor.game_stats_for_dota2.utils.SteamAPIUtility;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class SearchResultsRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultsViewHolder>{

    private List<SteamPlayer> mPlayers;

    private MatchCallback mMatchCallback;
    private PlayerCallback mPlayerCallback;

    private SteamAPIUtility mUtility;

    public SearchResultsRecyclerViewAdapter(List<SteamPlayer> players, MatchCallback matchCallback, PlayerCallback playerCallback) {
        mPlayers = players;
        mMatchCallback = matchCallback;
        mPlayerCallback = playerCallback;
    }

    @Override
    public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_search_result_entry, parent, false);
        return new SearchResultsViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final SearchResultsViewHolder holder, final int position) {
        SteamPlayer player = mPlayers.get(position);

        if(mUtility == null){
            mUtility = SteamAPIUtility.getInstance(holder.getContext());
        }

        //OnClick listener that either opens the bottom section or gives player object to callback
        //TODO change to correctly get the IDs
        holder.setTopOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.bottomIsToggled()){
                    holder.toggleBottom();
                }
                else{
                    //mPlayerCallback.playerSelected(player.getId());

                    //dummy ID
                    mPlayerCallback.playerSelected(mPlayers.get(position).getSteamid());
                }
            }
        });
        holder.setBottomOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mMatchCallback.matchSelected(player.getLatestMatch().getId());

                //DummyID
                mMatchCallback.matchSelected(mPlayers.get(position).getLatestMatch().getMatch_id());
            }
        });

        //get the correct matchPlayer for the match info
        MatchPlayer matchPlayer = null;
        for (int i = 0; i < player.getLatestMatch().getPlayers().size(); i++) {
            long tempId = player.getLatestMatch().getPlayers().get(i).getAccount_id();
            if(mUtility.convert32IdTo64(tempId) == player.getSteamid()){
                matchPlayer = player.getLatestMatch().getPlayers().get(i);
            }
        }

        if(matchPlayer != null) {
            //TODO change these to use correct player object format from API
            holder.setHeroName(mUtility.getHeroName(matchPlayer.getHero_id()));
            holder.setHeroPortrait(mUtility.getHeroImageUrl(matchPlayer.getHero_id()));
            //set the image icons (the indexes are offset here...)
            holder.setItemIcon1(mUtility.getItemImageUrl(matchPlayer.getItem_0()));
            holder.setItemIcon2(mUtility.getItemImageUrl(matchPlayer.getItem_1()));
            holder.setItemIcon3(mUtility.getItemImageUrl(matchPlayer.getItem_2()));
            holder.setItemIcon4(mUtility.getItemImageUrl(matchPlayer.getItem_3()));
            holder.setItemIcon5(mUtility.getItemImageUrl(matchPlayer.getItem_4()));
            holder.setItemIcon6(mUtility.getItemImageUrl(matchPlayer.getItem_5()));

            holder.setPlayerPortrait(player.getAvatarFull());
            holder.setPlayername(player.getPersonaname());
            //creating and setting the last played date for the player
            Date lastPlayed = new Date(player.getLatestMatch().getStart_time());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
            holder.setLastPlayed(sdf.format(lastPlayed));

            holder.setResult(player.getLatestMatch().isRadiant_win() && matchPlayer.getPlayer_slot() < 5);
            holder.setKDA(String.format("%d/%d/%d", matchPlayer.getKills(),
                    matchPlayer.getAssists(), matchPlayer.getDeaths()));
            holder.setMatchDuration(String.format("%d Minutes and %d Seconds", (player.getLatestMatch().getDuration() / 60),
                    (player.getLatestMatch().getDuration() % 60)));
        }
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public void clearPlayers(){
        mPlayers.clear();
    }

    public void setPlayers(List<SteamPlayer> players){
        mPlayers = players;
    }


}

class SearchResultsViewHolder extends RecyclerView.ViewHolder{

    //two main areas of the view, including the bottom which toggles open or closed
    private LinearLayout mTopLayout;
    private LinearLayout mBottomLayout;

    private ImageView mPlayerPortrait;
    private ImageView mHeroPortrait;
    private ImageView mItemIcon1;
    private ImageView mItemIcon2;
    private ImageView mItemIcon3;
    private ImageView mItemIcon4;
    private ImageView mItemIcon5;
    private ImageView mItemIcon6;

    //Text Views
    private TextView mResult;
    private TextView mPlayerName;
    private TextView mPlayerStatus;
    private TextView mLastPlayed;
    private TextView mKDA;
    private TextView mHeroName;
    private TextView mMatchDuration;

    public SearchResultsViewHolder(View itemView) {
        super(itemView);
        mTopLayout = (LinearLayout) itemView.findViewById(R.id.player_search_result_top);
        mBottomLayout = (LinearLayout) itemView.findViewById(R.id.player_search_result_bottom_extra);

        mPlayerPortrait = (ImageView) itemView.findViewById(R.id.player_icon);
        mHeroPortrait = (ImageView) itemView.findViewById(R.id.player_search_latest_match_hero_portrait);
        mItemIcon1 = (ImageView) itemView.findViewById(R.id.player_search_item1);

        mResult = (TextView) itemView.findViewById(R.id.player_search_latest_match_result);
        mPlayerName = (TextView) itemView.findViewById(R.id.player_name);
        mPlayerStatus = (TextView) itemView.findViewById(R.id.player_status);
        mLastPlayed = (TextView) itemView.findViewById(R.id.last_played_time);
        mKDA = (TextView) itemView.findViewById(R.id.player_search_latest_match_kda);
        mHeroName = (TextView) itemView.findViewById(R.id.player_search_latest_match_hero_name);
        mMatchDuration = (TextView) itemView.findViewById(R.id.player_search_latest_match_duration);

    }

    public void toggleBottom(){
        if(mBottomLayout.getVisibility() == View.GONE) {
            mBottomLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setPlayerPortrait(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mPlayerPortrait);
    }

    public void setHeroPortrait(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mHeroPortrait);
    }

    //TODO take in 6 urls and load into
    public void setItemIcon1(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mItemIcon1);
    }

    public void setItemIcon2(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mItemIcon2);
    }

    public void setItemIcon3(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mItemIcon3);
    }

    public void setItemIcon4(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mItemIcon4);
    }

    public void setItemIcon5(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mItemIcon5);
    }

    public void setItemIcon6(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mItemIcon6);
    }

    public void setPlayername(String name){
        mPlayerName.setText(name);
    }

    public void setResult(boolean isWin){
        if(isWin){
            mResult.setBackgroundColor(ContextCompat.getColor(mResult.getContext(), R.color.radiantGreen));
            mResult.setText("Victory");
        }
        else{
            mResult.setBackgroundColor(ContextCompat.getColor(mResult.getContext(), R.color.direRed));
            mResult.setText("Defeat");
        }
    }

    public void setPlayerStatus(String status){

    }

    public void setLastPlayed(String time){
        mLastPlayed.setText(time);
    }

    public void setKDA(String kda){
        mKDA.setText(kda);
    }

    public void setHeroName(String name){
        mHeroName.setText(name);
    }

    public void setMatchDuration(String duration){
        mMatchDuration.setText(duration);
    }

    public boolean bottomIsToggled(){
        return mBottomLayout.getVisibility() == View.VISIBLE;
    }

    public void setTopOnClick(View.OnClickListener listener){
        //TODO add method to add onclick to go to the player activity
        mTopLayout.setOnClickListener(listener);
    }

    public void setBottomOnClick(View.OnClickListener listener){
        //TODO add method to add onclick to go to the match activity
        mBottomLayout.setOnClickListener(listener);
    }

    public Context getContext(){
        return mTopLayout.getContext().getApplicationContext();
    }

}
