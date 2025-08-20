package com.giaodichnhadat.presistence.GiaoDichListView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.giaodichnhadat.presistence.GiaoDichDTO;

public interface GiaoDichListViewDAOGateway {
	List<GiaoDichDTO> layTatCaGiaoDich() throws SQLException, ParseException;
}
