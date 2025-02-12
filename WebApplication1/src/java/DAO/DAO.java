/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void deleteProduct(String pid) {
        String query = """
                       delete from product
                       where product_id = ?""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String des, String bid, String price, String cateid) {
        String query = """
                       insert into product
                       (`name`, description, brand_id, price, category_id)
                       values (?, ?, ?, ?, ?)""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, des);
            ps.setString(3, bid);
            ps.setString(4, price);
            ps.setString(5, cateid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editProduct(String name, String des, String bid, String price, String cateid, String pid) {
        String query = """
                       update product
                       set [name] = ?,
                       description = ?,
                       brand_id = ?,
                       price = ?,
                       category_id = ?,
                       where product_id = ?
                       """;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, des);
            ps.setString(3, bid);
            ps.setString(4, price);
            ps.setString(5, cateid);
            ps.setString(6, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
