/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author MadelineHebel
 */
//This interface is part of the Data Access Objects.
// Handles the storage and retrieving of data.
// Creates a "contract" to determine what needs to be done but doesnt 
// specifiy how.
//this interface is telling the application that data needs to be read or
// written into storage 

//works on CRUD - Create, Read, Update, Delete
public interface DVDLibraryDao {

    /**
     * Adds to the roster and associates it with the given name. If there is
     * already a dvd associated with the given name it will return that dvd
     * object, otherwise it will return null.
     *
     * @param title with which DVD is to be associated
     * @param dvd to be added to the library
     * @return the dvd object previously associated with the given name if it
     * exists, null otherwise
     */
    DVD addDVD(String name, DVD dvd)
            throws DVDLibraryDaoException;

    /**
     * Returns a List of all in the library.
     *
     * @return List containing all dvds in the library.
     */
    List<DVD> getAllDVDs()
            throws DVDLibraryDaoException;

    /**
     * Returns the dvd object associated with the given name. Returns null if no
     * such student exists
     *
     * @param title of the DVD to retrieve
     * @return the dvd object associated with the given name, null if no such
     * dvd exists
     */
    DVD getDVD(String name)
            throws DVDLibraryDaoException;
    

    /**
     * Removes from the library the dvd associated with the title. Returns the
     * dvd object that is being removed or null if there is no dvd associated
     * with the given name
     *
     * @param title of dvd to be removed
     * @return dvd object that was removed or null if no dvd was associated with
     * the given name
     */
    DVD removeDVD(String name)
            throws DVDLibraryDaoException;

    //@param title of the DVD to be edited and overwritten
    DVD editDVD(String name)
            throws DVDLibraryDaoException;

    public class DVDLibraryDaoException extends Exception {

        public DVDLibraryDaoException(String message) {
            super(message);
        }

        public DVDLibraryDaoException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
