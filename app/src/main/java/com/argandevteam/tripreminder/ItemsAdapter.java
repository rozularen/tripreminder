package com.argandevteam.tripreminder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by markc on 21/07/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter {

    List<Item> itemsList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_card, parent, false);

        ItemViewHolder itemViewHolder = new ItemViewHolder(itemCardView);

        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = itemsList.get(position);

        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.setData(item);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private Item item;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(Item item) {
            this.item = item;
        }
    }
}
