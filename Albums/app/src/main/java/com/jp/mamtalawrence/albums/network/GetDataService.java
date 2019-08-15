package com.jp.mamtalawrence.albums.network;

import com.jp.mamtalawrence.albums.models.AlbumData;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GetDataService {
    @GET("/albums")
    Call<List<AlbumData>> getAlbumData();
}
