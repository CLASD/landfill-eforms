package sanitation.la.project.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import sanitation.la.project.myapplication.dummy.DummyContent;
import sanitation.la.project.myapplication.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class FormEntryFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton addNewButton;
    private  MyArrayAdapter adapter;
    private ArrayList<EntryData> data;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FormEntryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FormEntryFragment newInstance(int columnCount) {
        FormEntryFragment fragment = new FormEntryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<EntryData>();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);

            //Double d = getArguments().getDouble("CH4 PPM");
            for(int i=0; i<10; i++) {
                EntryData e = new EntryData("CH4", i * .02);
                data.add(e);
            }
            //data.add(d);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formentry_list, container, false);
        addNewButton = (FloatingActionButton) view.findViewById(R.id.newEntryButton);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddEntryClicked(0);
            }
        });


        adapter = new MyArrayAdapter(getContext(),  data);

        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(adapter);

                // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new MyFormEntryRecyclerViewAdapter(data, mListener));
//        }
        return view;
    }


    public ArrayList<EntryData> getData(){
        return data;
    }

    public void addItem(EntryData e){
        data.add(e);
        adapter.notifyDataSetChanged();

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
