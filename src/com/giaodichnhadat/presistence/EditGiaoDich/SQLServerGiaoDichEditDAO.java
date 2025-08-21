package com.giaodichnhadat.presistence.EditGiaoDich;

import com.giaodichnhadat.presistence.GiaoDichDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLServerGiaoDichEditDAO implements GiaoDichDAOGateway {
    private final Connection conn;

    public SQLServerGiaoDichEditDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String username = "sa";
        String password = "Nguyenvukhoa544@";
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=TeamForOne;encrypt=true;trustServerCertificate=true";
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected!!!");
    }

    @Override
    public GiaoDichDTO editGiaoDich(GiaoDichDTO dto) throws SQLException {
        System.out.println("DAO nhận DTO: maGiaoDich=" + dto.getMaGiaoDich() +
                " ngayGiaoDich=" + dto.getNgayGiaoDich() +
                ", loaiGD=" + dto.getLoai() +
                ", donGia=" + dto.getDonGia() +
                ", dienTich=" + dto.getDienTich() +
                ", loaiNha=" + dto.getLoaiNha() +
                ", loaiDat=" + dto.getLoaiDat() +
                ", diaChi=" + dto.getDiaChi());
        String sql = """
        UPDATE GiaoDich
        SET NgayGiaoDich = ?, 
            LoaiGiaoDich = ?,
            DiaChi = ?, 
            DienTich = ?,
            DonGia = ?, 
            LoaiDat = ?, 
            LoaiNha = ?
        WHERE MaGiaoDich = ?
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Ngày giao dịch
            java.util.Date utilDate = dto.getNgayGiaoDich();
            java.sql.Date sqlDate = utilDate != null ? new java.sql.Date(utilDate.getTime()) : null;
            stmt.setDate(1, sqlDate);

            stmt.setDouble(5, dto.getDonGia());
            stmt.setString(2, dto.getLoai());
            stmt.setDouble(4, dto.getDienTich());
            stmt.setString(3, dto.getDiaChi());

            // Loại giao dịch xử lý cột loại đất / loại nhà
            if ("dat".equalsIgnoreCase(dto.getLoai())) {
                stmt.setString(6, dto.getLoaiDat());
                stmt.setNull(7, Types.NVARCHAR); // LoaiNha null
            } else if ("nha".equalsIgnoreCase(dto.getLoai())) {
                stmt.setNull(6, Types.CHAR); // LoaiDat null

                // Sửa đoạn này để map "Cao cấp"/"Thường" sang "cao_cap"/"thuong"
                String dbLoaiNha = null;
                if ("Cao cấp".equalsIgnoreCase(dto.getLoaiNha())) dbLoaiNha = "cao_cap";
                else if ("Thường".equalsIgnoreCase(dto.getLoaiNha())) dbLoaiNha = "thuong";
                else dbLoaiNha = dto.getLoaiNha(); // fallback nếu đã chuẩn

                stmt.setString(7, dbLoaiNha);
            } else {
                throw new SQLException("Loại giao dịch không hợp lệ: " + dto.getLoai());
            }

            stmt.setString(8, dto.getMaGiaoDich());

            // Quan trọng: dùng executeUpdate thay vì executeQuery
            stmt.executeUpdate();
        }

        // Trả lại bản ghi đã update
        return getGiaoDichByMa(dto.getMaGiaoDich());
    }

    @Override
    public GiaoDichDTO getGiaoDichByMa(String maGiaoDich) throws SQLException {
        GiaoDichDTO giaoDich = null;
        String sql = "SELECT * FROM GiaoDich WHERE MaGiaoDich = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maGiaoDich);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    giaoDich = new GiaoDichDTO();
                    giaoDich.setMaGiaoDich(rs.getString("MaGiaoDich"));
                    giaoDich.setNgayGiaoDich(rs.getDate("NgayGiaoDich"));
                    giaoDich.setLoai(rs.getString("LoaiGiaoDich"));
                    giaoDich.setDiaChi(rs.getString("DiaChi"));
                    giaoDich.setDienTich(rs.getDouble("DienTich"));
                    giaoDich.setDonGia(rs.getDouble("DonGia"));
                    giaoDich.setLoaiDat(rs.getString("LoaiDat"));
                    giaoDich.setLoaiNha(rs.getString("LoaiNha"));
                }
            }
        }
        return giaoDich;
    }
}
