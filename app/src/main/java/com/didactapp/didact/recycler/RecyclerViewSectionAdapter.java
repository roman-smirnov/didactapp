package com.didactapp.didact.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.Section;

import java.util.List;


public class RecyclerViewSectionAdapter extends RecyclerView.Adapter<RecyclerViewSectionHolder> {
    private List<Section> sectionList;
    private Context context;
    private View.OnClickListener clickListener;

    public RecyclerViewSectionAdapter(Context context, List<Section> sectionList, View.OnClickListener clickListener) {
        this.clickListener = clickListener;
        this.sectionList = sectionList;
        this.context = context;
    }

    @Override
    public RecyclerViewSectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, null);
        layoutView.setOnClickListener(clickListener);
        RecyclerViewSectionHolder recyclerViewHolder = new RecyclerViewSectionHolder(layoutView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewSectionHolder holder, int position) {

        Section sectionAtPosition = sectionList.get(position);

        if (sectionAtPosition == null || holder == null) {
            return;
        }

        String name = sectionAtPosition.getName();
        if (name != null) {
            holder.name.setText(name);
        }

        holder.number.setText(String.valueOf(sectionAtPosition.getSectionNum()));
    }

    @Override
    public int getItemCount() {
        return this.sectionList.size();
    }

    public int getSectionId(int position) {
        return sectionList.get(position).getChapterId();
    }
}