package com.giaodichnhadat.business;

import java.util.Date;

public class GiaoDichDat extends GiaoDich{
	private String loaiDat;

    public GiaoDichDat(String maGiaoDich, Date ngayGiaoDich, double donGia, double dienTich, String loaiDat) {
        super(maGiaoDich, ngayGiaoDich, donGia, "dat", dienTich);
        this.loaiDat = loaiDat;
    }

    public String getLoaiDat() {
        return loaiDat;
    }

    public void setLoaiDat(String loaiDat) {
        this.loaiDat = loaiDat;
    }

    @Override
    public double thanhTien(){
        if(loaiDat.equalsIgnoreCase("A")){
            return dienTich * donGia * 1.5;
        } else {
            return dienTich * donGia;
        }
    }
}
