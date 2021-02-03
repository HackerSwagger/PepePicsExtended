package com.example.pepepicsextended;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class CustomListViewAdapter extends RecyclerView.Adapter<CustomListViewAdapter.ViewHolder> {


    private List<RowItem> items;

    public CustomListViewAdapter(List<RowItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView somePepe;
        TextView daName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            somePepe = itemView.findViewById(R.id.item_image);
            daName = itemView.findViewById(R.id.item_name);
        }

        public void setData(RowItem rowItem) {

            File pepePic = new File("E:/frens/Frens-LessDuplicates/abcd.jpg");
            System.out.println(pepePic.exists());

            Picasso.get()
                    .load(rowItem.getImageId())
                    .fit()
                    .centerCrop()
                    //.networkPolicy(NetworkPolicy.OFFLINE).noFade()
                    .into(somePepe);

            daName.setText(rowItem.getTitle());
        }
    }

    public List<RowItem> getItems() {
        return items;
    }

    public void setItems(List<RowItem> items) {
        this.items = items;
    }
}