package com.sap.dryice.dbEntities;

public class RTExtendedData {
    private String idRPi;
    private String maxCO2;
    private String maxTemperature;
    private String maxRelHumedity;
    private String minCO2;
    private String minTemperature;
    private String minRelHumedity;

    public RTExtendedData() {
    }

    public RTExtendedData(String idRPi, String maxCO2, String maxTemperature, String maxRelHumedity, String minCO2, String minTemperature, String minRelHumedity) {
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

    public String getMaxCO2() {
        return maxCO2;
    }

    public void setMaxCO2(String maxCO2) {
        this.maxCO2 = maxCO2;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMaxRelHumedity() {
        return maxRelHumedity;
    }

    public void setMaxRelHumedity(String maxRelHumedity) {
        this.maxRelHumedity = maxRelHumedity;
    }

    public String getMinCO2() {
        return minCO2;
    }

    public void setMinCO2(String minCO2) {
        this.minCO2 = minCO2;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMinRelHumedity() {
        return minRelHumedity;
    }

    public void setMinRelHumedity(String minRelHumedity) {
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
