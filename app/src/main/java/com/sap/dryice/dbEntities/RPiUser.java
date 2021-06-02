package com.sap.dryice.dbEntities;

public class RPiUser {
    private String idRPi;
    private String pwd;
    private String name;
    private double latitude;
    private double longitude;

    public RPiUser() {
    }

    public RPiUser(String idRPi, String pwd, String name, double latitude, double longitude) {
        this.idRPi = idRPi;
        this.pwd = pwd;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIdRPi() {
        return idRPi;
    }

    public void setIdRPi(String idRPi) {
        this.idRPi = idRPi;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "RPiUser{" +
                "idRPi='" + idRPi + '\'' +
                ", pwd='" + pwd + '\'' +
                ", username='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
