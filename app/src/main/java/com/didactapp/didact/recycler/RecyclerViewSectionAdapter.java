package com.didactapp.didact.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.Chapter;

import java.util.List;


public class RecyclerViewSectionAdapter extends RecyclerView.Adapter<RecyclerViewSectionHolder> {
    private List<Chapter> chapterList;
    private Context context;

    public RecyclerViewSectionAdapter(Context context, List<Chapter> chapterList) {
        this.chapterList = chapterList;
        this.context = context;
    }

    @Override
    public RecyclerViewSectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, null);
        RecyclerViewSectionHolder recyclerViewHolder = new RecyclerViewSectionHolder(layoutView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewSectionHolder holder, int position) {
        Chapter chapterAtPosition = chapterList.get(position);
        holder.sectionNum.setText(chapterAtPosition.getName());
    }

    @Override
    public int getItemCount() {
        return this.chapterList.size();
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

}