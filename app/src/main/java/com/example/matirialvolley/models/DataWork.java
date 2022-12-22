package com.example.matirialvolley.models;

public class DataWork {

    private int _id;
    private String _name;

    public DataWork(){
        this._id = 0;
        this._name = "";
    }

    public DataWork(int _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    public void setId(int id){
        this._id = id;
    }

    public int getId(){
        return this._id;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getName(){
        return this._name;
    }
}
