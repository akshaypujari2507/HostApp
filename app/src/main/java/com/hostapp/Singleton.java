package com.hostapp;

import android.content.Context;


public class Singleton {

    private static Singleton INSTANCE = null;
    public Context context;
    public String args;

    // other instance variables can be here

    private Singleton() {};

    public static synchronized Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return(INSTANCE);
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
