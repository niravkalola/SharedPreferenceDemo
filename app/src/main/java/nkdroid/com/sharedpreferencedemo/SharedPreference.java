package nkdroid.com.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SharedPreference {

    public static final String PREFS_NAME = "NKDROID_APP";
    public static final String FAVORITES = "Favorite";

    public SharedPreference() {
        super();
    }


    public void storeFavorites(Context context, List<BeanSampleList> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public ArrayList<BeanSampleList> loadFavorites(Context context) {
        SharedPreferences settings;
        List<BeanSampleList> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            BeanSampleList[] favoriteItems = gson.fromJson(jsonFavorites,BeanSampleList[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<BeanSampleList>(favorites);
        } else
            return null;

        return (ArrayList<BeanSampleList>) favorites;
    }


    public void addFavorite(Context context, BeanSampleList beanSampleList) {
        List<BeanSampleList> favorites = loadFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<BeanSampleList>();
        favorites.add(beanSampleList);
        storeFavorites(context, favorites);
    }

    public void removeFavorite(Context context, BeanSampleList beanSampleList) {
        ArrayList<BeanSampleList> favorites = loadFavorites(context);
        if (favorites != null) {
            favorites.remove(beanSampleList);
            storeFavorites(context, favorites);
        }
    }




}
