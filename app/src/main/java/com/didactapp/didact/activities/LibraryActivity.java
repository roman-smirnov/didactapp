package com.didactapp.didact.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.didactapp.didact.R;
import com.didactapp.didact.contracts.LibraryContract;
import com.didactapp.didact.entities.Book;
import com.didactapp.didact.network.BookRemoteGateway;
import com.didactapp.didact.persistence.BookLocalGateway;
import com.didactapp.didact.presenters.LibraryPresenter;
import com.didactapp.didact.recycler.RecyclerViewBookAdapter;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.didactapp.didact.utils.Constants.BOOK_ID_INTENT_KEY;

/**
 * this activity is a view controller for displaying a grid of available books
 */
public class LibraryActivity extends BaseActivity implements LibraryContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    /*  num of columns the book grid is composed of */
    private static final int NUM_OF_COLUMNS = 2;

    /* view refs */
    private RecyclerView recyclerView;
    private TextView noContentView;
    private Snackbar noNetworkSnackbar;
    private Snackbar loadErrorSnackbar;
    private LibraryContract.Presenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        /* get layout elements */
        recyclerView = findViewById(R.id.library_grid);
        swipeRefreshLayout = findViewById(R.id.library_swipe_refresh);
        noContentView = findViewById(R.id.library_no_content);
        noNetworkSnackbar = Snackbar.make(swipeRefreshLayout, R.string.error_no_network, Snackbar.LENGTH_INDEFINITE);
        loadErrorSnackbar = Snackbar.make(swipeRefreshLayout, R.string.error_loading, Snackbar.LENGTH_INDEFINITE);

        /* set swipe refresh listener */
        swipeRefreshLayout.setOnRefreshListener(this);

        /* init recyclerView view */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.HORIZONTAL));

        /* retrieve  or create presenter */
        presenter = (LibraryContract.Presenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new LibraryPresenter(BookRemoteGateway.getInstance(), BookLocalGateway.getInstance(this));
        }

        /* set the presenter view callback */
        presenter.takeView(this);
    }


    /* save the presenter on screen rotation */
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void showBooks(List<Book> bookList) {
        /* swap the list old data with the new data */
        RecyclerViewBookAdapter recyclerViewAdapter = new RecyclerViewBookAdapter(this, bookList, this);
        recyclerView.swapAdapter(recyclerViewAdapter, false);
        recyclerView.setVisibility(VISIBLE);
    }

    @Override
    public void hideBooks() {
        recyclerView.setVisibility(GONE);
    }

    @Override
    public void showBookDetailsUi(String bookId) {
        launchActivity(this, ChapterActivity.class);
    }

    @Override
    public void showSpinner() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSpinner() {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showNoContent() {
        noContentView.setVisibility(VISIBLE);
    }

    @Override
    public void hideNoContent() {
        noContentView.setVisibility(GONE);

    }

    @Override
    public void showLoadError() {
        if (loadErrorSnackbar.isShown()) {
            return;
        }
        loadErrorSnackbar.show();
    }

    @Override
    public void hideLoadError() {
        if (!loadErrorSnackbar.isShown()) {
            return;
        }
        loadErrorSnackbar.dismiss();

    }

    @Override
    public void showNoNetwork() {
        if (noNetworkSnackbar.isShown()) {
            return;
        }
        noNetworkSnackbar.show();
    }

    @Override
    public void hideNoNetwork() {
        if (!noNetworkSnackbar.isShown()) {
            return;
        }
        noNetworkSnackbar.dismiss();

    }

    @Override
    public void checkNetworkState() {
        if (isNetworkConnected()) {
            presenter.networkConnected();
        } else {
            presenter.networkDisconnected();
        }
    }

    @Override
    public void onRefresh() {
        presenter.update();
    }

    @Override
    public void onClick(View v) {
        /* launch the chapter list activity. also pass the selected book id to it */
        int pos = recyclerView.getChildAdapterPosition(v);
        int bookId = ((RecyclerViewBookAdapter) recyclerView.getAdapter()).getBookId(pos);
        launchActivity(this, ChapterActivity.class, BOOK_ID_INTENT_KEY, bookId);
    }
}
