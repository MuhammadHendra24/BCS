	package Getway;

import DAL.Currency;
import DAL.IncOutGoing;
import DAL.Vendor;
import List.ListCurrency;
import List.ListIncomingOutgoing;
import controller.application.IncomingController;
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

public class IncomOutgoingGateway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();


    public void save(IncOutGoing incOutGoing){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into Incoming values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, incOutGoing.sortdokumen);
            pst.setString(3, incOutGoing.noDoc);
            pst.setString(4, incOutGoing.dateNoDoc);
            pst.setString(5, incOutGoing.vendor);
            pst.setString(6, incOutGoing.noInvoice);
            pst.setString(7, incOutGoing.noDo);
            pst.setString(8, incOutGoing.dateNoDo);
            pst.setString(9, incOutGoing.itemCode);
            pst.setString(10, incOutGoing.description);
            pst.setString(11, incOutGoing.kurenci);
            pst.setString(12, incOutGoing.U_m);
            pst.setString(13, incOutGoing.qtygood);
            pst.setString(14, incOutGoing.qtyreject);
            pst.setString(15, incOutGoing.price);
            pst.setString(16, incOutGoing.grosWeigh);
            pst.setString(17, incOutGoing.userId);        
            pst.setString(18, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            

        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }

    public void view(IncOutGoing incOutGoing){
    	   con = dbCon.geConnection();
          try {
             pst = con.prepareStatement("SELECT * FROM Incoming");
             rs = pst.executeQuery();
             while (rs.next()) {
                incOutGoing.id = rs.getString(1);
                incOutGoing.sortdokumen = rs.getString(2);
                incOutGoing.noDoc = rs.getString(3);
                incOutGoing.dateNoDoc = rs.getString(4);
                incOutGoing.vendor = rs.getString(5);
                incOutGoing.noInvoice = rs.getString(6);
                incOutGoing.noDo = rs.getString(7);
                incOutGoing.dateNoDo = rs.getString(8);
                incOutGoing.itemCode = rs.getString(9);
                incOutGoing.description = rs.getString(10);
                incOutGoing.kurenci = rs.getString(11);
                incOutGoing.U_m = rs.getString(12);
                incOutGoing.qtygood= rs.getString(13);
                incOutGoing.qtyreject = rs.getString(14);
                incOutGoing.price = rs.getString(15);
                incOutGoing.grosWeigh =  rs.getString(16);
                incOutGoing.userId =  rs.getString(17);
                incOutGoing.addedDate =  rs.getString(18);
                incOutGoing.userName = sql.getName(incOutGoing.userId, incOutGoing.userName, "User");
                incOutGoing.listIncoming.addAll(new ListIncomingOutgoing(incOutGoing.id, incOutGoing.sortdokumen, incOutGoing.noDoc, incOutGoing.dateNoDoc, incOutGoing.vendor, incOutGoing.noInvoice, incOutGoing.noDo, incOutGoing.dateNoDo, incOutGoing.itemCode, incOutGoing.description, incOutGoing.kurenci, incOutGoing.U_m, incOutGoing.qtygood,incOutGoing.qtyreject, incOutGoing.price, incOutGoing.grosWeigh,incOutGoing.userName, incOutGoing.addedDate));
                
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }
   
    public void  selectedView(IncOutGoing incOutGoing){
        con = dbCon.geConnection();
        try {
        	 pst = con.prepareStatement("select * from Incoming where Id=?");
             pst.setString(1, incOutGoing.id);
             rs = pst.executeQuery();
             while (rs.next()) {
            	  incOutGoing.sortdokumen = rs.getString(2);
                  incOutGoing.noDoc = rs.getString(3);
                  incOutGoing.dateNoDoc = rs.getString(4);
                  incOutGoing.vendor = rs.getString(5);
                  incOutGoing.noInvoice = rs.getString(6);
                  incOutGoing.noDo = rs.getString(7);
                  incOutGoing.dateNoDo = rs.getString(8);
                  incOutGoing.itemCode = rs.getString(9);
                  incOutGoing.description = rs.getString(10);
                  incOutGoing.kurenci = rs.getString(11);
                  incOutGoing.U_m = rs.getString(12);
                  incOutGoing.qtygood= rs.getString(13);
                  incOutGoing.qtyreject = rs.getString(14);
                  incOutGoing.price = rs.getString(15);
                  incOutGoing.grosWeigh =  rs.getString(16);
               }pst.close();
              con.close();
              rs.close();
          } catch (SQLException ex) {
        	  ex.printStackTrace();
        	
          }
      }
    public void update(IncOutGoing incOutGoing) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update Incoming set ShortDocument=?,NoDoc=?,DateNoDoc=?,Vendor=?,NoInvoice=?,NoDo=?,DateNoDo=?,ItemCode=?,Description=?,Currency=?,UM=?,GoodQty=?,RejectQty=?,Price=?,GWeight=? where Id=?");
            pst.setString(1, incOutGoing.sortdokumen);
            pst.setString(2, incOutGoing.noDoc);
            pst.setString(3, incOutGoing.dateNoDoc);
            pst.setString(4, incOutGoing.vendor);
            pst.setString(5, incOutGoing.noInvoice);
            pst.setString(6, incOutGoing.noDo);
            pst.setString(7, incOutGoing.dateNoDo);
            pst.setString(8, incOutGoing.itemCode);
            pst.setString(9, incOutGoing.description);
            pst.setString(10, incOutGoing.kurenci);
            pst.setString(11, incOutGoing.U_m);
            pst.setString(12, incOutGoing.qtygood);
            pst.setString(13, incOutGoing.qtyreject);
            pst.setString(14, incOutGoing.price);
            pst.setString(15, incOutGoing.grosWeigh);
            pst.setString(16, incOutGoing.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Update!");
         	alert.setContentText("Data Incoming Berhasil di update");
         	alert.showAndWait();
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    } public void delete(IncOutGoing incOutGoing) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from Incoming where Id=?");
            pst.setString(1, incOutGoing.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchView(IncOutGoing incOutGoing) {
        con = dbCon.geConnection();
        incOutGoing.listIncoming.clear();
        try {
            pst = con.prepareStatement("select * from Incoming where ShortDocument like ? or NoDoc like ?");
            pst.setString(1, "%" + incOutGoing.sortdokumen + "%");
            pst.setString(2, "%" + incOutGoing.sortdokumen + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
            	incOutGoing.id = rs.getString(1);
                incOutGoing.sortdokumen = rs.getString(2);
                incOutGoing.noDoc = rs.getString(3);
                incOutGoing.dateNoDoc = rs.getString(4);
                incOutGoing.vendor = rs.getString(5);
                incOutGoing.noInvoice = rs.getString(6);
                incOutGoing.noDo = rs.getString(7);
                incOutGoing.dateNoDo = rs.getString(8);
                incOutGoing.itemCode = rs.getString(9);
                incOutGoing.description = rs.getString(10);
                incOutGoing.kurenci = rs.getString(11);
                incOutGoing.U_m = rs.getString(12);
                incOutGoing.qtygood= rs.getString(13);
                incOutGoing.qtyreject = rs.getString(14);
                incOutGoing.price = rs.getString(15);
                incOutGoing.grosWeigh =  rs.getString(16);
                incOutGoing.userId =  rs.getString(17);
                incOutGoing.addedDate =  rs.getString(18);
                incOutGoing.userName = sql.getName(incOutGoing.userId, incOutGoing.userName, "User");
                incOutGoing.listIncoming.addAll(new ListIncomingOutgoing(incOutGoing.id, incOutGoing.sortdokumen, incOutGoing.noDoc, incOutGoing.dateNoDoc, incOutGoing.vendor, incOutGoing.noInvoice, incOutGoing.noDo, incOutGoing.dateNoDo, incOutGoing.itemCode, incOutGoing.description, incOutGoing.kurenci, incOutGoing.U_m, incOutGoing.qtygood,incOutGoing.qtyreject, incOutGoing.price, incOutGoing.grosWeigh,incOutGoing.userName, incOutGoing.addedDate));
                
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
        	 Logger.getLogger(IncOutGoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}