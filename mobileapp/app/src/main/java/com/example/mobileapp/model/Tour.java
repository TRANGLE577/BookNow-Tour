package com.example.mobileapp.model;

import com.google.gson.annotations.SerializedName;

public class Tour {

    @SerializedName("id")
    private long id;

    @SerializedName("locationId")
    private long locationId;

    @SerializedName("location")
    private Location location;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("tourDateDepart")
    private String tourDateDepart;

    @SerializedName("tourDateReturn")
    private String tourDateReturn;

    @SerializedName("tourAdultCost")
    private double tourAdultCost;

    @SerializedName("tourChildrenCost")
    private double tourChildrenCost;

    @SerializedName("status")
    private boolean status;

    public Tour() {
    }

    public Tour(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTourDateDepart() {
        return tourDateDepart;
    }

    public void setTourDateDepart(String tourDateDepart) {
        this.tourDateDepart = tourDateDepart;
    }

    public String getTourDateReturn() {
        return tourDateReturn;
    }

    public void setTourDateReturn(String tourDateReturn) {
        this.tourDateReturn = tourDateReturn;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getTourAdultCost() {
        return tourAdultCost;
    }

    public void setTourAdultCost(double tourAdultCost) {
        this.tourAdultCost = tourAdultCost;
    }

    public double getTourChildrenCost() {
        return tourChildrenCost;
    }

    public void setTourChildrenCost(double tourChildrenCost) {
        this.tourChildrenCost = tourChildrenCost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
