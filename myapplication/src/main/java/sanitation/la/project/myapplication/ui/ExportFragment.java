package sanitation.la.project.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import sanitation.la.project.myapplication.R;
import sanitation.la.project.myapplication.formClass.Instantaneous;
import sanitation.la.project.myapplication.formClass.InstantaneousModel;

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
//        if(v == exportButton)
//        {
//            Log.d(TAG, "Export Clicked.");
//            String s = mListener.exportClicked();
//           if( s != null){
//               Toast t  = Toast.makeText(getContext(), "File Exported: " + s, Toast.LENGTH_LONG);
//               t.show();
//            }
//
////            Gson g = new Gson();
////            g.toJson("JSon Test");
////            g.toJson(100);
////            g.toJson("abc123");
////            String t = g.toJson("IT works?");
////            Log.d(TAG, "Gson Test: ");
////            Log.d(TAG, t);
//
//
//        }

        if(v == exportButton) {
            String s = mListener.exportClicked();
            if(s != null) {
                Toast t = Toast.makeText(getContext(), "File Exported: " + s, Toast.LENGTH_LONG);
                t.show();
            }
            Instantaneous instantaneous = new Instantaneous();
            Log.d(TAG, "Export Clicked.");
            Gson gson = new Gson();
            InstantaneousModel instantaneousModel = new InstantaneousModel(1, 1, 1, 1230, 1235, 20160527, 1, 444.0, 1);
            gson.toJson(instantaneousModel.toString());
            Log.d("JSON", instantaneousModel.toString());
        }
    }
}
