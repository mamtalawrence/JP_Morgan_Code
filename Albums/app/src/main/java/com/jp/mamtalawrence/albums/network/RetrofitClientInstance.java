package com.jp.mamtalawrence.albums.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jp.mamtalawrence.albums.utils.Constants.BASE_URL;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    /**
     * Method to get Retrofit Instance
     *
     * @return Retrofit Instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
