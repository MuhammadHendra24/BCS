package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.SortDoc;
import Getway.SortDocGetway;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SortDocBLL {
   	    SQL sql = new SQL();
	    SortDocGetway sortDocGetway = new SortDocGetway();
	    DBConnection dbCon = new DBConnection();
	    Connection con = dbCon.geConnection();
	    PreparedStatement pst;
	    ResultSet rs;

	    public void save(SortDoc sortDoc){
	        if(isUniqName(sortDoc)){
	            sortDocGetway.save(sortDoc);
	        }
	    }

	    public void update(SortDoc sortDoc){
	        if(isUpdate(sortDoc)){
	            if(isSame(sortDoc)){
	                sortDocGetway.update(sortDoc);
	            }else if(isUniqName(sortDoc)){
	                sortDocGetway.update(sortDoc);
	            }
	        }
	    }


	    public boolean isUniqName(SortDoc sortDoc) {
	        boolean inUniqName = false;
	        try {
	            pst = con.prepareStatement("select * from SortDocumen where SortDocName=? and SortDocDescription=?");
	            pst.setString(1, sortDoc.sortDocName);
	            pst.setString(2, sortDoc.sortDocDescription);
	            rs = pst.executeQuery();
	            while (rs.next()){
	            	Alert alert = new Alert(AlertType.INFORMATION);
	                alert.setTitle("");
	               	alert.setContentText("This Currency name already exist");
	             	alert.showAndWait();
	                return inUniqName;
	            }
	            inUniqName = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return inUniqName;
	    }

	    public boolean isUpdate(SortDoc sortDoc) {
	        boolean isUpdate = false;
	        try {
	            pst = con.prepareStatement("select * from SortDocumen where Id=? and SortDocName=? and SortDocDescription=?");
	            pst.setString(1,sortDoc.id);
	            pst.setString(2,sortDoc.sortDocName);
	            pst.setString(3,sortDoc.sortDocDescription);
	            rs = pst.executeQuery();
	            while (rs.next()){

	                return isUpdate;
	            }
	            isUpdate = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return isUpdate;
	    }

	    private boolean isSame(SortDoc sortDoc){
	        boolean isSame = false;
	        try {
	            pst = con.prepareStatement("select * from SortDocumen where id=? and SortDocName=?");
	            pst.setString(1,sortDoc.id);
	            pst.setString(2,sortDoc.sortDocName);
	            rs = pst.executeQuery();
	            while (rs.next()){

	                return isSame=true;
	            }


	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return isSame;
	    }
	}
