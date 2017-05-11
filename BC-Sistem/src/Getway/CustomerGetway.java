
package Getway;

import DAL.Customer;
import List.ListCustomer;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerGetway {

    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into Customer values(?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, customer.customerName);
            pst.setString(3, customer.customerAddress);
            pst.setString(4, customer.customerNpwp);
            pst.setString(5, customer.customerSkep);
            pst.setString(6, customer.userId);
            pst.setString(7, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            
            Alert alert = new Alert(AlertType.INFORMATION);
           	alert.setContentText("Customer" + customer.customerName + "berhasil di simpan");
         	alert.showAndWait();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Customer");
            rs = pst.executeQuery();
            while (rs.next()) {
                customer.id = rs.getString(1);
                customer.customerName = rs.getString(2);
                customer.customerAddress = rs.getString(3);
                customer.customerNpwp = rs.getString(4);
                customer.customerSkep = rs.getString(5);
                customer.userId = rs.getString(6);
                customer.date = rs.getString(7);
                customer.userName = sql.getName(customer.userId, customer.userName, "User");
                customer.customerList.addAll(new ListCustomer(customer.id, customer.customerName, customer.customerAddress, customer.customerNpwp, customer.customerSkep, customer.userName, customer.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedView(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Customer where Id=?");
            pst.setString(1, customer.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                customer.customerName = rs.getString(2);
                customer.customerAddress = rs.getString(3);
                customer.customerNpwp = rs.getString(4);
                customer.customerSkep = rs.getString(5);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchView(Customer customer) {
        con = dbCon.geConnection();
        customer.customerList.clear();
        try {
            pst = con.prepareStatement("select * from Customer where CustomerName like ? or CustomerAddress like ?");
            pst.setString(1, "%" + customer.customerName + "%");
            pst.setString(2, "%" + customer.customerName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                customer.id = rs.getString(1);
                customer.customerName = rs.getString(2);
                customer.customerAddress = rs.getString(3);
                customer.customerNpwp = rs.getString(4);
                customer.customerSkep = rs.getString(5);
                customer.userId = rs.getString(6);
                customer.date = rs.getString(7);
                customer.userName = sql.getName(customer.userId, customer.userName, "User");
                customer.customerList.addAll(new ListCustomer(customer.id, customer.customerName, customer.customerAddress, customer.customerNpwp, customer.customerSkep, customer.userName, customer.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("UPDATE Customer set CustomerName=?,CustomerAddress=?,CustomerNpwp=?, CustomerSkep=?  where Id=?");
            pst.setString(1, customer.customerName);
            pst.setString(2, customer.customerAddress);
            pst.setString(3, customer.customerNpwp);
            pst.setString(4, customer.customerSkep);
            pst.setString(5, customer.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Update");
         	alert.setContentText("Data Berhasil di Update");
         	alert.showAndWait();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Customer customer) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from Customer where id=?");
            pst.setString(1, customer.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}
