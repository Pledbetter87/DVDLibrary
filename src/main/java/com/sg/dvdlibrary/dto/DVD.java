/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

import java.util.Objects;

/**
 *
 * @author MadelineHebel
 */
//This is the Data Transfer Object - class that has getters and setters and no
// other methods.
// Ferrys data between the layers of the application
// Is part of the Model
public class DVD {

    private String title;
    private String release;
    private String rating;
    private String director;
    private String studio;
    private String review;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    
    
    //to make testing easier, we implement the equals and hashCode methods
    
    //This will allow us to easily compare two Student objects to see if the 
    //values of their fields match
    
    //Both equals and hashCode are methods inherited from the Object class
    
    //default implementation of equals simply compares the heap location of two 
    //DVD references to see if they are pointing to the same place on the heap, 
    //equating whether or not they are literally the same object. 
    
    //two objects that are equal to one another must have the same hashCode value
    
    
    //If you override either equals or hashCode, you are expected to override the 
    //other - and should utilize the same properties in both. Failure to do so 
    //will can cause irregular, or breaking issues in Java code.
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.release);
        hash = 17 * hash + Objects.hashCode(this.rating);
        hash = 17 * hash + Objects.hashCode(this.director);
        hash = 17 * hash + Objects.hashCode(this.studio);
        hash = 17 * hash + Objects.hashCode(this.review);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.release, other.release)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.review, other.review)) {
            return false;
        }
        return true;
    }
    
    
    //This toString method is added mostly for convenience. Often in error 
    //messages, JUnit will print out information about the object that failed a 
    //test. It helps if that information is readable to us, but the default 
    //toString method only really serializes the objects class name and 
    //hashcode - both interesting information, but not often particular useable. 
    //Overriding this method can allow us to printout all of the objects property 
    //values instead, which can allow for much faster insight into issues when 
    //reading test logs!
    @Override
    public String toString(){
        return "DVD{" + title + "}";
    }

    
    
}
