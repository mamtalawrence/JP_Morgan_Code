package com.jp.mamtalawrence.albums.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.jp.mamtalawrence.albums.R;
import com.jp.mamtalawrence.albums.models.AlbumData;
import com.jp.mamtalawrence.albums.presenter.AlbumsDisplayPresenter;
import com.jp.mamtalawrence.albums.presenter.IAlbumsDisplayView;
import com.jp.mamtalawrence.albums.utils.DataUtils;

import java.util.List;

/**
 * Activity to show album list sort by title
 */
public class AlbumsDisplayActivity extends Activity implements IAlbumsDisplayView {
    private ProgressDialog mProgressDialog;
    private RecyclerView mAlbumRecyclerView;
    private AlbumsDisplayPresenter mAlbumsDisplayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_display);
        initView();
    }

    /**
     * Method to initialize the view and variables
     */
    private void initView() {
        mProgressDialog = new ProgressDialog(AlbumsDisplayActivity.this);
        mProgressDialog.setMessage(getString(R.string.loading_indicator));

        mAlbumRecyclerView = findViewById(R.id.albumListRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AlbumsDisplayActivity.this);
        mAlbumRecyclerView.setLayoutManager(layoutManager);

        //Get and set the data list
        mAlbumsDisplayPresenter = new AlbumsDisplayPresenter(this, this);
        mAlbumsDisplayPresenter.ShowAlbumListInformation();
    }

    @Override
    public void updateListView(List<AlbumData> albumList) {
        //Re-verify the list
        if (mAlbumsDisplayPresenter.isDataValid(albumList)) {
            AlbumListAdapter albumListAdapter = new AlbumListAdapter(this, DataUtils.getSortedList(albumList));
            mAlbumRecyclerView.setAdapter(albumListAdapter);
        }
    }

    @Override
    public void showProgressBar() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(AlbumsDisplayActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
