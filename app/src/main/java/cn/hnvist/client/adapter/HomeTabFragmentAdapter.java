package cn.hnvist.client.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import cn.hnvist.client.bean.TabBean;

public class HomeTabFragmentAdapter extends FragmentStateAdapter {
    List<TabBean> data;

    public HomeTabFragmentAdapter(@NonNull Fragment fragment, List<TabBean> data) {
        super(fragment);
        this.data = data;
    }

    public void setTabBean(List<TabBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return data.get(position).getFragment();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
