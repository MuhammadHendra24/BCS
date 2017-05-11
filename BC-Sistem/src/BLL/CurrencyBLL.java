package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAL.Currency;
import Getway.CurrencyGetway;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class CurrencyBLL {
	 SQL sql = new SQL();
	    CurrencyGetway currencyGetway = new CurrencyGetway();
	    DBConnection dbCon = new DBConnection();
	    Connection con = dbCon.geConnection();
	    PreparedStatement pst;
	    ResultSet rs;

	    public void save(Currency currency){
	        if(isUniqName(currency)){
	            currencyGetway.save(currency);
	        }
	    }

	    public void update(Currency currency){
	        if(isUpdate(currency)){
	            if(isSame(currency)){
	                currencyGetway.update(currency);
	            }else if(isUniqName(currency)){
	                currencyGetway.update(currency);
	            }
	        }
	    }


	    public boolean isUniqName(Currency currency) {
	        boolean inUniqName = false;
	        try {
	            pst = con.prepareStatement("select * from Currency where Curency=? and CurencyDescription=?");
	            pst.setString(1, currency.currency);
	            pst.setString(2, currency.currencyDescription);
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

	    public boolean isUpdate(Currency currency) {
	        boolean isUpdate = false;
	        try {
	            pst = con.prepareStatement("select * from Currency where Id=? and Curency=? and CurencyDescription=?");
	            pst.setString(1,currency.id);
	            pst.setString(2,currency.currency);
	            pst.setString(3,currency.currencyDescription);
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

	    private boolean isSame(Currency currency){
	        boolean isSame = false;
	        try {
	            pst = con.prepareStatement("select * from Currency where id=? and Curency=?");
	            pst.setString(1,currency.id);
	            pst.setString(2,currency.currency);
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
