package sanitation.la.project.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import sanitation.la.project.myapplication.R;
import sanitation.la.project.myapplication.adapters.FormPickerListAdapter;
import sanitation.la.project.myapplication.helpers.OnFragmentInteractionListener;

/**
 * Created by saul on 3/3/16.
 */
public class FormPickerFragment  extends BaseFragment{


    private  String TAG = getClass().getSimpleName();
    private ListView listView;
    private FormPickerListAdapter mAdapter;

    public FormPickerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_form_picker, container, false);

        listView = (ListView) v.findViewById(R.id.listView);
        mAdapter = new FormPickerListAdapter(getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                Toast.makeText(getContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
                Log.d(TAG, "Form PIcked " + position);
                mListener.onFormPicked(position);

            }
        });

        listView.setAdapter(mAdapter);

        return v;
    }


}
