package com.jp.mamtalawrence.albums.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.jp.mamtalawrence.albums.R;
import com.jp.mamtalawrence.albums.models.AlbumData;
import com.jp.mamtalawrence.albums.network.GetDataService;
import com.jp.mamtalawrence.albums.network.RetrofitClientInstance;
import com.jp.mamtalawrence.albums.utils.DataUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.jp.mamtalawrence.albums.utils.Constants.*;

public class AlbumsDisplayPresenter {
    private Context mContext;
    private IAlbumsDisplayView mAlbumsDisplayView;
    private SharedPreferences mSharedPreferences;

    public AlbumsDisplayPresenter(IAlbumsDisplayView view, Context context) {
        mAlbumsDisplayView = view;
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_ALBUM_INFO_LIST, MODE_PRIVATE);
    }

    /**
     * Method to get the information from server or Shared Preferences
     */
    public void ShowAlbumListInformation() {
        if (DataUtils.isOffLineDataAvailable(mSharedPreferences, IS_ALBUM_INFO_SAVED)) {
            //TODO need to check if data modified in server
            List<AlbumData> albumData = DataUtils.getListFromPreferences(mSharedPreferences,
                    SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST);
            if (isDataValid(albumData)) {
                mAlbumsDisplayView.updateListView(albumData);
            }
        } else if (DataUtils.isNetworkConnected(mContext)) {
            mAlbumsDisplayView.showProgressBar();
            performNetworkTask();
        } else {
            //TODO: need to add retry after network available
            mAlbumsDisplayView.showToast(mContext.getString(R.string.network_alert));
        }
    }

    /**
     * Method to perform network task and get the data
     */
    private void performNetworkTask() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<AlbumData>> call = service.getAlbumData();
        call.enqueue(new Callback<List<AlbumData>>() {

            @Override
            public void onResponse(@NonNull Call<List<AlbumData>> call, @NonNull Response<List<AlbumData>> response) {
                mAlbumsDisplayView.hideProgressBar();
                List<AlbumData> albumData = response.body();
                if (isDataValid(albumData)) {
                    mAlbumsDisplayView.updateListView(albumData);
                    //Store data in SharedPreferences
                    DataUtils.SaveListInPreferences(response.body(), mSharedPreferences,
                            SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST, IS_ALBUM_INFO_SAVED);
                } else {
                    mAlbumsDisplayView.showToast(mContext.getString(R.string.warning_alert));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AlbumData>> call, @NonNull Throwable t) {
                mAlbumsDisplayView.hideProgressBar();
                mAlbumsDisplayView.showToast(mContext.getString(R.string.warning_alert));
            }
        });
    }

    /**
     * Check list not null or empty
     *
     * @param albumList data list
     * @return true if not null or empty else false
     */
    public boolean isDataValid(List<AlbumData> albumList) {
        return albumList != null && albumList.size() > 0;
    }
}
