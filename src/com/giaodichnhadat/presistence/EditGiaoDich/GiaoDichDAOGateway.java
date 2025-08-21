package com.giaodichnhadat.presistence.EditGiaoDich;

import com.giaodichnhadat.presistence.GiaoDichDTO;

import java.sql.SQLException;
import java.util.List;

public interface GiaoDichDAOGateway {
    GiaoDichDTO editGiaoDich(GiaoDichDTO giaoDichDTO) throws SQLException;
    GiaoDichDTO getGiaoDichByMa(String maGiaoDich) throws SQLException;
}
