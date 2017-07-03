package com.licenta.tripp.model;

/**
 * Created by Tudor on 6/28/2017.
 */
public class Result {

    private static final String OKAY_STATUS = "OK";

    private String status;

    public String getStatus( ) {
        return this.status;
    }

    public boolean isOkay( ) {
        return OKAY_STATUS.equals( this.getStatus( ) );
    }
}
