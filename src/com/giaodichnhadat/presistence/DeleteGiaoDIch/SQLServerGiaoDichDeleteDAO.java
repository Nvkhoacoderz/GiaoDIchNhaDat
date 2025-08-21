package com.giaodichnhadat.presistence.DeleteGiaoDIch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLServerGiaoDichDeleteDAO implements DeleteGiaoDichDAOGateway{
	private Connection conn;

	public SQLServerGiaoDichDeleteDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url ="jdbc:sqlserver://127.0.0.1:1433;databaseName=TeamForOne;TrustServerCertificate=True;";
		String username="sa";
		String password="Nguyenvukhoa544@";
		
		conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected!!!");
	}
	
	@Override
    public void xoaGiaoDich(String maGiaoDich) throws SQLException {
        // Câu lệnh xóa dữ liệu liên quan trong bảng DoanhThu trước
        String deleteDoanhThuSQL = "DELETE FROM DoanhThu WHERE MaGiaoDich = ?";
        String deleteGiaoDichSQL = "DELETE FROM GiaoDich WHERE MaGiaoDich = ?";

        // Dùng transaction để đảm bảo cả 2 câu lệnh đều thành công hoặc rollback
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(deleteDoanhThuSQL)) {
                stmt1.setString(1, maGiaoDich);
                int deletedDoanhThu = stmt1.executeUpdate();
                System.out.println("Đã xóa " + deletedDoanhThu + " bản ghi DoanhThu có MaGiaoDich = " + maGiaoDich);
            }

            try (PreparedStatement stmt2 = conn.prepareStatement(deleteGiaoDichSQL)) {
                stmt2.setString(1, maGiaoDich);
                int deletedGiaoDich = stmt2.executeUpdate();
                if (deletedGiaoDich == 0) {
                    System.out.println("Không tìm thấy giao dịch để xóa: " + maGiaoDich);
                } else {
                    System.out.println("Đã xóa thành công giao dịch: " + maGiaoDich);
                }
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            System.err.println("Lỗi khi xóa giao dịch: " + maGiaoDich);
            e.printStackTrace();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
