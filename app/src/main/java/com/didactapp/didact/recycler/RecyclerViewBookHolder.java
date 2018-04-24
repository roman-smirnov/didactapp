package com.didactapp.didact.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.didactapp.didact.R;


/**
 * class to hold a list item within a recycler list view
 **/
class RecyclerViewBookHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView title;
    TextView tagline;


    RecyclerViewBookHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.book_image);
        title = itemView.findViewById(R.id.book_title);
        tagline = itemView.findViewById(R.id.book_tagline);
    }

}