
package Getway;

import DAL.Vendor;
import List.ListVendor;
import dataBase.DBConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendorGetway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(Vendor vendor) {
        con = dbCon.geConnection();
        if (isUniqVendorName(vendor)) {
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into Vendor values(?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, vendor.vendorName);
                pst.setString(3, vendor.vendorContactNumber);
                pst.setString(4, vendor.vendorAddress);
                pst.setString(5, vendor.vendorDescription);
                pst.setString(6, vendor.creatorId);
                pst.setString(7, LocalDate.now().toString());
                pst.executeUpdate();
                con.close();
                pst.close();
                Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("save!");
             	alert.setContentText("Supplyer" + "  '" + vendor.vendorName + "' " + "Added Sucessfuly");
             	alert.showAndWait();
             	
             	
            } catch (SQLException ex) {
                Logger.getLogger(Vendor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void view(Vendor vendor) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from Vendor");
            rs = pst.executeQuery();
            while (rs.next()) {
            	vendor.id = rs.getString(1);
            	vendor.vendorName = rs.getString(2);
            	vendor.vendorContactNumber = rs.getString(3);
            	vendor.vendorAddress = rs.getString(4);
            	vendor.vendorDescription = rs.getString(5);
            	vendor.creatorId = rs.getString(6);
            	vendor.date = rs.getString(7);

            	vendor.vendorDetails.addAll(new ListVendor(vendor.id, vendor.vendorName, vendor.vendorContactNumber, vendor.vendorAddress, vendor.vendorDescription, vendor.date));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {

            System.out.println("Exception");
        }
    }

    public void searchView(Vendor vendor) {
       vendor.vendorDetails.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from Vendor where VendorName like ? or VendorPhoneNumber like ? ORDER BY VendorName");
            pst.setString(1, "%" + vendor.vendorName + "%");
            pst.setString(2, "%" + vendor.vendorName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                vendor.id = rs.getString(1);
                vendor.vendorName = rs.getString(2);
                vendor.vendorContactNumber = rs.getString(3);
                vendor.vendorAddress = rs.getString(4);
                vendor.vendorDescription = rs.getString(5);
                vendor.creatorId = rs.getString(6);
                vendor.date = rs.getString(7);
                vendor.vendorDetails.addAll(new ListVendor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7)));
            }rs.close();con.close();pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectedView(Vendor vendor) {
        System.out.println("name :" + vendor.vendorName);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from Vendor where id=?");
            pst.setString(1, vendor.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                vendor.id = rs.getString(1);
                vendor.vendorName = rs.getString(2);
                vendor.vendorContactNumber = rs.getString(3);
                vendor.vendorAddress = rs.getString(4);
                vendor.vendorDescription = rs.getString(5);
                vendor.creatorId = rs.getString(6);
                vendor.date = rs.getString(7);
            }rs.close();con.close();pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(Vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Vendor vendor) {
        System.out.println("we are in update");
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from Vendor where Id=? and VendorName=?");
            pst.setString(1, vendor.id);
            pst.setString(2, vendor.vendorName);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Into the loop");
                updateNow(vendor);
                rs.close();
                pst.close();
                con.close();
                return;
            }rs.close();con.close();pst.close();
            if (isUniqVendorName(vendor)) {
                System.out.println("Out of the loop");
                updateNow(vendor);
                rs.close();con.close();pst.close();
            }
            
            
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Vendor vendor) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from Vendor where Id=?");
            pst.setString(1, vendor.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isUniqVendorName(Vendor vendor) {
        con = dbCon.geConnection();
        boolean uniqVendor = false;
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select VendorName from Vendor where VendorName=?");
            pst.setString(1, vendor.vendorName);
            rs = pst.executeQuery();
            while (rs.next()) {
                
                Alert alert = new Alert(AlertType.WARNING);
            	alert.setTitle("Warning!");
             	alert.setContentText("Vendor" + "  '" + vendor.vendorName + "' " + "Sudah ada ");
             	alert.showAndWait();
             	
                return uniqVendor;
            }rs.close();con.close();pst.close();
            uniqVendor = true;
        } catch (SQLException ex) {
            Logger.getLogger(Vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqVendor;
    }

    public void updateNow(Vendor vendor) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update Vendor set VendorName=? , VendorPhoneNumber=?,VendorAddress=? ,VendorDescription=? where Id=?");
            pst.setString(1, vendor.vendorName);
            pst.setString(2, vendor.vendorContactNumber);
            pst.setString(3, vendor.vendorAddress);
            pst.setString(4, vendor.vendorDescription);
            pst.setString(5, vendor.id);
            pst.executeUpdate();
            con.close();pst.close();
            Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("update!");
         	alert.setContentText("Vendor" + "  '" + vendor.vendorName + "' " + "Berhasil di update");
         	alert.showAndWait();
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParmanently(Vendor vendor) {
        con = dbCon.geConnection();
        try {
            System.out.println("and i am hear");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from Vendor where Id=?");
            pst.setString(1, vendor.id);
            pst.executeUpdate();
            con.close();pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Vendor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isUpdate(Vendor vendor) {
        con = dbCon.geConnection();
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from Vendor where Id=?");
            pst.setString(1, vendor.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    
    public boolean isNotUse(Vendor vendor){
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from Brands where VendorId=?");
            pst.setString(1, vendor.id);
            rs = pst.executeQuery();
            while(rs.next()){
            	 Alert alert = new Alert(AlertType.WARNING);
             	alert.setTitle("Warning!");
              	alert.setContentText("This Vendor supplyed  '"+ rs.getString(2) +"' brand \n delete brand first");
              	alert.showAndWait();
              
                return isNotUse;
            }rs.close();
            pst.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(VendorGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
}
