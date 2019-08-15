package com.jp.mamtalawrence.albums.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import com.google.gson.Gson;
import com.jp.mamtalawrence.albums.models.AlbumData;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Common calculation and handling using throwout the application
 */
public class DataUtils {

    /**
     * Method sort the list by title
     *
     * @param albumList list
     * @return sorted list by title
     */
    public static List<AlbumData> getSortedList(List<AlbumData> albumList) {
        if (albumList != null && albumList.size() > 0) {
            Collections.sort(albumList, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    AlbumData p1 = (AlbumData) o1;
                    AlbumData p2 = (AlbumData) o2;
                    return p1.getTitle().compareToIgnoreCase(p2.getTitle());
                }
            });
        }
        return albumList;
    }

    /**
     * Check internet connection
     *
     * @param context context
     * @return true if internet connected else false
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }

    /**
     * Check off-line data
     *
     * @return true if data available else false
     */
    public static boolean isOffLineDataAvailable(SharedPreferences sharedPreferences, String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, false);
        } else {
            return false;
        }
    }

    /**
     * Save the list in sharedPreferences by key
     *
     * @param albumList         list of album
     * @param sharedPreferences sharedPreferences object
     * @param listKey           key for store list data
     * @param bKey              key for store boolean flag
     */
    public static void SaveListInPreferences(List<AlbumData> albumList, SharedPreferences sharedPreferences, String
            listKey, String bKey) {
        Gson gson = new Gson();
        String ListJsonString = gson.toJson(albumList);
        // Put the json format string to Shared Preferences object.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(listKey, ListJsonString);
        editor.putBoolean(bKey, true);
        editor.commit();
    }


    /**
     * Get the list from Preferences by key
     *
     * @param sharedPreferences sharedPreferences object
     * @param key               key for get data
     * @return list of album
     */
    public static List<AlbumData> getListFromPreferences(SharedPreferences sharedPreferences, String key) {
        // Get saved string data in it.
        String ListJsonString = sharedPreferences.getString(key, "");
        if (!ListJsonString.isEmpty()) {
            Gson gson = new Gson();
            return Arrays.asList(gson.fromJson(ListJsonString, AlbumData[].class));
        }
        return null;
    }
}
