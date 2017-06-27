package com.licenta.tripp.dao;

import com.licenta.tripp.model.User;
import com.licenta.tripp.util.DBConnect;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tudor on 6/22/2017.
 */
public class UserDAO extends DBConnect {

    private Connection dbConnection = null;
    private PreparedStatement preparedStatement = null;

    public User findByUser(String loginUsername){
        User user = new User();

        String sql = " SELECT username, password, interests, token " +
                    " FROM user " +
                    " where username = ?; ";

        try{
            dbConnection = connect();
            preparedStatement = (PreparedStatement) dbConnection.prepareStatement(sql);
            preparedStatement.setString(1,loginUsername+"");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){

                String username = rs.getString("username");
                String password = rs.getString("password");
                //String interests = rs.getString("interests");

                user.setUsername(username);
                user.setPassword(password);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public void register(User user){

        String sql = " insert into user (username, password, interests) values(?, ?, ?) ";

        try {

            dbConnection = connect();
            preparedStatement = (PreparedStatement) dbConnection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername()+"");
            preparedStatement.setString(2,user.getPassword()+"");
            preparedStatement.setString(3,"");

            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertToken(String username, String token) {

        String sql = "update user set token = ? where username = ?";

        try{
            dbConnection = connect();
            preparedStatement = (PreparedStatement) dbConnection.prepareStatement(sql);
            preparedStatement.setString(1,token+"");
            preparedStatement.setString(2,username+"");

            preparedStatement.executeUpdate();
            dbConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changePassword(String username, String password) {

        String sql = "update user set password = ? where username = ?";

        try{
            dbConnection = connect();
            preparedStatement = (PreparedStatement) dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, password+"");
            preparedStatement.setString(2, username+"");

            preparedStatement.executeUpdate();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findToken(String token) {

        User user = new User();

        String sql = " SELECT username, password, interests, token " +
                " FROM user " +
                " where token = ?; ";

        try{
            dbConnection = connect();
            preparedStatement = (PreparedStatement) dbConnection.prepareStatement(sql);
            preparedStatement.setString(1,token+"");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){

                String username = rs.getString("username");
                String password = rs.getString("password");
                String userToken = rs.getString("token");
                //String interests = rs.getString("interests");

                user.setUsername(username);
                user.setPassword(password);
                user.setToken(userToken);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }


}
