package com.didactapp.didact.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.didactapp.didact.R;


class RecyclerViewSectionHolder extends RecyclerView.ViewHolder {

    LottieAnimationView state;
    TextView name;
    TextView number;


    RecyclerViewSectionHolder(View itemView) {
        super(itemView);
        state = itemView.findViewById(R.id.section_state);
        name = itemView.findViewById(R.id.section_name);
        number = itemView.findViewById(R.id.section_number);
    }

}