package d3v.bnb.rssimetro.Models;

/**
 * Created by Bruno on 2/17/2018.
 */

public class Measurement {

    private String measurementID;
    
    private String posID;
    private String equipType;
    private double rssi1;
    private double rssi2;
    private double rssi3;
    private double rssi4;

    public Measurement(String measurementID, String equipType, double rssi1, double rssi2, double rssi3, double rssi4) {
        this.measurementID = measurementID;
        //posxxmxx - ID_Template
        this.posID = this.measurementID.substring(5); 
        this.equipType = equipType;
        this.rssi1 = rssi1;
        this.rssi2 = rssi2;
        this.rssi3 = rssi3;
        this.rssi4 = rssi4;
    }

    public Measurement(String measurementID) {
        this.measurementID = measurementID;
    }

    public Measurement() {
    }

    public String getMeasurementID() {
        return measurementID;
    }

    public void setMeasurementID(String measurementID) {
        this.measurementID = measurementID;
    }

    public String getPosID() {
        return posID;
    }

    public void setPosID(String posID) {
        this.posID = posID;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public double getRssi1() {
        return rssi1;
    }

    public void setRssi1(double rssi1) {
        this.rssi1 = rssi1;
    }

    public double getRssi2() {
        return rssi2;
    }

    public void setRssi2(double rssi2) {
        this.rssi2 = rssi2;
    }

    public double getRssi3() {
        return rssi3;
    }

    public void setRssi3(double rssi3) {
        this.rssi3 = rssi3;
    }

    public double getRssi4() {
        return rssi4;
    }

    public void setRssi4(double rssi4) {
        this.rssi4 = rssi4;
    }
}
