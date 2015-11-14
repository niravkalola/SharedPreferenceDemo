package nkdroid.com.sharedpreferencedemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentFavourite extends Fragment {
    private ListView favouriteListView;
    public static FavouritesListAdapter favouritsListAdapter;
    private ArrayList<BeanSampleList> favouritesBeanSampleList;
    SharedPreference sharedPreference;
    public static FragmentFavourite newInstance() {
        FragmentFavourite fragment = new FragmentFavourite();
        return fragment;
    }
    public FragmentFavourite() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreference=new SharedPreference();
        try {
            favouritesBeanSampleList = sharedPreference.loadFavorites(getActivity());
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_favourite, container, false);
        favouriteListView =(ListView)rootView.findViewById(R.id.favourits_list);
        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "onResume Called");
        if(favouritesBeanSampleList != null ) {
            try {
                favouritsListAdapter = new FavouritesListAdapter(getActivity(), favouritesBeanSampleList);
                favouriteListView.setAdapter(favouritsListAdapter);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            favouritsListAdapter.notifyDataSetChanged();
        }
    }

    public class FavouritesListAdapter extends BaseAdapter {

        Context context;
        LayoutInflater inflater;
        ArrayList<BeanSampleList> favouritesBeanSampleList;
        public FavouritesListAdapter(Context context, ArrayList<BeanSampleList> favouritesBeanSampleList) {
            this.context = context;
            this.favouritesBeanSampleList = favouritesBeanSampleList;
        }

        public int getCount() {
            return favouritesBeanSampleList.size();
        }

        public Object getItem(int position) {
            return favouritesBeanSampleList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView txtTitle,txtSubTitle;
            ImageView btnFavourite;
        }

        public View getView(final int position, View convertView,ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_favourites_list, parent, false);
                holder = new ViewHolder();
                holder.txtTitle = (TextView) convertView.findViewById(R.id.txtFavtitle);
                holder.txtSubTitle = (TextView) convertView.findViewById(R.id.txtFavSubTitle);
                holder.btnFavourite = (ImageView) convertView
                        .findViewById(R.id.favouritesToggleImg);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txtTitle.setText(favouritesBeanSampleList.get(position).getTitle());
            holder.txtSubTitle.setText(favouritesBeanSampleList.get(position).getSubTitle());
            holder.btnFavourite.setImageResource(R.drawable.ic_favorite);

            holder.btnFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPreference.removeFavorite(context, favouritesBeanSampleList.get(position));
                    favouritesBeanSampleList.remove(favouritesBeanSampleList.get(position));
                    holder.btnFavourite.setImageResource(R.drawable.ic_favorite_outline);
                    notifyDataSetChanged();
                }

            });
            return convertView;
        }
    }
}
