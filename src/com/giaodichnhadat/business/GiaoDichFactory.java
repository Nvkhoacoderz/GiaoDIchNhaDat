package com.giaodichnhadat.business;

import com.giaodichnhadat.presistence.GiaoDichDTO;

public class GiaoDichFactory {
	public static GiaoDich createGiaoDich(GiaoDichDTO dto) {
		if("nha".equalsIgnoreCase(dto.getLoai())) {
			return new GiaoDichNha(
					dto.getMaGiaoDich(), 
					dto.getNgayGiaoDich(), 
					dto.getDonGia(),  
					dto.getDienTich(), 
					dto.getDiaChi(), 
					dto.getLoaiNha());
		}else if("dat".equalsIgnoreCase(dto.getLoai())) {
			return new GiaoDichDat(
					dto.getMaGiaoDich(), 
					dto.getNgayGiaoDich(), 
					dto.getDonGia(),  
					dto.getDienTich(),  
					dto.getLoaiDat());
		}
		
		return null;
	}
}
