package cn.hnvist.client.ui.model;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hnvist.client.bean.NewsBean;
import cn.hnvist.client.result.NewsListResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<NewsBean>> liveData;
    private List<NewsBean> data;
    private String loadTime = "";
    private static Gson gson;
    private static OkHttpClient client;
    private static final String URL = "http://mob.hnvist.cn/mp/home/newsService/getList";

    public HomeViewModel() {
        liveData = new MutableLiveData<>();
        data = new ArrayList<>();
        gson = new Gson();
        client = new OkHttpClient();
    }

    public LiveData<List<NewsBean>> getData() {
        String requestURL = String.format("%s?lastTime=%s&type=1&size=%s", URL, loadTime, 10);
        Log.e("url", requestURL);
        Request request = new Request.Builder()
                .get()
                .url(requestURL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                NewsListResult newsListResult = gson.fromJson(body, NewsListResult.class);
                if (200 == newsListResult.getJsonp().getCode()) {
                    int size = newsListResult.getJsonp().getData().size();
                    for (int i = 0; i < size; i++) {
                        NewsListResult.JsonpBean.DataBean dataBean = newsListResult.getJsonp().getData().get(i);
                        data.add(new NewsBean(
                                dataBean.getId(),
                                dataBean.getImage(),
                                dataBean.getName(),
                                dataBean.getDesc() + "……",
                                dataBean.getPublishTime(),
                                dataBean.getLoadTime()));
                        if (i == size-1){
                            loadTime = newsListResult.getJsonp().getData().get(i).getLoadTime();
                        }
                    }
                    liveData.postValue(data);
                    Log.e("Model", "请求成功");
                }
            }
        });
        return liveData;
    }
}
