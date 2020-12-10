package cn.hnvist.client.ui.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import cn.hnvist.client.R;
import cn.hnvist.client.adapter.HomeTabFragmentAdapter;
import cn.hnvist.client.bean.TabBean;
import cn.hnvist.client.ui.fragment.home.tab.clazz.ClazzFragment;
import cn.hnvist.client.ui.fragment.home.tab.department.DepartmentFragment;
import cn.hnvist.client.ui.fragment.home.tab.news.NewsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private EditText homeSearch;
    private TabLayout homeTab;
    private Context context;
    private ViewPager2 homePager;
    private HomeTabFragmentAdapter fragmentAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initView(root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        init();
    }

    private void init() {
        mViewModel.getFragments().observe(this, new Observer<List<TabBean>>() {
            @Override
            public void onChanged(List<TabBean> tabBeans) {
                fragmentAdapter.setTabBean(tabBeans);
                homePager.setCurrentItem(1);
                new TabLayoutMediator(homeTab, homePager, true, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabBeans.get(position).getTitle());
                    }
                }).attach();
            }
        });


    }

    private void initView(View view) {
        context = view.getContext();
        fragmentAdapter = new HomeTabFragmentAdapter(this, new ArrayList<>());
        homeSearch = (EditText) view.findViewById(R.id.home_search);
        homeTab = (TabLayout) view.findViewById(R.id.home_tab);
        homePager = (ViewPager2) view.findViewById(R.id.home_pager);

        homePager.setAdapter(fragmentAdapter);
    }

}