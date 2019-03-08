/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MadelineHebel
 */
//This is "brain" of the application. Tells the other components what to do.
//Doesn't do any of the work itself.
public class DVDLibraryController {

    //the dependency injection for the View and the DAO. 
    // The controller should not be responsible for instantiating a new DAOImpl.
    // Controller doesnt have to make any decisions about which implementation to
    // use. The specific implementation will be injected into the controller.
    // Makes them loosely coupled - ensures that theyre coupled to the what 
    // and not the how of the other components in the system
    // LOOSELY COUPLED - components can be replaced with alternative
    // implementations that provide the same services. Less contstrained - 
    // dependencies of a class that use different classes is reduced - makes
    // code easier to maintain and update - one change wont break the machine
    // Also passing an instance of the View into the controller - dont want to 
    // make the Controller responsible for injecting the right into the UserIO
    // implementation into the View - that is the job of the App class.
    
    private DVDLibraryView view; //property declaration
    private DVDLibraryDao dao; //property declaration

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {//uses the boolean value of true set above 
                //this is the menu that will show to the user
                //continues to run the program as long as the user
                //selects options that are not "exit"
                
                menuSelection = showMenuGetSelection();

                //these cases tell the menu what to do upon selection
                switch (menuSelection) {
                    case 1:
                        createDVD();//runs the createDVD method
                        break;
                    case 2:
                        removeDVD();//runs the removedDVD method
                        break;
                    case 3:
                        editDVD();//runs the editDVD method
                        break;
                    case 4:
                        searchDVDs();//runs the listDVDs method
                        break;
                    case 5:
                        viewDVD();//runs the viewDVDmethod
                        break;
                    case 6:
                        listDVDs();
                        break;
                    case 7:
                        keepGoing = false;//exits program
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        } //this try-catch block will react to any DVDLibraryExceptions that
        //get thrown and will print out error message
    }
    
    private int showMenuGetSelection(){
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    //this method orchestrates the activity to list all DVDs 
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    //this method asks the View to display the View DVD banner and gets the 
    //DVD title from the user. It then asks the DAO for the DVD object associated
    //with the title. Then it asks the view to display the DVD information.
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getTitleChoice();
        DVD dvd = dao.getDVD(title);
        view.displayDVD(dvd);
    }

    //this method asks the view to display the remove DVD banner and asks the 
    // user for the title of the DVD they want to remove. Then is asks the DAO
    // to remove the DVD and then the view to display the removal success banner
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getTitleChoice();
        dao.removeDVD(title);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    //this method allows user to edit a DVD
    //it combines the view DVD method, remove DVD, and the add DVD methods into one
    // reads the DVD object - deletes it - writes new DVD over it 
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String title = view.getTitleChoice();
        DVD dvd = dao.getDVD(title);
        view.displayDVD(dvd);
        dao.removeDVD(title);
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
    }
    
    private void searchDVDs() throws DVDLibraryDaoException {
        view.displaySearchBanner();
        String title = view.getTitleChoice();
        List<DVD> library = dao.getAllDVDs();
        List<DVD> search = new ArrayList<>();
         for(DVD aDVD : library){
            if(aDVD.getTitle().contains(title)){
                search.add(aDVD);
            }
        }
         view.searchDVDs(search);
    }

    //constructor method for property declarations 
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

}
