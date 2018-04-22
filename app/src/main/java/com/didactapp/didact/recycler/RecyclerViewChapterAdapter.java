package com.didactapp.didact.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.Chapter;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewChapterAdapter extends RecyclerView.Adapter<RecyclerViewChapterHolder> {
    private List<Chapter> chapterList;
    private Context context;
    private View.OnClickListener clickListener;


    public RecyclerViewChapterAdapter(Context context, List<Chapter> chapterList, View.OnClickListener clickListener) {
        this.context = context;
        this.chapterList = chapterList;
        this.clickListener = clickListener;


    }

    @Override
    public RecyclerViewChapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, null);
        layoutView.setOnClickListener(clickListener);
        RecyclerViewChapterHolder recyclerViewHolder = new RecyclerViewChapterHolder(layoutView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewChapterHolder holder, int position) {
        Chapter chapterAtPosition = chapterList.get(position);
        String name = chapterAtPosition.getName();
        if (name != null) {
            holder.name.setText(name);
        }

        String desc = chapterAtPosition.getDescription();
        if (desc != null) {
            holder.description.setText(desc);
        }

//        TODO: find a way to move picasso out of here and remove global context variable
        Picasso.with(context)
                .load(R.mipmap.ic_launcher)
                .placeholder(R.drawable.ic_book) // show this image if not loaded yet
                .error(R.drawable.ic_book)      // show this if error or image not exist
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.chapterList.size();
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public int getChapterId(int position) {
        return chapterList.get(position).getChapterId();
    }

}