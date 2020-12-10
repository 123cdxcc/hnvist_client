package cn.hnvist.client.ui.fragment.home;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hnvist.client.bean.TabBean;
import cn.hnvist.client.ui.fragment.home.tab.clazz.ClazzFragment;
import cn.hnvist.client.ui.fragment.home.tab.department.DepartmentFragment;
import cn.hnvist.client.ui.fragment.home.tab.news.NewsFragment;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<TabBean>> fragments;
    private Fragment clazzFragment, newsFragment, departmentFragment;

    public HomeViewModel() {
        this.fragments = new MutableLiveData<>();
    }

    public LiveData<List<TabBean>> getFragments(){
        newsFragment = NewsFragment.newInstance();
        clazzFragment = ClazzFragment.newInstance();
        departmentFragment = DepartmentFragment.newInstance();
        List<TabBean> data = new ArrayList<>();
        data.add(new TabBean("班级", clazzFragment));
        data.add(new TabBean("咨询", newsFragment));
        data.add(new TabBean("院部", departmentFragment));
        fragments.setValue(data);
        return fragments;
    }
}