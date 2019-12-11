package com.yukihuy.myapplication;

public class Question {
    public Question(String noiDung, String dapAn1, String dapAn2, String dapAn3, String dapAn4, String dung, String chon) {
        NoiDung = noiDung;
        DapAn1 = dapAn1;
        DapAn2 = dapAn2;
        DapAn3 = dapAn3;
        DapAn4 = dapAn4;
        Dung = dung;
        Chon = chon;
    }

    private String NoiDung;
    private String DapAn1;
    private String DapAn2;
    private String DapAn3;
    private String DapAn4;
    private String Dung;
    private String Chon;

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getDapAn1() {
        return DapAn1;
    }

    public void setDapAn1(String dapAn1) {
        DapAn1 = dapAn1;
    }

    public String getDapAn2() {
        return DapAn2;
    }

    public void setDapAn2(String dapAn2) {
        DapAn2 = dapAn2;
    }

    public String getDapAn3() {
        return DapAn3;
    }

    public void setDapAn3(String dapAn3) {
        DapAn3 = dapAn3;
    }

    public String getDapAn4() {
        return DapAn4;
    }

    public void setDapAn4(String dapAn4) {
        DapAn4 = dapAn4;
    }

    public String getDung() {
        return Dung;
    }

    public void setDung(String dung) {
        Dung = dung;
    }

    public String getChon() {
        return Chon;
    }

    public void setChon(String chon) {
        Chon = chon;
    }

    public Question(){
    }
}
