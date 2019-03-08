/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author MadelineHebel
 */
// this Data Access Object implements the Dao interface i created. 
// It fufills the contract that the DAO interface established. 
// Specifies HOW the things laid out in the DAO will be done
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private final String DVD_FILE;
    public static final String DELIMITER = "::";

    //@Override is saying that this method will override the super method
    // that was declared in the parent DAO interface.
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeLibrary(); //ensures it is written to persistant storage 
        return newDVD;
    }

    //this method gets all DVDs out of the map as a collection and passes them
    //into a new ArrayList. This allows us to convert the collection of DVD objects
    // an ArrayList that we can return in the View.
    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        //empty parameter list () because it is getting ALL
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    //this is asking the DVD HashMap for a specific DVD object by its tite
    //and returns that object
    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD editDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        writeLibrary();
        return dvds.get(title);

    }

    //this method asks the DVD HashMap to remove the DVD object that is mapped
    // with the given title.
    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDVD = dvds.remove(title);
        writeLibrary();
        return removedDVD;//shows user that the correct DVD has been removed 
    }


    //here is the Map that has-a HashMap collection of DVDs
    // will store all DVDs using their Title as the Key. All values are stored
    // in the associated DVD object
    private Map<String, DVD> dvds = new HashMap<>();

    //this translates a line of text in the dvd.txt file into a DVD object
    // takes in the string  - breaks it apart at the ::
    // creates the new DVD object and assigns the values to their appropriate
    // values by the [ ]
    private DVD unmarshallDVD(String dvdAsText) {

        String[] dvdTokens = dvdAsText.split(DELIMITER); //declaring the string
        // array that objects will be stored as - calling it dvdTokens - the
        // .split(DELIMITER) is the action to chop up the object

        String title = dvdTokens[0];

        DVD dvdFromFile = new DVD();

        dvdFromFile.setTitle(dvdTokens[0]);

        dvdFromFile.setRelease(dvdTokens[1]);

        dvdFromFile.setRating(dvdTokens[2]);

        dvdFromFile.setDirector(dvdTokens[3]);

        dvdFromFile.setStudio(dvdTokens[4]);

        dvdFromFile.setReview(dvdTokens[5]);

        // have now created a dvd! Return it!
        return dvdFromFile;
    }

    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load DVD data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent dvd unmarshalled
        DVD currentDVD;
        // Go through DVD_FILE line by line, decoding each line into a 
        // DVD object by calling the unmarshallDVD method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the title as the map key for our DVD object.
            // Put currentDVD into the map using title as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDVD(DVD aDVD) {
        // We need to turn a DVD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 
        // Start with the title, since that's supposed to be first.
        String dvdAsText = aDVD.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        dvdAsText += aDVD.getRelease() + DELIMITER;

        dvdAsText += aDVD.getRating() + DELIMITER;

        dvdAsText += aDVD.getDirector() + DELIMITER;

        dvdAsText += aDVD.getStudio() + DELIMITER;

        dvdAsText += aDVD.getReview(); //no DELIMITER on last property!

        // have now turned a DVD to text!
        return dvdAsText;
    }

    /**
     * Writes all DVDs in the library out to a DVD_FILE. See loadLibrary for
     * file format.
     *
     * @throws DVDLibraryDaoException if an error occurs writing to the file
     */
    private void writeLibrary() throws DVDLibraryDaoException {
        //We are not handling the IOException - but we are translating it
        // to an application specific exception and then simple throwing it
        // (i.e. 'reporting' it) to the code that called us. 
        // It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));//file is blank at this point
        } catch (IOException e) { //happens when the file doesnt exist or you
            // do not have permission to write to that file
            throw new DVDLibraryDaoException( //throws DAO excep. because we have
                    //translated both into DAO. Throws exception into the controller
                    //which displays error to the user and shuts down. Allows us
                    //to use the same functionality for both.
                    "Could not save DVD data.", e);
        }

        // Write out the DVD objects to the library file.
        //Could just grab the dvd map,
        // get the Collection of DVDs and iterate over them but i
        // already created a method that gets a List of DVDs so
        // i'll reuse it.
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            // turn a DVD into a String
            dvdAsText = marshallDVD(currentDVD);
            // write the DVD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    //no-args constructor is providing the earlier default behaviour that 
    //DVDLibraryrDaoFileImpl was originally built upon - instantiation and the 
    //assignment of "dvd.txt" to the DVD_FILE variable
    public DVDLibraryDaoFileImpl() {
        DVD_FILE = "dvd.txt";
    }

    //overloaded constructor has allowed us to create DVDLibraryDaoFileImpl 
    //instances that utilize another file - allowing the file reference to be 
    //injected upon construction - something that will be perfect for test setup, 
    //and ensuring we don't overwrite our production application data
    public DVDLibraryDaoFileImpl(String dvdTextFile) {
        DVD_FILE = dvdTextFile;
    }

}
