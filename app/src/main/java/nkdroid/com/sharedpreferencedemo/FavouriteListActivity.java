package nkdroid.com.sharedpreferencedemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;


public class FavouriteListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        txtHeader.setText("Favourites List");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        FragmentFavourite fragmentFavourite = FragmentFavourite.newInstance();

        if (manager.findFragmentByTag("fragment_fav") == null) {
            ft.replace(R.id.main_content, fragmentFavourite, "fragment_fav").commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:

                finish();

                break;


        }
        return super.onOptionsItemSelected(item);
    }

}
