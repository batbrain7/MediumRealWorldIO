package com.example.mohitkumar.trialapp.core.comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.data.comment.Comment;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;

import java.util.List;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<Comment> comments;
    ICommentPresenter presenter;

    public CommentRecyclerAdapter(Context context, List<Comment> comments, ICommentPresenter presenter) {
        this.context = context;
        this.comments = comments;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_layout, viewGroup, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Comment comment = comments.get(i);
        CommentViewHolder holder = (CommentViewHolder) viewHolder;
        holder.username.setText(comment.author.username);
        holder.date.setText(comment.createdAt);
        holder.comment.setText(comment.body);
        if (comment.author.username.equals(PrefManager.getString(Constants.USER_NAME, ""))) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        public TextView comment;
        public ImageView deleteButton;
        public TextView date;
        public TextView username;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment = itemView.findViewById(R.id.commentText);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            date = itemView.findViewById(R.id.datePosted);
            username = itemView.findViewById(R.id.userName);
        }
    }
}
