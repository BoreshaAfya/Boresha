package app.amaroll.loise.bore.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import app.amaroll.loise.bore.R;

/**
 * Created by loise on 9/16/15.
 */
public class Nominated extends Fragment {

    private Spinner spinner1, spinner2;

    private Button b1;
    EditText name;
    EditText regno;
    EditText workplace;

    protected ArrayAdapter<CharSequence> mAdapter;

    String county, occupation;

    //This is the table which will contain all the data in parse//
    public static final String POSTS = "nominee";

    //end//


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nominate_fragment, container, false);

        //Since No login this enables automatic user authentication//

        ParseUser.enableAutomaticUser();


        //Allocate all your resources id here//

        spinner1 =(Spinner)v.findViewById(R.id.spinner);
        spinner2 = (Spinner)v.findViewById(R.id.spinner2);

        b1 = (Button)v.findViewById(R.id.button);

        name = (EditText)v.findViewById(R.id.editText);
        regno = (EditText)v.findViewById(R.id.editText2);


        //end//


        //County Spinner//
        this.mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.occupation,
                android.R.layout.simple_spinner_item);
        spinner1.setAdapter(this.mAdapter);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                county =  ((TextView) view).getText().toString();

            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //end//



        //occupation Spinner//
        this.mAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.place,
                android.R.layout.simple_spinner_item);
        spinner2.setAdapter(this.mAdapter);


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                occupation =  ((TextView) view).getText().toString();

            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //end//

        //Submit button//
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String regno1 = regno.getText().toString();

                if (!name.equals("")) {
					/*
					 * Save Post ParseObject
					 */
                    ParseObject post = new ParseObject("nominee");
                    post.put("name", name1);
                    post.put("regno", regno1);
                    post.put("county", county);
                    post.put("occupation", occupation);


                    post.saveInBackground();

                    Toast.makeText(getActivity(), "Updating", Toast.LENGTH_SHORT).show();
                    //finish();

                } else {
                    Toast.makeText(getActivity(), "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;

    }

}


