package com.giaodichnhadat.business;

import java.util.Date;

public class GiaoDichNha extends GiaoDich {
	private String loaiNha;
    private String diaChi;

    public GiaoDichNha(String maGiaoDich, Date ngayGiaoDich, double donGia, double dienTich, String diaChi, String loaiNha) {
        super(maGiaoDich, ngayGiaoDich, donGia, "nha", dienTich);
        this.loaiNha = loaiNha;
        this.diaChi = diaChi;
    }

    public String getLoaiNha() {
        return loaiNha;
    }

    public void setLoaiNha(String loaiNha) {
        this.loaiNha = loaiNha;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public double thanhTien(){
        if(loaiNha.equalsIgnoreCase("cao_cap")){
            return dienTich * donGia;
        } else {
            return dienTich * donGia * 0.9;
        }
    }
}
