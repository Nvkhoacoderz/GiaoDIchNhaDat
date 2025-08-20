package com.giaodichnhadat.presentation.GiaoDichListView;


import com.giaodichnhadat.presentation.Publisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GiaoDichListViewModel extends Publisher{
	public GiaoDichListViewItem viewItem;
	public ObservableList<GiaoDichListViewItem> listItems = FXCollections.observableArrayList();
	public ObservableList<GiaoDichListViewItem> fullList = FXCollections.observableArrayList();
}
