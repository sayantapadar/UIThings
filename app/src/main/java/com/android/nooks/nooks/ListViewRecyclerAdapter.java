package com.android.nooks.nooks;

/**
 * Created by SAYAN on 04-03-2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ListViewRecyclerAdapter extends RecyclerView.Adapter<ListViewRecyclerAdapter.MyViewHolder> {

    private Context context;

    public ArrayList<Information> data;

    private LayoutInflater inflater;

    private boolean isStarredContext;

    public ListViewRecyclerAdapter(Context context, ArrayList<Information> data, boolean isStarredContext) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.isStarredContext = isStarredContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.custom_row_home_popup, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        //final Information infoData = data.get(position);

        myViewHolder.imageView.setImageResource(data.get(position).imageId);
        myViewHolder.textView.setText(data.get(position).title);
        myViewHolder.textDetails.setText(data.get(position).details);

        if (DataSet.getStarredData().contains(data.get(position))) {
            myViewHolder.star.setBackgroundResource(android.R.drawable.btn_star_big_on);
        } else
            myViewHolder.star.setBackgroundResource(android.R.drawable.btn_star_big_off);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        TextView textDetails;
        Button share;
        Button delete;
        Button star;
        RelativeLayout cardHolder;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img_row);
            textView = (TextView) itemView.findViewById(R.id.txt_row);
            textDetails = (TextView) itemView.findViewById(R.id.txt_detail);
            share = (Button) itemView.findViewById(R.id.buttonShare);
            delete = (Button) itemView.findViewById(R.id.buttonDelete);
            star = (Button) itemView.findViewById(R.id.buttonStar);
            cardHolder = (RelativeLayout) itemView.findViewById(R.id.cardHolder);


            cardHolder.setOnClickListener(this);
            share.setOnClickListener(this);
            delete.setOnClickListener(this);
            star.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Information info = data.get(getAdapterPosition());
            final int previousPosition=getAdapterPosition();

            switch (v.getId()) {
                case R.id.cardHolder:
                    Intent intent = new Intent(context, PetViewActivity.class);
                    //intent.putExtra("image", info.imageId);
                    //intent.putExtra("title", info.title);
                    //intent.putExtra("details", info.details);
                    //intent.putExtra("starred", info.starred);
                    //intent.putParcelableArrayListExtra("dataset", data);
                    intent.putExtra("position", previousPosition);
                    intent.putExtra("data_name", info.dataName);
                    intent.putExtra("parent_activity",((Activity)context).getClass());
                    //intent.putExtra("class_id",isStarredContext?300:200);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    break;


                case R.id.buttonDelete:
                    if (!isStarredContext)
                        removeItem(info);
                    else
                        removeStarredItem(info);
                    Snackbar.make(v, "Removed item", Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    addItem(previousPosition, info);
                                }
                            }).show();
                    break;


                case R.id.buttonStar:
                    if (isStarredContext) {
                        removeStarredItem(info);
                        Snackbar.make(v, "Removed Starred item", Snackbar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addItem(previousPosition, info);
                                    }
                                }).show();
                    } else {
                        if (info.starred == false) {
                            star.setBackgroundResource(android.R.drawable.btn_star_big_on);
                            info.starred = true;
                            DataSet.addStarred(info);
                        } else {
                            star.setBackgroundResource(android.R.drawable.btn_star_big_off);
                            info.starred = false;
                            DataSet.removeStarred(info);
                        }
                    }
                    break;


                case R.id.buttonShare:
                    Snackbar.make(v, "Share not implemented yet, m8", Snackbar.LENGTH_SHORT).setAction("", null).show();
                    break;
            }
        }

    }

    // This removes the data from our Dataset and Updates the Recycler View.
    private void removeItem(Information infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

    private void removeStarredItem(Information infoData) {

        int currPosition = data.indexOf(infoData);
        DataSet.removeStarred(infoData);
        notifyItemRemoved(currPosition);
        infoData.starred = false;
    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    private void addItem(int position, Information infoData) {

        data.add(position, infoData);
        notifyItemInserted(position);
    }
}