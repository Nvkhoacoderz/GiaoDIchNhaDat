package com.giaodichnhadat.business.DeleteGiaoDich;

import java.sql.SQLException;

import com.giaodichnhadat.presistence.DeleteGiaoDIch.DeleteGiaoDichDAOGateway;

public class DeleteGiaoDichUC {
	private DeleteGiaoDichDAOGateway gateway;

	public DeleteGiaoDichUC(DeleteGiaoDichDAOGateway gateway) {
		super();
		this.gateway = gateway;
	}
	
	public void xoa(String maGiaoDich) {
		try {
			gateway.xoaGiaoDich(maGiaoDich);
	    } catch (SQLException e) {
	        e.printStackTrace(); // hoặc log hoặc ném ra ngoại lệ tùy yêu cầu
	        // Gợi ý: bạn có thể ném ra một exception "mềm" cho tầng cao hơn
	        throw new RuntimeException("Lỗi khi xóa giao dịch: " + e.getMessage(), e);
	    }
    }
}
