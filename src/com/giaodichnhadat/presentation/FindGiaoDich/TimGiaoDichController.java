package com.giaodichnhadat.presentation.FindGiaoDich;

import java.text.SimpleDateFormat;

import com.giaodichnhadat.business.FindGiaoDIch.FindGiaoDichUC;
import com.giaodichnhadat.business.FindGiaoDIch.GiaoDichViewFindDTO;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewItem;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewModel;


public class TimGiaoDichController {
    //chức năng tìm
	private FindGiaoDichUC giaoDichUC;
	private GiaoDichListViewModel gdModel;
	
	public TimGiaoDichController(FindGiaoDichUC giaoDichUC, GiaoDichListViewModel gdModel) {
		super();
		this.giaoDichUC = giaoDichUC;
		this.gdModel = gdModel;
	}

	
	public GiaoDichListViewModel getGdModel() {
		return gdModel;
	}


	public void setGdModel(GiaoDichListViewModel gdModel) {
		this.gdModel = gdModel;
	}


	public void excute(String maGiaoDich) {
		try {
	        GiaoDichViewFindDTO viewFindDTO = giaoDichUC.excute(maGiaoDich);
	        if (viewFindDTO == null) {
	            gdModel.viewItem = null; // hoặc xử lý khác khi không tìm thấy
	            return;
	        }
	        GiaoDichListViewItem gdViewFindItem = this.convertToViewModel(viewFindDTO);
	        gdModel.viewItem = gdViewFindItem;
	    } catch (Exception e) {
	        e.printStackTrace(); // nên log lỗi cho dễ debug
	    }
	}
	
	private GiaoDichListViewItem convertToViewModel(GiaoDichViewFindDTO item) {
		GiaoDichListViewItem viewItem = new GiaoDichListViewItem();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		viewItem.setMaGiaoDich(item.maGiaoDich);
		viewItem.setDiaChi(item.diaChi);
		viewItem.setNgayGiaoDich(fmt.format(item.ngayGiaoDich));
		viewItem.setLoaiGD(item.loaiGD);
		viewItem.setDonGia(String.format("%.2f", item.donGia));
		viewItem.setLoai(item.loai);
		viewItem.setThanhTien(String.format("%.2f", item.thanhTien));
		viewItem.setDienTich(String.format("%.2f", item.dienTich));
		
		return viewItem;
	}
	
	
}
