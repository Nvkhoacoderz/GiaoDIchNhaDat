package com.giaodichnhadat.presistence.FindGiaoDich;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.giaodichnhadat.presistence.GiaoDichDTO;

public interface FindGiaoDichDAOGateway {
	GiaoDichDTO timGiaoDich(String maGiaoDich) throws SQLException;
}
