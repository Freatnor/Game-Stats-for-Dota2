package com.freatnor.game_stats_for_dota2.presenters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freatnor.game_stats_for_dota2.Player;
import com.freatnor.game_stats_for_dota2.SteamAPIModels.MatchPlayer;
import com.freatnor.game_stats_for_dota2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Jonathan Taylor on 9/6/16.
 */
public class MatchDetailsPlayerRecyclerViewAdapter extends RecyclerView.Adapter<MatchDetailsPlayerViewHolder> {

    public static final int RADIANT = 1;
    public static final int DIRE = 2;

    //TODO change to list of real Player objects in the future
    private List<Player> mPlayers;
    //For if there's any parts distinct between dire and radiant
    private int mSide;

    public MatchDetailsPlayerRecyclerViewAdapter(List<Player> players, int side) {
        mPlayers = players;
        mSide = side;
    }

    @Override
    public MatchDetailsPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_detail_player_layout, parent, false);
        return new MatchDetailsPlayerViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final MatchDetailsPlayerViewHolder holder, int position) {
        Player player = mPlayers.get(position);

        //TODO update for correct access methods...
        /*holder.setHeroName(player.getHeroName());
        holder.setPlayerName(player.getPlayerName());
        holder.setKills(String.valueOf(player.getKills()));
        holder.setDeaths(String.valueOf(player.getDeaths()));
        holder.setAssists(String.valueOf(player.getAssists()));
        holder.setNetGold(String.format("%dG", player.getNetGold));

        //set the images
        holder.setHeroPortrait(player.getHeroPortraitUrl());
        holder.setItemIcon1(player.getItemIcon1);
        holder.setItemIcon2(player.getItemIcon2);
        holder.setItemIcon3(player.getItemIcon3);
        holder.setItemIcon4(player.getItemIcon4);
        holder.setItemIcon5(player.getItemIcon5);
        holder.setItemIcon6(player.getItemIcon6);*/


        //TEST CODE
        holder.setHeroName(player.mLastPlayedMatch.mHeroName);
        holder.setPlayerName(player.mName);

        holder.setKills(String.valueOf(player.mLastPlayedMatch.mKills));
        holder.setDeaths(String.valueOf(player.mLastPlayedMatch.mDeaths));
        holder.setAssists(String.valueOf(player.mLastPlayedMatch.mAssists));

        holder.setHeroPortrait(player.mLastPlayedMatch.mHeroPortraitUrl);
        holder.setItemIcon1(player.mLastPlayedMatch.mItemIconUrl);
        holder.setItemIcon2(player.mLastPlayedMatch.mItemIconUrl);
        holder.setItemIcon3(player.mLastPlayedMatch.mItemIconUrl);
        holder.setItemIcon4(player.mLastPlayedMatch.mItemIconUrl);
        holder.setItemIcon5(player.mLastPlayedMatch.mItemIconUrl);
        holder.setItemIcon6(player.mLastPlayedMatch.mItemIconUrl);

        holder.setOverviewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.itemsSectionIsToggled()){
                    holder.toggleItemsSection();
                }
                else{
                    //TODO add in the interface for getting to the player details
                }
            }
        });

        holder.setItemsSectionOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO interface for going to player details
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class MatchDetailsPlayerViewHolder extends RecyclerView.ViewHolder{

    private LinearLayout mPlayerOverviewSection;
    private LinearLayout mPlayerItemsSection;

    private ImageView mHeroPortrait;
    private ImageView mItemIcon1;
    private ImageView mItemIcon2;
    private ImageView mItemIcon3;
    private ImageView mItemIcon4;
    private ImageView mItemIcon5;
    private ImageView mItemIcon6;

    private TextView mHeroName;
    private TextView mPlayerName;
    private TextView mKills;
    private TextView mDeaths;
    private TextView mAssists;
    private TextView mNetGold;


    public MatchDetailsPlayerViewHolder(View itemView) {
        super(itemView);
        mPlayerItemsSection = (LinearLayout) itemView.findViewById(R.id.match_player_items);
        mPlayerOverviewSection = (LinearLayout) itemView.findViewById(R.id.match_player_overview);

        mHeroPortrait = (ImageView) itemView.findViewById(R.id.match_player_hero_portrait);
        mItemIcon1 = (ImageView) itemView.findViewById(R.id.match_player_item1);
        mItemIcon2 = (ImageView) itemView.findViewById(R.id.match_player_item2);
        mItemIcon3 = (ImageView) itemView.findViewById(R.id.match_player_item3);
        mItemIcon4 = (ImageView) itemView.findViewById(R.id.match_player_item4);
        mItemIcon5 = (ImageView) itemView.findViewById(R.id.match_player_item5);
        mItemIcon6 = (ImageView) itemView.findViewById(R.id.match_player_item6);

        mHeroName = (TextView) itemView.findViewById(R.id.match_player_hero_name);
        mPlayerName = (TextView) itemView.findViewById(R.id.match_player_name);
        mKills = (TextView) itemView.findViewById(R.id.kills);
        mDeaths = (TextView) itemView.findViewById(R.id.deaths);
        mAssists = (TextView) itemView.findViewById(R.id.assists);
        mNetGold = (TextView) itemView.findViewById(R.id.net_gold);
    }

    public void toggleItemsSection(){
        if(mPlayerItemsSection.getVisibility() == View.GONE){
            mPlayerItemsSection.setVisibility(View.VISIBLE);
        }
    }

    public boolean itemsSectionIsToggled(){
        return mPlayerItemsSection.getVisibility() == View.VISIBLE;
    }

    public void setOverviewOnClickListener(View.OnClickListener listener){
        mPlayerOverviewSection.setOnClickListener(listener);
    }

    public void setItemsSectionOnClickListener(View.OnClickListener listener){
        mPlayerItemsSection.setOnClickListener(listener);
    }

    public void setHeroPortrait(String url){
        Picasso.with(mHeroPortrait.getContext()).load(url).into(mHeroPortrait);
    }

    //methods to load images into itemicons via Picasso
    public void setItemIcon1(String url){
        Picasso.with(mItemIcon1.getContext()).load(url).into(mItemIcon1);
    }

    public void setItemIcon2(String url){
        Picasso.with(mItemIcon2.getContext()).load(url).into(mItemIcon2);
    }

    public void setItemIcon3(String url){
        Picasso.with(mItemIcon3.getContext()).load(url).into(mItemIcon3);
    }

    public void setItemIcon4(String url){
        Picasso.with(mItemIcon4.getContext()).load(url).into(mItemIcon4);
    }

    public void setItemIcon5(String url){
        Picasso.with(mItemIcon5.getContext()).load(url).into(mItemIcon5);
    }

    public void setItemIcon6(String url){
        Picasso.with(mItemIcon6.getContext()).load(url).into(mItemIcon6);
    }

    //Methods to set the text in the various views
    public void setHeroName(String name){
        mHeroName.setText(name);
    }

    public void setPlayerName(String name){
        mPlayerName.setText(name);
    }

    public void setKills(String kills){
        mKills.setText(kills);
    }

    public void setDeaths(String deaths){
        mDeaths.setText(deaths);
    }

    public void setAssists(String assists){
        mAssists.setText(assists);
    }

    public void setNetGold(String netGold){
        mNetGold.setText(netGold);
    }
}
