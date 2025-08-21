package com.giaodichnhadat.presentation;

import java.sql.SQLException;

import com.giaodichnhadat.business.DeleteGiaoDich.DeleteGiaoDichUC;
import com.giaodichnhadat.business.EditGiaoDich.GiaoDichEditUC;
import com.giaodichnhadat.business.EditGiaoDich.OpenDialog.OpenAndEditFormUseCase;
import com.giaodichnhadat.business.FindGiaoDIch.FindGiaoDichUC;
import com.giaodichnhadat.presentation.DeleteGiaoDich.DeleteGiaoDichController;
import com.giaodichnhadat.presentation.EditGiaoDich.GiaoDichEditController;
import com.giaodichnhadat.presentation.EditGiaoDich.GiaoDichEditItem;
import com.giaodichnhadat.presentation.EditGiaoDich.GiaoDichEditModel;
import com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog.OpenAndEditFormController;
import com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog.OpenAndEditFormView;
import com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog.OpenEditFormModel;
import com.giaodichnhadat.presentation.EditGiaoDich.Subscriber;
import com.giaodichnhadat.presentation.FindGiaoDich.TimGiaoDichController;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewItem;
import com.giaodichnhadat.presentation.GiaoDichListView.GiaoDichListViewModel;
import com.giaodichnhadat.presistence.DeleteGiaoDIch.SQLServerGiaoDichDeleteDAO;
import com.giaodichnhadat.presistence.EditGiaoDich.OpenDialog.SQLServerOpenEditForm;
import com.giaodichnhadat.presistence.EditGiaoDich.SQLServerGiaoDichEditDAO;
import com.giaodichnhadat.presistence.FindGiaoDich.SQLServerGiaoDichFindDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainGiaoDIchUI implements GDSubscriber {
    @FXML private TextField txtField;
    @FXML private Button btnTim;
    @FXML private TableView<GiaoDichListViewItem> tableView;

    @FXML private TableColumn<GiaoDichListViewItem, String> colMa;
    @FXML private TableColumn<GiaoDichListViewItem, String> colLoaiGiaoDich;
    @FXML private TableColumn<GiaoDichListViewItem, String> colLoai;
    @FXML private TableColumn<GiaoDichListViewItem, String> colDiaChi;
    @FXML private TableColumn<GiaoDichListViewItem, String> colNgayGiaoDich;
    @FXML private TableColumn<GiaoDichListViewItem, String> colDonGia;
    @FXML private TableColumn<GiaoDichListViewItem, String> colDienTich;
    @FXML private TableColumn<GiaoDichListViewItem, String> colThanhTien;

    private GiaoDichListViewModel viewModel;
    private TimGiaoDichController timController;
    private DeleteGiaoDichController xoaController;

    public void setViewModel(GiaoDichListViewModel viewModel) {
        this.viewModel = viewModel;
        tableView.setItems(viewModel.fullList);
    }

    @FXML
    public void initialize() {
        // Thiết lập các cột cho tableView
        colMa.setCellValueFactory(new PropertyValueFactory<>("maGiaoDich"));
        colLoaiGiaoDich.setCellValueFactory(new PropertyValueFactory<>("loaiGD"));
        colLoai.setCellValueFactory(new PropertyValueFactory<>("loai"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colNgayGiaoDich.setCellValueFactory(new PropertyValueFactory<>("ngayGiaoDich"));
        colDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        colDienTich.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
        colThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

        try {
            SQLServerGiaoDichFindDAO timDAO = new SQLServerGiaoDichFindDAO();
            SQLServerGiaoDichDeleteDAO xoaDAO = new SQLServerGiaoDichDeleteDAO();
            if (viewModel == null) {
                viewModel = new GiaoDichListViewModel();
                // Có thể load dữ liệu toàn bộ vào viewModel ở đây nếu có method loadAll()
                // viewModel.loadAll(dao);
            }
            timController = new TimGiaoDichController(new FindGiaoDichUC(timDAO), viewModel);
            xoaController = new DeleteGiaoDichController(new DeleteGiaoDichUC(xoaDAO), viewModel);
            tableView.setItems(viewModel.fullList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTim() throws ClassNotFoundException, SQLException {
        String tuKhoa = txtField.getText().trim().toLowerCase();

        if (tuKhoa.isEmpty()) {
            tableView.setItems(viewModel.fullList);
            tableView.refresh();
            return;
        }

        timController.excute(tuKhoa);
        GiaoDichListViewItem item = timController.getGdModel().viewItem;

        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không tìm thấy kết quả cho từ khóa: " + tuKhoa);
            alert.showAndWait();

            tableView.setItems(viewModel.fullList);
            tableView.refresh();
            return;
        }

        ObservableList<GiaoDichListViewItem> foundList = FXCollections.observableArrayList();
        foundList.add(item);
        tableView.setItems(foundList);
    }

    // Đây là method bắt buộc của GDSubscriber, dùng để cập nhật dữ liệu hiển thị
    @Override
    public void show() {
        if (viewModel != null) {
            tableView.setItems(viewModel.fullList);
            tableView.refresh();
        }
    }

    @FXML
    private void handleXoa() throws SQLException, ClassNotFoundException {
    	// Lấy giao dịch được chọn trong bảng
    	GiaoDichListViewItem selectedItem = tableView.getSelectionModel().getSelectedItem();

        // Nếu chưa chọn gì thì thông báo
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn giao dịch để xóa.");
            alert.showAndWait();
            return;
        }

        // Xác nhận xóa
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Xác nhận xóa");
        confirm.setHeaderText(null);
        confirm.setContentText("Bạn có chắc chắn muốn xóa giao dịch \"" + selectedItem.getMaGiaoDich() + "\"?");
        confirm.showAndWait().ifPresent(response -> {
            if (response.getText().equalsIgnoreCase("OK")) {
                xoaController.xoaGiaoDich(selectedItem); // gọi presenter xử lý xóa
                tableView.getItems().remove(selectedItem); // xóa khỏi bảng
            }
        });
    }

    @FXML
    private void handleSua() throws ClassNotFoundException, SQLException {
        // Lấy giao dịch được chọn trong bảng
        GiaoDichListViewItem selectedItem = tableView.getSelectionModel().getSelectedItem();

        // Nếu chưa chọn gì thì thông báo
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn giao dịch để sửa.");
            alert.showAndWait();
            return;
        }

        String maGiaoDich = selectedItem.getMaGiaoDich();

        // Tạo/cấu hình các controller, model cần thiết để truyền vào OpenAndEditFormView
        OpenEditFormModel editFormModel = new OpenEditFormModel();
        GiaoDichEditModel giaoDichEditModel = new GiaoDichEditModel();

        // Đăng ký subscriber sau khi khởi tạo model
        giaoDichEditModel.registerSubscriber(new Subscriber() {
            @Override
            public void update() {
                GiaoDichEditItem itemDaSua = giaoDichEditModel.listItem;
                if (itemDaSua != null) {
                    for (int i = 0; i < viewModel.fullList.size(); i++) {
                        GiaoDichListViewItem item = viewModel.fullList.get(i);
                        if (item.getMaGiaoDich().equals(itemDaSua.getMaGiaoDich())) {
                            viewModel.fullList.set(i, convertToListView(itemDaSua));
                            break;
                        }
                    }
                    tableView.setItems(viewModel.fullList);
                    tableView.refresh();
                }
            }
        });

        OpenAndEditFormUseCase openAndEditFormUseCase = new OpenAndEditFormUseCase(new SQLServerOpenEditForm());
        OpenAndEditFormController editFormController = new OpenAndEditFormController(openAndEditFormUseCase, editFormModel);
        GiaoDichEditController giaoDichEditController = new GiaoDichEditController(giaoDichEditModel,
                new GiaoDichEditUC(new SQLServerGiaoDichEditDAO())
        );

        // Tạo view sửa và show form
        OpenAndEditFormView editFormView = new OpenAndEditFormView(editFormModel, editFormController, giaoDichEditController);
        editFormView.showDialog(maGiaoDich);

        // Không cần gọi setItems + refresh ở đây nữa, vì đã có callback trên giaoDichEditModel tự động cập nhật lại table khi sửa
    }
    private GiaoDichListViewItem convertToListView(GiaoDichEditItem itemDaSua) {
        GiaoDichListViewItem lvItem = new GiaoDichListViewItem();
        lvItem.setMaGiaoDich(itemDaSua.getMaGiaoDich());
        lvItem.setLoaiGD(itemDaSua.getLoai());
        lvItem.setDiaChi(itemDaSua.getDiaChi());
        lvItem.setNgayGiaoDich(itemDaSua.getNgayGiaoDich());
        lvItem.setDonGia(itemDaSua.getDonGia());
        lvItem.setDienTich(itemDaSua.getDienTich());
        lvItem.setLoai(itemDaSua.getLoaiNha() != null ? itemDaSua.getLoaiNha() : itemDaSua.getLoaiDat());
//        lvItem.setThanhTien(/* Nếu có công thức hoặc set từ itemDaSua */);
        // ... set các trường còn lại tương ứng
        return lvItem;
    }
}
