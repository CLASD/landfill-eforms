package sanitation.la.project.myapplication.formClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Totten on 5/17/2016.
 */
public class ImeInspections {
    int imeInspectionPK;
    int inspectionDate;
    String description;
    String imeShape;
    ArrayList employee;
    String value;
    String repair;
    ArrayList<String> imeRepairs;
    String inspectionDateDate;
    String inspectionDateTime;
    int repairCount;

    public ImeInspections() {

    }

    // constructor
    public ImeInspections(int imeInspectionPK, int inspectionDate, String description, String imeShape, ArrayList employee, String value, String repair, ArrayList<String> imeRepairs, String inspectionDateDate, String inspectionDateTime, int repairCount) {
        this.imeInspectionPK = imeInspectionPK;
        this.inspectionDate = inspectionDate;
        this.description = description;
        this.imeShape = imeShape;
        this.employee = employee;
        this.value = value;
        this.repair = repair;
        this.imeRepairs = imeRepairs;
        this.inspectionDateDate = inspectionDateDate;
        this.inspectionDateTime = inspectionDateTime;
        this.repairCount = repairCount;
    }

    public int getImeInspectionPK() {
        return imeInspectionPK;
    }

    public int getInspectionDate() {
        return inspectionDate;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList getEmployee() {
        return employee;
    }

    public String getImeShape() {
        return imeShape;
    }

    public String getValue() {
        return value;
    }

    public String getRepair() {
        return repair;
    }

    public ArrayList<String> getImeRepairs() {
        return imeRepairs;
    }

    public String getInspectionDateDate() {
        return inspectionDateDate;
    }

    public int getRepairCount() {
        return repairCount;
    }

    public String getInspectionDateTime() {
        return inspectionDateTime;
    }

    public void setImeInspectionPK(int imeInspectionPK) {
        this.imeInspectionPK = imeInspectionPK;
    }

    public void setRepairCount(int repairCount) {
        this.repairCount = repairCount;
    }

    public void setInspectionDateTime(String inspectionDateTime) {
        this.inspectionDateTime = inspectionDateTime;
    }

    public void setImeRepairs(ArrayList<String> imeRepairs) {
        this.imeRepairs = imeRepairs;
    }

    public void setInspectionDateDate(String inspectionDateDate) {
        this.inspectionDateDate = inspectionDateDate;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public void setEmployee(ArrayList employee) {
        this.employee = employee;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setImeShape(String imeShape) {
        this.imeShape = imeShape;
    }

    public void setInspectionDate(int inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "{\"imeInspectionPK\":" + imeInspectionPK + ",\"inspectionDate\":" + inspectionDate + ",\"description\":" + description + ",\"imeShape\":" + imeShape + ",\"employee\":" + employee + ",\"value\":" + value + ",\"repair\":" + repair + ",\"imerepairs\":" + imeRepairs + ",\"inspectionDateDate\":" + inspectionDateDate + ",\"inspectionDateTime\":" + inspectionDateTime + ",\"repairCount\":" + repairCount + "}";
    }
}
