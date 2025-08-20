package com.giaodichnhadat.business;

import java.util.Date;

public abstract class GiaoDich {
	 protected String maGiaoDich;
	    protected Date ngayGiaoDich;
	    protected double donGia;
	    protected String loai;
	    protected double dienTich;

	    public GiaoDich(String maGiaoDich, Date ngayGiaoDich, double donGia, String loai, double dienTich) {
	        this.maGiaoDich = maGiaoDich;
	        this.ngayGiaoDich = ngayGiaoDich;
	        this.donGia = donGia;
	        this.loai = loai;
	        this.dienTich = dienTich;
	    }

	    public String getMaGiaoDich() {
	        return maGiaoDich;
	    }

	    public void setMaGiaoDich(String maGiaoDich) {
	        this.maGiaoDich = maGiaoDich;
	    }

	    public Date getNgayGiaoDich() {
	        return ngayGiaoDich;
	    }

	    public void setNgayGiaoDich(Date ngayGiaoDich) {
	        this.ngayGiaoDich = ngayGiaoDich;
	    }

	    public double getDonGia() {
	        return donGia;
	    }

	    public void setDonGia(double donGia) {
	        this.donGia = donGia;
	    }

	    public String getLoai() {
	        return loai;
	    }

	    public void setLoai(String loai) {
	        this.loai = loai;
	    }

	    public double getDienTich() {
	        return dienTich;
	    }

	    public void setDienTich(double dienTich) {
	        this.dienTich = dienTich;
	    }

	    public abstract double thanhTien();
}
