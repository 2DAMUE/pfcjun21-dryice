package com.sap.dryice.viewmodel.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RTDataEntity {
    private String idRPi;
    private double CO2;
    private double Temperature;
    private double Humedity;

    public RTDataEntity() {
    }

    public RTDataEntity(String idRPi, double CO2, double temperature, double relHumedity) {
        this.idRPi = idRPi;
        this.CO2 = CO2;
        this.Temperature = temperature;
        this.Humedity = relHumedity;
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
        return Temperature;
    }

    public void setTemperature(double temperature) {
        this.Temperature = temperature;
    }

    public double getRelHumedity() {
        return Humedity;
    }

    public void setRelHumedity(double relHumedity) {
        this.Humedity = relHumedity;
    }

    @Override
    public String toString() {
        return "RTData{" +
                "co2=" + CO2 +
                ", temperature=" + Temperature +
                ", relHumedity=" + Humedity +
                '}';
    }
}
