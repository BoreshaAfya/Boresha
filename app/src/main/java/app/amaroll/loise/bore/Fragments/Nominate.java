package app.amaroll.loise.bore.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import app.amaroll.loise.bore.Vote;

/**
 * Created by loise on 9/15/15.
 */
public class Nominate extends Fragment implements View.OnClickListener {
    //Declaration of values;
    Spinner spinner1, spinner2;
    EditText etNname, etNnumber, etNwork, etNresoan;
    Button btnVote;
    String place, occupation;

    //This is the table which will contain all the data in parse//
    public static final String POSTS = "nominee";

    AlertDialog.Builder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nominate_fragment, container, false);

        ParseUser.enableAutomaticUser();

        //initialization of values
        spinner1 = (Spinner) v.findViewById(R.id.spinner);
        spinner2 = (Spinner) v.findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.occupation, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner1.setAdapter(staticAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                occupation = ((TextView) view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //the second spinner is called here
        ArrayAdapter<CharSequence> staticAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.place, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner2.setAdapter(staticAdapter1);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = ((TextView) view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ///initalization of edit texts;
        etNname = (EditText) v.findViewById(R.id.editText);
        etNnumber = (EditText) v.findViewById(R.id.editText2);
        etNwork = (EditText) v.findViewById(R.id.editText3);
        etNresoan = (EditText) v.findViewById(R.id.editText4);

        //initialization of a button
        btnVote = (Button) v.findViewById(R.id.button);
        btnVote.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        //changing the edittexts to string;
        //dialog box here
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Setting message manually and performing action on button click
        builder.setTitle("Thanks")
                .setMessage("You can now proceed to voting")
                .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        //changing the edit texts to strings;

        String name   = etNname.getText().toString();
        String number = etNnumber.getText().toString();
        String work   = etNwork.getText().toString();
        String reason = etNresoan.getText().toString();

        if (!name.equals("")) {
					/*
					 * Save Post ParseObject
					 */
            ParseObject post = new ParseObject("nominee");
            post.put("name", name);
            post.put("number", number);
            post.put("work", work);
            post.put("reason", reason);


            post.saveInBackground();

            Toast.makeText(getActivity(), "Updating", Toast.LENGTH_SHORT).show();
            //finish();
            startActivity(new Intent(getActivity(),Vote.class));
           // ((MainActivity)getActivity()).selectFragment();

        } else {
            Toast.makeText(getActivity(), "Empty fields", Toast.LENGTH_SHORT).show();
        }
    }
}

