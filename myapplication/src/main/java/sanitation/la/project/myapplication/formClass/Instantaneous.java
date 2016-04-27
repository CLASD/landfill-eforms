package sanitation.la.project.myapplication.formClass;

import java.util.Date;

/**
 * Created by Totten on 4/5/2016.
 */

public class Instantaneous {
    int InstantaneousDataPK;
    int SitePK;
    int EmployeePK;
    int StartTime;
    int FinishTime;
    int date;
    int InstrumentPK;
    Double maxCH;
    int SiteSamplingPointPK;

    public Instantaneous() {

    }

    // constructor
    public Instantaneous(int InstantaneousDataPK, int SitePK, int EmployeePK, int StartTime, int FinishTime, int date, int InstrumentPK, Double maxCH, int SiteSamplingPointPK) {
        this.InstantaneousDataPK = InstantaneousDataPK;
        this.SitePK = SitePK;
        this.EmployeePK = EmployeePK;
        this.StartTime = StartTime;
        this.FinishTime = FinishTime;
        this.InstrumentPK = InstrumentPK;
        this.maxCH = maxCH;
        this.SiteSamplingPointPK = SiteSamplingPointPK;
        this.date = date;
    }

    public int getDate(){
        return date;
    }
    public int getSiteSamplingPointPK() {
        return SiteSamplingPointPK;
    }

    public void setSiteSamplingPointPK(int SiteSamplingPointPK) {
        SiteSamplingPointPK = SiteSamplingPointPK;
    }

    public Double getMaxCH() {
        return maxCH;
    }

    public void setMaxCH(Double maxCH) {
        this.maxCH = maxCH;
    }

    public int getInstrumentPK() {
        return InstrumentPK;
    }

    public void setInstrumentPK(int InstrumentPK) {
        InstrumentPK = InstrumentPK;
    }

    public int getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(int FinishTime) {
        FinishTime = FinishTime;
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int StartTime) {
        StartTime = StartTime;
    }

    public int getSitePK() {
        return SitePK;
    }

    public void setSitePK(int SitePK) {
        SitePK = SitePK;
    }

    public int getEmployeePK() {
        return EmployeePK;
    }

    public void setEmployeePK(int EmployeePK) {
        EmployeePK = EmployeePK;
    }

    public int getInstantaneousDataPK() {
        return InstantaneousDataPK;
    }

    public void setInstantaneousDataPK(int InstantaneousDataPK) {
        InstantaneousDataPK = InstantaneousDataPK;
    }

//    public String toString(){
//        return (this.getInstantaneousDataPK() + this.EmployeePK + this.getSitePK() + "");
//    }

    @Override
    public String toString() {
        return "{\"InstantaneousData\":[{Instantaneous:" + InstantaneousDataPK + ", SitePK:" + SitePK + ", EmployeePK:" + EmployeePK + ", StartTime:" + StartTime + ", FinishTime:" + FinishTime + ", date:" + date + ", InstrumentPK:" + InstrumentPK + ", maxCH:" + maxCH + "SiteSamplingPointPK:" + SiteSamplingPointPK + "}]}";
    }
}
