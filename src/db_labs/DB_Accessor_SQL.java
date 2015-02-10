/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_labs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public class DB_Accessor_SQL {

    private String driverName;
    private String url;
    private String userName;
    private String password;
    private Connection conn;

    public DB_Accessor_SQL() {
    }

    public DB_Accessor_SQL(String driverName, String url, String userName, String password) {
        setDriverName(driverName);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }

    public String getDriverName() {
        return driverName;
    }

    private void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public void openDatabaseConnection()
            throws IllegalArgumentException, ClassNotFoundException, SQLException {
        Class.forName(driverName);                    // <----------------------------------------- outdataed / best practice??
        conn = DriverManager.getConnection(url, userName, password);
        System.out.println("Connection Is Open");
    }

    public void closeDatabaseConnection() throws SQLException {
        conn.close();
        System.out.println("Connection Is Closed");
    }

    public List getAllRecords() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM hotel;";
        List<Map<String,Object>> list = null;
        Map map = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData md = null;

        try {
            openDatabaseConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            list = new ArrayList();

            while (rs.next()) {
                map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    try {
                        map.put(md.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {

                    }
                }
                list.add(map);
            }
            
        } catch (SQLException se) {
            se.getLocalizedMessage();
        } finally {
            closeDatabaseConnection();
        }
        return list;
    }
    
    public void createRecord(List<String> columns, List<String> values) throws SQLException {
        
        StringBuilder sb = new StringBuilder("INSERT INTO hotel (");
        for(String col : columns) {
            sb.append(col).append(",");
        }
        sb = sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES (");
        for(String val : values) {
            sb.append("?,");
        }
        sb = sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        
        PreparedStatement pStmt = conn.prepareStatement(sb.toString());
        
        for(int i=0; i < values.size(); i++) {
            pStmt.setObject(i+1, values.get(i));
        }
        
        pStmt.executeUpdate();
        conn.close();
    }
    
    public void updateRecord(String tableName, String column, String value, String identifier) throws SQLException {
        
        StringBuilder sb = new StringBuilder("UPDATE ");
            
        sb.append(tableName).append(" SET ").append(column).append("=")
                .append(value).append(" WHERE ").append(column).append( "=").append(identifier);

            
        
       // sb = sb.append( " WHERE ").append(column).append("=").append(column);
        
        PreparedStatement pStmt = conn.prepareStatement(sb.toString());
    }

//    public static void main(String[] args) {
//
//        DB_Accessor_SQL db = new DB_Accessor_SQL("com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/sakila",
//                "root", "password");
//
//        try {
////            db.openDatabaseConnection();
//           // System.out.println(db.getAllRecords());
////            db.createRecord("INSERT INTO hotel (hotel_id,hotel_name,street,city,state,notes)\n" +
////            "VALUES (null, \"Radisson\", \"1234 Fake St\", \"Milwaukee\", \"WI\", \"none\")");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DB_Accessor_SQL.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(DB_Accessor_SQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

}
