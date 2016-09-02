package com.freatnor.game_stats_for_dota2.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jonathan Taylor on 9/1/16.
 */
public class SearchResultsRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultsViewHolder>{

    @Override
    public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchResultsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class SearchResultsViewHolder extends RecyclerView.ViewHolder{

    public SearchResultsViewHolder(View itemView) {
        super(itemView);
    }
}
