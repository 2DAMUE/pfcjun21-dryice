package com.sap.dryice.dbEntities;

public class User {
    private String userId;
    private String username;
    private String name;
    private String email;
    private String idRPi;
    private double latitude;
    private double longitude;

    public User() {
    }

    public User(String userId, String username, String name, String email, String idRPi, double latitude, double longitude) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.idRPi = idRPi;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdRPi() {
        return idRPi;
    }

    public void setIdRPi(String idRPi) {
        this.idRPi = idRPi;
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
        return "User{" +
                "userId='" + userId + '\'' +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", idRPi='" + idRPi + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
