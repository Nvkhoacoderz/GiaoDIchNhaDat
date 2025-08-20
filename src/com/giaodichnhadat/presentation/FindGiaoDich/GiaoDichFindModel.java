package com.giaodichnhadat.presentation.FindGiaoDich;

import com.giaodichnhadat.presentation.Publisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GiaoDichFindModel extends Publisher{
	public GDViewFindItem viewItem;
	 public ObservableList<GDViewFindItem> listItems = FXCollections.observableArrayList();
	 public ObservableList<GDViewFindItem> fullList = FXCollections.observableArrayList();
}
