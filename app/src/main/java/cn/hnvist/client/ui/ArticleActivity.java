package cn.hnvist.client.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;

import cn.hnvist.client.BaseApplication;
import cn.hnvist.client.R;
import cn.hnvist.client.bean.NewsDetails;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ArticleActivity extends AppCompatActivity {

    // 启动文章详情 Activity ，必传入文章 ID
    public static void actionStart(String articleID) {
        // 文章 ID 判空
        if (articleID == null && articleID.equals("")) return;

        // 构建 Intent，启动 Activity
        Context context = BaseApplication.mContext;
        Intent intent = new Intent().setClass(context, ArticleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("articleID", articleID);
        context.startActivity(intent);
    }

    public static String TAG = "Article";
    private String mArticleID;

    private WebView mWebView;
    private LinearLayout mLoadView;
    private MaterialToolbar mMaterialToolbar;
    private ScrollView mScrollView;
    private TextView mArticleTitle;

    public OkHttpClient client;
    public Gson gson = new Gson();
    public NewsDetails news;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        Intent intent = getIntent();
        this.mArticleID = intent.getStringExtra("articleID");

        // this.mArticleID = "1606977887873";

        initView();
        loadData();
    }

    // 初始化 view
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        mWebView = findViewById(R.id.a_article_body);
        mLoadView = findViewById(R.id.a_article_loadview);
        mMaterialToolbar = findViewById(R.id.a_article_topAppBar);
        mScrollView = findViewById(R.id.a_article_scroll);
        mArticleTitle = findViewById(R.id.a_article_title);

        // 监听页面滑动
        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // 设置文章标题，通过滑动页面
                if(scrollY > 250) {
                    mMaterialToolbar.setTitleMarginStart(0);
                    mMaterialToolbar.setTitle(news.getJsonp().getData().getName());
                } else {
                    mMaterialToolbar.setTitle("");
                }
            }
        });

        // 设置顶部 TapBar 返回导航
        mMaterialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // 渲染文章详情
    private void renderArticleBody(String htmlStr) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                WebSettings webSettings = mWebView.getSettings();
                webSettings.setLoadWithOverviewMode(true); // 缩放到屏幕大小
                webSettings.setAppCacheEnabled(false);

                mWebView.loadData(htmlStr, "text/html", "utf-8");

                mWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        setLoadStart(false);
                    }
                });

                // 设置文章标题
                mArticleTitle.setText(news.getJsonp().getData().getName());

            }
        });

    }

    // 加载数据
    private void loadData() {

        String url = "http://mob.hnvist.cn/mp/home/newsService/newsDetail?idNews=" + mArticleID;
        if (client == null) {
            client = new OkHttpClient();
        }

        Request request = new Request
                .Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();
                if (data.equals("") || data == null) {
                    toast("文章详情加载失败！！！");
                    finish();
                    return;
                }
                news = gson.fromJson(data, NewsDetails.class);
                String aContext = news.getJsonp().getData().getContent();
                renderArticleBody(aContext);
                // Log.d(TAG, "onResponse: " + aContext);

            }
        });

    }

    // 设置页面数据加载状态！
    private void setLoadStart(boolean isLoad) {
        if (mLoadView == null) return;
        if (isLoad) {
            mLoadView.setVisibility(View.VISIBLE);
        } else {
            mLoadView.setVisibility(View.GONE);
        }
    }

    // toast 方法封装
    public void toast(String msg) {
        runOnUiThread(() -> Toast.makeText(this, msg, Toast.LENGTH_LONG).show());
    }

}
