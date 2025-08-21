package com.giaodichnhadat.presistence.FindGiaoDich;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.giaodichnhadat.presistence.GiaoDichDTO;

public class SQLServerGiaoDichFindDAO implements FindGiaoDichDAOGateway {
	private Connection conn;

	public SQLServerGiaoDichFindDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url ="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeamForOne;TrustServerCertificate=True;";
		String username="sa";
		String password="Nguyenvukhoa544@";
		
		conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected!!!");
	}
	
	public GiaoDichDTO timGiaoDich(String maGiaoDich) throws SQLException{
		GiaoDichDTO giaoDich = null;
		PreparedStatement stmt;
		
		String query = "select * from GiaoDich where MaGiaoDich = ?";
		
		ResultSet rs = null;
		
		stmt = conn.prepareStatement(query);
		stmt.setString(1, maGiaoDich);
		rs = stmt.executeQuery();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			while(rs.next()) {
				giaoDich = new GiaoDichDTO();
				giaoDich.setMaGiaoDich(rs.getString("MaGiaoDich"));
				giaoDich.setNgayGiaoDich(fmt.parse(rs.getString("NgayGiaoDich")));;
				giaoDich.setLoai(rs.getString("LoaiGiaoDich"));
				giaoDich.setDiaChi(rs.getString("DiaChi"));
				giaoDich.setDienTich(rs.getDouble("DienTich"));
				giaoDich.setDonGia(rs.getDouble("DonGia"));
				giaoDich.setLoaiDat(rs.getString("LoaiDat"));
				giaoDich.setLoaiNha(rs.getString("LoaiNha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return giaoDich;
	}
}
