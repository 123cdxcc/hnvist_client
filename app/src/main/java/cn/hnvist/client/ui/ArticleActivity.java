package cn.hnvist.client.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

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

    public OkHttpClient client;
    public Gson gson = new Gson();
    public NewsDetails news;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        Intent intent = getIntent();
        this.mArticleID = intent.getStringExtra("articleID");

        initView();
        loadData();
    }

    private void initView() {
        mWebView = findViewById(R.id.a_article_body);
    }

    private void renderArticleBody(String htmlStr) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                WebSettings webSettings = mWebView.getSettings();
                webSettings.setLoadWithOverviewMode(true); // 缩放到屏幕大小

                mWebView.loadData(htmlStr, "text/html", "utf-8");

            }
        });

    }

    private void loadData() {
        String htmlStr = "<h1 id=\"markdown\">常用 Markdown 语法教程</h1>\n" +
                "<hr>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"\">引用示例</h3>\n" +
                "<blockquote>\n" +
                "  <p>根据 Github 开源项目改编开发，很高兴你的使用</p>\n" +
                "</blockquote>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"-1\">标题示例</h3>\n" +
                "<h1 id=\"h1\">这是 H1 标题</h1>\n" +
                "<h2 id=\"h2\">这是 H2 标题</h2>\n" +
                "<h3 id=\"h3\">这是 H3 标题</h3>\n" +
                "<h4 id=\"h4\">这是 H4 标题</h4>\n" +
                "<h5 id=\"h5\">这是 H5 标题</h5>\n" +
                "<h6 id=\"h6\">这是 H6 标题</h6></div>" +
                "<h1 id=\"markdown\">常用 Markdown 语法教程</h1>\n" +
                "<hr>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"\">引用示例</h3>\n" +
                "<blockquote>\n" +
                "  <p>根据 Github 开源项目改编开发，很高兴你的使用</p>\n" +
                "</blockquote>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"-1\">标题示例</h3>\n" +
                "<h1 id=\"h1\">这是 H1 标题</h1>\n" +
                "<h2 id=\"h2\">这是 H2 标题</h2>\n" +
                "<h3 id=\"h3\">这是 H3 标题</h3>\n" +
                "<h4 id=\"h4\">这是 H4 标题</h4>\n" +
                "<h5 id=\"h5\">这是 H5 标题</h5>\n" +
                "<h6 id=\"h6\">这是 H6 标题</h6></div>" +
                "<h1 id=\"markdown\">常用 Markdown 语法教程</h1>\n" +
                "<hr>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"\">引用示例</h3>\n" +
                "<blockquote>\n" +
                "  <p>根据 Github 开源项目改编开发，很高兴你的使用</p>\n" +
                "</blockquote>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"-1\">标题示例</h3>\n" +
                "<h1 id=\"h1\">这是 H1 标题</h1>\n" +
                "<h2 id=\"h2\">这是 H2 标题</h2>\n" +
                "<h3 id=\"h3\">这是 H3 标题</h3>\n" +
                "<h4 id=\"h4\">这是 H4 标题</h4>\n" +
                "<h5 id=\"h5\">这是 H5 标题</h5>\n" +
                "<h6 id=\"h6\">这是 H6 标题</h6></div>" +
                "<h1 id=\"markdown\">常用 Markdown 语法教程</h1>\n" +
                "<hr>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"\">引用示例</h3>\n" +
                "<blockquote>\n" +
                "  <p>根据 Github 开源项目改编开发，很高兴你的使用</p>\n" +
                "</blockquote>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"-1\">标题示例</h3>\n" +
                "<h1 id=\"h1\">这是 H1 标题</h1>\n" +
                "<h2 id=\"h2\">这是 H2 标题</h2>\n" +
                "<h3 id=\"h3\">这是 H3 标题</h3>\n" +
                "<h4 id=\"h4\">这是 H4 标题</h4>\n" +
                "<h5 id=\"h5\">这是 H5 标题</h5>\n" +
                "<h6 id=\"h6\">这是 H6 标题</h6></div>" +
                "<h1 id=\"markdown\">常用 Markdown 语法教程</h1>\n" +
                "<hr>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"\">引用示例</h3>\n" +
                "<blockquote>\n" +
                "  <p>根据 Github 开源项目改编开发，很高兴你的使用</p>\n" +
                "</blockquote>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"-1\">标题示例</h3>\n" +
                "<h1 id=\"h1\">这是 H1 标题</h1>\n" +
                "<h2 id=\"h2\">这是 H2 标题</h2>\n" +
                "<h3 id=\"h3\">这是 H3 标题</h3>\n" +
                "<h4 id=\"h4\">这是 H4 标题</h4>\n" +
                "<h5 id=\"h5\">这是 H5 标题</h5>\n" +
                "<h6 id=\"h6\">这是 H6 标题</h6></div>" +
                "<h1 id=\"markdown\">常用 Markdown 语法教程</h1>\n" +
                "<hr>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"\">引用示例</h3>\n" +
                "<blockquote>\n" +
                "  <p>根据 Github 开源项目改编开发，很高兴你的使用</p>\n" +
                "</blockquote>\n" +
                "<p><br></p>\n" +
                "<h3 id=\"-1\">标题示例</h3>\n" +
                "<h1 id=\"h1\">这是 H1 标题</h1>\n" +
                "<h2 id=\"h2\">这是 H2 标题</h2>\n" +
                "<h3 id=\"h3\">这是 H3 标题</h3>\n" +
                "<h4 id=\"h4\">这是 H4 标题</h4>\n" +
                "<h5 id=\"h5\">这是 H5 标题</h5>\n" +
                "<h6 id=\"h6\">这是 H6 标题</h6></div>";

        String url = "http://mob.hnvist.cn/mp/home/newsService/newsDetail?idNews=1607145427469";
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
                news = gson.fromJson(data, NewsDetails.class);
                String aContext = news.getJsonp().getData().getContent();
                renderArticleBody(aContext);
                Log.d(TAG, "onResponse: " + aContext);

            }
        });

        renderArticleBody(htmlStr);
    }



}
