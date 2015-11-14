package nkdroid.com.sharedpreferencedemo;


import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;


/**
 * Created by nirav.
 * Base activity for application.
 * Contains basic actionbar integration for all the screen which extends this Activity.
 */
public class BaseActivity extends FragmentActivity {

    public TextView txtHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ActionBar Style
        ActionBar.LayoutParams acBarParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        acBarParams.gravity= Gravity.CENTER| Gravity.CENTER_VERTICAL;
        // Adding the new custom textview to the actionbar that has center alined property.

        txtHeader=new TextView(this);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActionBar().setIcon(new ColorDrawable(Color.TRANSPARENT));
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getActionBar().setDisplayShowCustomEnabled(true);

        txtHeader.setTextColor(Color.BLACK);
        txtHeader.setSingleLine(true);
        txtHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        getActionBar().setCustomView(txtHeader, acBarParams);
        txtHeader.setMaxWidth((int)(getResources().getDisplayMetrics().widthPixels/2));

    }
}
