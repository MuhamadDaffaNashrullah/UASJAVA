package org.valo.database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connectdb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/valorant", "root", "");
            return connect; // Return the created Connection object
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
