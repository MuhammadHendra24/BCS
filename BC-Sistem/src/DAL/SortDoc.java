package DAL;


import List.ListSortDoc;

import dataBase.DBConnection;
import dataBase.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SortDoc {



    public String id;
    public String sortDocName;
    public String sortDocDescription;
    public String addBy;
    public String date;
    public String userId;
    public String userName;

    public ObservableList<ListSortDoc> ListSortDetail = FXCollections.observableArrayList();




}