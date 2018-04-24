package com.didactapp.didact.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.Book;
import com.didactapp.didact.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewBookAdapter extends RecyclerView.Adapter<RecyclerViewBookHolder> {
    private List<Book> bookList;
    private Context context;
    private View.OnClickListener clickListener;

    public RecyclerViewBookAdapter(Context context, List<Book> bookList, View.OnClickListener clickListener) {
        this.context = context;
        this.bookList = bookList;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerViewBookHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library, null);
        layoutView.setOnClickListener(clickListener);
        RecyclerViewBookHolder recyclerViewHolder = new RecyclerViewBookHolder(layoutView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewBookHolder holder, int position) {

        Book bookAtPosition = bookList.get(position);
        holder.title.setText(bookAtPosition.getTitle());
        holder.tagline.setText(bookAtPosition.getTagLine());

//        TODO: find a way to move picasso out of here and remove global context variable
        Picasso.with(context)
                .load(Constants.BASE_URL + bookAtPosition.getThumbnailUrl())
                .placeholder(R.drawable.ic_book) // show this image if not loaded yet
                .error(R.drawable.ic_book)      // show this if error or image not exist
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    public int getBookId(int position) {
        return bookList.get(position).getBookId();
    }

}