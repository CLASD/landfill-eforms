package sanitation.la.project.myapplication;

import java.util.ArrayList;

/**
 * Created by saul on 2/2/16.
 */
public class EntryData {

    private int id, grid;
    private String name, stime, etime, date;
    private ArrayList<Double> data;


    public EntryData(String name, double data, int grid, String s, String e, String d){
        this.name = name;
        this.data = new ArrayList<Double>();
        this.data.add(data);
        this.grid = grid;
        stime = s;
        etime = e;
        date = d;
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

    public int getGrid(){ return grid; }
    public String getStartTimeStr(){ return stime; }
    public String getEndTimeStr(){ return etime; }
    public String getDateStr(){ return date; }

}
