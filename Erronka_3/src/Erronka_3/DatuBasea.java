package Erronka_3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatuBasea {

    public static String erabIzena;
    public static String erabKod;

    public static boolean frogatuErabiltzailea(String Erabiltzailea, String Pasahitza) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatuBaseKonexioa.getConnection();
            String sql = "SELECT * FROM Erabiltzailea WHERE ErabiltzaileIzena = ? AND Pasahitza = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Erabiltzailea);
            stmt.setString(2, Pasahitza);
            rs = stmt.executeQuery();

            if (rs.next()) {
                erabIzena = rs.getString("Izena") + " " + rs.getString("Abizena");
                erabKod = rs.getString("Kod");
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
