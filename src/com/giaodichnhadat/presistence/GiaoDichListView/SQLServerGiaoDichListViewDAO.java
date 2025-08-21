package com.giaodichnhadat.presistence.GiaoDichListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.giaodichnhadat.presistence.GiaoDichDTO;

public class SQLServerGiaoDichListViewDAO implements GiaoDichListViewDAOGateway{
	private Connection conn;

	public SQLServerGiaoDichListViewDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url ="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeamForOne;TrustServerCertificate=True;";
		String username="sa";
		String password="Nguyenvukhoa544@";
		
		conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected!!!");
	}
	@Override
	public List<GiaoDichDTO> layTatCaGiaoDich() throws SQLException, ParseException {
	    List<GiaoDichDTO> list = new ArrayList<>();
	    String query = "SELECT * FROM GiaoDich";
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	    try (Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            GiaoDichDTO gd = new GiaoDichDTO();
				gd.setMaGiaoDich(rs.getString("MaGiaoDich"));
				gd.setNgayGiaoDich(fmt.parse(rs.getString("NgayGiaoDich")));;
				gd.setLoai(rs.getString("LoaiGiaoDich"));
				gd.setDiaChi(rs.getString("DiaChi"));
				gd.setDienTich(rs.getDouble("DienTich"));
				gd.setDonGia(rs.getDouble("DonGia"));
				gd.setLoaiDat(rs.getString("LoaiDat"));
				gd.setLoaiNha(rs.getString("LoaiNha"));

	            // Gợi ý: tính thành tiền nếu muốn
	            try {
//	                double dienTich = Double.parseDouble(gd.dienTich);
//	                double donGia = Double.parseDouble(gd.donGia);
//	                double thanhTien = dienTich * donGia;
//	                gd.thanhTien = String.valueOf(thanhTien);
	            } catch (Exception e) {
//	                gd.thanhTien = "Lỗi";
	            }

	            list.add(gd);
	        }
	    }

	    return list;
	}
}
