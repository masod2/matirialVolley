package com.example.matirialvolley.Sett;

public class Datum {

    private int id;
    private int workId;
    private int userId;
    private String detailsAddress;
    private String lat;
    private String _long;
    private String createdAt;
    private Work work;
    private Users user;
    private PhotoOrderHome photoOrderHome;

    public Datum() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int  workId) {
        this.workId = workId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public PhotoOrderHome getPhotoOrderHome() {
        return photoOrderHome;
    }

    public void setPhotoOrderHome(PhotoOrderHome photoOrderHome) {
        this.photoOrderHome = photoOrderHome;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
