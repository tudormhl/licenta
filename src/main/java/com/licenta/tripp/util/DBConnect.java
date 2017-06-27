package com.licenta.tripp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Tudor on 6/22/2017.
 */
public class DBConnect {

    private Connection DBConnect;
    public static final Logger log = LoggerFactory.getLogger(DBConnect.class);

    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Succes");
            String url = "jdbc:mysql://localhost/licenta";
            DBConnect = DriverManager.getConnection(url,"root", "admin");
            log.info("---------Database connected----------");

        }catch(Exception e){
            e.printStackTrace();
        }

        return DBConnect;
    }

}
