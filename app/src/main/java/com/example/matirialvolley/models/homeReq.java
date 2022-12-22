package com.example.matirialvolley.models;

public class homeReq {

    private Integer id;

    private Integer userId;

    private String detailsAddress;

    private int lat;

    private int _long;

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

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLong() {
        return _long;
    }

    public void setLong(int _long) {
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
