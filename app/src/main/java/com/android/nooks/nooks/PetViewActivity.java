package com.android.nooks.nooks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PetViewActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton fab;
    ImageView imageExtra;
    Class parentActivity;
    Context context;
    String dataName;
    int position;
    boolean inZoom = false;
    float prevY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_view);

        Intent intent=getIntent();
        context=this;

        parentActivity= (Class) intent.getSerializableExtra("parent_activity");
        //String titleExtra=intent.getStringExtra("title");
        //String detailExtra=intent.getStringExtra("details");

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
        dataName=intent.getStringExtra("data_name");
        final ArrayList<Information> data=DataSet.getPetsData(dataName);
        position=intent.getIntExtra("position", 0);
        String titleExtra=data.get(position).title;
        String detailExtra=data.get(position).details;

        TextView titleView= (TextView) findViewById(R.id.titleText);
        TextView detailView= (TextView) findViewById(R.id.detailsText);
        titleView.setText(titleExtra);
        detailView.setText(detailExtra);
        imageExtra= (ImageView) findViewById(R.id.image);
        //imageExtra.setImageResource(intent.getIntExtra("image", 0));
        imageExtra.setImageResource(data.get(position).imageId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NavUtils.navigateUpTo((Activity) context,new Intent(context,parentClass));
                //NavUtils.shouldUpRecreateTask((Activity) context, new Intent(context, HomePopupActivity.class).putExtra("data_id", dataName));
                Intent intent=new Intent(context,parentActivity).putExtra("data_id", dataName);
                intent.putExtra("title_toolbar", dataName);
                if(parentActivity==HomePopupActivity.class)
                    intent.putExtra("initial_scroll_position",position);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(titleExtra);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        setPalette();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(data.get(position).starred)
                    Snackbar.make(view, "Already its favourite", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                else {
                    Snackbar.make(view, "Set as favourite", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    data.get(position).starred=true;
                    DataSet.addStarred(data.get(position));
                }
            }
        });
    }
    private void setPalette(){
        Bitmap bitmap = ((BitmapDrawable) imageExtra.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
                int primary = getResources().getColor(R.color.colorPrimary);
                collapsingToolbarLayout.setContentScrimColor(palette.getVibrantColor(primary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkVibrantColor(primaryDark));
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            //NavUtils.navigateUpTo(this,new Intent(this,parentClass));
            Intent intent=new Intent(context,parentActivity).putExtra("data_id",dataName);
            if(parentActivity==HomePopupActivity.class)
                intent.putExtra("initial_scroll_position",position);
            intent.putExtra("title_toolbar", dataName);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
