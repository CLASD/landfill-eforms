package sanitation.la.project.myapplication.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import sanitation.la.project.myapplication.adapters.InstantaneousAdapter;
import sanitation.la.project.myapplication.formClass.Instantaneous;
import sanitation.la.project.myapplication.helpers.DatabaseHandler;
import sanitation.la.project.myapplication.helpers.DbHelper;
import sanitation.la.project.myapplication.data.EntryData;
import sanitation.la.project.myapplication.adapters.MyArrayAdapter;
import sanitation.la.project.myapplication.helpers.OnFragmentInteractionListener;
import sanitation.la.project.myapplication.R;
import sanitation.la.project.myapplication.helpers.lacDbEntry;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class FormEntryFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnFragmentInteractionListener mListener;
    private FloatingActionButton addNewButton;
    private ArrayAdapter adapter;
    private ArrayList<EntryData> data;
    private ArrayList<Instantaneous> instantaneousData;
    private DatabaseHandler db;
    private int formId = 0;

    private String title = "";

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
        //data = new ArrayList<Double>();
        db = new DatabaseHandler(getContext());
        instantaneousData = new ArrayList<>();
        instantaneousData.addAll( db.getAllInstantaneous() );
        Log.d(TAG, "Loaded " + instantaneousData.size() + " entries from local db");

        //loadFromDb();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            Bundle b = getArguments();
            formId = b.getInt("FORMID");


            String[] n = getResources().getStringArray(R.array.form_names);
            title = n[formId];

//            Bundle b = getArguments();
//            double d[] = b.getDoubleArray("DATA");
//            for(double a: d) {
//                data.add(a);
//                Log.d(TAG, "LIST DATA: " + a);
//            }

//            //Double d = getArguments().getDouble("CH4 PPM");
//            for(int i=0; i<10; i++) {
//                EntryData e = new EntryData("CH4", i * .02);
//                data.add(e);
//            }
            //data.add(d);
        }

    }

//    // TODO: 3/3/16  make this work, and switch tables based on formId, everything else should be good
//    public void loadFromDb(){
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                lacDbEntry.mEntry.COLUMN_NAME_ENTRY_ID,
//                lacDbEntry.mEntry.COLUMN_NAME_TITLE,
//                //lacDbEntry.mEntry.COLUMN_NAME_CONTENT,
//        };
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                lacDbEntry.mEntry.COLUMN_NAME_ENTRY_ID + " ASC";
//        String[] args = {""};
//        Cursor c = db.query(lacDbEntry.mEntry.TABLE_NAME, projection, null, null,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );
//
//        while(c.moveToNext()){
//            Log.d(TAG, " " + c.getString(0) + " " + c.getString(1) ); //+ " " + c.getString(2));
//
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formentry_list, container, false);
        addNewButton = (FloatingActionButton) view.findViewById(R.id.newEntryButton);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddEntryClicked(formId);
            }
        });

        TextView formType = (TextView) view.findViewById(R.id.formTypeTextView);
        formType.setText(title);


//        data = mListener.getData();
//        adapter = new MyArrayAdapter(getContext(),  data);
        adapter = new InstantaneousAdapter(getContext(), instantaneousData);

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
        Log.d(TAG, "Adding data to list... " + e.getName());

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
