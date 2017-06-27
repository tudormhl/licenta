package com.licenta.tripp.model;

import java.util.List;

/**
 * Created by Tudor on 6/22/2017.
 */
public class User {

    private String username;
    private String password;
    private List<Interest> interestList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Interest> getInterestList() {

        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }

    public User(){

    }
}
