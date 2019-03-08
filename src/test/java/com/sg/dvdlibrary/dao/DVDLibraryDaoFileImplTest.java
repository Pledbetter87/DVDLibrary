/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.FileWriter;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author MadelineHebel
 */

//Our DVDLibraryDao has four methods that we must test:
//Add dvd
//Get dvd
//Get all dvds
//Remove dvd

//When designing tests for these methods in our DAO, while we need to make sure 
//we design & implement tests for each method, in most cases we will utilize more 
//than one per test to  'Arrange' our DAO's state correctly before we Assert on it

//First we must consider what the effects of each method will be on the DAO, and 
//then how we can use the other tools (DAO methods) available to us to determine 
//whether the State was correctly changed. 

public class DVDLibraryDaoFileImplTest {
    //add in a new DVDLibraryDao property declaration to our test class, and 
    //then use the setUp in the JUnit class to reference the new overloaded 
    //constructor we built in the ClassRosterDaoFileImpl with a newly created 
    //file to ensure we aren't messing up production data, and that our testDAO 
    //is effectively empty
    
    DVDLibraryDao testDao;
    
    public DVDLibraryDaoFileImplTest() {
       
    }
    
    //before every test runs, we will have created a new blank testlibrary.txt 
    //file using the FileWriter, and then used that as our fileName when 
    //instantiating our testDao - both ensuring that we are starting with a 
    //fresh, empty Dao object, as well as minimizing our interference with the 
    //normal application's data stored in the dvd.txt file.
    @Before
    public void setUp() throws Exception{
        String testFile = "testlibrary.txt";
        new FileWriter(testFile);//blanks the file
        testDao = new DVDLibraryDaoFileImpl(testFile);
    }
    
    //Our Add/Get DVD test is pretty straightforward. We know that the DAO is 
    //in an empty state since we created a new empty instance within our setUp method.
    
    //The first step of this test is to create a new DVD object (Arrange)
    
    //Then we add that DVD to the DAO (Act)
    
    //Next, we get the DVD back out of the DAO and put it in another variable (Act)
    
    //Finally, we check to see that the data within the stored DVD is equal to 
    //the retrieved DVD from the DAO (Assert)
    
    @Test
    public void testAddGetDVD() throws Exception {
        //create method inputs
        DVD dvd = new DVD();
        String title = "Movie";
        dvd.setRelease("2000");
        dvd.setDirector("director");
        dvd.setRating("R");
        dvd.setStudio("The Studio");
        dvd.setReview("Good");
        
        //add to the DAO
        testDao.addDVD(title, dvd);
        
        //get from the DAO
        DVD retrievedDVD = testDao.getDVD(title);
        
        //check that the data is equal
        assertEquals("Checking title", 
                dvd.getTitle(), retrievedDVD.getTitle());
        assertEquals("Checking release", 
                dvd.getRelease(), retrievedDVD.getRelease());
        assertEquals("Checking director", 
                dvd.getDirector(), retrievedDVD.getDirector());
        assertEquals("Checking rating", 
                dvd.getRating(), retrievedDVD.getRating());
        assertEquals("Checking studio", 
                dvd.getStudio(), retrievedDVD.getStudio());
        assertEquals("Checking review", 
                dvd.getReview(), retrievedDVD.getReview());
        
    }
        //This test is slightly more complicated than the previous Add/Get test, 
        //but not by much. Here we're focusing on testing the two methods addDVD 
        //and getAllDVDs and verifying each method works. To do this properly, 
        //we should really utilize multiple DVD objects.
        
        //do the following:
        //Create and add two DVD objects to the DAO (Arrange)
        
        //Get all of the DVD objects from the DAO (Act)
        
        //Check to see that the DAO returned the 2 objects (Assert)
        
        @Test
        public void testAddGetAllStudents() throws Exception {
        //create first DVD
        DVD firstDVD = new DVD();
        firstDVD.setTitle("The First Movie");
        firstDVD.setRelease("2000");
        firstDVD.setDirector("director");
        firstDVD.setRating("R");
        firstDVD.setStudio("The Studio");
        firstDVD.setReview("Good");
        
        //create second DVD
        DVD secondDVD = new DVD();
        secondDVD.setTitle("The First Movive");
        secondDVD.setRelease("2002");
        secondDVD.setDirector("A Different Director");
        secondDVD.setRating("R");
        secondDVD.setStudio("Different Studio");
        secondDVD.setReview("Better");
        
        //add both to DAO
        testDao.addDVD(firstDVD.getTitle(), firstDVD);
        testDao.addDVD(secondDVD.getTitle(), secondDVD);
        
        //retrieve the list of all DVDs within the DAO
        
    }
        
        
    
    
}
