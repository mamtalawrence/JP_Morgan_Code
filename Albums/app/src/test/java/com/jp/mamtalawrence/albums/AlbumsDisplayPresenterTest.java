package com.jp.mamtalawrence.albums;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jp.mamtalawrence.albums.models.AlbumData;
import com.jp.mamtalawrence.albums.utils.DataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.jp.mamtalawrence.albums.utils.Constants.IS_ALBUM_INFO_SAVED;
import static com.jp.mamtalawrence.albums.utils.Constants.SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
    private SharedPreferences.Editor mMockEditor;

    @Before
    public void setUp() {
        when(mMockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mMockConnectivityManager);
    }

    @Test
    public void testGetSortedList() {
        List<AlbumData> albumList = new ArrayList<>();
        AlbumData item1 = new AlbumData(1, 1, "Item z");
        albumList.add(item1);
        item1 = new AlbumData(2, 2, "Item y");
        albumList.add(item1);
        item1 = new AlbumData(3, 3, "Item x");
        albumList.add(item1);

        assertEquals("Item x", albumList.get(2).getTitle());
        assertEquals("Item y", albumList.get(1).getTitle());
        assertEquals("Item z", albumList.get(0).getTitle());

        albumList = DataUtils.getSortedList(albumList);
        assertEquals("Item x", albumList.get(0).getTitle());
        assertEquals("Item y", albumList.get(1).getTitle());
        assertEquals("Item z", albumList.get(2).getTitle());
    }

    @Test
    public void testIsNetworkConnected() {
        assertFalse(DataUtils.isNetworkConnected(mMockContext));
        when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mMockNetworkInfo);
        assertTrue(DataUtils.isNetworkConnected(mMockContext));
    }

    @Test
    public void testIsOffLineDataAvailable() {
        assertFalse(DataUtils.isOffLineDataAvailable(null, IS_ALBUM_INFO_SAVED));
        when(mMockSharedPreferences.getBoolean(IS_ALBUM_INFO_SAVED, false)).thenReturn(true);
        assertTrue(DataUtils.isOffLineDataAvailable(mMockSharedPreferences, IS_ALBUM_INFO_SAVED));
        when(mMockSharedPreferences.getBoolean(IS_ALBUM_INFO_SAVED, false)).thenReturn(false);
        assertFalse(DataUtils.isOffLineDataAvailable(mMockSharedPreferences, IS_ALBUM_INFO_SAVED));
    }

    @Test
    public void testSaveAndGetListInPreferences() {
        when(mMockSharedPreferences.getString(SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST, "")).thenReturn("");
        assertNull(DataUtils.getListFromPreferences(mMockSharedPreferences, SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST));
        when(mMockSharedPreferences.getString(SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST, "")).thenReturn("[\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"quidem molestiae enim\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"sunt qui excepturi placeat culpa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 3,\n" +
                "    \"title\": \"omnis laborum odio\"\n" +
                "  }]");
        List<AlbumData> listData = DataUtils.getListFromPreferences(mMockSharedPreferences,
                SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST);
        assertNotNull(listData);
        assertEquals(3, listData.size());
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
        DataUtils.SaveListInPreferences(listData, mMockSharedPreferences, SHARED_PREFERENCES_KEY_ALBUM_INFO_LIST,
                IS_ALBUM_INFO_SAVED);

    }
}