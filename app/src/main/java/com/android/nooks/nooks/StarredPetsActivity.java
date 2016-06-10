package com.android.nooks.nooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

public class StarredPetsActivity extends AppCompatActivity {
    Context context;
    Class parentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred_pets);

        Intent intent=getIntent();
        context=this;
        /*int parentClassId=intent.getIntExtra("class_id",0);

        switch (parentClassId)
        {
            case 100: parentClass=HomeActivity.class;
                break;
            case 200: parentClass=AdoptionActivity.class;
                break;
            case 300: parentClass=StarredPetsActivity.class;
                break;
        }*/
        parentClass=HomeActivity.class;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpTo((Activity) context, new Intent(context, parentClass));
                finish();
            }
        });

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerViewStarred);
        ListViewRecyclerAdapter adapter=new ListViewRecyclerAdapter(this,DataSet.getStarredData(),true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            NavUtils.navigateUpTo(this, new Intent(this, parentClass));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
