package db_labs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alex
 */

public class HotelDAO implements DAOStrategy {

    private DB_Accessor_SQL db;

    public HotelDAO(DB_Accessor_SQL db) {
        setDb(db);
    }

    public DB_Accessor_SQL getDb() {
        return db;
    }

    private void setDb(DB_Accessor_SQL db) {
        this.db = db;
    }

    @Override
    public List<Hotel> getAllRecords() throws Exception {
        List listOfHotels = new ArrayList();
        try {
            listOfHotels = db.getAllRecords();
        } catch (Exception e) {
            e.getLocalizedMessage();
        } finally {
            db.closeDatabaseConnection();
        }

        return listOfHotels;
    }

    @Override
    public void createRecord(String hotelName, String street,
            String city, String state) throws SQLException {

        Hotel hotel = new Hotel(hotelName, street, city, state);

        List<String> columns = new ArrayList<>();
        columns.add("hotel_id");
        columns.add("hotel_name");
        columns.add("street");
        columns.add("city");
        columns.add("state");
        columns.add("notes");

        List<String> values = new ArrayList<>();

        values.add(null);
        values.add(hotel.getName());
        values.add(hotel.getStreet_address());
        values.add(hotel.getCity());
        values.add(hotel.getState());
        values.add(null);
        
        db.createRecord(columns,values);
        System.out.println("end of method");
    }

    public void updateRecord(String column, String value, String identifier) throws SQLException {
        final String table = "hotel";
        
        db.updateRecord(table, column, value, identifier);
        
    }

    @Override
    public void openDatabaseConnection() throws ClassNotFoundException, SQLException {
        db.openDatabaseConnection();
    }
    
    @Override
    public void closeDatabaseConnection() throws SQLException{
        db.closeDatabaseConnection();
    }

    public static void main(String[] args) {
        
        try {
            HotelDAO dao = new HotelDAO(new DB_Accessor_SQL("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/sakila",
                    "root", "password"));
            dao.openDatabaseConnection();
//            System.out.println(dao.getAllRecords());
//            dao.createRecord("Super 9","Oakland Ave.", "Milwaukee", "WI");
            dao.updateRecord("hotel_name", "Super8", "Radisson");
            dao.closeDatabaseConnection();
        } catch (Exception ex) {
            
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

}
