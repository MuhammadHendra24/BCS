	package Getway;

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

import DAL.IncOutGoing;
import DAL.Outgoing;
import List.ListIncomingOutgoing;
import List.ListOutgoing;

public class OutgoingGateway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    SQL sql = new SQL();


    public void save(Outgoing outgoing){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into Outgoing values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, outgoing.sortdokumen);
            pst.setString(3, outgoing.noDoc);
            pst.setString(4, outgoing.dateNoDoc);
            pst.setString(5, outgoing.customer);
            pst.setString(6, outgoing.noInvoice);
            pst.setString(7, outgoing.noDo);
            pst.setString(8, outgoing.dateNoDo);
            pst.setString(9, outgoing.itemCode);
            pst.setString(10, outgoing.description);
            pst.setString(11, outgoing.kurenci);
            pst.setString(12, outgoing.U_m);
            pst.setString(13, outgoing.qtygood);
            pst.setString(14, outgoing.price);
            pst.setString(15, outgoing.grosWeigh);
            pst.setString(16, outgoing.userId);        
            pst.setString(17, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            con.close();
            

        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }

    public void view(Outgoing outgoing){
    	   con = dbCon.geConnection();
          try {
             pst = con.prepareStatement("SELECT * FROM Outgoing");
             rs = pst.executeQuery();
             while (rs.next()) {
            	 outgoing.id = rs.getString(1);
            	 outgoing.sortdokumen = rs.getString(2);
            	 outgoing.noDoc = rs.getString(3);
            	 outgoing.dateNoDoc = rs.getString(4);
            	 outgoing.customer = rs.getString(5);
               	 outgoing.noInvoice = rs.getString(6);
            	 outgoing.noDo = rs.getString(7);
            	 outgoing.dateNoDo = rs.getString(8);
            	 outgoing.itemCode = rs.getString(9);
            	 outgoing.description = rs.getString(10);
            	 outgoing.kurenci = rs.getString(11);
            	 outgoing.U_m = rs.getString(12);
            	 outgoing.qtygood= rs.getString(13);
              	 outgoing.price = rs.getString(14);
            	 outgoing.grosWeigh =  rs.getString(15);
            	 outgoing.userId =  rs.getString(16);
            	 outgoing.addedDate =  rs.getString(17);
            	 outgoing.userName = sql.getName(outgoing.userId, outgoing.userName, "User");
                 outgoing.listoutgoing.addAll(new ListOutgoing(outgoing.id, outgoing.sortdokumen, outgoing.noDoc, outgoing.dateNoDoc, outgoing.customer, outgoing.noInvoice, outgoing.noDo, outgoing.dateNoDo, outgoing.itemCode, outgoing.description, outgoing.kurenci, outgoing.U_m, outgoing.qtygood, outgoing.price, outgoing.grosWeigh,outgoing.userName, outgoing.addedDate));
             }pst.close(); 
            con.close();
            rs.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }
   
    public void  selectedView(Outgoing outgoing){
        con = dbCon.geConnection();
        try {
        	 pst = con.prepareStatement("select * from Outgoing where Id=?");
             pst.setString(1, outgoing.id);
             rs = pst.executeQuery();
             while (rs.next()) {
            	 outgoing.sortdokumen = rs.getString(2);
            	 outgoing.noDoc = rs.getString(3);
            	 outgoing.dateNoDoc = rs.getString(4);
            	 outgoing.customer = rs.getString(5);
               	 outgoing.noInvoice = rs.getString(6);
            	 outgoing.noDo = rs.getString(7);
            	 outgoing.dateNoDo = rs.getString(8);
            	 outgoing.itemCode = rs.getString(9);
            	 outgoing.description = rs.getString(10);
            	 outgoing.kurenci = rs.getString(11);
            	 outgoing.U_m = rs.getString(12);
            	 outgoing.qtygood= rs.getString(13);
              	 outgoing.price = rs.getString(14);
            	 outgoing.grosWeigh =  rs.getString(15);
               }pst.close();
              con.close();
              rs.close();
          } catch (SQLException ex) {
        	  ex.printStackTrace();
        	
          }
      }
    public void update(Outgoing outgoing){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update Outgoing set ShortDocument=?,NoDoc=?,DateNoDoc=?,Customer=?,NoInvoice=?,NoDo=?,DateNoDo=?,ItemCode=?,Description=?,Currency=?,UM=?,GoodQty=?,Price=?,GWeight=? where Id=?");
            pst.setString(1, outgoing.sortdokumen);
            pst.setString(2, outgoing.noDoc);
            pst.setString(3, outgoing.dateNoDoc);
            pst.setString(4, outgoing.customer);
            pst.setString(5, outgoing.noInvoice);
            pst.setString(6, outgoing.noDo);
            pst.setString(7, outgoing.dateNoDo);
            pst.setString(8, outgoing.itemCode);
            pst.setString(9, outgoing.description);
            pst.setString(10, outgoing.kurenci);
            pst.setString(11, outgoing.U_m);
            pst.setString(12, outgoing.qtygood);
            pst.setString(13, outgoing.price);
            pst.setString(14, outgoing.grosWeigh);
            pst.setString(15, outgoing.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Update!");
         	alert.setContentText("Data Outgoing Berhasil di update");
         	alert.showAndWait();
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    } public void delete(Outgoing outgoing){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from Outgoing where Id=?");
            pst.setString(1, outgoing.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchView(Outgoing outgoing){
        con = dbCon.geConnection();
        outgoing.listoutgoing.clear();
        try {
            pst = con.prepareStatement("select * from Outgoing where ShortDocument like ? or NoDoc like ?");
            pst.setString(1, "%" + outgoing.sortdokumen + "%");
            pst.setString(2, "%" + outgoing.sortdokumen + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
            	outgoing.id = rs.getString(1);
            	outgoing.sortdokumen = rs.getString(2);
            	outgoing.noDoc = rs.getString(3);
            	outgoing.dateNoDoc = rs.getString(4);
            	outgoing.customer = rs.getString(5);
            	outgoing.noInvoice = rs.getString(6);
            	outgoing.noDo = rs.getString(7);
            	outgoing.dateNoDo = rs.getString(8);
            	outgoing.itemCode = rs.getString(9);
            	outgoing.description = rs.getString(10);
            	outgoing.kurenci = rs.getString(11);
            	outgoing.U_m = rs.getString(12);
            	outgoing.qtygood= rs.getString(13);
            	outgoing.price = rs.getString(14);
            	outgoing.grosWeigh =  rs.getString(15);
            	outgoing.userId =  rs.getString(16);
            	outgoing.addedDate =  rs.getString(17);
            	outgoing.userName = sql.getName(outgoing.userId, outgoing.userName, "User");
            	outgoing.listoutgoing.addAll(new ListOutgoing(outgoing.id, outgoing.sortdokumen, outgoing.noDoc, outgoing.dateNoDoc, outgoing.customer,outgoing.noInvoice, outgoing.noDo, outgoing.dateNoDo, outgoing.itemCode, outgoing.description, outgoing.kurenci, outgoing.U_m, outgoing.qtygood, outgoing.price, outgoing.grosWeigh,outgoing.userName, outgoing.addedDate));
                
            }pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
        	 Logger.getLogger(Outgoing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

