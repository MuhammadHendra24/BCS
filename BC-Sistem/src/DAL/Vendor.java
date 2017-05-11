package DAL;

import List.ListVendor;
import dataBase.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vendor {

    public String id;
    public String vendorName;
    public String vendorContactNumber;
    public String vendorAddress;
    public String vendorDescription;
    public String creatorId;
    public String date;


    public ObservableList<ListVendor> vendorDetails = FXCollections.observableArrayList();




}
