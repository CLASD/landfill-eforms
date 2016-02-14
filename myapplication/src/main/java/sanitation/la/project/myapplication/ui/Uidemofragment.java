package sanitation.la.project.myapplication.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import sanitation.la.project.myapplication.data.EntryData;
import sanitation.la.project.myapplication.helpers.OnFragmentInteractionListener;
import sanitation.la.project.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Uidemofragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Uidemofragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Uidemofragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String ARG_FID = "FORMID";

    private int fId = 0;
    private OnFragmentInteractionListener mListener;

    private Button submitButton;
    private EditText editText1;
    private TextView nameText1;
    private EditText gridText;
    private EntryData entryData;
    private TimePicker stime, etime;
    private DatePicker date;

    public Uidemofragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Uidemofragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Uidemofragment newInstance(String param1, String param2) {
        Uidemofragment fragment = new Uidemofragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            fId = getArguments().getInt(ARG_FID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int r = fId == 0 ? R.layout.fragment_uidemofragment : R.layout.fragment_newentry;
        View v = inflater.inflate(r, container, false);

        if(fId==1){
            editText1 = (EditText) v.findViewById(R.id.data1EditText);
            gridText = (EditText) v.findViewById(R.id.gridEditText);
            nameText1 = (TextView) v.findViewById(R.id.data1TextView);

            submitButton = (Button) v.findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitEntry();
                }
            });

            stime = (TimePicker) v.findViewById(R.id.timePicker1);
            etime = (TimePicker) v.findViewById(R.id.timePicker2);
            date = (DatePicker) v.findViewById(R.id.datePicker);
        }

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

    public void submitEntry(){

        try {
            entryData = new EntryData(nameText1.getText().toString(),
                                Double.parseDouble(editText1.getText().toString()),
                                Integer.parseInt(gridText.getText().toString()) ,
                                stime.getCurrentHour()+ ":" +  stime.getCurrentMinute(),
                                etime.getCurrentHour() + ":" +  etime.getCurrentMinute(),
                                date.getMonth() + "/" + date.getDayOfMonth() + "/" + date.getYear());

            mListener.onNewEntrySubmited(entryData);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

}
