
package Getway;



import DAL.Currency;
import DAL.SortDoc;
import List.ListCurrency;
import List.ListSortDoc;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortDocGetway {

	SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(SortDoc sortDoc) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into SortDocumen values(?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, sortDoc.sortDocName);
            pst.setString(3, sortDoc.sortDocDescription);
            pst.setString(4, sortDoc.userId);
            pst.setString(5, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
           	alert.setContentText("Sordokument" + sortDoc.sortDocName + "berhasil di simpan");
         	alert.showAndWait();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void view(SortDoc sortDoc) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from SortDocumen");
            rs = pst.executeQuery();
            while (rs.next()) {
                sortDoc.id = rs.getString(1);
                sortDoc.sortDocName = rs.getString(2);
                sortDoc.sortDocDescription = rs.getString(3);
                sortDoc.userId = rs.getString(4);
                sortDoc.date = rs.getString(5);
                sortDoc.userName = sql.getName(sortDoc.userId, sortDoc.userName, "User");
                sortDoc.ListSortDetail.addAll(new ListSortDoc(sortDoc.id, sortDoc.sortDocName, sortDoc.sortDocDescription, sortDoc.userName, sortDoc.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedView(SortDoc sortDoc) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from SortDocumen where Id=?");
            pst.setString(1, sortDoc.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                sortDoc.sortDocName = rs.getString(2);
                sortDoc.sortDocDescription = rs.getString(3);
                }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchView(SortDoc sortDoc) {
        con = dbCon.geConnection();
        sortDoc.ListSortDetail.clear();
        try {
            pst = con.prepareStatement("select * from SortDocumen where SortDocName like ? or SortDocDescription like ?");
            pst.setString(1, "%" + sortDoc.sortDocName + "%");
            pst.setString(2, "%" + sortDoc.sortDocName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                sortDoc.id = rs.getString(1);
                sortDoc.sortDocName = rs.getString(2);
                sortDoc.sortDocDescription= rs.getString(3);
                sortDoc.userId = rs.getString(4);
                sortDoc.date = rs.getString(5);
                sortDoc.userName = sql.getName( sortDoc.userId,  sortDoc.userName, "User");
                sortDoc.ListSortDetail.addAll(new ListSortDoc(sortDoc.id, sortDoc.sortDocName, sortDoc.sortDocDescription, sortDoc.userName, sortDoc.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SortDoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(SortDoc sortDoc) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("UPDATE SortDocumen SET SortDocName=?,SortDocDescription=? where Id=?");
            pst.setString(1, sortDoc.sortDocName);
            pst.setString(2, sortDoc.sortDocDescription);
            pst.setString(3, sortDoc.id);
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

    public void delete(SortDoc sortDoc) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from SortDocumen where id=?");
            pst.setString(1, sortDoc.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
}
