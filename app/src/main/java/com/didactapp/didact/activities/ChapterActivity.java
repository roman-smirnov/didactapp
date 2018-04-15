package com.didactapp.didact.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.didactapp.didact.R;
import com.didactapp.didact.contracts.ChapterContract;
import com.didactapp.didact.entities.Chapter;
import com.didactapp.didact.network.chapter.ChapterRemoteGateway;
import com.didactapp.didact.network.chapter.ChapterRemoteGatewayCallback;
import com.didactapp.didact.presenters.ChapterPresenter;
import com.didactapp.didact.recycler.RecyclerViewChapterAdapter;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ChapterActivity extends BaseActivity implements ChapterContract.View, ChapterRemoteGatewayCallback {

    private static final int NUM_OF_COLUMNS = 1 ;



    private RecyclerView recycler;
    private ProgressBar spinner;
    private TextView noNetwork;
    private TextView loadError;
    private ChapterContract.Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);


        /* get layout elements */
        recycler = findViewById(R.id.chapter_list);
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
        presenter = new ChapterPresenter();
        presenter.takeView(this);

        /* fetch data from server */
        ChapterRemoteGateway remoteGateway = ChapterRemoteGateway.getInstance();
        remoteGateway.getChapterList(this);
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
    public void showNoContent() {

    }

    @Override
    public void hideNoContent() {

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
    public void onLoadSuccess(List<Chapter> chapterList) {
        presenter.onChaptersLoaded(chapterList);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onLoadFailed() {

    }
}
