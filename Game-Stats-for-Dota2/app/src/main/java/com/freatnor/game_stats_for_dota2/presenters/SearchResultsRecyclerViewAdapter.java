package com.freatnor.game_stats_for_dota2.presenters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freatnor.game_stats_for_dota2.Player;
import com.freatnor.game_stats_for_dota2.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class SearchResultsRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultsViewHolder>{

    private List<Player> mPlayers;

    public SearchResultsRecyclerViewAdapter(List<Player> players) {
        mPlayers = players;
    }

    @Override
    public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_search_result_entry, parent, false);
        return new SearchResultsViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final SearchResultsViewHolder holder, int position) {
        holder.setTopOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleBottom();
            }
        });
        holder.setHeroName(mPlayers.get(position).mLastPlayedMatch.mHeroName);
        holder.setHeroPortrait(mPlayers.get(position).mLastPlayedMatch.mHeroPortraitUrl);
        holder.setItemIcon(mPlayers.get(position).mLastPlayedMatch.mItemIconUrl);

        holder.setPlayerPortrait(mPlayers.get(position).mImageUrl);
        holder.setPlayername(mPlayers.get(position).mName);
        //creating and setting the last played date for the player
        Date lastPlayed = new Date(mPlayers.get(position).mLastPlayed);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        holder.setLastPlayed(sdf.format(lastPlayed));

        holder.setResult(mPlayers.get(position).mLastPlayedMatch.mIsWin);
        holder.setKDA(String.format("%d/%d/%d", mPlayers.get(position).mLastPlayedMatch.mKills,
                mPlayers.get(position).mLastPlayedMatch.mAssists, mPlayers.get(position).mLastPlayedMatch.mDeaths));
        holder.setMatchDuration(String.format("%d Minutes and %d Seconds", (mPlayers.get(position).mLastPlayedMatch.mDuration / 60),
                (mPlayers.get(position).mLastPlayedMatch.mDuration % 60)));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }
}

class SearchResultsViewHolder extends RecyclerView.ViewHolder{

    //two main areas of the view, including the bottom which toggles open or closed
    private LinearLayout mTopLayout;
    private LinearLayout mBottomLayout;

    private ImageView mPlayerPortrait;
    private ImageView mHeroPortrait;
    private ImageView mItemIcon;
    //should be 5 more item icons here
    //TODO add the 5 item icons left

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
        mItemIcon = (ImageView) itemView.findViewById(R.id.player_search_item1);

        mResult = (TextView) itemView.findViewById(R.id.player_search_latest_match_result);
        mPlayerName = (TextView) itemView.findViewById(R.id.player_name);
        mPlayerStatus = (TextView) itemView.findViewById(R.id.player_status);
        mLastPlayed = (TextView) itemView.findViewById(R.id.last_played_time);
        mKDA = (TextView) itemView.findViewById(R.id.player_search_latest_match_kda);
        mHeroName = (TextView) itemView.findViewById(R.id.player_search_latest_match_hero_name);
        mMatchDuration = (TextView) itemView.findViewById(R.id.player_search_latest_match_duration);

    }

    public void toggleBottom(){
        //TODO method to toggle the bottom element to animate open
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
    public void setItemIcon(String url){
        Picasso.with(mTopLayout.getContext()).load(url).into(mItemIcon);
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

    public void setBottomOnClick(){
        //TODO add method to add onclick to go to the match activity
    }

}
