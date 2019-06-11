package com.example.mohitkumar.trialapp.data.MainPage;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import lombok.Data;

@Data
public class Articles {
    public String title;
    public String slug;
    public String body;
    public String createdAt;
    public String updatedAt;
    public String description;
    public Author author;
    public int favoritesCount;
    public boolean favorited;

//    protected Articles(Parcel in) {
//        title = in.readString();
//        slug = in.readString();
//        body = in.readString();
//        createdAt = in.readString();
//        updatedAt = in.readString();
//        description = in.readString();
//        favoritesCount = in.readInt();
//        favorited = in.readByte() != 0;
//    }
//
//    public static final Creator<Articles> CREATOR = new Creator<Articles>() {
//        @Override
//        public Articles createFromParcel(Parcel in) {
//            return new Articles(in);
//        }
//
//        @Override
//        public Articles[] newArray(int size) {
//            return new Articles[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(title);
//        dest.writeString(slug);
//        dest.writeString(body);
//        dest.writeString(createdAt);
//        dest.writeString(updatedAt);
//        dest.writeString(description);
//        dest.writeInt(favoritesCount);
//        dest.writeByte((byte) (favorited ? 1 : 0));
//    }
//
//    public static DiffUtil.ItemCallback<Articles> DIFF_CALLBACK = new DiffUtil.ItemCallback<Articles>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull Articles oldItem, @NonNull Articles newItem) {
//            return oldItem.title.equals(newItem.title);
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull Articles articles, @NonNull Articles t1) {
//            return false;
//        }
//    };
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this)
//            return true;
//
//        Articles article = (Articles) obj;
//        return article.title.equals(title);
//    }
}
