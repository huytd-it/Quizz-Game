package com.yukihuy.myapplication.Model;

import ml.huytools.lib.Annotation.JsonName;
import ml.huytools.lib.MVP.Model;


public class User extends Model {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinh_dai_dien() {
        return hinh_dai_dien;
    }

    public void setHinh_dai_dien(String hinh_dai_dien) {
        this.hinh_dai_dien = hinh_dai_dien;
    }

    public int getDiem_cao_nhat() {
        return diem_cao_nhat;
    }

    public void setDiem_cao_nhat(int diem_cao_nhat) {
        this.diem_cao_nhat = diem_cao_nhat;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    @JsonName
    private int id;
    @JsonName
    private String ten_dang_nhap;
    @JsonName
    private String mat_khau;
    @JsonName
    private String email;
    @JsonName
    private String hinh_dai_dien;
    @JsonName
    private int diem_cao_nhat;
    @JsonName
    private int credit;
    @JsonName
    private int trang_thai;

}
