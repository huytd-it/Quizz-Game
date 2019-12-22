package com.yukihuy.myapplication.Model;


import ml.huytools.lib.Annotation.JsonName;
import ml.huytools.lib.Model;

public class Field extends Model {
    @JsonName
    private  int id;
    @JsonName
    private  String ten_linh_vuc;

    public Field() {
    }

    public Field(int id, String ten_linh_vuc) {
        this.id = id;
        this.ten_linh_vuc = ten_linh_vuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_linh_vuc() {
        return ten_linh_vuc;
    }

    public void setTen_linh_vuc(String ten_linh_vuc) {
        this.ten_linh_vuc = ten_linh_vuc;
    }
}
