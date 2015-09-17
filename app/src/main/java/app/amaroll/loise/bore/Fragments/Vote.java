package app.amaroll.loise.bore.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.amaroll.loise.bore.R;

/**
 * Created by loise on 9/16/15.
 */
public class Vote extends Fragment {

    //declaration of values
    public ListView listView1 ;
    private ArrayAdapter<String> listAdapter ;
    ProgressDialog barProgressDialog;
    public static final String POSTS = "nominee";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.real_vote, container, false);

        // Get ListView object from xml
        listView1 = (ListView)v.findViewById(R.id.mainListView);
        barProgressDialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);
        ParseQuery query = new ParseQuery("nominee");
        query.setLimit(100);


        query.orderByDescending("createAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> results, ParseException e) {
                barProgressDialog.dismiss();

                if (e == null) {
                    final ArrayList<HashMap<String, String>> articles = new ArrayList<HashMap<String, String>>();
                    for (ParseObject result : results) {
                        HashMap<String, String> article = new HashMap<String, String>();
                        article.put("name",
                                result.getString("name"));
                        article.put("work",
                                result.getString("work"));
                        articles.add(article);
                    }
                    SimpleAdapter sAdap;
                    sAdap = new SimpleAdapter(getActivity(), articles, R.layout.vote_item,
                            new String[]{"name", "work"}, new int[]
                            {R.id.textView8, R.id.textView7});

                    listView1.setAdapter(sAdap);

                }
            }

        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
//                String  itemValue    = (String) listView1.getItemAtPosition(position);
                HashMap<String,String> itemValue =(HashMap<String,String>)listView1.getItemAtPosition(position);
                String name = itemValue.get("name");
                String work = itemValue.get("work");

                ParseObject post = new ParseObject("voted");
                post.put("name",name);
                post.put("work",work);
                post.saveInBackground();

                // Show Alert
                Toast.makeText(getActivity(),"Thanks for voting for: " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }
        });

        return v;
    }
}



