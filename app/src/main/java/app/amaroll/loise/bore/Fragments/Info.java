package app.amaroll.loise.bore.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.amaroll.loise.bore.R;

/**
 * Created by loise on 9/15/15.
 */
public class Info extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_activity,container,false);

        return v;
    }
}
