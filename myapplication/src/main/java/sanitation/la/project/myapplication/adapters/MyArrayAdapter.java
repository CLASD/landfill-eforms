package sanitation.la.project.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sanitation.la.project.myapplication.data.EntryData;
import sanitation.la.project.myapplication.R;

/**
 * Created by saul on 2/2/16.
 */
public class MyArrayAdapter extends ArrayAdapter {

    private ArrayList<EntryData> data;
    private Context mContext;


    public MyArrayAdapter(Context c, ArrayList<EntryData> d){
        super(c, -1);
        mContext = c;
        data = new ArrayList<EntryData>();
        data = d;
    }


    @Override
    public int getCount(){

        return data.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_form_entry, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.content);
        TextView info = (TextView) rowView.findViewById(R.id.infoTextView);
       // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        //double
        EntryData e = data.get(position);
        String s = "Grid id: " + e.getGrid() + " [ " + e.getData().get(0) + " ] " + e.getName() ;
        info.setText(String.format("%s - %s : %s", e.getStartTimeStr(), e.getEndTimeStr(), e.getDateStr()));
        textView.setText(s);

        if(e.getData().get(0) >= 500.)
            textView.setTextColor(Color.RED);

        return rowView;
    }




}
