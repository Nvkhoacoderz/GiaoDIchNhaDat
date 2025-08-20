package com.giaodichnhadat.presentation.DeleteGiaoDich;

import com.giaodichnhadat.business.DeleteGiaoDich.DeleteGiaoDichUC;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewItem;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewModel;

public class DeleteGiaoDichController {
	private DeleteGiaoDichUC uc;
	private GiaoDichListViewModel viewModel;
	
	public DeleteGiaoDichController(DeleteGiaoDichUC uc, GiaoDichListViewModel viewModel) {
		super();
		this.uc = uc;
		this.viewModel = viewModel;
	}

	public void xoaGiaoDich(GiaoDichListViewItem item) {
		uc.xoa(item.getMaGiaoDich());
    }
}
