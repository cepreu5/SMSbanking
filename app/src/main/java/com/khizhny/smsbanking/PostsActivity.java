package com.khizhny.smsbanking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.khizhny.smsbanking.fragment.MyPostsFragment;
import com.khizhny.smsbanking.fragment.MyCountryPostsFragment;

public class PostsActivity extends AppCompatActivity {

    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private String country;
    private MyCountryPostsFragment myCountryPostsFragment;
    private MyPostsFragment myPostsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        country=getCountry();

        Bundle data = new Bundle();
        data.putString("country",getCountry());
        myCountryPostsFragment=new MyCountryPostsFragment();
        myCountryPostsFragment.setArguments(data);

        myPostsFragment=new MyPostsFragment();
        myPostsFragment.setArguments(data);

        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[]{
                    myCountryPostsFragment,
                    myPostsFragment,
            };

            private final String[] mFragmentNames = new String[]{
                   country,
                   getString(R.string.my_posts)
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private String getCountry(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return settings.getString("country_preference",null);
    }

}
