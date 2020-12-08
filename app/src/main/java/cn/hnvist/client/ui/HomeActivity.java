package cn.hnvist.client.ui;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import cn.hnvist.client.R;
import cn.hnvist.client.adapter.NewsItemAdapter;
import cn.hnvist.client.bean.NewsBean;
import cn.hnvist.client.ui.model.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView honeNewsList;
    private List<NewsBean> data;
    private NewsItemAdapter adapter;
    private HomeViewModel viewModel;

    private SmartRefreshLayout homeRefreshLayout;
    private TextView homeLoading;
    private int isLoading = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {

        viewModel.getData().observe(this, new Observer<List<NewsBean>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<NewsBean> newsBeans) {
                if (isLoading == 0){
                    homeLoading.setVisibility(View.GONE);
                    homeRefreshLayout.setVisibility(View.VISIBLE);
                    isLoading++;
                }
                Log.e("观察着", "收到数据更新");
                homeRefreshLayout.finishLoadMore(true);
                adapter.setNewsBean(newsBeans);
            }
        });

        /*new Thread(() -> {
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
        }).start();*/
    }

    private void initView() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        honeNewsList = findViewById(R.id.hone_news_list);
        homeRefreshLayout = (SmartRefreshLayout) findViewById(R.id.home_refreshLayout);
        homeLoading = (TextView) findViewById(R.id.home_loading);
        data = new ArrayList<>();
        adapter = new NewsItemAdapter(data);

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
                 initData();
            }
        });
    }
}