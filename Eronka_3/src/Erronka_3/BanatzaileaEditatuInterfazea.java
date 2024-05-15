package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BanatzaileaEditatuInterfazea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Izena;
	private JTextField Abizena;
	private JTextField ErabiltzaileIzena;
	private JTextField Pasahitza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BanatzaileaEditatuInterfazea frame = new BanatzaileaEditatuInterfazea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BanatzaileaEditatuInterfazea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulua = new JLabel("SARTU INFORMAZIO BERRIA");
		lblTitulua.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblTitulua.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulua.setBounds(10, 11, 363, 29);
		contentPane.add(lblTitulua);
		
		JLabel lblizena = new JLabel("Izena:");
		lblizena.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblizena.setBounds(30, 51, 200, 29);
		contentPane.add(lblizena);
		
		Izena = new JTextField();
		Izena.setBounds(30, 91, 200, 34);
		contentPane.add(Izena);
		Izena.setColumns(10);
		
		JLabel lblAbizena = new JLabel("Abizena:");
		lblAbizena.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblAbizena.setBounds(30, 136, 200, 29);
		contentPane.add(lblAbizena);
		
		Abizena = new JTextField();
		Abizena.setColumns(10);
		Abizena.setBounds(30, 176, 200, 34);
		contentPane.add(Abizena);
		
		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile Izena:");
		lblErabiltzaileIzena.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzaileIzena.setBounds(30, 221, 200, 29);
		contentPane.add(lblErabiltzaileIzena);
		
		ErabiltzaileIzena = new JTextField();
		ErabiltzaileIzena.setColumns(10);
		ErabiltzaileIzena.setBounds(30, 261, 200, 34);
		contentPane.add(ErabiltzaileIzena);
		
		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPasahitza.setBounds(30, 306, 200, 29);
		contentPane.add(lblPasahitza);
		
		Pasahitza = new JTextField();
		Pasahitza.setColumns(10);
		Pasahitza.setBounds(30, 346, 200, 34);
		contentPane.add(Pasahitza);
		
		JButton btnEditatu = new JButton("Editatu");
		btnEditatu.setBounds(250, 400, 89, 29);
		contentPane.add(btnEditatu);
	}
}
