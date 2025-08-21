package com.giaodichnhadat.presistence.EditGiaoDich.OpenDialog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLServerOpenEditForm implements OpenEditFormGateway {
    private Connection conn;

    public SQLServerOpenEditForm() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String username = "sa";
        String password = "Nguyenvukhoa544@";
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=TeamForOne;encrypt=true;trustServerCertificate=true";
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected!!!");
    }

    public List<ParentTypeDTO> getAll(){
        List<ParentTypeDTO> list = new ArrayList<ParentTypeDTO>();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from ParentType";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                ParentTypeDTO dto = new ParentTypeDTO();
                dto.id = rs.getInt("Id");
                dto.name = rs.getString("name");
                dto.description = rs.getString("Description");
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
//                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<ChildrenTypeDTO> getAll(String parentTypeId) {
        List<ChildrenTypeDTO> list = new ArrayList<ChildrenTypeDTO>();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from ChildrenType where ParentTypeId = '" + parentTypeId + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                ChildrenTypeDTO dto = new ChildrenTypeDTO();
                dto.id = rs.getInt("Id");
                dto.name = rs.getString("Name");
                dto.description = rs.getString("Description");
                dto.parentId = rs.getInt("ParentTypeId");
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
//                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
