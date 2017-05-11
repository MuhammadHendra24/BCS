
package Getway;

import DAL.ItemMaster;
import List.ListItem;
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


public class ItemMasterGateway {

    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(ItemMaster itemMaster) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into ItemMaster values(?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, itemMaster.itemCode);
            pst.setString(3, itemMaster.itemDescription);
            pst.setString(4, itemMaster.um);
            pst.setString(5, itemMaster.userId);
            pst.setString(6, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
           	alert.setContentText("ItemMantaenance" + itemMaster.itemCode + "berhasil di simpan");
         	alert.showAndWait();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(ItemMaster itemMaster) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from ItemMaster");
            rs = pst.executeQuery();
            while (rs.next()) {
                itemMaster.id = rs.getString(1);
                itemMaster.itemCode = rs.getString(2);
                itemMaster.itemDescription = rs.getString(3);
                itemMaster.um = rs.getString(4);
                itemMaster.userId = rs.getString(5);
                itemMaster.date = rs.getString(6);
                itemMaster.userName = sql.getName(itemMaster.userId, itemMaster.userName, "User");
                itemMaster.itemList.addAll(new ListItem(itemMaster.id, itemMaster.itemCode, itemMaster.itemDescription, itemMaster.um, itemMaster.userName, itemMaster.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedView(ItemMaster itemMaster) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from ItemMaster where Id=?");
            pst.setString(1, itemMaster.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                itemMaster.itemCode = rs.getString(2);
                itemMaster.itemDescription = rs.getString(3);
                itemMaster.um = rs.getString(4);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchView(ItemMaster itemMaster) {
        con = dbCon.geConnection();
        itemMaster.itemList.clear();
        try {
            pst = con.prepareStatement("select * from ItemMaster where ItemCode like ? or ItemDescription like ?");
            pst.setString(1, "%" + itemMaster.itemCode + "%");
            pst.setString(2, "%" + itemMaster.itemCode + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                itemMaster.id = rs.getString(1);
                itemMaster.itemCode = rs.getString(2);
                itemMaster.itemDescription = rs.getString(3);
                itemMaster.um = rs.getString(4);
                itemMaster.userId = rs.getString(5);
                itemMaster.date = rs.getString(6);
                itemMaster.userName = sql.getName(itemMaster.userId, itemMaster.userName, "User");
                itemMaster.itemList.addAll(new ListItem(itemMaster.id, itemMaster.itemCode, itemMaster.itemDescription, itemMaster.um, itemMaster.userName, itemMaster.date));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update(ItemMaster itemMaster) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("UPDATE ItemMaster set ItemCode=?,ItemDescription=?, Um=? where Id=? ");
            pst.setString(1, itemMaster.itemCode);
            pst.setString(2, itemMaster.itemDescription);
            pst.setString(3, itemMaster.um);
            pst.setString(4, itemMaster.id);
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

    public void delete(ItemMaster itemMaster) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from ItemMaster where id=?");
            pst.setString(1, itemMaster.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //private boolean isNotUsed(ItemMaster item) {
      //  con = dbCon.geConnection();
        //boolean isNotUsed = false;
       // try {
           // pst = con.prepareStatement("select * from Sells where CustomerId=?");
          //  pst.setString(1, item.id);
          //  rs = pst.executeQuery();
           // while (rs.next()) {
            //	Alert alert = new Alert(AlertType.ERROR);
            	//alert.setTitle("Error");
              // 	alert.setContentText("This Customer use in sell'"+ rs.getString(2) +"' brand \n delete Customer first");
             	//alert.showAndWait();
          
              //  return isNotUsed;
           // }
           // rs.close();
           // pst.close();
           // con.close();
           // isNotUsed = true;
       // } catch (SQLException e) {
          //  e.printStackTrace();
       // }
   // }

}
