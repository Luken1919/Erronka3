package Erronka_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatuBasea {

    public static String erabIzena;
    public static String erabKod;
    private static final String URL = "jdbc:mysql://localhost:3306/Erronka_pakAG";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
    
    
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    
    public static boolean frogatuErabiltzailea(String erabiltzailea, String pasahitza) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        erabIzena = erabiltzailea;
        try {
            // Connect to the database
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Prepare a secure SQL statement using PreparedStatement
            String sql = "SELECT * FROM Erabiltzailea WHERE Erab_Izena = ? AND Pasahitza = ?";
            stmt = conn.prepareStatement(sql);

            // Set parameters to prevent SQL injection attacks
            stmt.setString(1, erabiltzailea);
            stmt.setString(2, pasahitza);

            // Execute the query and get the results
            rs = stmt.executeQuery();

            if (rs.next()) {
				erabIzena = rs.getString("Izena") + " " + rs.getString("Abizena");
				erabKod = rs.getString("idErabiltzailea");
				
			}
            return rs.next();
            // Check if a user record is found
             // Returns true if a record exists, false otherwise

        } catch (SQLException e) {
            // Handle database errors gracefully
            e.printStackTrace();
            return false;  // Indicate authentication failure in case of errors
        } finally {
            // Close resources in a finally block to ensure proper cleanup
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();  // Log or handle closing errors
            }
        }
    }
}
