package com.android.nooks.nooks;


import android.app.ActivityOptions;
import android.content.Intent;
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
public class HomePetsFragment extends Fragment {


    public HomePetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_home_pets,container,false);

        layout.findViewById(R.id.home_rescue_dog_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bndlanimation =
                        ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
                startActivity(new Intent(getActivity(), HomePopupActivity.class).putExtra("data_id", "catDog").putExtra("title_toolbar","Dogs"),bndlanimation);
            }
        });
        layout.findViewById(R.id.home_buy_dog_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bndlanimation =
                        ActivityOptions.makeCustomAnimation(getActivity().getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
                startActivity(new Intent(getActivity(), HomePopupActivity.class).putExtra("data_id", "catDog").putExtra("title_toolbar", "Cats").putExtra("initial_scroll_position",0), bndlanimation);
            }
        });

        RecyclerView horizontalItem1= (RecyclerView) layout.findViewById(R.id.pet_recycler_horizontal_1);
        HorizontalItemRecyclerAdapter horizontalItemRecyclerAdapter=new HorizontalItemRecyclerAdapter(getActivity(),DataSet.getPetsData("catDog"));
        horizontalItem1.setAdapter(horizontalItemRecyclerAdapter);
        horizontalItem1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        return layout;
    }

}
