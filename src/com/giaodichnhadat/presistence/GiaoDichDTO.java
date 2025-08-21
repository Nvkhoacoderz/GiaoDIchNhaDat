package com.giaodichnhadat.presistence;

import java.util.Date;

public class GiaoDichDTO {
	private String maGiaoDich;
	private Date ngayGiaoDich;
	private double donGia;
	private String loai;
	private double dienTich;
	private String diaChi;
	private String loaiNha;
	private String loaiDat;
	private double thanhTien;
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
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double getDonGia() {
		return donGia;
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
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getLoaiNha() {
		return loaiNha;
	}
	public void setLoaiNha(String loaiNha) {
		this.loaiNha = loaiNha;
	}
	public String getLoaiDat() {
		return loaiDat;
	}
	public void setLoaiDat(String loaiDat) {
		this.loaiDat = loaiDat;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	
	
}
