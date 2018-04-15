package com.didactapp.didact.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.didactapp.didact.R;
import com.didactapp.didact.contracts.SectionContract;
import com.didactapp.didact.entities.Section;
import com.didactapp.didact.network.section.SectionRemoteGateway;
import com.didactapp.didact.network.section.SectionRemoteGatewayCallback;
import com.didactapp.didact.presenters.SectionPresenter;
import com.didactapp.didact.recycler.RecyclerViewSectionAdapter;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SectionActivity extends BaseActivity implements SectionContract.View, SectionRemoteGatewayCallback {
    @Override
    public void showNoContent() {

    }

    @Override
    public void hideNoContent() {

    }

    private static final int NUM_OF_COLUMNS = 1;

    private RecyclerView recycler;
    private ProgressBar spinner;
    private TextView noNetwork;
    private TextView loadError;
    private SectionContract.Presenter presenter;


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
        presenter = new SectionPresenter();
        presenter.takeView(this);

        /* fetch data from server */
        SectionRemoteGateway remoteGateway = SectionRemoteGateway.getInstance();
        remoteGateway.getSectionList(this);
    }

    @Override
    public void showSections(List<Section> sectionList) {
        RecyclerViewSectionAdapter recyclerViewAdapter = new RecyclerViewSectionAdapter(this, sectionList);
        recycler.swapAdapter(recyclerViewAdapter, false);
        recycler.setVisibility(VISIBLE);
    }

    @Override
    public void hideSections() {
        recycler.setVisibility(GONE);
    }

    @Override
    public void showSectionDetailsUi(String sectionId) {
//        ???
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
    public void checkNetworkState() {
        if (isNetworkConnected()) {
            presenter.networkConnected();
        } else {
            presenter.networkDisconnected();
        }
    }


    @Override
    public void onLoadSuccess(List<Section> sectionList) {
        presenter.onSectionsLoaded(sectionList);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onLoadFailed() {

    }
}
