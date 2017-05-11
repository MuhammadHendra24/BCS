package DAL;

import List.ListUser;
import dataBase.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Users {

    public ObservableList<String> allUser = FXCollections.observableArrayList();

    public String id;
    public String userName;
    public String fullName;
    public String emailAddress;
    public String password;
    public String status;
    public String imagePath;
    public Blob userImage;
//    public File userImage;
    public String date;
    public String creatorId;
    public Image image;

    public ObservableList<ListUser> Ulist = FXCollections.observableArrayList();


}
