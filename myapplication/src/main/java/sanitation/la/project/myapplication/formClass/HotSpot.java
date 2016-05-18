package sanitation.la.project.myapplication.formClass;

import java.util.ArrayList;

/**
 * Created by Totten on 5/17/2016.
 */
public class HotSpot {
    int id;
    String readingDate;
    String description;
    int employeePK;
    String value;
    String imeNumber;
    String pointType;
    String siteName;
    ArrayList<ImeInspections> imeInspections;

    public HotSpot() {

    }

    // constructor
    public HotSpot(int id, String readingDate, String description, int employeePK, String value, String imeNumber, String pointType, String siteName, ArrayList<ImeInspections> imeInspections) {
        this.id = id;
        this.readingDate = readingDate;
        this.description = description;
        this.employeePK = employeePK;
        this.value = value;
        this.imeNumber = imeNumber;
        this.pointType = pointType;
        this.siteName = siteName;
        this.imeInspections = imeInspections;
    }

    public int getId() {
        return id;
    }

    public ArrayList<ImeInspections> getImeInspections() {
        return imeInspections;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getPointType() {
        return pointType;
    }

    public String getImeNumber() {
        return imeNumber;
    }

    public String getValue() {
        return value;
    }

    public int getEmployeePK() {
        return employeePK;
    }

    public String getDescription() {
        return description;
    }

    public String getReadingDate() {
        return readingDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImeInspections(ArrayList<ImeInspections> imeInspections) {
        this.imeInspections = imeInspections;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public void setImeNumber(String imeNumber) {
        this.imeNumber = imeNumber;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setEmployeePK(int employeePK) {
        this.employeePK = employeePK;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReadingDate(String readingDate) {
        this.readingDate = readingDate;
    }

    public String toString() {
        return "{\"id\":" + id + ",\"readingDate\":" + readingDate + ",\"description\":" + description + ",\"employeePK\":" + employeePK + ",\"value\":" + value + ",\"imeNumber\":" + imeNumber + ",\"pointType:\"" + pointType + ",\"siteName\":" + siteName + ",\"imeInspections\":" + imeInspections + "}";
    }
}
