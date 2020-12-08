package cn.hnvist.client.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hnvist.client.R;
import cn.hnvist.client.adapter.NewsItemAdapter;
import cn.hnvist.client.bean.NewsBean;
import cn.hnvist.client.result.NewsListResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView honeNewsList;
    private List<NewsBean> data;
    private NewsItemAdapter adapter;
    private static Gson gson;
    private static OkHttpClient client;

    private String lastTime = "";

    private static final String URL = "http://mob.hnvist.cn/mp/home/newsService/getList";
    private SmartRefreshLayout homeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        initData("");
    }

    private void initData(String loadTime) {

        new Thread(() -> {
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
                                lastTime = newsListResult.getJsonp().getData().get(i).getLoadTime();
                            }
                        }
                    } else {
                        runOnUiThread(() -> {
                            homeRefreshLayout.finishLoadMore(false);
                            Toast.makeText(HomeActivity.this, "数据异常", Toast.LENGTH_SHORT).show();
                        });

                    }
                    runOnUiThread(() -> {
                        homeRefreshLayout.finishLoadMore(true);
                        adapter.notifyDataSetChanged();
                    });

                }
            });
        }).start();
    }

    private void initView() {
        honeNewsList = findViewById(R.id.hone_news_list);
        homeRefreshLayout = (SmartRefreshLayout) findViewById(R.id.home_refreshLayout);
        data = new ArrayList<>();
        adapter = new NewsItemAdapter(data);
        gson = new Gson();
        client = new OkHttpClient();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        honeNewsList.setLayoutManager(linearLayoutManager);

        honeNewsList.setAdapter(adapter);
        adapter.setOnItemClickListener(new NewsItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, NewsBean data) {
                ArticleActivity.actionStart(data.getId());
            }
        });
        homeRefreshLayout.setEnableRefresh(false);
        homeRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        homeRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData(lastTime);
            }
        });
    }

    @Override
    protected void onStop() {
        client.clone();
        super.onStop();
    }
}