package sanitation.la.project.myapplication.data;

import java.util.ArrayList;

/**
 * Created by saul on 2/2/16.
 */
public class EntryData {

    public class DataPair{
        private double d;
        private String n;
        public DataPair(Double dat, String label){
            d = dat;
            n = label;
        }
        public String getLabel(){
            return n;
        }
        public double getData(){
            return d;
        }
        public String toString(){
            return String.format("%.2f %s", d, n);
        }
    }
    private int id, grid;
    private String name, stime, etime, date;
    private ArrayList<DataPair> data;


    public EntryData(String name, double data, int grid, String s, String e, String d){
        this.name = name;
        this.data = new ArrayList<DataPair>();
        this.data.add(new DataPair(data, "ch4 ppm"));
        this.grid = grid;
        stime = s;
        etime = e;
        date = d;
    }

    public EntryData(ArrayList<DataPair> data,String s, String e, String d){

        this.data = data;
        name = "blah";
        grid = 1;

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

    public ArrayList<DataPair>  getData(){
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
