package com.android.nooks.nooks;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class HomePopupActivity extends AppCompatActivity {
    Toolbar toolbar;
    Context context;
    String dataId;
    int initialScrollPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        context=this;

        dataId=getIntent().getStringExtra("data_id");
        initialScrollPosition=getIntent().getIntExtra("initial_scroll_position",0);

        String toolbarTitle=getIntent().getStringExtra("title_toolbar");
        Log.d("App","Id i have is "+dataId);
        ArrayList<Information> dataRequired=DataSet.getPetsData(dataId);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(toolbarTitle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NavUtils.navigateUpTo((Activity) context,new Intent(context,parentClass));
                Intent intent=new Intent(context,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bndlanimation =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
            }
        });

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        ListViewRecyclerAdapter adapter=new ListViewRecyclerAdapter(this,dataRequired,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.scrollToPosition(initialScrollPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.removeItem(R.id.action_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent=new Intent(this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Bundle bndlanimation =
                    ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right).toBundle();
            startActivity(intent,bndlanimation);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
