package com.didactapp.didact.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.didactapp.didact.R;


class
RecyclerViewChapterHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView name;
    TextView description;

    RecyclerViewChapterHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.chapter_image);
        name = itemView.findViewById(R.id.chapter_name);
        description = itemView.findViewById(R.id.chapter_description);
    }

}