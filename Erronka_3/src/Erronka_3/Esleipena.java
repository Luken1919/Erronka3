/*
 * 16 may 2024
 */
package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * Class Esleipena.
 */
public class Esleipena extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** content pane. */
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Esleipena frame = new Esleipena();
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
	public Esleipena() {
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
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 126, 38);
		panel.add(lblErabiltzailea);

		/*
		 * Paketeak frame-era joateko botoia
		 */
		JButton btnPaketea = new JButton("Paketeak");
		btnPaketea.setBackground(new Color(0, 86, 179));
		btnPaketea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paketea paketea = new Paketea();
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
		btnBanatzailea.setBackground(new Color(0, 86, 179));
		btnBanatzailea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnBanatzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Banatzailea banatzailea = new Banatzailea();
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
		JComboBox Banatzaileak = new JComboBox();
		Banatzaileak.setBackground(new Color(0, 86, 179));
		Banatzaileak.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		Banatzaileak.setBounds(10, 11, 200, 35);
		PaketeakEsleitu.add(Banatzaileak);

		/*
		 * Sortutako pakete guztiak erakutsiko diren zerrenda
		 */
		JList PaketeGuztiak = new JList();
		PaketeGuztiak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		PaketeGuztiak.setBounds(100, 100, 300, 450);
		PaketeakEsleitu.add(PaketeGuztiak);

		/*
		 * Banatzaileari ezarritako paketeak ikusiko diren zerrenda
		 */
		JList BanatzailearenPaketeak = new JList();
		BanatzailearenPaketeak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		BanatzailearenPaketeak.setBounds(622, 100, 300, 450);
		PaketeakEsleitu.add(BanatzailearenPaketeak);

		/*
		 * Pakete guztietatik banatzailearen paketera, aukeratutako paketea gehitzeko
		 * botoia
		 */
		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.setForeground(new Color(255, 255, 255));
		btnGehitu.setBackground(new Color(0, 86, 179));
		btnGehitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnGehitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnGehitu.setBounds(443, 140, 140, 30);
		PaketeakEsleitu.add(btnGehitu);
		/*
		 * Banatzailearen paketeetatik pakete gutietara, aukeratutako paketea kentzeko
		 * botoia
		 */
		JButton btnKendu = new JButton("Kendu");
		btnKendu.setBackground(new Color(0, 86, 179));
		btnKendu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnKendu.setFont(new Font("Arial", Font.BOLD, 15));
		btnKendu.setBounds(443, 189, 140, 30);
		PaketeakEsleitu.add(btnKendu);

		/*
		 * Banatzailearen paketeetatik pakete gutietara, pakete guztiak kentzeko botoia
		 */
		JButton btnKenduDneak = new JButton("Kendu Dneak");
		btnKenduDneak.setBackground(new Color(0, 86, 179));
		btnKenduDneak.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnKenduDneak.setFont(new Font("Arial", Font.BOLD, 15));
		btnKenduDneak.setBounds(443, 285, 140, 30);
		PaketeakEsleitu.add(btnKenduDneak);

		/*
		 * Pakete guztietatik banatzailearen paketera, pakete guztiak gehitzeko botoia
		 */

		JButton btnGehituDenak = new JButton("Gehitu Denak");
		btnGehituDenak.setBackground(new Color(0, 86, 179));
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
}
