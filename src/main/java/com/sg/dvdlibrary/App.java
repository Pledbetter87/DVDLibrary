/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author MadelineHebel
 */
public class App {
    //CRUD application - it is meant to Create, Read, Update, and Delete

    //configues, instantiates, and assembles the classes in the application
    //App will now:
    //Declare UserIO variable and initialize it with a UserConsoleIOImpl reference
    //Declare and instantiate DVDLibraryView object - passing UserIO created 
    //in the previous step into the constructor.
    //Declare a DVDLibraryDao variable and initialize it with a DVDLibraryDaoFileImpl reference
    //Instantiate a DVDLibraryController - passing the DVDLibraryDao - and 
    //DVDLibraryView object into the constructor 
    //Call the run method on the controller
    public static void main(String[] args) {

        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller
                = new DVDLibraryController(myDao, myView);
        controller.run();
    }

}
