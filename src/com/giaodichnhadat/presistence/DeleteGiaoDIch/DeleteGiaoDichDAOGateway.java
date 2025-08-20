package com.giaodichnhadat.presistence.DeleteGiaoDIch;

import java.sql.SQLException;

public interface DeleteGiaoDichDAOGateway {
	void xoaGiaoDich(String maGiaoDich) throws SQLException;
}
