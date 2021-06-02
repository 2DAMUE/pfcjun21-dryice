package com.sap.dryice.dbEntities;

public class RTData {
    private String idRPi;
    private double CO2;
    private double temperature;
    private double relHumedity;

    public RTData() {
    }

    public RTData(String idRPi, double CO2, double temperature, double relHumedity) {
        this.idRPi = idRPi;
        this.CO2 = CO2;
        this.temperature = temperature;
        this.relHumedity = relHumedity;
    }

    public String getIdRPi(){return idRPi;}

    public void setIdRPi(String idRPi) {
        this.idRPi = idRPi;
    }

    public double getCO2() {
        return CO2;
    }

    public void setCO2(double CO2) {
        this.CO2 = CO2;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getRelHumedity() {
        return relHumedity;
    }

    public void setRelHumedity(double relHumedity) {
        this.relHumedity = relHumedity;
    }

    @Override
    public String toString() {
        return "RTData{" +
                "co2=" + CO2 +
                ", temperature=" + temperature +
                ", relHumedity=" + relHumedity +
                '}';
    }
}
