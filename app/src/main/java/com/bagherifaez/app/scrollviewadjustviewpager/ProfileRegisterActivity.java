package com.bagherifaez.app.scrollviewadjustviewpager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.badoualy.stepperindicator.StepperIndicator;

public class ProfileRegisterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String TAG = "ProfileRegisterActivity";
    Button button;

    boolean isBusiness = false;
    String id, userName, userEmail,accountStatus,avatarUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // register the event to listen.

        // get user data from session

        setContentView(R.layout.activity_profile_register);

        initialiseNavigation();
        // Session Manager
        NestedScrollView scrollView = (NestedScrollView) findViewById (R.id.nestedScroller);
        scrollView.setFillViewport (true);

//        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        final NonSwipeableViewPager pager = (NonSwipeableViewPager) findViewById(R.id.vpProfileRegister);
        assert pager != null;
        final EmptyPagerAdapter emptyPagerAdapter = new EmptyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(emptyPagerAdapter);

        button = (Button) findViewById(R.id.btnProfileRegisterNext);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                pager.setCurrentItem(pager.getCurrentItem()+1);
            }
        });



        StepperIndicator indicator = (StepperIndicator) findViewById(R.id.stepper_indicator);
        assert indicator != null;
        // We keep last page for a "finishing" page
        indicator.setViewPager(pager, true);


    }

    private void initialiseNavigation() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView tvUsername = (TextView) hView.findViewById(R.id.tv_navHeader_userName);
        TextView tvUserEmail = (TextView) hView.findViewById(R.id.tv_navHeader_userEmail);
        TextView tvStatus = (TextView) hView.findViewById(R.id.tv_navHeader_status);
        final ImageView ivUserImage = (ImageView) hView.findViewById(R.id.iv_navHeader_userImage);
        tvUsername.setText("Guest Account");
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static class EmptyPagerAdapter extends FragmentPagerAdapter {

        public EmptyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return ProfileDetailsFragment.newInstance(position + 1, position == getCount() - 1);
                case 1:
                    return ProfileDetailsFragment.newInstance(position + 1, position == getCount() - 1);
                case 2:
                    return ProfileDetailsFragment.newInstance(position + 1, position == getCount() - 1);
                case 3:
                    return ProfileDetailsFragment.newInstance(position + 1, position == getCount() - 1);
            }
            return ProfileDetailsFragment.newInstance(position + 1, position == getCount() - 1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "step 1";
                case 1:
                    return "step 2";
                case 2:
                    return "step 3";
            }
            return "Page " + position;
        }
    }

}
