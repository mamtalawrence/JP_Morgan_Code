package com.jp.mamtalawrence.albums.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for album
 */
public class AlbumData {
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;

    /**
     * Constructor
     *
     * @param userId Album User Id
     * @param id     Album Id
     * @param title  Album Title
     */
    public AlbumData(Integer userId, Integer id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    /**
     * Method returns Album User Id
     *
     * @return Album User Id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Method returns Album Id
     *
     * @return Album Id
     */
    public Integer getId() {
        return id;
    }


    /**
     * Method returns Album Title
     *
     * @return Album Title
     */
    public String getTitle() {
        return title;
    }
}
