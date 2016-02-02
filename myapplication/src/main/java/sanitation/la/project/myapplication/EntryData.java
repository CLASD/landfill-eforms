package sanitation.la.project.myapplication;

import java.util.ArrayList;

/**
 * Created by saul on 2/2/16.
 */
public class EntryData {

    private int id;
    private String name;
    private ArrayList<Double> data;

    public EntryData(String name, double data){
        this.name = name;
        this.data = new ArrayList<Double>();
        this.data.add(data);
    }

    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }

    public ArrayList<Double>  getData(){
        return data;
    }

    public int getDataSize(){
        return data.size();
    }

}
