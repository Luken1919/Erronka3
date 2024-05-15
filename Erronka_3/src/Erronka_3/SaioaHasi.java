package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JPasswordField;

public class SaioaHasi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldErabIzen;
	private JPasswordField PasahitzaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaioaHasi frame = new SaioaHasi();
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
	public SaioaHasi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Irudia = new JPanel();
		Irudia.setBounds(0, 0, 531, 711);
		contentPane.add(Irudia);
		Irudia.setLayout(null);
		
		JLabel lblirudia = new JLabel();
		ImageIcon irudia= new ImageIcon(new ImageIcon("C:\\Users\\1ag3.julemeri\\Downloads\\Irudia.jpg").getImage().getScaledInstance(531, 711, Image.SCALE_DEFAULT));
		lblirudia.setIcon(irudia);
		lblirudia.setBounds(0, 0, 531, 711);
		Irudia.add(lblirudia);
		
		JPanel saioaHasi = new JPanel();
		saioaHasi.setBounds(530, 0, 454, 711);
		contentPane.add(saioaHasi);
		saioaHasi.setLayout(null);
		
		textFieldErabIzen = new JTextField();
		textFieldErabIzen.setCaretColor(new Color(0, 0, 0));
		textFieldErabIzen.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(253, 194, 116)));
		textFieldErabIzen.setBounds(124, 420, 220, 30);
		saioaHasi.add(textFieldErabIzen);
		textFieldErabIzen.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pasahitza");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(124, 461, 220, 30);
		saioaHasi.add(lblNewLabel);
		
		JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile Izena");
		lblErabiltzaileIzena.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblErabiltzaileIzena.setBounds(124, 379, 220, 30);
		saioaHasi.add(lblErabiltzaileIzena);
		
		JLabel lblTitulua = new JLabel("Saioa Hasi");
		lblTitulua.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulua.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblTitulua.setBounds(124, 24, 220, 30);
		saioaHasi.add(lblTitulua);
		
		JLabel lblirudiSaioaHasi = new JLabel();
		ImageIcon irudiaSaioaHasi= new ImageIcon(new ImageIcon("C:\\Users\\1ag3.julemeri\\Downloads\\logo1.png").getImage().getScaledInstance(275, 275, Image.SCALE_DEFAULT));
		lblirudiSaioaHasi.setIcon(irudiaSaioaHasi);
		lblirudiSaioaHasi.setBounds(87, 65, 275, 275);
		saioaHasi.add(lblirudiSaioaHasi);
		
		JButton btnSaioaHasi = new JButton("Saioa Hasi");
		btnSaioaHasi.setFont(new Font("Arial", Font.BOLD, 15));
		btnSaioaHasi.setBackground(new Color(253, 194, 116));
		btnSaioaHasi.setBounds(124, 605, 220, 30);
		saioaHasi.add(btnSaioaHasi);
		
		PasahitzaField = new JPasswordField();
		PasahitzaField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(253, 194, 116)));
		PasahitzaField.setBounds(124, 502, 220, 30);
		saioaHasi.add(PasahitzaField);
	}
}
