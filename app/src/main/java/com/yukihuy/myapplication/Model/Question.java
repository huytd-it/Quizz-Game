package com.yukihuy.myapplication.Model;

import ml.huytools.lib.Annotation.JsonName;
import ml.huytools.lib.MVP.Model;


public class Question extends Model {

    @JsonName
    private int id;
    @JsonName
    private String noi_dung;
    @JsonName
    private int id_linh_vuc;
    @JsonName
    private String phuong_an_A;
    @JsonName
    private String phuong_an_B;
    @JsonName
    private String phuong_an_C;
    @JsonName
    private String phuong_an_D;
    @JsonName
    private String dap_an;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }



    public String getPhuong_an_A() {
        return phuong_an_A;
    }

    public void setPhuong_an_A(String phuong_an_A) {
        this.phuong_an_A = phuong_an_A;
    }

    public String getPhuong_an_B() {
        return phuong_an_B;
    }

    public void setPhuong_an_B(String phuong_an_B) {
        this.phuong_an_B = phuong_an_B;
    }

    public String getPhuong_an_C() {
        return phuong_an_C;
    }

    public void setPhuong_an_C(String phuong_an_C) {
        this.phuong_an_C = phuong_an_C;
    }

    public String getPhuong_an_D() {
        return phuong_an_D;
    }

    public void setPhuong_an_D(String phuong_an_D) {
        this.phuong_an_D = phuong_an_D;
    }

    public String getDap_an() {
        return dap_an;
    }

    public void setDap_an(String dap_an) {
        this.dap_an = dap_an;
    }



    public Question() {
    }

    public Question(int id, String noi_dung, int id_linh_vuc, String phuong_an_A, String phuong_an_B, String phuong_an_C, String phuong_an_D, String dap_an, int trang_thai) {
        this.id = id;
        this.noi_dung = noi_dung;
        this.id_linh_vuc = id_linh_vuc;
        this.phuong_an_A = phuong_an_A;
        this.phuong_an_B = phuong_an_B;
        this.phuong_an_C = phuong_an_C;
        this.phuong_an_D = phuong_an_D;
        this.dap_an = dap_an;
        this.trang_thai = trang_thai;
    }



    public int getId_linh_vuc() {
        return id_linh_vuc;
    }

    public void setId_linh_vuc(int id_linh_vuc) {
        this.id_linh_vuc = id_linh_vuc;
    }



    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }

    @JsonName
    private int trang_thai;
}
