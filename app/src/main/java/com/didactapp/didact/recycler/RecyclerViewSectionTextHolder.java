package com.didactapp.didact.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.didactapp.didact.R;


class RecyclerViewSectionTextHolder extends RecyclerView.ViewHolder {

    TextView sectionNum;


    RecyclerViewSectionTextHolder(View itemView) {
        super(itemView);
        sectionNum = itemView.findViewById(R.id.section_num);
    }

}