package com.licenta.tripp.service;

import com.licenta.tripp.dao.UserDAO;
import com.licenta.tripp.model.User;

import java.sql.SQLException;

/**
 * Created by Tudor on 6/22/2017.
 */
public class UserService {

    private UserDAO userDao = new UserDAO();

    public boolean login(String username, String password) {

        User user = new User();

        user = userDao.findByUser(username);
        if (user != null){
            if (username.equalsIgnoreCase(user.getUsername()) && password.equalsIgnoreCase(user.getPassword())) {
                return true;
            }
        }

        return false;

    }

    public void insertToken(String username, String token) {
        userDao.insertToken(username, token);
    }

    public User findByUsername(String username){

        User user = userDao.findByUser(username);
        if(user != null){
            return user;
        }

        return null;
    }

    public void registerUser(User user){
        userDao.register(user);
    }

    public void changePassword(String username, String password) {
        userDao.changePassword(username, password);
    }
}
