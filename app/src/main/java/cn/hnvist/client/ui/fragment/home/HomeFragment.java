package cn.hnvist.client.ui.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayout;

import cn.hnvist.client.R;
import cn.hnvist.client.ui.fragment.home.tab.clazz.ClazzFragment;
import cn.hnvist.client.ui.fragment.home.tab.department.DepartmentFragment;
import cn.hnvist.client.ui.fragment.home.tab.news.NewsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private EditText homeSearch;
    private TabLayout homeTab;
    private Context context;
    private Fragment newsFragment, clazzFragment, departmentFragment;
    private Fragment[] fragments;
    private int lastIndex;

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
        initListener();
    }

    // 设置首页tab监听
    private void initListener() {
        homeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tId = tab.getPosition();
                switch (tId){
                    case 0:
                        if (lastIndex != 0){
                            switchFragment(lastIndex, 0);
                            lastIndex = 0;
                        }
                        break;
                    case 1:
                        if (lastIndex != 1){
                            switchFragment(lastIndex, 1);
                            lastIndex = 1;
                        }
                        break;
                    case 2:
                        if (lastIndex != 2){
                            switchFragment(lastIndex, 2);
                            lastIndex = 2;
                        }
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void init() {
        homeTab.addTab(homeTab.newTab().setText("咨询"));
        homeTab.addTab(homeTab.newTab().setText("班级"));
        homeTab.addTab(homeTab.newTab().setText("院部"));
        // 设置咨询页面为第一页
        getFragmentManager().beginTransaction().replace(R.id.home_fragment, newsFragment).show(newsFragment).commit();
    }

    private void initView(View view) {
        homeSearch = (EditText) view.findViewById(R.id.home_search);
        homeTab = (TabLayout) view.findViewById(R.id.home_tab);
        newsFragment = NewsFragment.newInstance();
        clazzFragment = ClazzFragment.newInstance();
        departmentFragment = DepartmentFragment.newInstance();
        context = view.getContext();
        fragments = new Fragment[]{newsFragment, clazzFragment, departmentFragment};

    }

    private void switchFragment(int lastIndex, int index){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()){
            transaction.add(R.id.home_fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }
}