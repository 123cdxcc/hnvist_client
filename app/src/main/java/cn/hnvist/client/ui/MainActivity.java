package cn.hnvist.client.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import cn.hnvist.client.R;
import cn.hnvist.client.ui.fragment.MyActivity;
import cn.hnvist.client.ui.fragment.dashboard.DashboardFragment;
import cn.hnvist.client.ui.fragment.home.HomeFragment;
import cn.hnvist.client.ui.fragment.notifications.NotificationsFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment homeFragment,dashboardFragment,notificationsFragment,myactivity;
    private Fragment[] fragments;
    private int lastIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        initView();
        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, homeFragment).show(homeFragment).commit();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        if (lastIndex != 0){
                            switchFragment(lastIndex, 0);
                            lastIndex = 0;
                        }
                        return true;
                    case R.id.navigation_dashboard:
                        if (lastIndex != 1){
                            switchFragment(lastIndex, 1);
                            lastIndex = 1;
                        }
                        return true;
                    case R.id.navigation_notifications:
                        if (lastIndex != 2){
                            switchFragment(lastIndex, 2);
                            lastIndex = 2;
                        }
                        return true;
                    case R.id.id_navigation_wode:
                        if (lastIndex !=3){
                            switchFragment(lastIndex,3);
                            lastIndex=3;
                        }
                        return true;
                }
                return false;
            }
        });
    }

    private void initView() {
        homeFragment = new HomeFragment();
        dashboardFragment = new DashboardFragment();
        notificationsFragment = new NotificationsFragment();
        myactivity=new MyActivity();
        fragments = new Fragment[]{homeFragment, dashboardFragment, notificationsFragment,myactivity};
        lastIndex = 0;
    }

    private void switchFragment(int lastIndex, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < 4; i++) {
            transaction.hide(fragments[i]);
        }
        if (!fragments[index].isAdded()){
            transaction.add(R.id.nav_host_fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}