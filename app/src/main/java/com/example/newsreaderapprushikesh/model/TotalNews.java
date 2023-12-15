package com.example.newsreaderapprushikesh.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TotalNews implements Parcelable {

    @NonNull
    private String status;

    @NonNull
    @SerializedName("totalResults")
    private int totalNewsCount;

    @NonNull
    @SerializedName("articles")
    private List<News> newsList;

    public TotalNews() {
    }

    protected TotalNews(Parcel in) {
        status = in.readString();
        totalNewsCount = in.readInt();
        newsList = in.createTypedArrayList(News.CREATOR);
    }

    public static final Creator<TotalNews> CREATOR = new Creator<TotalNews>() {
        @Override
        public TotalNews createFromParcel(Parcel in) {
            return new TotalNews(in);
        }

        @Override
        public TotalNews[] newArray(int size) {
            return new TotalNews[size];
        }
    };

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    public int getTotalNewsCount() {
        return totalNewsCount;
    }

    public void setTotalNewsCount(int totalNewsCount) {
        this.totalNewsCount = totalNewsCount;
    }

    @NonNull
    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(@NonNull List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeInt(totalNewsCount);
        parcel.writeTypedList(newsList);
    }
}
