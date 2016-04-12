package sanitation.la.project.myapplication.formClass;

/**
 * Created by Totten on 4/5/2016.
 */

public class Instantaneous {
    int InstantaneousDataPK;
    int SitePK;
    int EmployeePK;
    int StartTime;
    int FinishTime;
    int InstrumentPK;
    String maxCH;
    int SiteSamplingPointPK;

    public Instantaneous() {

    }

    // constructor
    public Instantaneous(int InstantaneousDataPK, int SitePK, int EmployeePK, int StartTime, int
            FinishTime, int InstrumentPK, String maxCH, int SiteSamplingPointPK) {
        this.InstantaneousDataPK = InstantaneousDataPK;
        this.SitePK = SitePK;
        this.EmployeePK = EmployeePK;
        this.StartTime = StartTime;
        this.FinishTime = FinishTime;
        this.InstrumentPK = InstrumentPK;
        this.maxCH = maxCH;
        this.SiteSamplingPointPK = SiteSamplingPointPK;
    }

    public int getSiteSamplingPointPK() {
        return SiteSamplingPointPK;
    }

    public void setSiteSamplingPointPK(int SiteSamplingPointPK) {
        SiteSamplingPointPK = SiteSamplingPointPK;
    }

    public String getMaxCH() {
        return maxCH;
    }

    public void setMaxCH(String maxCH) {
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
}
