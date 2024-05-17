/*
 * 16 may 2024
 */
package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Class SaioaHasi.
 */
public class SaioaHasiUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** content pane. */
	private JPanel contentPane;

	/** Erabiltzaile izena textfield. */
	private JTextField textFieldErabIzen;

	/** Pasahitza passwordfield. */
	private JPasswordField PasahitzaField;

String erab_izenaString;
String pasahitzaString;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaioaHasiUI frame = new SaioaHasiUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Saioa hasi frame-a sortu.
	 */
	public SaioaHasiUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * Irudiaren panela sortu
		 */
		JPanel Irudia = new JPanel();
		Irudia.setBounds(0, 0, 531, 711);
		contentPane.add(Irudia);
		Irudia.setLayout(null);

		/*
		 * irudia gehitu panelera
		 */
		JLabel lblirudia = new JLabel();
		ImageIcon irudia = new ImageIcon(
				new ImageIcon("Irudiak/Irudia.jpg").getImage().getScaledInstance(531, 711, Image.SCALE_DEFAULT));
		lblirudia.setIcon(irudia);
		lblirudia.setBounds(0, 0, 531, 711);
		Irudia.add(lblirudia);

		/*
		 * Saio hasteko panela
		 */
		JPanel saioaHasi = new JPanel();
		saioaHasi.setBounds(530, 0, 454, 711);
		contentPane.add(saioaHasi);
		saioaHasi.setLayout(null);

		/*
		 * Erabiltzaile izena sartzeko textfield-a
		 */
		textFieldErabIzen = new JTextField();
		textFieldErabIzen.setCaretColor(new Color(0, 0, 0));
		textFieldErabIzen.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(253, 194, 116)));
		textFieldErabIzen.setBounds(124, 420, 220, 30);
		saioaHasi.add(textFieldErabIzen);
		textFieldErabIzen.setColumns(10);
		/*
		 * Erabiltzailearen pasahitza sartzeko textfield-a
		 */
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblPasahitza.setBounds(124, 461, 220, 30);
		saioaHasi.add(lblPasahitza);
		/*
		 * Erabiltzaile izena label
		 */
		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile Izena");
		lblErabiltzaileIzena.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblErabiltzaileIzena.setBounds(124, 379, 220, 30);
		saioaHasi.add(lblErabiltzaileIzena);
		/*
		 * Saio hasi label
		 */
		JLabel lblTitulua = new JLabel("Saioa Hasi");
		lblTitulua.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulua.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblTitulua.setBounds(124, 24, 220, 30);
		saioaHasi.add(lblTitulua);

		/*
		 * Saio hasi irudia ezarri
		 */
		JLabel lblirudiSaioaHasi = new JLabel();
		ImageIcon irudiaSaioaHasi = new ImageIcon(
				new ImageIcon("Irudiak/logo1.png").getImage().getScaledInstance(275, 275, Image.SCALE_DEFAULT));
		lblirudiSaioaHasi.setIcon(irudiaSaioaHasi);
		lblirudiSaioaHasi.setBounds(87, 65, 275, 275);
		saioaHasi.add(lblirudiSaioaHasi);

		/*
		 * Saio hasteko botoia
		 */
		JButton btnSaioaHasi = new JButton("Saioa Hasi");
		btnSaioaHasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatuBasea konexioa = new DatuBasea();
				try {
					konexioa.getConnection();
					System.out.println("kaixo");
					
					System.out.println(lortuerabizena()); 
					System.out.println(lortupasahitza()); 
					System.out.println( konexioa.frogatuErabiltzailea(erab_izenaString, pasahitzaString));
					BanatzaileaUI banatzaileaUI = new BanatzaileaUI();
					if (konexioa.frogatuErabiltzailea(erab_izenaString, pasahitzaString)) {
						dispose();
						banatzaileaUI.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Pasahitza edo erabiltzailea ez da existitzen");
					}
					
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnSaioaHasi.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnSaioaHasi.setFont(new Font("Arial", Font.BOLD, 15));
		btnSaioaHasi.setBackground(new Color(111, 141, 158));
		btnSaioaHasi.setBounds(124, 605, 220, 30);
		saioaHasi.add(btnSaioaHasi);
		/*
		 * Erabiltzaileren psahitza sartzeko passwordfield
		 */
		PasahitzaField = new JPasswordField();
		PasahitzaField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(253, 194, 116)));
		PasahitzaField.setBounds(124, 502, 220, 30);
		saioaHasi.add(PasahitzaField);
	}
	
	public String lortuerabizena() {
		erab_izenaString = textFieldErabIzen.getText();
		return  erab_izenaString;
		
		
	}
	
	public String lortupasahitza() {
		pasahitzaString = PasahitzaField.getText();
		return pasahitzaString;
	}

}
