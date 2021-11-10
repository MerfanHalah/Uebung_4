package org.hbrs.se1.ws21.uebung4.prototype.Strategy;

import org.hbrs.se1.ws21.uebung4.prototype.Model.Exceptions.PersistenceException;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<Employee> implements PersistenceStrategy<Employee> {


    // URL of file, in which the objects are stored
    private String location = "EmployeesList.se";
    private ObjectOutputStream objOut= null;
    private ObjectInputStream objIn = null;
    List<Employee> newList= null;


    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */


    @Override
    public void openConnection() throws PersistenceException {

    }

    @Override
    public void closeConnection() throws PersistenceException {

    }

    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Employee> employee ) throws PersistenceException {
        if (location == (null)) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connection");
        }
        try {

            this.objOut = new ObjectOutputStream(new FileOutputStream(location));

        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound, "File was not found");
        }

        try {
            objOut.writeObject(employee);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.InputError, e.getMessage());
        }
        try {
            objOut.close();

        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Connectiono is unavailable");
        }


    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<Employee> load() throws PersistenceException {
        if (location == (null)) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "No Connection");
        }
        try {

            this.objIn = new ObjectInputStream(new FileInputStream(location));
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound, "File was not found");
        }

        try {

            newList = (List<Employee>) objIn.readObject();
            objIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return newList;


    }

}



        // Some Coding hints ;-)
        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)



