/*
 * 22 may 2024
 */
package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private JPanel pnlGuztia;

	/** Izen abizena. */
	String IzenAbizena = DatuBasea.erabIzena;

	/** Pakete guztiak. */
	JList<String> PaketeGuztiak = new JList<>();

	/** Banatzailearen paketeak. */
	JList<String> BanatzailearenPaketeak = new JList<>();

	DatuBasea konexioa = new DatuBasea();

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
		pnlGuztia = new JPanel();
		pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlGuztia);
		pnlGuztia.setLayout(null);

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(253, 194, 116)));
		pnlMenu.setLayout(null);
		pnlMenu.setBounds(0, 0, 984, 60);

		/*
		 * Erabiltzailearen izena erakusteko label-a
		 */
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea: " + IzenAbizena);
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 316, 38);

		/*
		 * Paketeak frame-era joateko botoia
		 */
		JButton btnPaketea = new JButton("Paketeak");
		btnPaketea.setBackground(new Color(111, 141, 158));
		btnPaketea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketea.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketea.setBounds(854, 11, 120, 38);
		btnPaketea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeaUI paketea = new PaketeaUI();
				paketea.setVisible(true);
				dispose();
			}
		});

		/*
		 * Banatzaile frame-era joateko botoia
		 */
		JButton btnBanatzailea = new JButton("Banatzailea");
		btnBanatzailea.setBackground(new Color(111, 141, 158));
		btnBanatzailea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnBanatzailea.setFont(new Font("Arial", Font.BOLD, 15));
		btnBanatzailea.setBounds(708, 11, 120, 38);
		btnBanatzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaUI banatzailea = new BanatzaileaUI();
				banatzailea.setVisible(true);
				dispose();
			}
		});

		JPanel pnlPaketeakEsleitu = new JPanel();
		pnlPaketeakEsleitu.setBounds(0, 60, 984, 651);
		pnlPaketeakEsleitu.setLayout(null);

		/*
		 * Banatzaile guztiake rakutsiko dituen ComboBox-a
		 */
		JComboBox<String> Banatzaileak = new JComboBox<>();
		Banatzaileak.setBackground(new Color(111, 141, 158));
		Banatzaileak.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		Banatzaileak.setBounds(10, 11, 200, 35);
		Banatzaileak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aukeratutakoerabiltzailea = (String) Banatzaileak.getSelectedItem();
				String[] parts = aukeratutakoerabiltzailea.split(" ");
				String id = parts[parts.length - 1];
				lortuBanatzaiearenPaketeak(id);
			}
		});
		BanatzaileaUI banatzailea = new BanatzaileaUI();
		ArrayList<String> aukeratuBanatzaileak = banatzailea.lortuBanatzaileakzerrenda();
		for (String item : aukeratuBanatzaileak) {
			Banatzaileak.addItem(item);
		}

		/*
		 * Sortutako pakete guztiak erakutsiko diren zerrenda
		 */
		JScrollPane spPaketeZerrenda = new JScrollPane();
		spPaketeZerrenda.setBounds(100, 100, 300, 450);
		PaketeGuztiak = new JList<>();
		spPaketeZerrenda.setViewportView(PaketeGuztiak);
		PaketeGuztiak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		PaketeGuztiak.setBounds(100, 100, 300, 450);
		lortuPaketeGuztiak();

		/*
		 * Banatzaileari ezarritako paketeak ikusiko diren zerrenda
		 */
		JScrollPane spBanatzaielarenPaketak = new JScrollPane();
		spBanatzaielarenPaketak.setBounds(622, 100, 300, 450);
		BanatzailearenPaketeak = new JList<>();
		spBanatzaielarenPaketak.setViewportView(BanatzailearenPaketeak);
		BanatzailearenPaketeak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		BanatzailearenPaketeak.setBounds(622, 100, 300, 450);

		/*
		 * Pakete guztietatik banatzailearen paketera, aukeratutako paketea gehitzeko
		 * botoia
		 */
		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.setForeground(new Color(0, 0, 0));
		btnGehitu.setBackground(new Color(111, 141, 158));
		btnGehitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnGehitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnGehitu.setBounds(443, 140, 140, 30);
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String aukeratutakopaketea = (String) PaketeGuztiak.getSelectedValue();
				if (aukeratutakopaketea == null) {
					JOptionPane.showMessageDialog(null, "Aukeratu paketea");
				} else {
					String[] parts = aukeratutakopaketea.split(" ");
					String id = parts[0];
					String aukeratutakoerabiltzailea = (String) Banatzaileak.getSelectedItem();
					String[] parts2 = aukeratutakoerabiltzailea.split(" ");
					String id2 = parts2[parts2.length - 1];
					konexioa.paketeakEsleituGehitu(id2, id);
					lortuPaketeGuztiak();
					lortuBanatzaiearenPaketeak(id2);
				}

			}
		});

		/*
		 * Banatzailearen paketeetatik pakete gutietara, aukeratutako paketea kentzeko
		 * botoia
		 */
		JButton btnKendu = new JButton("Kendu");
		btnKendu.setBackground(new Color(111, 141, 158));
		btnKendu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnKendu.setFont(new Font("Arial", Font.BOLD, 15));
		btnKendu.setBounds(443, 189, 140, 30);
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String aukeratutakopaketea = (String) BanatzailearenPaketeak.getSelectedValue();
				if (aukeratutakopaketea == null) {
					JOptionPane.showMessageDialog(null, "Banatzailearen Paketea  aukeratu");
				}

				else {
					String[] parts = aukeratutakopaketea.split(" ");
					String id = parts[0];

					konexioa.paketeakEsleituKendu(id);

					lortuPaketeGuztiak();
					String aukeratutakoerabiltzailea2 = (String) Banatzaileak.getSelectedItem();
					String[] parts2 = aukeratutakoerabiltzailea2.split(" ");
					String id2 = parts2[parts2.length - 1];
					lortuBanatzaiearenPaketeak(id2);
				}

			}
		});

		/*
		 * Pakete guztien labela
		 */
		JLabel lblPaketeGuztiak = new JLabel("Pakete guztiak");
		lblPaketeGuztiak.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPaketeGuztiak.setBounds(100, 59, 150, 30);

		/*
		 * Banatzaileen paketeen labela
		 */
		JLabel lblBanaPaketeak = new JLabel("Banatzailearen Paketeak");
		lblBanaPaketeak.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblBanaPaketeak.setBounds(622, 67, 225, 22);

		pnlGuztia.add(pnlMenu);
		pnlGuztia.add(pnlPaketeakEsleitu);

		pnlMenu.add(lblErabiltzailea);
		pnlMenu.add(btnBanatzailea);
		pnlMenu.add(btnPaketea);

		pnlPaketeakEsleitu.add(Banatzaileak);
		pnlPaketeakEsleitu.add(btnGehitu);
		pnlPaketeakEsleitu.add(btnKendu);
		pnlPaketeakEsleitu.add(lblPaketeGuztiak);
		pnlPaketeakEsleitu.add(lblBanaPaketeak);
		pnlPaketeakEsleitu.add(spPaketeZerrenda);
		pnlPaketeakEsleitu.add(spBanatzaielarenPaketak);

	}

	/**
	 * Lortupaketeakguztiak.
	 */
	public void lortuPaketeGuztiak() {
		ArrayList<String> paketeaklist = konexioa.paketeakEsleituLortuPeketeak();
		DefaultListModel<String> model = new DefaultListModel<>();

		for (String paketea : paketeaklist) {
			model.addElement(paketea);
		}

		PaketeGuztiak.setModel(model);
	}

	/**
	 * Lortu banatzaileakhist.
	 *
	 * @param id , banatzailearen id-a
	 */
	private void lortuBanatzaiearenPaketeak(String id) {

		ArrayList<String> banatzailearenPaketeak = konexioa.paketeakEsleituBanatzailearenPaketakLortu(id);
		DefaultListModel<String> model = new DefaultListModel<>();

		for (String banatzailea : banatzailearenPaketeak) {
			model.addElement(banatzailea);
		}

		BanatzailearenPaketeak.setModel(model);
	}

}
