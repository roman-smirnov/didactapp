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
import com.didactapp.didact.contracts.SectionContract;
import com.didactapp.didact.entities.Section;
import com.didactapp.didact.network.SectionRemoteGateway;
import com.didactapp.didact.persistence.SectionLocalGateway;
import com.didactapp.didact.presenters.SectionPresenter;
import com.didactapp.didact.recycler.RecyclerViewBookAdapter;
import com.didactapp.didact.recycler.RecyclerViewSectionAdapter;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SectionActivity extends BaseActivity implements SectionContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private static final int NUM_OF_COLUMNS = 1;
    private static final String INTENT_KEY = "bookId";
    private static final String DATABASE_NAME = "book_db";

    private RecyclerView recyclerView;
    private TextView noContentView;
    private Snackbar noNetworkSnackbar;
    private Snackbar loadErrorSnackbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SectionContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);


        /* get layout elements */
        recyclerView = findViewById(R.id.chapter_list);
        swipeRefreshLayout = findViewById(R.id.library_swipe_refresh);
        noContentView = findViewById(R.id.library_no_content);
        noNetworkSnackbar = Snackbar.make(swipeRefreshLayout, R.string.error_no_network, Snackbar.LENGTH_INDEFINITE);
        loadErrorSnackbar = Snackbar.make(swipeRefreshLayout, R.string.error_loading, Snackbar.LENGTH_INDEFINITE);


        /* init recyclerView view */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.HORIZONTAL));

        /* retrieve or create presenter */
        presenter = (SectionContract.Presenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new SectionPresenter(SectionRemoteGateway.getInstance(), SectionLocalGateway.getInstance(this));
        }

        presenter.takeView(this);
    }

    @Override
    public void showSections(List<Section> sectionList) {
        RecyclerViewSectionAdapter recyclerViewAdapter = new RecyclerViewSectionAdapter(this, sectionList, this);
        recyclerView.swapAdapter(recyclerViewAdapter, false);
        recyclerView.setVisibility(VISIBLE);
    }

    @Override
    public void hideSections() {
        recyclerView.setVisibility(GONE);
    }

    @Override
    public void showSectionDetailsUi(int sectionId) {
//        TODO impl this
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
        int pos = recyclerView.getChildAdapterPosition(v);
        int bookId = ((RecyclerViewBookAdapter) recyclerView.getAdapter()).getBookId(pos);
        launchActivity(this, ChapterActivity.class, INTENT_KEY, bookId);
    }
}
