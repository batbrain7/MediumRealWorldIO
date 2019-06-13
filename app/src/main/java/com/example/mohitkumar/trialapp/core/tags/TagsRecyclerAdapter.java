package com.example.mohitkumar.trialapp.core.tags;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.core.comment.ICommentPresenter;
import com.example.mohitkumar.trialapp.core.feed.GlobalFeedAdapter;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;

import java.util.List;

public class TagsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ClickListener clickListener;

    private Context context;
    List<String> tags;

    public TagsRecyclerAdapter(Context context, List<String> tags) {
        this.context = context;
        this.tags = tags;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tag_layout, viewGroup, false);
        return new TagViewHolder(view);
    }

    interface ClickListener {
        void onItemClick(int position, View v, String s);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String tagName = tags.get(i);
        TagViewHolder holder = (TagViewHolder) viewHolder;
        if (tagName != null)
            holder.tag.setText(tagName);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    class TagViewHolder extends RecyclerView.ViewHolder {

        public TextView tag;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tagItem);
        }
    }

    public void setOnItemClickListener(TagsRecyclerAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
