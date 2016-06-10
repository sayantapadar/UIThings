package com.android.nooks.nooks;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    Context context;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;

        if (!DataSet.petsDataGenerated)
            DataSet.setPetsData();
        if(!DataSet.healthDataGenerated)
            DataSet.setHealthData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
        }

        final ViewPager imagePageViewer=(ViewPager) findViewById(R.id.image_page_viewer);
        final ImagePagerAdapter imagePagerAdapter=new ImagePagerAdapter();
        imagePageViewer.setAdapter(imagePagerAdapter);
        final ImageView img1= (ImageView) findViewById(R.id.image_page1);
        final ImageView img2= (ImageView) findViewById(R.id.image_page2);
        final ImageView img3= (ImageView) findViewById(R.id.image_page3);
        final ImageView img4= (ImageView) findViewById(R.id.image_page4);
        final ImageView img5= (ImageView) findViewById(R.id.image_page5);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout_home), toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab);
        if (tabLayout != null)
            tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                {
                    Bundle searchData=getsearchData(0);
                    searchView.setAppSearchData(searchData);

                }
                else
                {
                    Bundle searchData=getsearchData(1);
                    searchView.setAppSearchData(searchData);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        imagePageViewer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:img1.setImageResource(R.drawable.ic_big_dot);
                        img2.setImageResource(R.drawable.ic_small_dot);
                        img3.setImageResource(R.drawable.ic_small_dot);
                        img4.setImageResource(R.drawable.ic_small_dot);
                        img5.setImageResource(R.drawable.ic_small_dot);
                        break;
                    case 1:img1.setImageResource(R.drawable.ic_small_dot);
                        img2.setImageResource(R.drawable.ic_big_dot);
                        img3.setImageResource(R.drawable.ic_small_dot);
                        img4.setImageResource(R.drawable.ic_small_dot);
                        img5.setImageResource(R.drawable.ic_small_dot);
                        break;
                    case 2:img1.setImageResource(R.drawable.ic_small_dot);
                        img2.setImageResource(R.drawable.ic_small_dot);
                        img3.setImageResource(R.drawable.ic_big_dot);
                        img4.setImageResource(R.drawable.ic_small_dot);
                        img5.setImageResource(R.drawable.ic_small_dot);
                        break;
                    case 3:img1.setImageResource(R.drawable.ic_small_dot);
                        img2.setImageResource(R.drawable.ic_small_dot);
                        img3.setImageResource(R.drawable.ic_small_dot);
                        img4.setImageResource(R.drawable.ic_big_dot);
                        img5.setImageResource(R.drawable.ic_small_dot);
                        break;
                    case 4:img1.setImageResource(R.drawable.ic_small_dot);
                        img2.setImageResource(R.drawable.ic_small_dot);
                        img3.setImageResource(R.drawable.ic_small_dot);
                        img4.setImageResource(R.drawable.ic_small_dot);
                        img5.setImageResource(R.drawable.ic_big_dot);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setBackgroundColor(getColor(R.color.transparent_primary));

        Bundle searchData=getsearchData(0);
        searchView.setAppSearchData(searchData);
        return true;
    }

    private Bundle getsearchData(int i) {
        Bundle searchData=new Bundle();
        String label="";
        switch (i)
        {
            case 0: label="Pets"; break;
            case 1: label="Healthcare"; break;
        }
        searchData.putString("label", label);
        return searchData;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        HomePetsFragment homePetsFragment = new HomePetsFragment();
        HealthcareFragment healthcareFragment = new HealthcareFragment();
        viewPagerAdapter.addFrag(homePetsFragment, "Pets");
        viewPagerAdapter.addFrag(healthcareFragment, "Health Care");
        viewPager.setAdapter(viewPagerAdapter);
    }
    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[] {
                R.drawable.home_1,
                R.drawable.home_2,
                R.drawable.home_3,
                R.drawable.home_4,
                R.drawable.home_5
        };

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = HomeActivity.this;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}