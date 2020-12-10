package cn.hnvist.client.ui.fragment.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import cn.hnvist.client.R;
import cn.hnvist.client.ui.fragment.home.HomeFragment;
import cn.hnvist.client.ui.fragment.notifications.NotificationsFragment;

public class DashboardFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    HomeFragment homeFragment;
    NotificationsFragment notificationsFragment;
    String [] strings={"联系人","陌生人"};
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        viewPager= view.findViewById(R.id.id_xiaoxi_view_pager);
        tabLayout=view.findViewById(R.id.id_xiaoxi_tab_layout);
        viewPager.setOrientation(viewPager.ORIENTATION_HORIZONTAL);
        List<Fragment> fragments=new ArrayList<>();
        homeFragment=new HomeFragment();
        notificationsFragment=new NotificationsFragment();
        fragments.add(homeFragment);
        fragments.add(notificationsFragment);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment=fragments.get(position);
                return fragment;
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(strings[position]);
            }
        }).attach();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}