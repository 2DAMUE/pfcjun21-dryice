package com.sap.dryice.viewmodel.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RTExtendedDataEntity {
    private String idRPi;
    private double maxCO2;
    private double maxTemperature;
    private double maxHumedity;
    private double minCO2;
    private double minTemperature;
    private double minHumedity;

    public RTExtendedDataEntity() {
    }

    public RTExtendedDataEntity(String idRPi, double maxCO2, double maxTemperature, double maxHumedity, double minCO2, double minTemperature, double minHumedity) {
        this.idRPi = idRPi;
        this.maxCO2 = maxCO2;
        this.maxTemperature = maxTemperature;
        this.maxHumedity = maxHumedity;
        this.minCO2 = minCO2;
        this.minTemperature = minTemperature;
        this.minHumedity = minHumedity;
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

    public double getMaxHumedity() {
        return maxHumedity;
    }

    public void setMaxHumedity(double maxHumedity) {
        this.maxHumedity = maxHumedity;
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

    public double getMinHumedity() {
        return minHumedity;
    }

    public void setMinHumedity(double minHumedity) {
        this.minHumedity = minHumedity;
    }

    @Override
    public String toString() {
        return "RTExtendedData{" +
                "idRPi=" + idRPi +
                ", maxCO2=" + maxCO2 +
                ", maxTemperature=" + maxTemperature +
                ", maxRelHumedity=" + maxHumedity +
                ", minCO2=" + minCO2 +
                ", minTemperature=" + minTemperature +
                ", minRelHumedity=" + minHumedity +
                '}';
    }
}
