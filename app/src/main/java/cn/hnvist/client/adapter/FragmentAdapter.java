package cn.hnvist.client.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior,List<Fragment> list) {
        super(fm, behavior);
        this.list=list;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
