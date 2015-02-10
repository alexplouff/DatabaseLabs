/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_labs;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface DAOStrategy {
    
    public abstract void openDatabaseConnection()
        throws  ClassNotFoundException, SQLException;
        
    public abstract void closeDatabaseConnection()
        throws SQLException;
    
    public abstract void createRecord(String hotelName, String street,
            String city, String state )                  // C.
        throws SQLException;
//    public abstract List getRecord(String query)       // R.
//        throws SQLException;
//    public abstract void updateRecord(String query)    // U.
//        throws SQLException;
//    public abstract void deleteRecord(String query)    // D.
//        throws SQLException;
    
    public abstract List getAllRecords() throws Exception;
}
