package sanitation.la.project.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import sanitation.la.project.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExportFragment extends BaseFragment implements View.OnClickListener {


    private final String TAG = getClass().getSimpleName();
    private Button exportButton;
    public ExportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_export, container, false);
        exportButton = (Button) v.findViewById(R.id.ebutton);

        exportButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == exportButton)
        {
            Log.d(TAG, "Export Clicked.");
            mListener.exportClicked();
//            Gson g = new Gson();
//            g.toJson("JSon Test");
//            g.toJson(100);
//            g.toJson("abc123");
//            String t = g.toJson("IT works?");
//            Log.d(TAG, "Gson Test: ");
//            Log.d(TAG, t);


        }
    }
}
