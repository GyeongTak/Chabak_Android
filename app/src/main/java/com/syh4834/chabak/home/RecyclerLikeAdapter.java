package com.syh4834.chabak.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syh4834.chabak.PlaceDetailActivity;
import com.syh4834.chabak.R;
import com.syh4834.chabak.api.data.PlaceList;

import java.util.ArrayList;

public class RecyclerLikeAdapter extends RecyclerView.Adapter<RecyclerLikeAdapter.ItemViewHolder> {

    private ArrayList<PlaceList> listRegion = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public RecyclerLikeAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_like, parent, false);
        return new RecyclerLikeAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerLikeAdapter.ItemViewHolder holder, int position) {
        holder.onBind(listRegion.get(position));
    }

    @Override
    public int getItemCount() {
        return listRegion.size();
    }

    public void addItem(PlaceList title) {
        listRegion.add(title);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLikeTitle;
        private TextView tvAvgStar;
        private ImageView imgLikePicture;
        private CheckBox chb_like_white;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        ItemViewHolder(View itemView) {
            super(itemView);

            tvLikeTitle = itemView.findViewById(R.id.tv_like_title);
            tvAvgStar = itemView.findViewById(R.id.tv_avg_star);
            imgLikePicture = itemView.findViewById(R.id.img_like_picture);
            chb_like_white = itemView.findViewById(R.id.chb_like_white);
            imgLikePicture.setClipToOutline(true);
            chb_like_white.setClickable(false);
        }

        void onBind(PlaceList recyclerReviewData) {
            tvLikeTitle.setText(recyclerReviewData.getPlaceTitle());
            tvAvgStar.setText(""+recyclerReviewData.getPlaceAvgStar());
            Glide.with(itemView).load(recyclerReviewData.getPlaceThumbnail()).into(imgLikePicture);
            if(recyclerReviewData.getUserLike()) {
                chb_like_white.setChecked(true);
            } else {
                chb_like_white.setChecked(false);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), PlaceDetailActivity.class);
                    intent.putExtra("PlaceIdx", recyclerReviewData.getPlaceIdx());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}