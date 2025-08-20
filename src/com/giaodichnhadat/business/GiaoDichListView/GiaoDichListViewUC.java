package com.giaodichnhadat.business.GiaoDichListView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.giaodichnhadat.business.GiaoDich;
import com.giaodichnhadat.business.GiaoDichDat;
import com.giaodichnhadat.business.GiaoDichFactory;
import com.giaodichnhadat.business.GiaoDichNha;
import com.giaodichnhadat.presistence.GiaoDichDTO;
import com.giaodichnhadat.presistence.GiaoDichListView.GiaoDichListViewDAOGateway;

public class GiaoDichListViewUC {
	private GiaoDichListViewDAOGateway giaoDichGateway;

	public GiaoDichListViewUC(GiaoDichListViewDAOGateway giaoDichGateway){
		super();
		this.giaoDichGateway = giaoDichGateway;
	}
	
	public List<GiaoDichListViewDTO> excute() throws SQLException, ParseException{
		List<GiaoDich> gds = null;
		List<GiaoDichDTO> dtos = null;
		
		try {
			dtos = giaoDichGateway.layTatCaGiaoDich();
			
			gds = this.convertToBusinessObject(dtos);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return this.convertToViewModel(gds);
	}
	
	private List<GiaoDich> convertToBusinessObject(List<GiaoDichDTO> dtos) {
		List<GiaoDich> gds = new ArrayList<GiaoDich>();
		
		for(GiaoDichDTO dto : dtos) {
			GiaoDich gd = GiaoDichFactory.createGiaoDich(dto);
			gds.add(gd);
		}
		
		return gds;
	}
	
	private List<GiaoDichListViewDTO> convertToViewModel(List<GiaoDich> gds) {
		List<GiaoDichListViewDTO> dtoListViews = new ArrayList<GiaoDichListViewDTO>();
		for(GiaoDich gd : gds) {
			GiaoDichListViewDTO dtoListView = new GiaoDichListViewDTO();
			dtoListView.maGiaoDich = gd.getMaGiaoDich();
			dtoListView.ngayGiaoDich = gd.getNgayGiaoDich();
			dtoListView.donGia = gd.getDonGia();
			dtoListView.dienTich = gd.getDienTich();
			dtoListView.loai = gd.getLoai();
			dtoListView.thanhTien = gd.thanhTien();
			if (gd instanceof GiaoDichNha) {
	            dtoListView.loaiGD = "nhà";
	            dtoListView.loai = ((GiaoDichNha) gd).getLoaiNha();  // lấy loại chi tiết nhà
	            dtoListView.diaChi = ((GiaoDichNha) gd).getDiaChi();
	        } else if (gd instanceof GiaoDichDat) {
	            dtoListView.loaiGD = "đất";
	            dtoListView.loai = ((GiaoDichDat) gd).getLoaiDat();  // lấy loại chi tiết đất
	        } else {
	            dtoListView.loaiGD = "";
	            dtoListView.loai = "";
	            dtoListView.diaChi = "";
	        }

			dtoListViews.add(dtoListView);
		}
		
		return dtoListViews;
	}
}
