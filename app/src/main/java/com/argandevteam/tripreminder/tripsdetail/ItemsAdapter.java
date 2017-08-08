package com.argandevteam.tripreminder.tripsdetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.data.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markc on 21/07/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter {

    List<Item> itemsList;

    public ItemsAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public void replaceData(List<Item> itemsToShow) {
        itemsList = itemsToShow;

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

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

        @BindView(R.id.item_title)
        TextView itemTitle;

        private Item item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData(Item item) {
            this.item = item;
            itemTitle.setText(item.getName());
        }
    }
}
