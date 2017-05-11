/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import DAL.Vendor;
import DAL.Users;
import List.ListUser;
import dataBase.DBConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsersGetway {

    DBConnection dbConnection = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void save(Users users) {

        if (isUniqName(users)) {
            con = dbConnection.geConnection();
            try {
                pst = con.prepareStatement("insert into User values(?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, users.userName);
                pst.setString(3, users.fullName);
                pst.setString(4, users.emailAddress);
                pst.setString(5, users.password);
                pst.setString(6, "1");
                if (users.imagePath != null) {
                    InputStream is;
                    is = new FileInputStream(new File(users.imagePath));
                    pst.setBlob(7, is);
                } else {
                    pst.setBlob(7, (Blob) null);
                }
                pst.setString(8, LocalDate.now().toString());
                pst.setString(9, users.creatorId);
                pst.executeUpdate();
                pst.close();
                con.close();

                Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Save");
             	alert.setContentText("User" + "  '" + users.userName + "' " + "berhasil di simpan");
             	alert.showAndWait();
                        

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void view(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("select * from User");
            rs = pst.executeQuery();
            while (rs.next()) {
                users.id = rs.getString(1);
                users.userName = rs.getString(2);
                users.Ulist.addAll(new ListUser(users.id, users.userName));
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectedView(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareCall("select * from User where id=?");
            pst.setString(1, users.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                users.id = rs.getString(1);
                users.userName = rs.getString(2);
                users.fullName = rs.getString(3);
                users.emailAddress = rs.getString(4);
                users.password = rs.getString(5);
                users.status = rs.getString(6);
                users.userImage = rs.getBlob(7);
                if (users.userImage != null) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(users.userImage.getBytes(1, (int) users.userImage.length()));
                    users.image = new Image(byteArrayInputStream);
                } else {
                    users.image = new Image("/image/rifat.jpg");
                }
                users.date = rs.getString(8);
                users.creatorId = rs.getString(9);

            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Vendor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("UPDATE User SET FullName=?,EmailAddress=?,Password=COALESCE(?, Password), Status=COALESCE(?, Status), UserImage=COALESCE(?, UserImage) WHERE UsrName=?");
            pst.setString(1, users.fullName);
            pst.setString(2, users.emailAddress);
            pst.setString(3, users.password);
            pst.setString(4, users.status);
            if (users.imagePath != null) {
                InputStream is;
                is = new FileInputStream(new File(users.imagePath));
                pst.setBlob(5, is);
            } else if (users.imagePath == null) {
                pst.setBlob(5, (Blob) null);
            }
            pst.setString(6, users.userName);
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Update!");
         	alert.setContentText("User" + "  '" + users.userName + "' " + "Data BerHasil di update");
         	alert.showAndWait();
         	
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(Users users) {
        con = dbConnection.geConnection();
        try {
            pst = con.prepareStatement("delete from User where Id=?");
            pst.setString(1, users.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUniqName(Users users) {
        con = dbConnection.geConnection();
        boolean isUniqName = false;
        try {
            pst = con.prepareStatement("select * from User where UsrName=?");
            pst.setString(1, users.userName);
            rs = pst.executeQuery();
            while (rs.next()) {
                           	          	
            	 Alert alert = new Alert(AlertType.WARNING);
            	alert.setTitle("warning!");
             	alert.setContentText("User name" + "  '" + users.userName + "' " + "Already Used");
             	alert.showAndWait();                    
                return isUniqName;
            }
            rs.close();
            pst.close();
            con.close();
            isUniqName = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUniqName;
    }
}
