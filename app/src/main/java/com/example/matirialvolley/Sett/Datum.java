package com.example.matirialvolley.Sett;

public class Datum {

    private Integer id;

    private Integer userId;

    private String detailsAddress;

    private String lat;

    private String _long;

    private Integer workId;

    private String createdAt;

    private DataWork work;

    private PhotoOrderHome photoOrderHome;
    private String photo;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public DataWork getWork() {
        return work;
    }

    public void setWork(DataWork work) {
        this.work = work;
    }

    public PhotoOrderHome getPhotoOrderHome() {
        return photoOrderHome;
    }

    public void setPhotoOrderHome(PhotoOrderHome photoOrderHome) {
        this.photoOrderHome = photoOrderHome;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
