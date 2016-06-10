package com.android.nooks.nooks;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private static final String PREF_FILE_NAME = "testpref";
    private static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearned;
    private boolean mFromSavedInstanceState;
    private View containerView;

    private RecyclerView recyclerView;
    private NavigationRecyclerAdapter adaptor;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false");
        mUserLearned = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null)
            mFromSavedInstanceState = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        adaptor = new NavigationRecyclerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LinearLayout homeContainer = (LinearLayout) layout.findViewById(R.id.home_container_nav_drawer);
        homeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getClass() != HomeActivity.class) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        LinearLayout contactContainer = (LinearLayout) layout.findViewById(R.id.contact_us_container_nav_drawer);
        contactContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ContactUsActivity.class));
            }
        });

        return layout;
    }


    public void setUp(int fragmnentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmnentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (mFromSavedInstanceState) {
                    mUserLearned = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearned + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
                mUserLearned = false;
            }
        };
        //if (!mUserLearned && !mFromSavedInstanceState)
        //    mDrawerLayout.openDrawer(containerView);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static List<Information> getData() {
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_book_black_36dp, R.drawable.ic_book_black_36dp};
        String[] titles = {"Adopt or Buy", "Starred Pets"};
        for (int i = 0; i < icons.length && i < titles.length; i++) {
            Information current = new Information();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }
}

