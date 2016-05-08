package sanitation.la.project.myapplication.ui;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.Date;
import java.util.List;

import sanitation.la.project.myapplication.R;
import sanitation.la.project.myapplication.adapters.GridViewAdapter;
import sanitation.la.project.myapplication.adapters.LocationListAdapter;
import sanitation.la.project.myapplication.data.LocationPoint;
import sanitation.la.project.myapplication.helpers.LocationHelper;
import sanitation.la.project.myapplication.helpers.OnFragmentInteractionListener;

public class LocationTestFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private LocationHelper gps;
    private Button wButton;
    private TextView lTv;
    private TextView title;
    private SeekBar disBar;
    private Switch gSwitch;

    private ListView list;
    private LocationListAdapter mAdapter;

    private static final int INITIAL_DELAY_MILLIS = 300;

    public LocationTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_location_test, container, false);
        wButton = (Button) v.findViewById(R.id.wbutton);
        lTv = (TextView) v.findViewById(R.id.locationTestTextView);
        title = (TextView) v.findViewById(R.id.locationTitleTextView);
        disBar = (SeekBar) v.findViewById(R.id.locationSpeedSeekBar);
        gps = new LocationHelper(getContext());
        list = (ListView) v.findViewById(R.id.listView);
        gSwitch = (Switch) v.findViewById(R.id.locationSwitch);
        gSwitch.setChecked(true);  //gps only by default
        gSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = gSwitch.isChecked();
                gps.setNetworkEnabled(!b);      // if gps only switch is check, set network to not enabled
            }
        });

        mAdapter = new LocationListAdapter(getContext());

        //add some animations, wont see till reloads work
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter =
                new SwingBottomInAnimationAdapter(mAdapter);
        swingBottomInAnimationAdapter.setAbsListView(list);

        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        list.setAdapter(swingBottomInAnimationAdapter);
        list.setAdapter(mAdapter);
        //list.setOnClickListener(this);

        wButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = "";
                temp += String.format("New Point Added "); //Lat: %.12f, Long: %.12f %n", gps.getLatitude(), gps.getLongitude());

                lTv.setText(temp);
                mAdapter.addItem(new LocationPoint(gps.getLatitude(), gps.getLongitude(), new Date()));
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
