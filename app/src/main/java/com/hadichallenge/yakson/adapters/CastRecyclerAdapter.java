package com.hadichallenge.yakson.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hadichallenge.yakson.R;
import com.hadichallenge.yakson.helpers.ImageLoadHelper;
import com.hadichallenge.yakson.interfaces.RecyclerItemClickListener;
import com.hadichallenge.yakson.models.CastModel;
import com.hadichallenge.yakson.network.core.ApiConstant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CastRecyclerAdapter extends RecyclerView.Adapter<CastRecyclerAdapter.ViewHolder> {

    public ArrayList<CastModel> movieModelArrayList;
    private static RecyclerItemClickListener recyclerItemClickListener;

    public CastRecyclerAdapter(ArrayList<CastModel> movieModelArrayList, RecyclerItemClickListener recyclerItemClickListener) {
        this.movieModelArrayList = movieModelArrayList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public CastRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_recycler_now_playing, viewGroup, false);
        return new CastRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CastRecyclerAdapter.ViewHolder viewHolder, int position) {

        ImageLoadHelper.getInstance().loadImage(viewHolder.moviePosterImageView,
                ApiConstant.IMAGE_PREFIX + movieModelArrayList.get(position).getProfile_path());

        viewHolder.movieNameTextView.setText(movieModelArrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.moviePosterImageView)
        ImageView moviePosterImageView;

        @BindView(R.id.moviesTitleTextView)
        TextView movieNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> recyclerItemClickListener.onItemClick(v, getAdapterPosition()));

        }
    }
}
