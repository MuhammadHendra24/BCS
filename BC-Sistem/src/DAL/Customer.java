package DAL;


import List.ListCustomer;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Customer {

    public String id;
    public String customerName;
    public String customerAddress;
    public String customerNpwp;
    public String customerSkep;
    public String addBy;
    public String date;
    public String userId;
    public String userName;

    public ObservableList<ListCustomer> customerList = FXCollections.observableArrayList();

}