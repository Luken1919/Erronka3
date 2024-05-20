package Erronka_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		Connection conn 		= null;
		PreparedStatement stmt 	= null;
		ResultSet rs 			= null;
		erabIzena 				= erabiltzailea;

		try {   
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String sql = "SELECT * FROM Erabiltzailea WHERE Erab_Izena = ? AND Pasahitza = ? AND mota'Kudeatzailea'";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, erabiltzailea);
			stmt.setString(2, pasahitza);
			rs = stmt.executeQuery();

			if (rs.next()) {
				erabIzena 	= rs.getString("Izena") + " " + rs.getString("Abizena");
				erabKod 	= rs.getString("idErabiltzailea");

				return true;
			} else {
				// ez du topatu
				return false;
			}

		} catch (SQLException e) {    
			e.printStackTrace();
			return false;

		} finally {        
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();

			} catch (SQLException e) {
				e.printStackTrace();  
			}
		}
	}

	public static ArrayList<String> getBanatzaileak() {
		ArrayList<String> banatzaileak = new ArrayList<>(); {
			Connection conn 		= null;
			PreparedStatement stmt 	= null;
			ResultSet rs 			= null;



			try {   
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				

				String sql = "SELECT * FROM erabiltzailea WHERE mota='Banatzailea'";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
	                String izena = rs.getString("Izena");
	                String abizena = rs.getString("Abizena");
	                String idString = rs.getString("idErabiltzailea");
	                banatzaileak.add(izena + " " + abizena + " " + idString);
	            }
				
				rs.close();
				stmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return banatzaileak;
		}
	} 
	
	public ArrayList<String> getBanatzaielaHistoriala (String idString) {
    	ArrayList<String> BanatzaileHistoriala = new ArrayList<>(); {
			Connection conn 		= null;
			PreparedStatement stmt 	= null;
			ResultSet rs 			= null;



			try {   
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				

				String sql = "SELECT *  FROM entregatuta WHERE erabiltzailea_idErabiltzailea=  "+ idString + "";
				stmt = conn.prepareStatement(sql);
				System.out.println(stmt);
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
	                String idpakete = rs.getString("idEntregatuta");
	                String helbideapekete = rs.getString("Helbidea");
	                String entregadaraString =rs.getString("Entrega_data");
	                String entregaorduaString = rs.getString("Entrega_Ordua");
	             String paketetamainaString = rs.getString("Pakete_Tamaina");
	                BanatzaileHistoriala.add(idpakete + " " + helbideapekete + " " + entregadaraString + " " + entregaorduaString + " " + paketetamainaString );
	            }
				
				rs.close();
				stmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return BanatzaileHistoriala;
		}
	}
	
	public void sortuBanatzailea(String Izena, String Abizena, String Pasahitza) {
	    String sql = "INSERT INTO erabiltzailea (Izena, Abizena, Pasahitza, Mota) VALUES (?, ?, ?, 'Banatzailea')";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, Izena);
	        pstmt.setString(2, Abizena);
	        pstmt.setString(3, Pasahitza);

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	   public ArrayList<String> lortupaketeak () {
	    	ArrayList<String> paketeak = new ArrayList<>(); {
				Connection conn 		= null;
				PreparedStatement stmt 	= null;
				ResultSet rs 			= null;



				try {   
					conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					

					String sql = "SELECT * FROM paketea";
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
		                String idpakete = rs.getString("idPaketea");
		                String helbideapekete = rs.getString("Helbidea");
		                paketeak.add(idpakete + " " + helbideapekete);
		            }
					
					rs.close();
					stmt.close();
					conn.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				return paketeak;
			}
		}
	   public ArrayList<String> lortupaketeakhistoriala () {
	    	ArrayList<String> paketeakhistoriala = new ArrayList<>(); {
				Connection conn 		= null;
				PreparedStatement stmt 	= null;
				ResultSet rs 			= null;



				try {   
					conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					

					String sql = "SELECT entregatuta.idEntregatuta,entregatuta.Helbidea, erabiltzailea.izena, erabiltzailea.abizena  FROM entregatuta inner join erabiltzailea on erabiltzailea.idErabiltzailea = entregatuta.erabiltzailea_idErabiltzailea";
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
		                String idpakete = rs.getString("idEntregatuta");
		                String helbideapekete = rs.getString("Helbidea");
		                String izenabanatzaileaString = rs.getString("Izena");
		                String abizenabanatzaileaString = rs.getString("Abizena");
		                paketeakhistoriala.add(idpakete + " " + helbideapekete + " " + izenabanatzaileaString + " " + abizenabanatzaileaString);
		            }
					
					rs.close();
					stmt.close();
					conn.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				return paketeakhistoriala;
			}
		}
	   
	   public void sortuPaketea(String Bezero_zenbakia, String Helbidea, String Pakete_Tamaina) {
		    String sql = "INSERT INTO paketea (Bezero_zenbakia, Helbidea, Pakete_Tamaina, Erabiltzailea_idErabiltzailea) VALUES (?, ?, ?, '1')";
		    

		    try (Connection conn = getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
		    	System.out.println(pstmt);
		        pstmt.setString(1, Bezero_zenbakia);
		        pstmt.setString(2, Helbidea);
		        pstmt.setString(3, Pakete_Tamaina);

		        pstmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
}
