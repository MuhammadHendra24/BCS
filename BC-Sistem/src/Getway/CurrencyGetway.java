
package Getway;

import DAL.Currency;
import DAL.Customer;
import DAL.ItemMaster;
import DAL.SortDoc;
import DAL.Vendor;
import List.ListCurrency;
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


public class CurrencyGetway {

    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(Currency curency) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into Currency values(?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, curency.currency);
            pst.setString(3, curency.currencyDescription);
            pst.setString(4, curency.userId);
            pst.setString(5, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            
            Alert alert = new Alert(AlertType.INFORMATION);
           	alert.setContentText("Currency" + curency.currency + " berhasil di simpan ");
         	alert.showAndWait();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Currency curency) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Currency");
            rs = pst.executeQuery();
            while (rs.next()) {
                curency.id = rs.getString(1);
                curency.currency = rs.getString(2);
                curency.currencyDescription = rs.getString(3);
                curency.userId = rs.getString(4);
                curency.date = rs.getString(5);
                curency.userName = sql.getName(curency.userId, curency.userName, "User");
                curency.currencyList.addAll(new ListCurrency(curency.id, curency.currency, curency.currencyDescription, curency.userName, curency.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedView(Currency curency) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Currency where Id=?");
            pst.setString(1, curency.id);
            rs = pst.executeQuery();
            while (rs.next()) {
     	        curency.currency = rs.getString(2);
                curency.currencyDescription = rs.getString(3);
                }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchView(Currency curency) {
        con = dbCon.geConnection();
        curency.currencyList.clear();
        try {
            pst = con.prepareStatement("select * from Currency where Curency like ? or CurencyDescription like ?");
            pst.setString(1, "%" + curency.currency + "%");
            pst.setString(2, "%" + curency.currency + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                curency.id = rs.getString(1);
                curency.currency = rs.getString(2);
                curency.currencyDescription= rs.getString(3);
                curency.userId = rs.getString(4);
                curency.date = rs.getString(5);
                curency.userName = sql.getName( curency.userId,  curency.userName, "User");
                curency.currencyList.addAll(new ListCurrency(curency.id, curency.currency, curency.currencyDescription, curency.userName, curency.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Currency curency) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("UPDATE Currency SET Curency=?,CurencyDescription=? where Id=?");
            pst.setString(1, curency.currency);
            pst.setString(2, curency.currencyDescription);
            pst.setString(3, curency.id);
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

    public void delete(Currency curency) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from Currency where id=?");
            pst.setString(1, curency.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
}
