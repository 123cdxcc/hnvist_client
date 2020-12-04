package cn.hnvist.client;

import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hnvist.client.adapter.NewsItemAdapter;
import cn.hnvist.client.bean.NewsBean;
import cn.hnvist.client.result.NewsListResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    private ListView honeNewsList;
    private List<NewsBean> data;
    private NewsItemAdapter adapter;
    private static Gson gson;
    private static OkHttpClient client;

    private static final String URL = "http://mob.hnvist.cn/mp/home/newsService/getList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initStart();
    }

    private void initStart() {
        new Thread(()-> {
            String requestURL = String.format("%s?lastTime=%s&type=1&size=10", URL, System.currentTimeMillis());
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
                    if ( 200 == newsListResult.getJsonp().getCode()){
                        for (int i = 0; i < newsListResult.getJsonp().getData().size(); i++) {
                            NewsListResult.JsonpBean.DataBean dataBean = newsListResult.getJsonp().getData().get(i);
                            data.add(new NewsBean(i, dataBean.getImage(), dataBean.getName(), dataBean.getDesc() + "……", dataBean.getPublishTime()));
                        }
                    } else {
                        Toast.makeText(HomeActivity.this, "数据异常", Toast.LENGTH_SHORT).show();
                    }
                    // Log.e("数据报告", );
//                    data.forEach(System.out::println);
                    runOnUiThread(()-> adapter.notifyDataSetChanged());
                }
            });
        }).start();
    }

    private void initView() {
        honeNewsList = findViewById(R.id.hone_news_list);
        data = new ArrayList<>();
        adapter = new NewsItemAdapter(data, this);
        gson = new Gson();
        client = new OkHttpClient();

        honeNewsList.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        client.clone();
        super.onStop();
    }
}