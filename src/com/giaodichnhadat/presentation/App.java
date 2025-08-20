package com.giaodichnhadat.presentation;

import com.giaodichnhadat.business.GiaoDichListView.GiaoDichListViewUC;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewController;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewModel;
import com.giaodichnhadat.presistence.GiaoDichListView.SQLServerGiaoDichListViewDAO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{
	@Override
	public void start(Stage primaryStage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/giaodichnhadat/presentation/MainGiaoDich.fxml"));

	     // Đọc root node và controller từ FXMLLoader
	        Parent root = loader.load();
	        
	        
	     // Lấy controller SampleController do FXMLLoader tạo
	        MainGiaoDIchUI sampleController = loader.getController();
	        
	        // Khởi tạo DAO, UseCase, ViewModel, Controller
	        SQLServerGiaoDichListViewDAO dao = new SQLServerGiaoDichListViewDAO();
	        GiaoDichListViewModel viewModel = new GiaoDichListViewModel();
	        GiaoDichListViewUC useCase = new GiaoDichListViewUC(dao);
	        GiaoDichListViewController controller = new GiaoDichListViewController(viewModel, useCase);
	        
	        //Khởi tạo findUsecase, findcontr
	       
	        //Khởi tạo lớp trung gian
	        
	        // Load dữ liệu vào ViewModel
	        controller.excute();

	        // Truyền viewModel vào SampleController (setViewModel)
	        sampleController.setViewModel(viewModel);

	        // Set scene và show stage
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Quản lý Giao Dịch Nhà Đất");
	        primaryStage.show();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
