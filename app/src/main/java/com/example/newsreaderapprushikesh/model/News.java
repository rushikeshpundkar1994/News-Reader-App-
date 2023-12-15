package com.example.newsreaderapprushikesh.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class News implements Parcelable {

    @NonNull
    @SerializedName("source")
    private Source source;

    @NonNull
    @SerializedName("title")
    private String newsTitle;

    @NonNull
    @SerializedName("description")
    private String newsDescription;

    @NonNull
    @SerializedName("url")
    private String newsUrl;

    @NonNull
    @SerializedName("urlToImage")
    private String newsImage;

    @NonNull
    @SerializedName("publishedAt")
    private Date newsPublishedDate;

    protected News(Parcel in) {
        source = in.readParcelable(Source.class.getClassLoader());
        newsTitle = in.readString();
        newsDescription = in.readString();
        newsUrl = in.readString();
        newsImage = in.readString();
        newsPublishedDate = new Date(in.readLong());
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @NonNull
    public Source getSource() {
        return source;
    }

    public void setSource(@NonNull Source source) {
        this.source = source;
    }

    @NonNull
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(@NonNull String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @NonNull
    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(@NonNull String newsDescription) {
        this.newsDescription = newsDescription;
    }

    @NonNull
    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(@NonNull String newsUrl) {
        this.newsUrl = newsUrl;
    }

    @NonNull
    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(@NonNull String newsImage) {
        this.newsImage = newsImage;
    }

    @NonNull
    public String getNewsPublishedDate() {
        return new SimpleDateFormat("dd/MM/yyyy h:mm a", Locale.getDefault()).format(newsPublishedDate);
    }

    public void setNewsPublishedDate(@NonNull Date newsPublishedDate) {
        this.newsPublishedDate = newsPublishedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(source, i);
        parcel.writeString(newsTitle);
        parcel.writeString(newsDescription);
        parcel.writeString(newsUrl);
        parcel.writeString(newsImage);
        parcel.writeLong(newsPublishedDate.getTime());
    }

    //Added for Child JSON Object
    public static class Source implements Parcelable {
        @SerializedName("name")
        private String sourceName;

        protected Source(Parcel in) {
            sourceName = in.readString();
        }

        public static final Creator<Source> CREATOR = new Creator<Source>() {
            @Override
            public Source createFromParcel(Parcel in) {
                return new Source(in);
            }

            @Override
            public Source[] newArray(int size) {
                return new Source[size];
            }
        };

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(sourceName);
        }
    }

    //Image Binding
    @BindingAdapter({"bind:imgUrl"})
    public static void setImage(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }
}
