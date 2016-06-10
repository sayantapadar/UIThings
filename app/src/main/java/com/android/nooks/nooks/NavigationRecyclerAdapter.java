package com.android.nooks.nooks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by SAYAN on 21-02-2016.
 */
public class NavigationRecyclerAdapter extends RecyclerView.Adapter<NavigationRecyclerAdapter.myViewHolder> {
    private LayoutInflater inflater;
    Context context;
    List<Information> data = Collections.emptyList();

    public NavigationRecyclerAdapter(Context context, List<Information> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public void deleteItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView title;
        ImageView icon;
        LinearLayout container;

        public myViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            container = (LinearLayout) itemView.findViewById(R.id.row_container);
            container.setOnClickListener(this);
            container.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("App", "Clicked at " + getAdapterPosition());
            switch (getAdapterPosition()) {
                //case 0:
                 //   if (context.getClass() != AdoptionActivity.class)
                 //       context.startActivity(new Intent(context, AdoptionActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                //    break;
                //case 1:
                //    context.startActivity(new Intent(context, MusicActivity.class));
                //    break;
                case 1:
                    Intent intent=new Intent(context, StarredPetsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //int classId=0;
                    //if(context.getClass()==HomePopupActivity.class)
                    //    classId=200;
                    //else if(context.getClass()==HomeActivity.class)
                    //    classId=100;
                    //intent.putExtra("class_id",classId);
                    context.startActivity(intent);
                    break;
                //case 3:
                //    context.startActivity(new Intent(context, PublicationsActivity.class));
                //    break;
            }
        }

        @Override
        public boolean onLongClick(View v) {

            return false;
        }
    }
}