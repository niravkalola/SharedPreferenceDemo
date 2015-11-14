
package nkdroid.com.sharedpreferencedemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FragmentList extends Fragment  {

    public static FragmentList newInstance() {
        FragmentList fragment = new FragmentList();
        return fragment;
    }
    public FragmentList() {
        // Required empty public constructor
    }

    Activity activity;
    private ListView postsListView;
    private PostsListAdapter postsListAdapter;
    private ArrayList<BeanSampleList> postsBeanSampleList =new ArrayList<BeanSampleList>();

    SharedPreference sharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();
        postsBeanSampleList.add(new BeanSampleList(1,"India","New Delhi"));
        postsBeanSampleList.add(new BeanSampleList(2,"United States", "Washington, D.C."));
        postsBeanSampleList.add(new BeanSampleList(3,"Spain","Madrid"));
        postsBeanSampleList.add(new BeanSampleList(4,"Russia","Moscow"));
        postsBeanSampleList.add(new BeanSampleList(5,"Brazil","Brasília"));
        postsBeanSampleList.add(new BeanSampleList(6,"Germany","Berlin"));
        postsBeanSampleList.add(new BeanSampleList(7,"France","Paris"));
        postsBeanSampleList.add(new BeanSampleList(8,"Ireland","Dublin"));
        postsBeanSampleList.add(new BeanSampleList(9,"Italy","Rome"));
        postsBeanSampleList.add(new BeanSampleList(10,"Japan","Tokyo"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container,false);

        postsListView = (ListView) view.findViewById(R.id.posts_list);
        postsListAdapter = new PostsListAdapter(activity, postsBeanSampleList);
        postsListView.setAdapter(postsListAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        postsListAdapter.notifyDataSetChanged();
    }

    public class PostsListAdapter extends BaseAdapter {

        private Context context;
        ArrayList<BeanSampleList> postBeanSampleList;
        SharedPreference sharedPreference;

        public PostsListAdapter(Context context, ArrayList<BeanSampleList> postBeanSampleList) {

            this.context = context;
            this.postBeanSampleList = postBeanSampleList;
            sharedPreference = new SharedPreference();
        }

        private class ViewHolder {
            TextView txtTitle,txtSubTitle;
            ImageView btnFavourite;
        }

        @Override
        public int getCount() {
            return postBeanSampleList.size();
        }

        @Override
        public Object getItem(int position) {
            return postBeanSampleList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_posts_list, parent, false);
                holder = new ViewHolder();
                holder.txtTitle = (TextView) convertView
                        .findViewById(R.id.txtPostTitle);
                holder.txtSubTitle = (TextView) convertView
                        .findViewById(R.id.txtPostSubTitle);

                holder.btnFavourite = (ImageView) convertView
                        .findViewById(R.id.favouritesToggle);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            BeanSampleList beanSampleList = (BeanSampleList) getItem(position);
            holder.txtTitle.setText(beanSampleList.getTitle());
            holder.txtSubTitle.setText(beanSampleList.getSubTitle());


            if (checkFavoriteItem(beanSampleList)) {
                holder.btnFavourite.setImageResource(R.drawable.ic_favorite);
                holder.btnFavourite.setTag("active");
            } else {
                holder.btnFavourite.setImageResource(R.drawable.ic_favorite_outline);
                holder.btnFavourite.setTag("deactive");
            }
            holder.btnFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String tag = holder.btnFavourite.getTag().toString();
                    if (tag.equalsIgnoreCase("deactive")) {
                        sharedPreference.addFavorite(context, postBeanSampleList.get(position));
                        holder.btnFavourite.setTag("active");
                        holder.btnFavourite.setImageResource(R.drawable.ic_favorite);
                    } else {
                        sharedPreference.removeFavorite(context, postBeanSampleList.get(position));
                        holder.btnFavourite.setTag("deactive");
                        holder.btnFavourite.setImageResource(R.drawable.ic_favorite_outline);
                    }
                }
            });
            return convertView;
        }


        public boolean checkFavoriteItem(BeanSampleList checkProduct) {
            boolean check = false;
            List<BeanSampleList> favorites = sharedPreference.loadFavorites(context);
            if (favorites != null) {
                for (BeanSampleList product : favorites) {
                    if (product.equals(checkProduct)) {
                        check = true;
                        break;
                    }
                }
            }
            return check;
        }
    }
}
