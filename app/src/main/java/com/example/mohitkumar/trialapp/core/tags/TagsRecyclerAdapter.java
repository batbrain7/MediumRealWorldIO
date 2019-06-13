package com.example.mohitkumar.trialapp.core.tags;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohitkumar.trialapp.R;

import java.util.List;

public class TagsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ClickListener clickListener;
    List<String> tags;

    public TagsRecyclerAdapter(List<String> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tag_layout, viewGroup, false);
        return new TagViewHolder(view);
    }

    public interface ClickListener {
        void onItemClick(int position, View v, String s);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String tagName = tags.get(i);
        TagViewHolder holder = (TagViewHolder) viewHolder;
        holder.tag.setText(tagName);
        holder.tagString = tagName;
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tag;
        String tagString;

         TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tagItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view, tagString);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TagsRecyclerAdapter.clickListener = clickListener;
    }
}
