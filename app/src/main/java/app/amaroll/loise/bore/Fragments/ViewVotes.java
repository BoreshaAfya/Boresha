package app.amaroll.loise.bore.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.amaroll.loise.bore.R;

/**
 * Created by loise on 10/13/15.
 */
public class ViewVotes extends Fragment {

    //declaration of values
    public ListView listView1 ;
    private ArrayAdapter<String> listAdapter ;
    ProgressDialog barProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.real_vote, container, false);

        // Get ListView object from xml
        listView1 = (ListView)v.findViewById(R.id.mainListView);
        barProgressDialog = ProgressDialog.show(getActivity(), "",
                "Loading. Please wait...", true);
        ParseQuery query = new ParseQuery("voted");
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
                    sAdap = new SimpleAdapter(getActivity(), articles, R.layout.voted_item,
                            new String[]{"name", "work"}, new int[]
                            {R.id.tvVotedname, R.id.tvVotedplace});

                    listView1.setAdapter(sAdap);

                }
            }

        });


        return v;
    }
}



