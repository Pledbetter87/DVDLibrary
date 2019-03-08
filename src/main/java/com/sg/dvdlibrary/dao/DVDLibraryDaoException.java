/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

/**
 *
 * @author MadelineHebel
 */
//this extends exception - now application has INHERITED all of the capabilities
// of Exception - could now add any new special featuresvbut in this case not.
//
public class DVDLibraryDaoException extends Exception {

    //this constructor is used when something is wrong in the application but
    // it wasn't caused by another exception. 
    public DVDLibraryDaoException(String message) {
        super(message);
    }

    //this constructor is used in cases when something is wrond in the application
    // that was caused by another exception in the implementation. 
    // Ex. FileNotFoundException
    //Exception EXTENDS throwable - this can handle the greatest nummber 
    // of possible errorss 
    public DVDLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
