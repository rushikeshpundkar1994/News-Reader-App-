package com.example.newsreaderapprushikesh.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.newsreaderapprushikesh.model.News;
import com.example.newsreaderapprushikesh.model.TotalNews;
import com.example.newsreaderapprushikesh.restapi.ApiClient;
import com.example.newsreaderapprushikesh.restapi.RestInterface;
import com.example.newsreaderapprushikesh.utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<News>> newsLiveData;
    private List<News> newsList;
    private String apiKey;

    public MainViewModel() {
        newsLiveData = new MutableLiveData<>();
        newsList = new ArrayList<>();
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setCountryCode(String countryCode) {
        getNews(countryCode);
    }

    public MutableLiveData<List<News>> getNewsLiveData() {
        return newsLiveData;
    }

    private RestInterface getRestInterface() {
        RestInterface[] restInterface = new RestInterface[1];
        restInterface[0] = ApiClient.getClient(Util.API_BASE_URL).create(RestInterface.class);
        return restInterface[0];
    }

    private void getNews(String langCode) {
        RestInterface restInterface = getRestInterface();
        Call<TotalNews> call;
        newsList.clear();
        newsLiveData.setValue(null);

        //Page size 100
        call = restInterface.getTotalNews(langCode, 100, apiKey);

        call.enqueue(new Callback<TotalNews>() {
            @Override
            public void onResponse(Call<TotalNews> call, Response<TotalNews> response) {
                if (response.body() != null) {
                    TotalNews totalNews = response.body();
                    fillNewsList(totalNews);
                }
            }

            @Override
            public void onFailure(Call<TotalNews> call, Throwable t) {
                newsLiveData.setValue(null);
            }
        });
    }

    private void fillNewsList(TotalNews totalNews) {
        newsList.addAll(totalNews.getNewsList());
        newsLiveData.setValue(newsList);
    }

}
