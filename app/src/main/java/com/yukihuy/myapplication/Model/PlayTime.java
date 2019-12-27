package com.yukihuy.myapplication.Model;

import ml.huytools.lib.Annotation.JsonName;
import ml.huytools.lib.MVP.Model;

public class PlayTime extends Model {
    @JsonName
    private int id;

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    @JsonName
    private  int diem;

    public int getSo_cau() {
        return so_cau;
    }

    public void setSo_cau(int so_cau) {
        this.so_cau = so_cau;
    }

    @JsonName
    private  int so_cau;

    public String getNgay_gio() {
        return ngay_gio;
    }

    public void setNgay_gio(String ngay_gio) {
        this.ngay_gio = ngay_gio;
    }

    @JsonName
    private  String ngay_gio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getNguoi_choi() {
        return nguoi_choi;
    }

    public void setNguoi_choi(User nguoi_choi) {
        this.nguoi_choi = nguoi_choi;
    }

    @JsonName(type = JsonName.Type.Model,clazz = User.class)
    private User nguoi_choi;
    @JsonName
    private String ten_dang_nhap;

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }
}
