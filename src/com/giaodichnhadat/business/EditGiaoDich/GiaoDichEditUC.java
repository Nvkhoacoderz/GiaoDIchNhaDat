package com.giaodichnhadat.business.EditGiaoDich;

import com.giaodichnhadat.business.GiaoDich;
import com.giaodichnhadat.business.GiaoDichDat;
import com.giaodichnhadat.business.GiaoDichFactory;
import com.giaodichnhadat.business.GiaoDichNha;
import com.giaodichnhadat.presistence.EditGiaoDich.GiaoDichDAOGateway;
import com.giaodichnhadat.presistence.GiaoDichDTO;

import java.sql.SQLException;

public class GiaoDichEditUC {
    private GiaoDichDAOGateway giaoDichDAOGateway;

    public GiaoDichEditUC(GiaoDichDAOGateway giaoDichDAOGateway) {
        this.giaoDichDAOGateway = giaoDichDAOGateway;
    }

    public GiaoDichEditDTO editGD(GiaoDichEditDTO giaoDichEditDTO) throws SQLException
    {
        System.out.println("UC nhận DTO: maGiaoDich=" + giaoDichEditDTO.maGiaoDich +
                ", loaiGD=" + giaoDichEditDTO.loaiGD +
                ", donGia=" + giaoDichEditDTO.donGia +
                ", dienTich=" + giaoDichEditDTO.dienTich +
                ", loaiNha=" + giaoDichEditDTO.loaiNha +
                ", loaiDat=" + giaoDichEditDTO.loaiDat +
                ", diaChi=" + giaoDichEditDTO.diaChi);
        GiaoDichDTO editDTO1 = convertToGiaoDichDTO(giaoDichEditDTO);
        GiaoDichDTO editDTO = giaoDichDAOGateway.editGiaoDich(editDTO1);
        GiaoDich giaoDich = convertToBusinessObjects(editDTO);
        return convertToViewDTO(giaoDich);
    }

    public GiaoDichEditDTO getGiaoDichByMa(String maGiaoDich) throws SQLException {
        GiaoDichDTO editDTO = giaoDichDAOGateway.getGiaoDichByMa(maGiaoDich);
        GiaoDich giaoDich = convertToBusinessObjects(editDTO);
        return convertToViewDTO(giaoDich);
    }

    private GiaoDichDTO convertToGiaoDichDTO(GiaoDichEditDTO editDTO) {
        GiaoDichDTO dto = new GiaoDichDTO();
        dto.setMaGiaoDich(editDTO.maGiaoDich);
        dto.setNgayGiaoDich(editDTO.ngayGiaoDich);
        dto.setDonGia(editDTO.donGia);
        dto.setLoai(editDTO.loaiGD);
        dto.setDienTich(editDTO.dienTich);
        dto.setDiaChi(editDTO.diaChi);
        dto.setLoaiNha(editDTO.loaiNha);
        dto.setLoaiDat(editDTO.loaiDat);
        return dto;
    }

    private GiaoDich convertToBusinessObjects(GiaoDichDTO dtos) {
        GiaoDich giaoDich = null;
        giaoDich = GiaoDichFactory.createGiaoDich(dtos);
        return giaoDich;
    }

    private GiaoDichEditDTO convertToViewDTO(GiaoDich st) {
        GiaoDichEditDTO dto = new GiaoDichEditDTO();
        dto.maGiaoDich = st.getMaGiaoDich();
        dto.ngayGiaoDich = st.getNgayGiaoDich();
        dto.donGia = st.getDonGia();
        dto.loaiGD = st.getLoai();
        dto.dienTich = st.getDienTich();
        if (st instanceof GiaoDichNha) {
            dto.loaiNha = ((GiaoDichNha) st).getLoaiNha();  // lấy loại chi tiết nhà
            dto.diaChi = ((GiaoDichNha) st).getDiaChi();
        } else if (st instanceof GiaoDichDat) {
            dto.loaiDat = ((GiaoDichDat) st).getLoaiDat();  // lấy loại chi tiết đất
        } else {
            dto.loaiNha = "";
            dto.loaiDat = "";
        }
        return dto;
    }

}
