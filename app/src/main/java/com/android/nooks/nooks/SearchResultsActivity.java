package com.android.nooks.nooks;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {
    TextView search_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        search_textView=(TextView)findViewById(R.id.search_text_view);

        handleIntent(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Bundle searchData=intent.getBundleExtra(SearchManager.APP_DATA);
            doMySearch(query,searchData);
        }
    }

    private void doMySearch(String query, Bundle searchData) {
        search_textView.setText(searchData.getString("label")+"\n"+query);
    }
}
