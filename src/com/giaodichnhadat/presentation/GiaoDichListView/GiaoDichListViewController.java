package com.giaodichnhadat.presentation.GiaoDichListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.giaodichnhadat.business.FindGiaoDIch.GiaoDichViewFindDTO;
import com.giaodichnhadat.business.GiaoDichListView.GiaoDichListViewDTO;
import com.giaodichnhadat.business.GiaoDichListView.GiaoDichListViewUC;
import com.giaodichnhadat.presentation.FindGiaoDich.GDViewFindItem;
import com.giaodichnhadat.presentation.FindGiaoDich.GiaoDichFindModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GiaoDichListViewController{
	private GiaoDichListViewModel viewModel;
	private GiaoDichListViewUC listViewUC;
	public GiaoDichListViewController(GiaoDichListViewModel viewModel, GiaoDichListViewUC listViewUC) {
		super();
		this.viewModel = viewModel;
		this.listViewUC = listViewUC;
	}
	
		public void excute() {
			try {
				List<GiaoDichListViewDTO> ListViewFindDTOs = listViewUC.excute();
				List<GiaoDichListViewItem> gdViewFindItem = this.convertToViewModel(ListViewFindDTOs);
				viewModel.fullList = FXCollections.observableArrayList(gdViewFindItem);
				viewModel.notifySubscriber();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		private List<GiaoDichListViewItem> convertToViewModel(List<GiaoDichListViewDTO> items) {
			List<GiaoDichListViewItem> viewItems = new ArrayList<GiaoDichListViewItem>();
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			for(GiaoDichListViewDTO item : items) {
				GiaoDichListViewItem viewItem = new GiaoDichListViewItem();
				viewItem.setMaGiaoDich(item.maGiaoDich);
				viewItem.setDiaChi(item.diaChi);
				viewItem.setNgayGiaoDich(fmt.format(item.ngayGiaoDich));
				viewItem.setLoaiGD(item.loaiGD);
				viewItem.setDonGia(String.format("%.2f", item.donGia));
				viewItem.setLoai(item.loai);
				viewItem.setThanhTien(String.format("%.2f", item.thanhTien));
				viewItem.setDienTich(String.format("%.2f", item.dienTich));
				viewItems.add(viewItem);
			}
			
			return viewItems;
		}
}
