package com.sap.dryice.viewmodel.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RTExtendedDataEntity {
    private String idRPi;
    private double maxCO2;
    private double maxTemperature;
    private double maxRelHumedity;
    private double minCO2;
    private double minTemperature;
    private double minRelHumedity;

    public RTExtendedDataEntity() {
    }

    public RTExtendedDataEntity(String idRPi, double maxCO2, double maxTemperature, double maxRelHumedity, double minCO2, double minTemperature, double minRelHumedity) {
        this.idRPi = idRPi;
        this.maxCO2 = maxCO2;
        this.maxTemperature = maxTemperature;
        this.maxRelHumedity = maxRelHumedity;
        this.minCO2 = minCO2;
        this.minTemperature = minTemperature;
        this.minRelHumedity = minRelHumedity;
    }

    public String getIdRPi() {
        return idRPi;
    }

    public void setIdRPi(String idRPi) {
        this.idRPi = idRPi;
    }

    public double getMaxCO2() {
        return maxCO2;
    }

    public void setMaxCO2(double maxCO2) {
        this.maxCO2 = maxCO2;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMaxRelHumedity() {
        return maxRelHumedity;
    }

    public void setMaxRelHumedity(double maxRelHumedity) {
        this.maxRelHumedity = maxRelHumedity;
    }

    public double getMinCO2() {
        return minCO2;
    }

    public void setMinCO2(double minCO2) {
        this.minCO2 = minCO2;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMinRelHumedity() {
        return minRelHumedity;
    }

    public void setMinRelHumedity(double minRelHumedity) {
        this.minRelHumedity = minRelHumedity;
    }

    @Override
    public String toString() {
        return "RTExtendedData{" +
                "idRPi=" + idRPi +
                ", maxCO2=" + maxCO2 +
                ", maxTemperature=" + maxTemperature +
                ", maxRelHumedity=" + maxRelHumedity +
                ", minCO2=" + minCO2 +
                ", minTemperature=" + minTemperature +
                ", minRelHumedity=" + minRelHumedity +
                '}';
    }
}
