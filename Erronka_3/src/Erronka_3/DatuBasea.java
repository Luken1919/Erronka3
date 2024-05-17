package Erronka_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatuBasea {

	private static final String URL = "jdbc:mysql://localhost:3306/Erronka_pakAG";
	private static final String ErabiltzaileaDB = "root";
	private static final String PasahitzaDB = "";
	public static String erabIzena;
    public static String erabKod;

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, ErabiltzaileaDB, PasahitzaDB);
	}


	/**
	 * Frogatu erabiltzailea eta pasahitza egokiak diren edo ez adierazten duen metodoa.
	 *
	 * @param Erabiltzailea Erabiltzaile izena
	 * @param Pasahitza     Erabiltzailearen pasahitza
	 * @return True, frogaketa egokia bada; False, frogaketa okerra bada edo errorea gertatu bada.
	 */
	public static boolean frogatuErabiltzailea(String Erabiltzailea, String Pasahitza) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, ErabiltzaileaDB, PasahitzaDB);
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