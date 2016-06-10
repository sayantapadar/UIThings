package com.android.nooks.nooks;

/**
 * Created by SAYAN on 04-03-2016.
 */

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class HorizontalItemRecyclerAdapter extends RecyclerView.Adapter<HorizontalItemRecyclerAdapter.MyViewHolder> {

    private Context context;

    public ArrayList<Information> data;

    private LayoutInflater inflater;

    public HorizontalItemRecyclerAdapter(Context context, ArrayList<Information> data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.custom_row_horizontal_item, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.imageView.setImageResource(data.get(position).imageId);
        myViewHolder.textLine1.setText(data.get(position).details);
        myViewHolder.textLine2.setText(data.get(position).details2);
        myViewHolder.textLine3.setText(data.get(position).details3);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textLine1;
        TextView textLine2;
        TextView textLine3;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.home_item_image);
            textLine1=(TextView)itemView.findViewById(R.id.home_item_line1);
            textLine2=(TextView)itemView.findViewById(R.id.home_item_line2);
            textLine3=(TextView)itemView.findViewById(R.id.home_item_line3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Information info = data.get(getAdapterPosition());
            final int position = getAdapterPosition();

            switch (v.getId()) {
                case R.id.home_item_container:
                    Snackbar.make(v, "You click an item m8", Snackbar.LENGTH_SHORT).show();
                    break;
            }
        }


    }
}