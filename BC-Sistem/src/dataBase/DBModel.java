/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBModel {

    PreparedStatement pst;

    public void createDataBase() {
        DBConnection con = new DBConnection();
        try {
            pst = con.mkDataBase().prepareStatement("create database if not exists GudangBerikat DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `GudangBerikat`.`User` (\n"
            		+ "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `UsrName` VARCHAR(20) NOT NULL,\n"
                    + "  `FullName` VARCHAR(50) ,\n"
                    + "  `EmailAddress` VARCHAR(50) ,\n"
                    + "  `Password` VARCHAR(45),\n"
                    + "  `Status` tinyint(1) NOT NULL DEFAULT '0',\n"
                    + "  `UserImage` mediumblob,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  `CreatorId` int(11),\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `GudangBerikat`.`UserPermission` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AddProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddSupplyer` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddBrand` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddCatagory` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddUnit` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddCustomer` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateSupplyer` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateBrand` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateCatagory` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateUnit` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateCustomer` tinyint(1) DEFAULT NULL,\n"
                    + "  `RMAManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `SellProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `ProvideDiscount` tinyint(1) DEFAULT NULL,\n"
                    + "  `EmployeManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `OrgManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `ChangeOwnPass` tinyint(1) DEFAULT NULL,\n"
                    + "  `UserId` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `GudangBerikat`.`Departement` (\n"
                    + "  `Id` int(1) NOT NULL ,\n"
                    + "  `Deptname` varchar(100) DEFAULT NULL,\n"
                    + "  `DeprtSection` varchar(100) DEFAULT NULL,\n"
                    + "  `DeptContactNumbers` text DEFAULT NULL,\n"
                    + "  `DeptContactAddress` text DEFAULT NULL,\n"
                    + "  `OrgLogo` mediumblob,\n"
                    + "  `UserId` int(11) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `GudangBerikat`.`Vendor` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `VendorName` varchar(100) DEFAULT NULL,\n"
                    + "  `VendorPhoneNumber` varchar(100) DEFAULT NULL,\n"
                    + "  `VendorAddress` text DEFAULT NULL,\n"
                    + "  `VendorDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `GudangBerikat`.`SortDocumen` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `SortDocName` varchar(50) DEFAULT NULL,\n"
                    + "  `SortDocDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS `GudangBerikat`.`Currency` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `Curency` varchar(200) DEFAULT NULL,\n"
                    + "  `CurencyDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS `GudangBerikat`.`Customer` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `CustomerName` varchar(200) NOT NULL,\n"
                    + "  `CustomerAddress` text,\n" 
                    + "  `CustomerNpwp` varchar(200) DEFAULT NULL,\n"
                    + "  `CustomerSkep` varchar(200) DEFAULT NULL,\n"
                    + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists `GudangBerikat`.`ItemMaster` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `ItemCode` varchar(200) NOT NULL,\n"
                    + "  `ItemDescription` varchar(200) DEFAULT NULL,\n"
                    + "  `Um` varchar(40) DEFAULT NULL,\n"
                    + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

           
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS `GudangBerikat`.`Incoming` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `ShortDocument` varchar(200) ,\n"
                    + "  `NoDoc` varchar(200) ,\n"
                    + "  `DateNoDoc` date ,\n"
                    + "  `Vendor` varchar(200) ,\n"
                    + "  `NoInvoice` varchar(200) ,\n"
                    + "  `NoDo` varchar(200)  ,\n"
                    + "  `DateNoDo` date ,\n"
                    + "  `ItemCode` varchar(200) ,\n"
                    + "  `Description` varchar(200) ,\n"
                    + "  `Currency` varchar(200) ,\n"
                    + "  `UM` varchar(200) ,\n"
                    + "  `GoodQty` varchar(200) ,\n"
                    + "  `RejectQty` varchar(200) ,\n"
                    + "  `Price` double ,\n"
                    + "  `GWeight`varchar(200) ,\n" 
                    + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS `GudangBerikat`.`Outgoing` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `ShortDocument` varchar(200) ,\n"
                    + "  `NoDoc` varchar(200) ,\n"
                    + "  `DateNoDoc` date ,\n"
                    + "  `Customer` varchar(200) ,\n"
                    + "  `NoInvoice` varchar(200) ,\n"
                    + "  `NoDo` varchar(200)  ,\n"
                    + "  `DateNoDo` date ,\n"
                    + "  `ItemCode` varchar(200) ,\n"
                    + "  `Description` varchar(200) ,\n"
                    + "  `Currency` varchar(200) ,\n"
                    + "  `UM` varchar(200) ,\n"
                    + "  `GoodQty` varchar(200) ,\n"
                    + "  `Price` double ,\n"
                    + "  `GWeight`varchar(200) ,\n" 
                    + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            System.out.println("Create Database Sucessfuly");

        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}