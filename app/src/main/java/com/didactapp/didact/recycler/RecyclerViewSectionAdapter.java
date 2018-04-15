package com.didactapp.didact.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.Section;

import java.util.List;


public class RecyclerViewSectionAdapter extends RecyclerView.Adapter<RecyclerViewSectionTextHolder> {
    private List<Section> sectionList;
    private Context context;

    public RecyclerViewSectionAdapter(Context context, List<Section> chapterList) {
        this.sectionList = chapterList;
        this.context = context;
    }

    @Override
    public RecyclerViewSectionTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, null);
        RecyclerViewSectionTextHolder recyclerViewHolder = new RecyclerViewSectionTextHolder(layoutView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewSectionTextHolder holder, int position) {
        Section sectionAtPosition = sectionList.get(position);
        holder.sectionNum.setText(sectionAtPosition.getSectionNum());
    }

    @Override
    public int getItemCount() {
        return this.sectionList.size();
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

}