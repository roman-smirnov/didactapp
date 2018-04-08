package com.didactapp.didact.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.R;
import com.didactapp.didact.contracts.LibraryContract;
import com.didactapp.didact.entities.Book;
import com.didactapp.didact.network.RemoteGateway;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.presenters.LibraryPresenter;
import com.didactapp.didact.recycler.RecyclerViewAdapter;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LibraryActivity extends BaseActivity implements LibraryContract.View, RemoteGatewayCallback {

    private static final int NUM_OF_COLUMNS = 2;



    private RecyclerView recycler;
    private ProgressBar spinner;
    private TextView noNetwork;
    private TextView loadError;
    private LibraryContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        /* get layout elements */
        recycler = findViewById(R.id.books_grid);
        spinner = findViewById(R.id.progress_spinner);
        noNetwork = findViewById(R.id.no_network);
        loadError = findViewById(R.id.loading_error);

        /* init recycler view */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recycler.addItemDecoration(new DividerItemDecoration(this, RecyclerView.HORIZONTAL));
//        recycler.addOnItemTouchListener(this);
        /* init presenter */
        presenter = new LibraryPresenter();
        presenter.takeView(this);

        /* fetch data from server */
        RemoteGateway remoteGateway = RemoteGateway.getInstance();
        remoteGateway.getBookList(this);
    }

    @Override
    public void showBooks(List<Book> bookList) {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, bookList);
        recycler.swapAdapter(recyclerViewAdapter, false);
        recycler.setVisibility(VISIBLE);
    }

    @Override
    public void hideBooks() {
        recycler.setVisibility(GONE);
    }

    @Override
    public void showBookDetailsUi(String bookId) {
        launchActivity(this, LibraryDetailActivity.class);
    }

    @Override
    public void showSpinner() {
        spinner.setVisibility(VISIBLE);
    }

    @Override
    public void hideSpinner() {
        spinner.setVisibility(GONE);

    }

    @Override
    public void showLoadError() {
        loadError.setVisibility(VISIBLE);

    }

    @Override
    public void hideLoadError() {
        loadError.setVisibility(GONE);

    }

    @Override
    public void showNoNetwork() {
        noNetwork.setVisibility(VISIBLE);
    }

    @Override
    public void hideNoNetwork() {
        noNetwork.setVisibility(GONE);

    }


    @Override
    public void onLoadSuccess(List<Book> bookList) {
        presenter.onBooksLoaded(bookList);
    }

    @Override
    public void onDataNotAvailable() {
        LogUtils.d("data not available");
    }

    @Override
    public void onLoadFailed() {
        LogUtils.d("load failed");
    }

    /* handle recyclerview item touch */
}
