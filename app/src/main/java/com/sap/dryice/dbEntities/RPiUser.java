package com.sap.dryice.dbEntities;

public class RPiUser {
    private String idRPi;
    private String pwd;
    private String userId;
    private double latitude;
    private double longitude;

    public RPiUser() {
    }

    public RPiUser(String idRPi, String pwd, String userId, double latitude, double longitude) {
        this.idRPi = idRPi;
        this.pwd = pwd;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                ", username='" + userId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
