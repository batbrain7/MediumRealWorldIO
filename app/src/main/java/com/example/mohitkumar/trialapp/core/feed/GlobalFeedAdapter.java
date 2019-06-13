package com.example.mohitkumar.trialapp.core.feed;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.MainActivity;
import com.example.mohitkumar.trialapp.core.comment.CommentActivity;
import com.example.mohitkumar.trialapp.data.mainpage.Article;
import com.example.mohitkumar.trialapp.util.Utils;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

import java.util.ArrayList;
import java.util.List;

public class GlobalFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ClickListener clickListener;

    Context context;
    private boolean isLoadingAdded = false;
    private List<Article> articles;
    private int LOADING = 0;
    private int ITEM = 1;
    private String slug;
    private GlobalViewModel viewModel;
    private boolean isGlobalViewModel = false;

    public GlobalFeedAdapter(Context context) {
        this.context = context;
        this.articles = new ArrayList<>();
    }

    public GlobalFeedAdapter(Context context, GlobalViewModel viewModel) {
        this.context = context;
        this.articles = new ArrayList<>();
        this.viewModel = viewModel;
        isGlobalViewModel = true;
    }

    public GlobalFeedAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_item, viewGroup, false);
                return new ContentViewHolder(view);
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loading_view, viewGroup, false);
                return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Article article = articles.get(i);

        switch (getItemViewType(i)) {
            case 1:

                // add null checks everywhere
                ContentViewHolder contentViewHolder = (ContentViewHolder) viewHolder;
                contentViewHolder.titleArticle.setText(article.title);
                if (article.body != null)
                    contentViewHolder.articleBody.setText(article.body.toCharArray(), 0, Math.min(article.body.length(), 20));
                contentViewHolder.date.setText(article.createdAt);
                contentViewHolder.favouriteCount.setText(Integer.toString(article.favoritesCount));
                slug = article.slug;
                contentViewHolder.slug = slug;
                if (article.author != null) {
                    contentViewHolder.userArticle.setText(article.author.username);
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
                                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource,
                                                               boolean isFirstResource) {
                                    contentViewHolder.imageView.setImageBitmap(resource);
                                    return true;
                                }
                            }).submit();
                }

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

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private void add(Article mc) {
        articles.add(mc);
        notifyItemInserted(articles.size() - 1);
    }

    public void addAll(List<Article> mcList) {

        for (Article mc : mcList) {
            add(mc);
        }
    }

    private void remove(Article city) {
        int position = articles.indexOf(city);
        if (position > -1) {
            articles.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Article());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = articles.size() - 1;
        Article item = getItem(position);
        if (item != null) {
            articles.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        GlobalFeedAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v, String s);
    }

    private Article getItem(int position) {
        return articles.get(position);
    }

    class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView articleBody;
        TextView titleArticle;
        TextView favouriteCount;
        TextView userArticle;
        TextView date;
        ImageView imageView;
        String slug;
        LinearLayout layout;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageArticle);
            articleBody = itemView.findViewById(R.id.articleBody);
            titleArticle = itemView.findViewById(R.id.titleArticle);
            date = itemView.findViewById(R.id.date);
            favouriteCount = itemView.findViewById(R.id.favoriteCount);
            userArticle = itemView.findViewById(R.id.userArticle);
            layout = itemView.findViewById(R.id.favoriteArticle);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.isLoggedIn()) {
                        if (isGlobalViewModel) {
                            viewModel.favoriteArticle(slug).observe((LifecycleOwner) context, singleArticle -> {
                                Log.d(TAG, singleArticle.toString());
                                favouriteCount.setText(Integer.toString(singleArticle.article.favoritesCount));
//                            Intent intent = new Intent(context, MainActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            context.startActivity(intent);
                            });
                        }
                    } else {
                        Toast.makeText(context, "Sign in to favorite an article", Toast.LENGTH_LONG).show();
                    }
                }
            });

            titleArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, CommentActivity.class);
                    intent.putExtra("slug", slug);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view, slug);
        }
    }
}
