package com.didactapp.didact.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.didactapp.didact.R;


class RecyclerViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView title;
    TextView tagline;


    RecyclerViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.book_image);
        title = itemView.findViewById(R.id.book_title);
        tagline = itemView.findViewById(R.id.book_tagline);
    }

}