package com.example.newsreaderapprushikesh.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.newsreaderapprushikesh.R;
import com.example.newsreaderapprushikesh.adapters.AdapterListNews;
import com.example.newsreaderapprushikesh.clicklisteners.AdapterItemClickListener;
import com.example.newsreaderapprushikesh.databinding.ActivityMainBinding;
import com.example.newsreaderapprushikesh.model.News;
import com.example.newsreaderapprushikesh.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LifecycleOwner, AdapterItemClickListener {
    ActivityMainBinding binding;
    MainViewModel viewModel;
    AdapterListNews adapterListNews;
    List<News> newsList;
    MainActivity context;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        newsList = new ArrayList<>();
        adapterListNews = new AdapterListNews(newsList, this);
        binding.recycleNewsFeed.setAdapter(adapterListNews);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(MainViewModel.class);
        viewModel.getNewsLiveData().observe(context, newsListUpdateObserver);
        viewModel.setApiKey(getString(R.string.api_key));
        viewModel.setCountryCode("in");

        // Create an OnBackPressedCallback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    //  super.onBackPressed();
                    finishAffinity();
                    return;
                }
                doubleBackToExitPressedOnce = true;
                Toast.makeText(context, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public void onNewsItemClick(News news) {
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra("news", news);
        startActivity(intent);
    }

    Observer<List<News>> newsListUpdateObserver = new Observer<List<News>>() {
        @Override
        public void onChanged(List<News> news) {
            newsList.clear();
            if (news != null) {
                newsList.addAll(news);
            }
            adapterListNews.notifyDataSetChanged();
        }
    };


}