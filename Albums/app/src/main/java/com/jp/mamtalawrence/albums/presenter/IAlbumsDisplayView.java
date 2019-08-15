package com.jp.mamtalawrence.albums.presenter;

import com.jp.mamtalawrence.albums.models.AlbumData;

import java.util.List;

public interface IAlbumsDisplayView {
    /**
     * Method to update the list view
     *
     * @param albumList album list
     */
    void updateListView(List<AlbumData> albumList);

    /**
     * Method to show Progress
     */
    void showProgressBar();

    /**
     * Method to hide Progress
     */
    void hideProgressBar();

    /**
     * Show Toast
     *
     * @param message message
     */
    void showToast(String message);
}
