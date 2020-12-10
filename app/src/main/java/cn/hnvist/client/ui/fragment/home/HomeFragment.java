package cn.hnvist.client.ui.fragment.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.hnvist.client.R;
import cn.hnvist.client.adapter.BannerImageAdapter;
import cn.hnvist.client.adapter.NewsAdapter;
import cn.hnvist.client.bean.BannerBean;
import cn.hnvist.client.bean.NewsBean;
import cn.hnvist.client.ui.ArticleActivity;


public class HomeFragment extends Fragment {
    private HomeViewModel viewModel;
    private ListView honeNewsList;
    private List<NewsBean> data;
    private NewsAdapter adapter;
    private SmartRefreshLayout homeRefreshLayout;
    private TextView homeLoading;
    private int isLoading = 0;
    private BannerImageAdapter bannerImageAdapter;
    private List<BannerBean> bannerData;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);
        initStart(root);
        initData();
        setBanner(root);
        TabLayout tab = root.findViewById(R.id.home_tab);
        tab.addTab(tab.newTab().setText("咨询"));
        tab.addTab(tab.newTab().setText("班级"));
        return root;
    }
    private void setBanner(View view){
        View viewBanner = LayoutInflater.from(view.getContext()).inflate(R.layout.item_header_banner, honeNewsList, false);
        Banner banner = viewBanner.findViewById(R.id.home_banner);
        honeNewsList.addHeaderView(viewBanner);
        banner.setAdapter(bannerImageAdapter)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(view.getContext()));
        banner.start();
    }

    private void initStart(View view) {
        honeNewsList.setAdapter(adapter);
        honeNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArticleActivity.actionStart(data.get(position - 1).getId());
            }
        });
        homeRefreshLayout.setEnableRefresh(false);
        homeRefreshLayout.setRefreshFooter(new ClassicsFooter(view.getContext()));
        homeRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });


    }

    private void initData() {

        viewModel.getData().observe(this, new Observer<List<NewsBean>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<NewsBean> newsBeans) {
                if (isLoading == 0) {
                    homeLoading.setVisibility(View.GONE);
                    homeRefreshLayout.setVisibility(View.VISIBLE);
                    isLoading++;
                }
                Log.e("观察着", "收到数据更新");
                homeRefreshLayout.finishLoadMore(true);
                adapter.setNewsBean(newsBeans);
                data = newsBeans;
            }
        });

    }

    private void initView(View view) {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        honeNewsList = view.findViewById(R.id.hone_news_list);
        homeRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.home_refreshLayout);
        homeLoading = (TextView) view.findViewById(R.id.home_loading);
        data = new ArrayList<>();
        adapter = new NewsAdapter(view.getContext(), data);

        bannerData = new ArrayList<>();
        bannerData.add(new BannerBean(1, "1", "https://i.loli.net/2020/12/09/UCFyNItO5HEPf3T.jpg"));
        bannerData.add(new BannerBean(2, "2", "https://i.loli.net/2020/12/09/c5NwmuExXSQUvdW.jpg"));
        bannerData.add(new BannerBean(3, "3", "https://i.loli.net/2020/12/09/iXQZeRymtvVbY1F.jpg"));
        bannerImageAdapter = new BannerImageAdapter(bannerData);
    }
}