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
import sanitation.la.project.myapplication.data.LocationPoint;

/**
 * Created by saul on 2/13/16.
 */
public class LocationListAdapter extends ArrayAdapter {

    private ArrayList<LocationPoint> data;
    private Context mContext;


    public LocationListAdapter(Context c){
        super(c, -1);
        mContext = c;
        data = new ArrayList<LocationPoint>();
       // data = d;
    }

    public void addItem(LocationPoint p){
        data.add(p);
        notifyDataSetChanged();
    }
    public LocationPoint getItem(int i){
        return data.get(i);
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
        LocationPoint e = data.get(position);
       // String s = "Grid id: " + e.getGrid() + " [ " + e.getData().get(0) + " ] " + e.getName() ;
        info.setText(String.format("Lat: %.6f  Long: %.6f", e.getLat(), e.getLon()));
        textView.setText(e.toString());

        return rowView;
    }




}
