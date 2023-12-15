package com.example.newsreaderapprushikesh.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.newsreaderapprushikesh.R;
import com.example.newsreaderapprushikesh.databinding.ActivityNewsDetailsBinding;
import com.example.newsreaderapprushikesh.model.News;

import java.io.Serializable;

public class NewsDetailsActivity extends AppCompatActivity {
    ActivityNewsDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details);

        MyClickHandlers handlers = new MyClickHandlers(this);
        binding.setHandlers(handlers);

        if (getIntent().getExtras() != null) {
            News news = getIntent().getParcelableExtra("news");
            binding.setNews(news);
        }
    }

    public class MyClickHandlers {

        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void onBackButtonClick(View view) {
            finish();
        }

    }
}