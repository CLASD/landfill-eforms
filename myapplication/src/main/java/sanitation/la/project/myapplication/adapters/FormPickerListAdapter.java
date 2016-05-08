package sanitation.la.project.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sanitation.la.project.myapplication.R;
import sanitation.la.project.myapplication.data.EntryData;

/**
 * Created by saul on 3/3/16.
 */
public class FormPickerListAdapter extends ArrayAdapter {


    private ArrayList<String> data;
    private Context mContext;


    public FormPickerListAdapter(Context c){
        super(c, -1);
        mContext = c;
        data = new ArrayList<String>();
        data.add("Instantaneous");
        data.add("Hot Spot");
        data.add("Integrated");
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
        String e = data.get(position);
        textView.setText(e);

           // textView.setTextColor(Color.RED);

        return rowView;
    }



}
