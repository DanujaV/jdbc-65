package lk.ijse.thogakade.model;

/*
    @author DanujaV
    @created 3/13/23 - 11:48 AM   
*/

import lk.ijse.thogakade.dto.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ItemModel {
    private final static String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }
    public static boolean save(String code, String description, double unitPrice, int qtyOnHand) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Item(code, description, unitPrice, qtyOnHand) VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            pstm.setString(2, description);
            pstm.setDouble(3, unitPrice);
            pstm.setInt(4, qtyOnHand);

            int affectedRows = pstm.executeUpdate();
            if(affectedRows > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /*public static boolean update(String code, String description, double unitPrice, int qtyOnHand) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, description);
            pstm.setDouble(2, unitPrice);
            pstm.setInt(3, qtyOnHand);
            pstm.setString(4, code);

            return pstm.executeUpdate() > 0;
        }

    }*/
    public static boolean update(Item item) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, item.getDescription());
            pstm.setDouble(2, item.getUnitPrice());
            pstm.setInt(3, item.getQtyOnHand());
            pstm.setString(4, item.getCode());

            return pstm.executeUpdate() > 0;
        }

    }
}