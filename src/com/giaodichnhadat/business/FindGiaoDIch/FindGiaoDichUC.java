package com.giaodichnhadat.business.FindGiaoDIch;

import java.sql.SQLException;
import java.text.ParseException;

import com.giaodichnhadat.business.GiaoDich;
import com.giaodichnhadat.business.GiaoDichDat;
import com.giaodichnhadat.business.GiaoDichFactory;
import com.giaodichnhadat.business.GiaoDichNha;
import com.giaodichnhadat.presistence.GiaoDichDTO;
import com.giaodichnhadat.presistence.FindGiaoDich.FindGiaoDichDAOGateway;

public class FindGiaoDichUC {
	private FindGiaoDichDAOGateway giaoDichGateway;

	public FindGiaoDichUC(FindGiaoDichDAOGateway giaoDichGateway) {
		super();
		this.giaoDichGateway = giaoDichGateway;
	}
	
	public GiaoDichViewFindDTO excute(String maGiaoDich) throws SQLException, ParseException {
	    GiaoDich gd = null;
	    GiaoDichDTO dto = null;

	    try {
	        dto = giaoDichGateway.timGiaoDich(maGiaoDich);

	        if (dto == null) {
	            return null;  // ✅ KHÔNG xử lý tiếp nếu không tìm thấy
	        }

	        gd = this.convertToBusinessObject(dto);

	        if (gd == null) {
	            return null;  // ✅ Phòng trường hợp convert thất bại
	        }

	        return this.convertToViewModel(gd);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // ✅ Cẩn thận: trả về null nếu gặp lỗi
	    }
	}
	
	private GiaoDich convertToBusinessObject(GiaoDichDTO dto) {
		GiaoDich gd = null;
		
		gd = GiaoDichFactory.createGiaoDich(dto);
		
		return gd;
	}
	
	private GiaoDichViewFindDTO convertToViewModel(GiaoDich gd) {
		GiaoDichViewFindDTO dtoView = new GiaoDichViewFindDTO();
		dtoView.maGiaoDich = gd.getMaGiaoDich();
		dtoView.ngayGiaoDich = gd.getNgayGiaoDich();
		dtoView.donGia = gd.getDonGia();
		dtoView.dienTich = gd.getDienTich();
		dtoView.loai = gd.getLoai();
		dtoView.thanhTien = gd.thanhTien();
		if (gd instanceof GiaoDichNha) {
            dtoView.loaiGD = "nhà";
            dtoView.loai = ((GiaoDichNha) gd).getLoaiNha();  // lấy loại chi tiết nhà
            dtoView.diaChi = ((GiaoDichNha) gd).getDiaChi();
        } else if (gd instanceof GiaoDichDat) {
            dtoView.loaiGD = "đất";
            dtoView.loai = ((GiaoDichDat) gd).getLoaiDat();  // lấy loại chi tiết đất
        } else {
            dtoView.loaiGD = "";
            dtoView.loai = "";
            dtoView.diaChi = "";
        }
		
		return dtoView;
	}
}
