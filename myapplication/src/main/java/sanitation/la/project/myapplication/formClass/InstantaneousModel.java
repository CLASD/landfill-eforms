package sanitation.la.project.myapplication.formClass;

import java.util.Date;

/**
 * Created by Totten on 4/26/2016.
 */

public class InstantaneousModel {
    int InstantaneousDataPK;
    int SitePK;
    int EmployeePK;
    int StartTime;
    int FinishTime;
    int date;
    int InstrumentPK;
    Double maxCH;
    int SiteSamplingPointPK;

    public InstantaneousModel(int InstantaneousDataPK, int SitePK, int EmployeePK, int StartTime, int FinishTime, int date, int InstrumentPK, Double maxCH, int SiteSamplingPointPK) {
        super();
        this.InstantaneousDataPK = InstantaneousDataPK;
        this.SitePK = SitePK;
        this.EmployeePK = EmployeePK;
        this.StartTime = StartTime;
        this.FinishTime = FinishTime;
        this.date = date;
        this.InstrumentPK = InstrumentPK;
        this.maxCH = maxCH;
        this.SiteSamplingPointPK = SiteSamplingPointPK;
    }

    @Override
    public String toString() {
        return "{\"InstantaneousData\":[{Instantaneous:" + InstantaneousDataPK + ", SitePK:" + SitePK + ", EmployeePK:" + EmployeePK + ", StartTime:" + StartTime + ", FinishTime:" + FinishTime + ", date:" + date + ", InstrumentPK:" + InstrumentPK + ", maxCH:" + maxCH + "SiteSamplingPointPK:" + SiteSamplingPointPK + "}]}";
    }
}
