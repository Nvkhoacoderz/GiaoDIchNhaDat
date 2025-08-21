package com.giaodichnhadat.presentation.EditGiaoDich;

import com.giaodichnhadat.business.EditGiaoDich.GiaoDichEditDTO;
import com.giaodichnhadat.business.EditGiaoDich.GiaoDichEditUC;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class GiaoDichEditController {
    private GiaoDichEditModel giaoDichEditModel;
    private GiaoDichEditUC giaoDichEditUC;

    public GiaoDichEditController(GiaoDichEditModel giaoDichEditModel, GiaoDichEditUC giaoDichEditUC) {
        this.giaoDichEditModel = giaoDichEditModel;
        this.giaoDichEditUC = giaoDichEditUC;
    }

    private GiaoDichEditItem convertToPresent(GiaoDichEditDTO dto){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        GiaoDichEditItem item = new GiaoDichEditItem();
        item.setMaGiaoDich(dto.maGiaoDich);
        item.setNgayGiaoDich(dto.ngayGiaoDich.toString());
        item.setDonGia(String.format("%.2f", dto.donGia));
        item.setLoai(dto.loaiGD);
        item.setDiaChi(dto.diaChi);
        item.setDienTich(String.format("%.2f", dto.dienTich));
        item.setLoaiNha(dto.loaiNha);
        item.setLoaiDat(dto.loaiDat);
        return item;
    }

    public void editGD(GiaoDichEditDTO giaoDichEditDTO) throws SQLException {
        System.out.println("Controller nhận DTO: maGiaoDich=" + giaoDichEditDTO.maGiaoDich +
                ", loaiGD=" + giaoDichEditDTO.loaiGD +
                ", donGia=" + giaoDichEditDTO.donGia +
                ", dienTich=" + giaoDichEditDTO.dienTich +
                ", loaiNha=" + giaoDichEditDTO.loaiNha +
                ", loaiDat=" + giaoDichEditDTO.loaiDat +
                ", diaChi=" + giaoDichEditDTO.diaChi);
        GiaoDichEditDTO giaoDichEditDTOs = giaoDichEditUC.editGD(giaoDichEditDTO);
        GiaoDichEditItem giaoDichEditItems = convertToPresent(giaoDichEditDTOs);
        giaoDichEditModel.listItem = giaoDichEditItems; // gửi thông đi
        giaoDichEditModel.notifySubscribers(); // thông báo đến subscribers
    }

    public GiaoDichEditDTO findByMaGiaoDich(String maGiaoDich) throws SQLException {
        GiaoDichEditDTO giaoDichEditDTO = giaoDichEditUC.getGiaoDichByMa(maGiaoDich);
        if (giaoDichEditDTO != null) {
            GiaoDichEditItem giaoDichEditItem = convertToPresent(giaoDichEditDTO);
            giaoDichEditModel.listItem = giaoDichEditItem; // gửi thông điệp
            giaoDichEditModel.notifySubscribers(); // thông báo đến subscribers
        } else {
            // Xử lý trường hợp không tìm thấy giao dịch
            giaoDichEditModel.listItem = null;
            giaoDichEditModel.notifySubscribers();
        }
        return giaoDichEditDTO;
    }
}
