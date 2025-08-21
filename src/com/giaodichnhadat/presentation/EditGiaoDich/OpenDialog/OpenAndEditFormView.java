package com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog;

import com.giaodichnhadat.business.EditGiaoDich.GiaoDichEditDTO;
import com.giaodichnhadat.presentation.EditGiaoDich.GiaoDichEditController;
import com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog.ChildrenTypeItem;
import com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog.OpenAndEditFormController;
import com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog.OpenEditFormModel;
import com.giaodichnhadat.presentation.EditGiaoDich.OpenDialog.ParentTypeItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenAndEditFormView {

    private OpenEditFormModel model;
    private OpenAndEditFormController controller;
    private GiaoDichEditController giaoDichEditController;

    public OpenAndEditFormView(OpenEditFormModel model, OpenAndEditFormController controller, GiaoDichEditController giaoDichEditController) {
        this.model = model;
        this.controller = controller;
        this.giaoDichEditController = giaoDichEditController;
    }

    // Sửa lại: chỉ truyền vào mã giao dịch, không trả về gì
    public void showDialog(String maGiaoDich) {
        Stage stage = new Stage();
        stage.setTitle("Sửa giao dịch");
        stage.initModality(Modality.APPLICATION_MODAL);

        double fieldWidth = 420;
        Font labelFont = Font.font("Segoe UI", FontWeight.BOLD, 16);

        // 1. Lấy dữ liệu cũ
        GiaoDichEditDTO oldDto = null;
        try {
            oldDto = giaoDichEditController.findByMaGiaoDich(maGiaoDich);
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Không lấy được dữ liệu giao dịch: " + ex.getMessage()).showAndWait();
            return;
        }

        // 2. Map hiển thị <-> giá trị logic
        Map<String, String> displayToValue = new LinkedHashMap<>();
        displayToValue.put("Nhà", "nha");
        displayToValue.put("Đất", "dat");
        Map<String, String> valueToDisplay = new LinkedHashMap<>();
        valueToDisplay.put("nha", "Nhà");
        valueToDisplay.put("dat", "Đất");

        // 3. Trường nhập liệu chung (set giá trị mặc định từ oldDto)
        Label lblMaGD = new Label("Mã giao dịch:"); lblMaGD.setFont(labelFont);
        TextField txtMaGiaoDich = new TextField(maGiaoDich);
        txtMaGiaoDich.setEditable(false); txtMaGiaoDich.setPrefWidth(fieldWidth);

        Label lblNgayGD = new Label("Ngày giao dịch:"); lblNgayGD.setFont(labelFont);
        DatePicker dateNgayGiaoDich = new DatePicker();
        dateNgayGiaoDich.setPrefWidth(fieldWidth);
        if (oldDto != null && oldDto.ngayGiaoDich != null) {
            LocalDate localDate;
            if (oldDto.ngayGiaoDich instanceof java.sql.Date) {
                localDate = ((java.sql.Date) oldDto.ngayGiaoDich).toLocalDate();
            } else {
                localDate = oldDto.ngayGiaoDich.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
            dateNgayGiaoDich.setValue(localDate);
        }

        Label lblDonGia = new Label("Đơn giá:"); lblDonGia.setFont(labelFont);
        TextField txtDonGia = new TextField(oldDto != null ? String.valueOf(oldDto.donGia) : "");
        txtDonGia.setPrefWidth(fieldWidth);

        Label lblLoaiGD = new Label("Loại giao dịch:"); lblLoaiGD.setFont(labelFont);
        ComboBox<String> comboLoaiGiaoDich = new ComboBox<>();
        String loaiDisplay = "Nhà";
        if (oldDto != null && oldDto.loaiGD != null) {
            loaiDisplay = valueToDisplay.getOrDefault(oldDto.loaiGD, "Nhà");
        }
        comboLoaiGiaoDich.getItems().addAll(displayToValue.keySet());
        comboLoaiGiaoDich.setValue(loaiDisplay);
        comboLoaiGiaoDich.setPrefWidth(fieldWidth);

        // FIX: Đảm bảo ComboBox luôn có giá trị mặc định
        if (comboLoaiGiaoDich.getValue() == null && !comboLoaiGiaoDich.getItems().isEmpty()) {
            comboLoaiGiaoDich.setValue(comboLoaiGiaoDich.getItems().get(0));
        }

        System.out.println("ComboBox value: " + comboLoaiGiaoDich.getValue());
        System.out.println("Mapped value: " + displayToValue.get(comboLoaiGiaoDich.getValue()));


        Label lblDienTich = new Label("Diện tích:"); lblDienTich.setFont(labelFont);
        TextField txtDienTich = new TextField(oldDto != null ? String.valueOf(oldDto.dienTich) : "");
        txtDienTich.setPrefWidth(fieldWidth);

        // Combo loại con
        Label lblLoaiCon = new Label("Loại nhà:"); lblLoaiCon.setFont(labelFont);
        ComboBox<ChildrenTypeItem> comboLoaiCon = new ComboBox<>();
        comboLoaiCon.setPrefWidth(fieldWidth);

        comboLoaiCon.setCellFactory(lv -> new ListCell<ChildrenTypeItem>() {
            @Override
            protected void updateItem(ChildrenTypeItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.name);
            }
        });
        comboLoaiCon.setButtonCell(new ListCell<ChildrenTypeItem>() {
            @Override
            protected void updateItem(ChildrenTypeItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.name);
            }
        });

        Label lblDiaChi = new Label("Địa chỉ:"); lblDiaChi.setFont(labelFont);
        TextField txtDiaChi = new TextField(oldDto != null ? oldDto.diaChi : "");
        txtDiaChi.setPrefWidth(fieldWidth);

        // --- GridPane layout ---
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(28, 38, 12, 38));
        grid.setHgap(20); grid.setVgap(22);

        int row = 0;
        grid.add(lblMaGD, 0, row); grid.add(txtMaGiaoDich, 1, row++);
        grid.add(lblNgayGD, 0, row); grid.add(dateNgayGiaoDich, 1, row++);
        grid.add(lblDonGia, 0, row); grid.add(txtDonGia, 1, row++);
        grid.add(lblLoaiGD, 0, row); grid.add(comboLoaiGiaoDich, 1, row++);
        grid.add(lblLoaiCon, 0, row); grid.add(comboLoaiCon, 1, row++);
        grid.add(lblDiaChi, 0, row); grid.add(txtDiaChi, 1, row++);
        grid.add(lblDienTich, 0, row); grid.add(txtDienTich, 1, row++);

        // --- Load loại cha khi mở form ---
        controller.execute();

        comboLoaiGiaoDich.fireEvent(new javafx.event.ActionEvent());


        comboLoaiGiaoDich.setOnAction(ev -> {
            String selectedDisplay = comboLoaiGiaoDich.getValue();
            String parentId = null;
            if (model.parentTypeItems != null) {
                for (ParentTypeItem item : model.parentTypeItems) {
                    if (item.name.equals(selectedDisplay)) {
                        parentId = item.id;
                        break;
                    }
                }
            }
            if (parentId != null) {
                controller.executes(parentId);
            }

            boolean isNha = "Nhà".equals(selectedDisplay);
            lblLoaiCon.setText(isNha ? "Loại nhà:" : "Loại đất:");
            txtDiaChi.setVisible(isNha);
            txtDiaChi.setManaged(isNha);
            lblDiaChi.setVisible(isNha);
            lblDiaChi.setManaged(isNha);
        });

        GiaoDichEditDTO finalOldDto = oldDto;
        model.registerSubscriber(() -> {
            comboLoaiCon.getItems().clear();
            if (model.childrenTypeItems != null) {
                comboLoaiCon.getItems().addAll(model.childrenTypeItems);
                // Set lại giá trị loại con từ oldDto
                ChildrenTypeItem selectedItem = null;
                if (finalOldDto != null) {
                    String loaiGDValue = displayToValue.get(comboLoaiGiaoDich.getValue());
                    if ("dat".equals(loaiGDValue) && finalOldDto.loaiDat != null) {
                        for (ChildrenTypeItem item : model.childrenTypeItems) {
                            if (item.name.equals(finalOldDto.loaiDat)) {
                                selectedItem = item;
                                break;
                            }
                        }
                    } else if ("nha".equals(loaiGDValue) && finalOldDto.loaiNha != null) {
                        for (ChildrenTypeItem item : model.childrenTypeItems) {
                            if (
                                    ("cao_cap".equals(finalOldDto.loaiNha) && "Cao cấp".equals(item.name)) ||
                                            ("thuong".equals(finalOldDto.loaiNha) && "Thường".equals(item.name)) ||
                                            (finalOldDto.loaiNha.equals(item.name))
                            ) {
                                selectedItem = item;
                                break;
                            }
                        }
                    }
                }
                comboLoaiCon.setValue(selectedItem != null ? selectedItem : model.childrenTypeItems.get(0));
            }
        });

        // --- Nút ---
        Button btnLuu = new Button("Lưu");
        Button btnThoat = new Button("Thoát");
        btnLuu.setPrefWidth(120);
        btnThoat.setPrefWidth(120);
        HBox buttonBox = new HBox(32, btnLuu, btnThoat);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(34, 0, 28, 0));

        VBox root = new VBox(grid, buttonBox);
        root.setStyle("-fx-background-color: #f7f7fa;");

        Scene scene = new Scene(root, 820, 470);
        stage.setScene(scene);
        System.out.println("ComboBox value just before save: " + comboLoaiGiaoDich.getValue());
        System.out.println("ComboBox items: " + comboLoaiGiaoDich.getItems());

        btnLuu.setOnAction(e -> {
            try {
                String displayLoaiGD = comboLoaiGiaoDich.getValue();
                String loaiGD = displayToValue.get(displayLoaiGD);

                System.out.println("displayLoaiGD: " + displayLoaiGD);
                if (displayLoaiGD == null || displayLoaiGD.isEmpty() || loaiGD == null) {
                    new Alert(Alert.AlertType.ERROR, "Vui lòng chọn loại giao dịch hợp lệ!").showAndWait();
                    return;
                }

                GiaoDichEditDTO dto = new GiaoDichEditDTO();
                dto.maGiaoDich = txtMaGiaoDich.getText();
                LocalDate localDate = dateNgayGiaoDich.getValue();
                Date utilDate = localDate != null ? Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
                dto.ngayGiaoDich = utilDate;
                dto.donGia = Double.parseDouble(txtDonGia.getText());
                dto.loaiGD = loaiGD;
                dto.dienTich = Double.parseDouble(txtDienTich.getText());

                ChildrenTypeItem selected = comboLoaiCon.getValue();
                if ("dat".equals(loaiGD)) {
                    dto.loaiDat = selected != null ? selected.name : null;
                    dto.loaiNha = null;
                    dto.diaChi = null;
                } else if ("nha".equals(loaiGD)) {
                    if (selected != null) {
                        if ("Cao cấp".equals(selected.name)) dto.loaiNha = "cao_cap";
                        else if ("Thường".equals(selected.name)) dto.loaiNha = "thuong";
                        else dto.loaiNha = selected.name;
                    } else {
                        dto.loaiNha = null;
                    }
                    dto.diaChi = txtDiaChi.getText();
                    dto.loaiDat = null;
                }
                System.out.println("ComboBox value: " + comboLoaiGiaoDich.getValue());
                System.out.println("Mapped value: " + displayToValue.get(comboLoaiGiaoDich.getValue()));
                System.out.println("ComboBox value just before save: " + comboLoaiGiaoDich.getValue());
                System.out.println("ComboBox items: " + comboLoaiGiaoDich.getItems());
                giaoDichEditController.editGD(dto);
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Lỗi nhập liệu hoặc lưu: " + ex.getMessage()).showAndWait();
            }
        });
        btnThoat.setOnAction(e -> { stage.close(); });

        comboLoaiGiaoDich.fireEvent(new javafx.event.ActionEvent());
        stage.showAndWait();
    }
}