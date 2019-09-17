package com.hadichallenge.yakson.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hadichallenge.yakson.R;
import com.hadichallenge.yakson.helpers.DateTimeHelper;
import com.hadichallenge.yakson.helpers.ImageLoadHelper;
import com.hadichallenge.yakson.interfaces.RecyclerItemClickListener;
import com.hadichallenge.yakson.models.MovieResultModel;
import com.hadichallenge.yakson.network.core.ApiConstant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieSearchResultRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HOLDER_TOP_RATED = 0;
    private final int HOLDER_SPACE = 1;

    public ArrayList<MovieResultModel> movieModelArrayList;
    private static RecyclerItemClickListener recyclerItemClickListener;

    private OnScroolToBottom onScroolToBottom;

    public MovieSearchResultRecyclerAdapter(ArrayList<MovieResultModel> movieModelArrayList, OnScroolToBottom onScroolToBottom, RecyclerItemClickListener recyclerItemClickListener) {
        this.movieModelArrayList = movieModelArrayList;
        this.onScroolToBottom = onScroolToBottom;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {

            case HOLDER_TOP_RATED:
                view = inflater.inflate(R.layout.row_recycler_top_rated, viewGroup, false);
                viewHolder = new ViewHolder(view);
                break;

            case HOLDER_SPACE:
                view = inflater.inflate(R.layout.row_space, viewGroup, false);
                viewHolder = new SpaceHolder(view);
                break;
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder.getItemViewType() == HOLDER_SPACE){
            return;
        }

        ViewHolder holder = (ViewHolder) viewHolder;
        ImageLoadHelper.getInstance().loadImage(holder.topRatedMoviesImage,
                ApiConstant.IMAGE_PREFIX + movieModelArrayList.get(position).getBackdropPath());
        holder.movieTitleTextView.setText(movieModelArrayList.get(position).getTitle());

        holder.movieReleaseDateTextView.setText(DateTimeHelper.getDateWithMonthName(
                movieModelArrayList.get(position).getReleaseDate()));

        if (position == movieModelArrayList.size() - 1) {
            onScroolToBottom.onScrollToBottom(true);
        } else {
            onScroolToBottom.onScrollToBottom(false);
        }

    }


    public static class SpaceHolder extends RecyclerView.ViewHolder {
        public SpaceHolder(View view) {
            super(view);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.topRatedMoviesImage)
        ImageView topRatedMoviesImage;

        @BindView(R.id.movieTitleTextView)
        TextView movieTitleTextView;

        @BindView(R.id.movieReleaseDateTextView)
        TextView movieReleaseDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> recyclerItemClickListener.onItemClick(v, getAdapterPosition()));

        }
    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == movieModelArrayList.size()) {
            return HOLDER_SPACE;
        }
        return HOLDER_TOP_RATED;
    }

    public interface OnScroolToBottom {
        void onScrollToBottom(boolean visible);
    }

}
