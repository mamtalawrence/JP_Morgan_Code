package com.jp.mamtalawrence.albums;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jp.mamtalawrence.albums.models.AlbumData;
import com.jp.mamtalawrence.albums.presenter.AlbumsDisplayPresenter;
import com.jp.mamtalawrence.albums.presenter.IAlbumsDisplayView;
import com.jp.mamtalawrence.albums.utils.DataUtils;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.jp.mamtalawrence.albums.utils.Constants.IS_ALBUM_INFO_SAVED;
import static com.jp.mamtalawrence.albums.utils.Constants.SHARED_PREFERENCES_FILE_ALBUM_INFO_LIST;
import static com.jp.mamtalawrence.albums.utils.Constants.SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * AlbumsDisplayPresenter class test with 100% class, method and line coverage
 */
@RunWith(MockitoJUnitRunner.class)
public class AlbumsDisplayPresenterTest {
    @Mock
    private Context mMockContext;

    @Mock
    private ConnectivityManager mMockConnectivityManager;

    @Mock
    private NetworkInfo mMockNetworkInfo;

    @Mock
    private SharedPreferences mMockSharedPreferences;

    @Mock
    private IAlbumsDisplayView mAlbumsDisplayView;

    private AlbumsDisplayPresenter mAlbumsDisplayPresenter;

    @Before
    public void setUp() {
        when(mMockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mMockConnectivityManager);
        when(mMockContext.getSharedPreferences(SHARED_PREFERENCES_FILE_ALBUM_INFO_LIST, MODE_PRIVATE))
                .thenReturn(mMockSharedPreferences);
        when(mMockContext.getString(R.string.network_alert)).thenReturn("Please check network connection and try " +
                "again!!");
        when(mMockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mMockConnectivityManager);
        mAlbumsDisplayPresenter = new AlbumsDisplayPresenter(mAlbumsDisplayView, mMockContext);
    }

    @Test
    public void testShowAlbumListInformation() {
        // Verify view method when offline data is available
        when(DataUtils.isOffLineDataAvailable(mMockSharedPreferences, IS_ALBUM_INFO_SAVED)).thenReturn(true);
        assertNotNull(mAlbumsDisplayPresenter);
        when(mMockSharedPreferences.getString(SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST, "")).thenReturn("[\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"quidem molestiae enim\"\n" +
                "  }]");

        when(mMockSharedPreferences.getBoolean(IS_ALBUM_INFO_SAVED, false)).thenReturn(true);
        mAlbumsDisplayPresenter.ShowAlbumListInformation();
        verify(mAlbumsDisplayView, atLeastOnce()).updateListView(ArgumentMatchers.<AlbumData>anyList());

        // Verify view method when offline data and network is not available
        when(DataUtils.isOffLineDataAvailable(mMockSharedPreferences, IS_ALBUM_INFO_SAVED)).thenReturn(false);
        mAlbumsDisplayPresenter.ShowAlbumListInformation();
        verify(mAlbumsDisplayView, atLeastOnce()).showToast(anyString());

        // Verify view method when offline data not available and network available
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mMockNetworkInfo);
        mAlbumsDisplayPresenter.ShowAlbumListInformation();
        verify(mAlbumsDisplayView, atLeastOnce()).showProgressBar();
    }

    @Test
    public void testIsDataValid() {
        Assert.assertFalse(mAlbumsDisplayPresenter.isDataValid(null));
        Assert.assertTrue(mAlbumsDisplayPresenter.isDataValid(getAlbumList()));
    }

    private List<AlbumData> getAlbumList() {
        List<AlbumData> albumList = new ArrayList<>();
        AlbumData item1 = new AlbumData(1, 1, "Item z");
        albumList.add(item1);
        item1 = new AlbumData(2, 2, "Item y");
        albumList.add(item1);
        item1 = new AlbumData(3, 3, "Item x");
        albumList.add(item1);
        return albumList;
    }

    @After
    public void tearDown() {
        mAlbumsDisplayPresenter = null;
        mMockContext = null;
        mMockConnectivityManager = null;
        mMockNetworkInfo = null;
        mMockSharedPreferences = null;
        mAlbumsDisplayView = null;
    }
}