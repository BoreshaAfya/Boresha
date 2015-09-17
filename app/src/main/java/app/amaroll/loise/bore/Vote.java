package app.amaroll.loise.bore;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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

/**
 * Created by loise on 9/16/15.
 */
public class Vote extends ActionBarActivity {

    private ListView listView ;
    private ArrayAdapter<String> listAdapter ;
    private Toolbar toolbar;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_fragment);

        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        // Get ListView object from xml
        //listView = (ListView) findViewById(R.id.mainListView);

        ParseQuery query = new ParseQuery("nominee");
        query.setLimit(100);


        query.orderByDescending("createAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> results, ParseException e) {


                if (e == null) {
                    final ArrayList<HashMap<String, String>> articles = new ArrayList<HashMap<String, String>>();
                    for (ParseObject result : results) {
                        HashMap<String, String> article = new HashMap<String, String>();
                        article.put("name",
                                result.getString("name"));
                        articles.add(article);
                    }
                    SimpleAdapter sAdap;
                    sAdap = new SimpleAdapter(Vote.this, articles, R.layout.vote_item,
                            new String[]{"name"}, new int[]
                            {R.id.textView2});

                    final ListView lisView1 = (ListView) findViewById(R.id.mainListView);
                    lisView1.setAdapter(sAdap);

                }
               }

            });


        }
    }