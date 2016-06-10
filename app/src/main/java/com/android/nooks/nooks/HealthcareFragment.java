package com.android.nooks.nooks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthcareFragment extends Fragment {


    public HealthcareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_healthcare,container,false);
        RecyclerView itemRecyclerView= (RecyclerView) layout.findViewById(R.id.health_item_recycler_view);
        HorizontalItemRecyclerAdapter adapter=new HorizontalItemRecyclerAdapter(getActivity(),DataSet.health);
        itemRecyclerView.setAdapter(adapter);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        return layout;
    }

}
