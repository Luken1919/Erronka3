/*
 * 16 may 2024
 * 
 */
package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class Banatzailea.
 */
public class Banatzailea extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Content pane. */
	private JPanel contentPane;

	/** Izena textfield. */
	private JTextField Izena;

	/** Abizena textfield. */
	private JTextField Abizena;

	/** Erabiltzaile izena textfield. */
	private JTextField ErabiltzaileIzena;

	/** Pasahitza passwordfield. */
	private JPasswordField PasahitzaField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Banatzailea frame = new Banatzailea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Baanatzailearen frame-a sortu.
	 */
	public Banatzailea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 60);
		contentPane.add(panel);
		panel.setLayout(null);

		/*
		 * Erabiltzaile izenaren label-a
		 */
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 120, 38);
		panel.add(lblErabiltzailea);

		/*
		 * Pakete Panelera joateko botoia
		 */
		JButton btnPaketea = new JButton("Paketeak");
		btnPaketea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paketea paketea = new Paketea();
				paketea.setVisible(true);
				dispose();
			}
		});
		btnPaketea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketea.setBackground(new Color(255, 255, 255));
		btnPaketea.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketea.setBounds(854, 11, 120, 38);
		panel.add(btnPaketea);

		/*
		 * Paketeak esleitu panelera joateko botoia
		 */

		JButton btnPaketeaEsleitu = new JButton("Paketeak Esleitu");
		btnPaketeaEsleitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketeaEsleitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Esleipena esleitu = new Esleipena();
				esleitu.setVisible(true);
				dispose();
			}
		});
		btnPaketeaEsleitu.setBackground(new Color(255, 255, 255));
		btnPaketeaEsleitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketeaEsleitu.setBounds(638, 11, 190, 38);
		panel.add(btnPaketeaEsleitu);

		/*
		 * Banatzaileak erakutsiko diren panel-a
		 */

		JPanel banatzaileak = new JPanel();
		banatzaileak.setBounds(0, 59, 492, 652);
		contentPane.add(banatzaileak);
		banatzaileak.setLayout(null);

		JLabel lblBanatzailea = new JLabel("Banatzaileak");
		lblBanatzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblBanatzailea.setBounds(10, 11, 352, 38);
		banatzaileak.add(lblBanatzailea);

		/*
		 * Banatzaileen zerrenda
		 */

		JList Banatzaileak = new JList();
		Banatzaileak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		Banatzaileak.setBounds(0, 60, 362, 592);
		banatzaileak.add(Banatzaileak);

		/*
		 * Banatzaileak sortzeko panela irikitzen duen botoia
		 */
		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnGehitu.setBackground(new Color(255, 255, 255));
		btnGehitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnGehitu.setBounds(372, 110, 110, 40);
		banatzaileak.add(btnGehitu);
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaGehitu gehitu = new BanatzaileaGehitu();
				gehitu.setVisible(true);
			}
		});

		/*
		 * Banatzaileak kentzeko panela irikitzen duen botoia
		 */
		JButton btnKendu = new JButton("Kendu");
		btnKendu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnKendu.setBackground(new Color(255, 255, 255));
		btnKendu.setFont(new Font("Arial", Font.BOLD, 15));
		btnKendu.setBounds(372, 299, 110, 40);
		banatzaileak.add(btnKendu);
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaKendu kendu = new BanatzaileaKendu();
				kendu.setVisible(true);
			}
		});

		/*
		 * Banatzaileak editatzeko panela irikitzen duen botoia
		 */
		JButton btnEditatu = new JButton("Editatu");
		btnEditatu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnEditatu.setBackground(new Color(255, 255, 255));
		btnEditatu.setFont(new Font("Arial", Font.BOLD, 15));
		btnEditatu.setBounds(372, 499, 110, 40);
		banatzaileak.add(btnEditatu);
		btnEditatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaEditatu editatu = new BanatzaileaEditatu();
				editatu.setVisible(true);
			}
		});

		/*
		 * Sortutako banatzaileak erakutsiko diren panela
		 */
		JPanel historiala = new JPanel();
		historiala.setBounds(491, 59, 493, 652);
		contentPane.add(historiala);
		historiala.setLayout(null);

		JLabel lblHistoriala = new JLabel("Autatutako banatzailearen historiala");
		lblHistoriala.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblHistoriala.setBounds(10, 11, 352, 38);
		historiala.add(lblHistoriala);

		/*
		 * Banatzaileen zerrenda
		 */
		JList BanatzaileHistoriala = new JList();
		BanatzaileHistoriala.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		BanatzaileHistoriala.setBounds(0, 62, 362, 590);
		historiala.add(BanatzaileHistoriala);

		/*
		 * Banatzaileak filtratzeko panela irikitzen duen botoia
		 */

		JButton btnFiltratu = new JButton("Filtratu");
		btnFiltratu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnFiltratu.setBackground(new Color(255, 255, 255));
		btnFiltratu.setFont(new Font("Arial", Font.BOLD, 15));
		btnFiltratu.setBounds(373, 199, 110, 40);
		historiala.add(btnFiltratu);

		/*
		 * Banatzaileak ordenatzeko panela irikitzen duen botoia
		 */
		JButton btnOrdenatu = new JButton("Ordenatu");
		btnOrdenatu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnOrdenatu.setBackground(new Color(255, 255, 255));
		btnOrdenatu.setFont(new Font("Arial", Font.BOLD, 15));
		btnOrdenatu.setBounds(373, 405, 110, 40);
		historiala.add(btnOrdenatu);

	}

	/**
	 * Class BanatzaileaGehitu.
	 */
	class BanatzaileaGehitu extends JFrame {

		/**
		 * Instantiates a new banatzailea gehitu.
		 */
		public BanatzaileaGehitu() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

			/*
			 * Banatzailearen izena zehazteko label-a
			 */

			JLabel lblizena = new JLabel("Izena:");
			lblizena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblizena.setBounds(30, 51, 200, 29);
			contentPane.add(lblizena);
			/*
			 * Idatziko den izena textfield-ean joango da
			 */
			Izena = new JTextField();
			Izena.setBounds(30, 91, 200, 34);
			contentPane.add(Izena);
			Izena.setColumns(10);
			/*
			 * Banatzailearen abizena zehazteko label-a
			 */

			JLabel lblAbizena = new JLabel("Abizena:");
			lblAbizena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblAbizena.setBounds(30, 136, 200, 29);
			contentPane.add(lblAbizena);
			/*
			 * Idatziko den abizena textfield-ean joango da
			 */
			Abizena = new JTextField();
			Abizena.setColumns(10);
			Abizena.setBounds(30, 176, 200, 34);
			contentPane.add(Abizena);
			/*
			 * Banatzailearen pasahitza zehazteko label-a
			 */
			JLabel lblPasahitza = new JLabel("Pashitza:");
			lblPasahitza.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblPasahitza.setBounds(30, 221, 200, 29);
			contentPane.add(lblPasahitza);
			/*
			 * Banatzailearen pasahitza sartzeko textfield berezia
			 */
			PasahitzaField = new JPasswordField();
			PasahitzaField.setColumns(10);
			PasahitzaField.setBounds(30, 261, 200, 34);
			contentPane.add(PasahitzaField);

			/*
			 * Banatzailea sortzeko botoia
			 */
			JButton btnEditatu = new JButton("Sortu");
			btnEditatu.setBounds(250, 400, 89, 29);
			contentPane.add(btnEditatu);
		}
	}

	/**
	 * Class BanatzaileaKendu.
	 */
	class BanatzaileaKendu extends JFrame {

		/**
		 * Instantiates a new banatzailea kendu.
		 */
		public BanatzaileaKendu() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 362, 458);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel ErabIzenaLabel = new JLabel("Erabiltzailea");
			ErabIzenaLabel.setFont(new Font("Arial", Font.BOLD, 15));
			ErabIzenaLabel.setBounds(123, 24, 90, 36);
			contentPane.add(ErabIzenaLabel);

			/*
			 * Banatzaileak erakusten dituen combobox-a
			 */
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(81, 68, 158, 22);
			contentPane.add(comboBox);

			/*
			 * Aukeratutako banatzailea ezabatzeko botoia
			 */
			JButton EzabatuButton = new JButton("Ezabatu");
			EzabatuButton.setFont(new Font("Arial Black", Font.BOLD, 15));
			EzabatuButton.setBounds(210, 363, 126, 45);
			contentPane.add(EzabatuButton);
		}
	}

	/**
	 * Class BanatzaileaEditatu.
	 */
	class BanatzaileaEditatu extends JFrame {

		/**
		 * Instantiates a new banatzailea editatu.
		 */
		public BanatzaileaEditatu() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

			/*
			 * Banatzailearen izena editatzeko label-a
			 */

			JLabel lblizena = new JLabel("Izena:");
			lblizena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblizena.setBounds(30, 51, 200, 29);
			contentPane.add(lblizena);

			/*
			 * Izen berria sartzeko textfield-a
			 */
			Izena = new JTextField();
			Izena.setBounds(30, 91, 200, 34);
			contentPane.add(Izena);
			Izena.setColumns(10);

			/*
			 * Banatzailearen abizena editatzeko label-a
			 */
			JLabel lblAbizena = new JLabel("Abizena:");
			lblAbizena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblAbizena.setBounds(30, 136, 200, 29);
			contentPane.add(lblAbizena);
			/*
			 * Abizen berria sartzeko textfield-a
			 */
			Abizena = new JTextField();
			Abizena.setColumns(10);
			Abizena.setBounds(30, 176, 200, 34);
			contentPane.add(Abizena);
			/*
			 * Banatzailearen erabiltzaile izena editatzeko label-a
			 */
			JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile Izena:");
			lblErabiltzaileIzena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblErabiltzaileIzena.setBounds(30, 221, 200, 29);
			contentPane.add(lblErabiltzaileIzena);
			/*
			 * Idatziko den erabiltzaile izena textfield-ean joango da
			 */

			ErabiltzaileIzena = new JTextField();
			ErabiltzaileIzena.setColumns(10);
			ErabiltzaileIzena.setBounds(30, 261, 200, 34);
			contentPane.add(ErabiltzaileIzena);

			/*
			 * Banatzailearen pasahitza editatzeko label-a
			 */
			JLabel lblPasahitza = new JLabel("Pasahitza:");
			lblPasahitza.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblPasahitza.setBounds(30, 306, 200, 29);
			contentPane.add(lblPasahitza);
			/*
			 * Idatziko den pasahitza textfield-ean joango da
			 */
			PasahitzaField = new JPasswordField();
			PasahitzaField.setColumns(10);
			PasahitzaField.setBounds(30, 346, 200, 34);
			contentPane.add(PasahitzaField);

			/*
			 * sartutako informazioa editatzeko botoia
			 */
			JButton btnEditatu = new JButton("Editatu");
			btnEditatu.setBounds(250, 400, 89, 29);
			contentPane.add(btnEditatu);
		}
	}

}
