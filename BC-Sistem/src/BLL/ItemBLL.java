package BLL;


import DAL.ItemMaster;
import Getway.ItemMasterGateway;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Hendra
 *
 * Oct 18, 2016
 */
public class ItemBLL {
    SQL sql = new SQL();
    ItemMasterGateway itemmasterGetway = new ItemMasterGateway();
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    public void save(ItemMaster item){
        if(isUniqName(item)){
            itemmasterGetway.save(item);
        }
    }

    public void update(ItemMaster item){
        if(isUPdate(item)){
            if(isSame(item)){
                itemmasterGetway.Update(item);
            }else if(isUniqName(item)){
                itemmasterGetway.Update(item);
            }
        }
    }


    public boolean isUniqName(ItemMaster item) {
        boolean inUniqName = false;
        try {
            pst = con.prepareStatement("select * from ItemMaster where ItemCode=? and ItemDescription=?");
            pst.setString(1, item.itemCode);
            pst.setString(2, item.itemDescription);
            rs = pst.executeQuery();
            while (rs.next()){
            	Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("");
               	alert.setContentText("Item Code Ini Sudah Ada");
             	alert.showAndWait();
                return inUniqName;
            }
            inUniqName = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inUniqName;
    }

    public boolean isUPdate(ItemMaster item) {
        boolean isUPdate = false;
        try {
            pst = con.prepareStatement("select * from ItemMaster where Id=? and ItemCode=? and ItemDescription=? and Um=?");
            pst.setString(1,item.id);
            pst.setString(2,item.itemCode);
            pst.setString(3,item.itemDescription);
            pst.setString(4,item.um);
          
            rs = pst.executeQuery();
            while (rs.next()){

                return isUPdate;
            }
            isUPdate = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUPdate;
    }

    private boolean isSame(ItemMaster item){
        boolean isSame = false;
        try {
            pst = con.prepareStatement("select * from ItemMaster where id=? and ItemCode=?");
            pst.setString(1,item.id);
            pst.setString(2,item.itemCode);
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
