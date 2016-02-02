package sanitation.la.project.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
       // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        EntryData e = data.get(position);
        String s = "" + e.getName() + e.getData().get(0);

        textView.setText(s);

        return rowView;
    }




}
