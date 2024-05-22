/*
 * 22 may 2024
 */
package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * Class Esleipena.
 */
public class EsleipenaUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** content pane. */
	private JPanel contentPane;

	/** Izen abizena. */
	String IzenAbizena = DatuBasea.erabIzena;

	/** Pakete guztiak. */
	JList<String> PaketeGuztiak = new JList<>();

	/** Banatzailearen paketeak. */
	JList<String> BanatzailearenPaketeak = new JList<>();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EsleipenaUI frame = new EsleipenaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Esleipen Framea sortu.
	 */
	public EsleipenaUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 984, 60);
		contentPane.add(panel);

		/*
		 * Erabiltzailearen izena erakusteko label-a
		 */
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea: " + IzenAbizena);
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 316, 38);
		panel.add(lblErabiltzailea);

		/*
		 * Paketeak frame-era joateko botoia
		 */
		JButton btnPaketea = new JButton("Paketeak");
		btnPaketea.setBackground(new Color(111, 141, 158));
		btnPaketea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeaUI paketea = new PaketeaUI();
				paketea.setVisible(true);
				dispose();
			}
		});
		btnPaketea.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketea.setBounds(854, 11, 120, 38);
		panel.add(btnPaketea);

		/*
		 * Banatzaile frame-era joateko botoia
		 */
		JButton btnBanatzailea = new JButton("Banatzailea");
		btnBanatzailea.setBackground(new Color(111, 141, 158));
		btnBanatzailea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnBanatzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaUI banatzailea = new BanatzaileaUI();
				banatzailea.setVisible(true);
				dispose();
			}
		});
		btnBanatzailea.setFont(new Font("Arial", Font.BOLD, 15));
		btnBanatzailea.setBounds(708, 11, 120, 38);
		panel.add(btnBanatzailea);

		JPanel PaketeakEsleitu = new JPanel();
		PaketeakEsleitu.setBounds(0, 60, 984, 651);
		contentPane.add(PaketeakEsleitu);
		PaketeakEsleitu.setLayout(null);

		/*
		 * Banatzaile guztiake rakutsiko dituen ComboBox-a
		 */
		JComboBox<String> Banatzaileak = new JComboBox<>();
		Banatzaileak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aukeratutakoerabiltzailea = (String) Banatzaileak.getSelectedItem();
				String[] parts = aukeratutakoerabiltzailea.split(" ");
				String id = parts[parts.length - 1];
				System.out.println("Aukeratutakoa: " + id);
				lortuBanatzaileakhist(id);
			}
		});
		BanatzaileaUI bantzaileBanatzaileaUI = new BanatzaileaUI();
		ArrayList<String> historial = bantzaileBanatzaileaUI.lortuBanatzaileakzerrenda();
		for (String item : historial) {
			Banatzaileak.addItem(item);
		}
		Banatzaileak.setBackground(new Color(111, 141, 158));
		Banatzaileak.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		Banatzaileak.setBounds(10, 11, 200, 35);
		PaketeakEsleitu.add(Banatzaileak);

		/*
		 * Sortutako pakete guztiak erakutsiko diren zerrenda
		 */
		PaketeGuztiak = new JList<>();

		lortupaketeakguztiak();

		PaketeGuztiak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		PaketeGuztiak.setBounds(100, 100, 300, 450);
		PaketeakEsleitu.add(PaketeGuztiak);

		/*
		 * Banatzaileari ezarritako paketeak ikusiko diren zerrenda
		 */
		BanatzailearenPaketeak = new JList<>();

		BanatzailearenPaketeak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		BanatzailearenPaketeak.setBounds(622, 100, 300, 450);
		PaketeakEsleitu.add(BanatzailearenPaketeak);

		/*
		 * Pakete guztietatik banatzailearen paketera, aukeratutako paketea gehitzeko
		 * botoia
		 */
		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatuBasea konexioaBasea = new DatuBasea();
				String aukeratutakopaketea = (String) PaketeGuztiak.getSelectedValue();
				String[] parts = aukeratutakopaketea.split(" ");
				String id = parts[0];
				String aukeratutakoerabiltzailea = (String) Banatzaileak.getSelectedItem();
				String[] parts2 = aukeratutakopaketea.split(" ");
				String iderab = parts[0];
				System.out.println("Aukeratutakoaa: " + iderab);
				System.out.println("Aukeratutakoa: " + id);
				konexioaBasea.paketeakEsleituGehitu(iderab, id);
				lortupaketeakguztiak();
				String aukeratutakoerabiltzailea3 = (String) Banatzaileak.getSelectedItem();
				String[] parts3 = aukeratutakoerabiltzailea3.split(" ");
				String id3 = parts3[parts3.length - 1];
				System.out.println("Aukeratutakoa3: " + id3);
				lortuBanatzaileakhist(id3);

			}
		});
		btnGehitu.setForeground(new Color(0, 0, 0));
		btnGehitu.setBackground(new Color(111, 141, 158));
		btnGehitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnGehitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnGehitu.setBounds(443, 140, 140, 30);
		PaketeakEsleitu.add(btnGehitu);
		/*
		 * Banatzailearen paketeetatik pakete gutietara, aukeratutako paketea kentzeko
		 * botoia
		 */
		JButton btnKendu = new JButton("Kendu");
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatuBasea konexioaBasea = new DatuBasea();
				String aukeratutakopaketea = (String) BanatzailearenPaketeak.getSelectedValue();
				String[] parts = aukeratutakopaketea.split(" ");
				String id = parts[0];

				System.out.println("Aukeratutakoa: " + id);
				konexioaBasea.paketeakEsleituKendu(id);

				lortupaketeakguztiak();
				String aukeratutakoerabiltzailea2 = (String) Banatzaileak.getSelectedItem();
				String[] parts2 = aukeratutakoerabiltzailea2.split(" ");
				String id2 = parts2[parts2.length - 1];
				System.out.println("Aukeratutakoa2: " + id2);
				lortuBanatzaileakhist(id2);

			}
		});
		btnKendu.setBackground(new Color(111, 141, 158));
		btnKendu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnKendu.setFont(new Font("Arial", Font.BOLD, 15));
		btnKendu.setBounds(443, 189, 140, 30);
		PaketeakEsleitu.add(btnKendu);

		/*
		 * Banatzailearen paketeetatik pakete gutietara, pakete guztiak kentzeko botoia
		 */
		JButton btnKenduDneak = new JButton("Kendu Dneak");
		btnKenduDneak.setBackground(new Color(111, 141, 158));
		btnKenduDneak.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnKenduDneak.setFont(new Font("Arial", Font.BOLD, 15));
		btnKenduDneak.setBounds(443, 285, 140, 30);
		PaketeakEsleitu.add(btnKenduDneak);

		/*
		 * Pakete guztietatik banatzailearen paketera, pakete guztiak gehitzeko botoia
		 */

		JButton btnGehituDenak = new JButton("Gehitu Denak");
		btnGehituDenak.setBackground(new Color(111, 141, 158));
		btnGehituDenak.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnGehituDenak.setFont(new Font("Arial", Font.BOLD, 15));
		btnGehituDenak.setBounds(443, 236, 140, 30);
		PaketeakEsleitu.add(btnGehituDenak);

		/*
		 * Pakete guztien labela
		 */
		JLabel lblPaketeGuztiak = new JLabel("Pakete guztiak");
		lblPaketeGuztiak.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPaketeGuztiak.setBounds(100, 59, 150, 30);
		PaketeakEsleitu.add(lblPaketeGuztiak);

		/*
		 * Banatzaileen paketeen labela
		 */
		JLabel lblBanaPaketeak = new JLabel("Banatzailearen Paketeak");
		lblBanaPaketeak.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblBanaPaketeak.setBounds(622, 67, 225, 22);
		PaketeakEsleitu.add(lblBanaPaketeak);
	}

	/**
	 * Lortupaketeakguztiak.
	 */
	public void lortupaketeakguztiak() {
		DatuBasea konexioaBasea = new DatuBasea();
		ArrayList<String> paketeaklist = konexioaBasea.paketeakEsleituLortuPeketeak();
		DefaultListModel<String> model = new DefaultListModel<>();

		for (String paketea : paketeaklist) {
			model.addElement(paketea);
			System.out.println(paketea);
		}

		PaketeGuztiak.setModel(model);
	}

	/**
	 * Lortu banatzaileakhist.
	 *
	 * @param id , banatzailearen id-a
	 */
	private void lortuBanatzaileakhist(String id) {
		DatuBasea losrtubanatzailehsit = new DatuBasea();

		ArrayList<String> banatzaileakhistList = losrtubanatzailehsit.paketeakEsleituBanatzailearenPaketakLortu(id);
		DefaultListModel<String> model = new DefaultListModel<>();

		for (String banatzailea : banatzaileakhistList) {
			model.addElement(banatzailea);
		}

		BanatzailearenPaketeak.setModel(model);
	}

}
