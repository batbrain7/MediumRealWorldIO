package com.example.mohitkumar.trialapp.core.Feed;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.data.MainPage.Articles;
import com.example.mohitkumar.trialapp.databinding.FeedItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GlobalFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private boolean isLoadingAdded = false;
    List<Articles> articles;
    int LOADING = 0;
    int ITEM = 1;

    public GlobalFeedAdapter(Context context) {
        this.context = context;
        this.articles = new ArrayList<>();
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            case 1 :
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item, viewGroup, false);
                return new ContentViewHolder(view);
            default :
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loading_view, viewGroup, false);
                return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Articles article = articles.get(i);

        switch (getItemViewType(i)) {
            case 1 :
                ContentViewHolder contentViewHolder = (ContentViewHolder) viewHolder;
                contentViewHolder.titleArticle.setText(article.title);
                contentViewHolder.userArticle.setText(article.author.username);
                contentViewHolder.articleBody.setText(article.body);
                contentViewHolder.date.setText(article.createdAt);
                contentViewHolder.favouriteCount.setText(Integer.toString(article.favoritesCount));
                Glide.with(context)
                        .asBitmap()
                        .load(article.author.image)
                        .listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                Toast.makeText(context, "ERROR in image laoding", Toast.LENGTH_LONG).show();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                contentViewHolder.imageView.setImageBitmap(resource);
                                return true;
                            }
                        }).submit();
                break;
            case 0:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == articles.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView articleBody;
        TextView titleArticle;
        TextView favouriteCount;
        TextView userArticle;
        TextView date;
        ImageView imageView;
        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageArticle);
            articleBody = itemView.findViewById(R.id.articleBody);
            titleArticle = itemView.findViewById(R.id.titleArticle);
            date = itemView.findViewById(R.id.date);
            favouriteCount = itemView.findViewById(R.id.favoriteCount);
            userArticle = itemView.findViewById(R.id.userArticle);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void add(Articles mc) {
        articles.add(mc);
        notifyItemInserted(articles.size() - 1);
    }

    public void addAll(List<Articles> mcList) {
        for (Articles mc : mcList) { add(mc); }
    }

    public void remove(Articles city) {
        int position = articles.indexOf(city);
        if (position > -1) {
            articles.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) { remove(getItem(0)); }
    }

    public boolean isEmpty() { return getItemCount() == 0; }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Articles());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = articles.size() - 1;
        Articles item = getItem(position);
        if (item != null) {
            articles.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Articles getItem(int position) {
        return articles.get(position);
    }
}
