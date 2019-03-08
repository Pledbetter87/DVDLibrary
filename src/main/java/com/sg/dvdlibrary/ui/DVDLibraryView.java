/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MadelineHebel
 */

//This is the View. It is the part of the program that interacts with the user.
// all components of the application go through the view for user interaction

public class DVDLibraryView {
    
    
    //UserIO dependency injection
    private UserIO io;
     
    //the menu that the user will see
    public int printMenuAndGetSelection() {
        io.print("=== Main Menu ===");
        io.print("1. Add DVD to collection");
        io.print("2. Remove DVD from collection");
        io.print("3. Edit DVD Info");
        io.print("4. DVD Search");
        io.print("5. Show DVD Info");
        io.print("6. Show All DVDs");
        io.print("7. Exit");
        //menu selection message
        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    //this displays the DVD values that are to be set and records them
    //  with the set action
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD Title");
        String release = io.readString("Please enter release date");
        String rating = io.readString("Please enter MPAA rating");
        String director = io.readString("Please enter director's name");
        String studio = io.readString("Please enter studio");
        String review = io.readString("Please enter user review");
        DVD currentDVD = new DVD();
        currentDVD.setTitle(title);
        currentDVD.setRelease(release);
        currentDVD.setRating(rating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setReview(review);
        return currentDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.print("DVD successfully created. " + "Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    //gets ArrayList of DVDs that was created by the method declared in the
    //DAOImpl and displays the Title, release date and rating.
    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print(currentDVD.getTitle() + ": "
                    + currentDVD.getRelease() + " Rated: "
                    + currentDVD.getRating());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the DVD title");
    }
    //pulls up and displays specific DVD object from the HashMap and 
    // displays the object specific values 
    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print("Rated: " + dvd.getRating());
            io.print("Released in: " + dvd.getRelease());
            io.print("Directed by: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("Review: " + dvd.getReview());
        } else {
            io.print("No such title.");//displays if the DVD isnt found 
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }
    
    public void displaySearchBanner() {
        io.print("=== Search DVDs ===");
    }
    
    public void searchDVDs(List search) {
        for(int i = 0; i < search.size(); i++){
            io.print(search.get(i).toString());
        }
        
    }

    public String getTitleEdit() {
        return io.readString("Please enter the DVD title");
    }

    public void editDVD(DVD dvd) {
        if (dvd != null) { //if DVD exists allows user to edit all fields 
            io.print(dvd.getTitle());
            io.print(dvd.getRating());
            io.print(dvd.getRelease());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getReview());
        } else {
            io.print("No such title."); //if DVD doesnt exist displays message
           
        }

    }
    
    //constructor that initializes the io member field
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
