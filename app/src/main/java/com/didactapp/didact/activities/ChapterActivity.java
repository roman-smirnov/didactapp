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
import com.didactapp.didact.contracts.ChapterContract;
import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.network.ChapterRemoteGateway;
import com.didactapp.didact.persistence.ChapterLocalGateway;
import com.didactapp.didact.presenters.ChapterPresenter;
import com.didactapp.didact.recycler.RecyclerViewChapterAdapter;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.didactapp.didact.utils.Constants.BOOK_ID_INTENT_KEY;
import static com.didactapp.didact.utils.Constants.NO_SUCH_BOOK;

public class ChapterActivity extends BaseActivity implements ChapterContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private static final int NUM_OF_COLUMNS = 1;



    private RecyclerView recycler;
    private TextView noContentView;
    private Snackbar noNetworkSnackbar;
    private Snackbar loadErrorSnackbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ChapterContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);


        /* get layout elements */
        recycler = findViewById(R.id.chapter_list);
        swipeRefreshLayout = findViewById(R.id.chapter_swipe_refresh);
        noContentView = findViewById(R.id.chapter_no_content);
        noNetworkSnackbar = Snackbar.make(swipeRefreshLayout, R.string.error_no_network, Snackbar.LENGTH_INDEFINITE);
        loadErrorSnackbar = Snackbar.make(swipeRefreshLayout, R.string.error_loading, Snackbar.LENGTH_INDEFINITE);

        /* set swipe refresh listener */
        swipeRefreshLayout.setOnRefreshListener(this);

        /* init recycler view */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, NUM_OF_COLUMNS);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recycler.addItemDecoration(new DividerItemDecoration(this, RecyclerView.HORIZONTAL));

        /* retrieve or create presenter */
        presenter = (ChapterContract.Presenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new ChapterPresenter(ChapterRemoteGateway.getInstance(), ChapterLocalGateway.getInstance(this));
        }

        presenter.takeView(this);

        int bookId = getIntent().getIntExtra(BOOK_ID_INTENT_KEY, NO_SUCH_BOOK);
        presenter.loadChapters(bookId);
    }


    /* save the presenter on screen rotation */
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    public void showChapters(List<Chapter> chapterList) {
        RecyclerViewChapterAdapter recyclerViewAdapter = new RecyclerViewChapterAdapter(this, chapterList);
        recycler.swapAdapter(recyclerViewAdapter, false);
        recycler.setVisibility(VISIBLE);
    }

    @Override
    public void hideChapters() {
        recycler.setVisibility(GONE);
    }

    @Override
    public void showChapterContent(int chapterId) {
        launchActivity(this, SectionActivity.class);

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
//        TODO impl this
    }
}
