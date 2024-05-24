/*
 * 16 may 2024
 * 
 */
package Erronka_3;

import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import com.mysql.cj.xdevapi.Statement;

/**
 * 
 * 
 */
public class PaketeaUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** content pane. */
	private JPanel pnlGuztia;

	/** Telefonoa textfield. */
	private JTextField TelefonoatextField;

	/** Helbidea textfield. */
	private JTextField HelbideatextField;

	private String IzenAbizena = DatuBasea.erabIzena;

	private JList<String> PaketeZerrenda;

	private JList<String> PaketeHistoriala;

	private DatuBasea konexioa = new DatuBasea();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaketeaUI frame = new PaketeaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Paketearen frame-a sortu.
	 */
	public PaketeaUI() {
		setTitle("Paketeen informazioa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		 * Erabiltzailearen izena erakutsiko duen label-a
		 */
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea: " + IzenAbizena);
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 298, 38);

		/*
		 * Banatzaile frame-era joateko botoia
		 */
		JButton btnBanatzailea = new JButton("Banatzailea");
		btnBanatzailea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnBanatzailea.setFont(new Font("Arial", Font.BOLD, 15));
		btnBanatzailea.setBackground(new Color(111, 141, 158));
		btnBanatzailea.setBounds(854, 11, 120, 38);
		btnBanatzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaUI banatzailea = new BanatzaileaUI();
				banatzailea.setVisible(true);
				dispose();
			}
		});
		/*
		 * Paketeak esleitu frame-era joateko botoia
		 */
		JButton btnPaketeaEsleitu = new JButton("Paketeak Esleitu");
		btnPaketeaEsleitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketeaEsleitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketeaEsleitu.setBackground(new Color(111, 141, 158));
		btnPaketeaEsleitu.setBounds(677, 11, 151, 38);
		btnPaketeaEsleitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EsleipenaUI esleitu = new EsleipenaUI();
				esleitu.setVisible(true);
				dispose();
			}
		});

		JPanel pnlZerrendaPanela = new JPanel();
		pnlZerrendaPanela.setBounds(0, 60, 492, 651);

		pnlZerrendaPanela.setLayout(null);

		/*
		 * paketeen zerrenda label
		 */
		JLabel lblPaketeZerenda = new JLabel("Paketeen zerrenda");
		lblPaketeZerenda.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPaketeZerenda.setBounds(10, 11, 259, 39);
		/*
		 * Pakete guztiak erakusten dituen zerrenda
		 */

		JScrollPane spPaketeZerrenda = new JScrollPane();
		spPaketeZerrenda.setBounds(0, 49, 362, 603);
		PaketeZerrenda = new JList<>();
		spPaketeZerrenda.setViewportView(PaketeZerrenda);
		PaketeZerrenda.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		PaketeZerrenda.setBounds(0, 61, 362, 590);
		lortuPaketeak();

		konexioa.lortuPaketeak();
		/*
		 * Paketea sortzeko botoia, frame-a irekitzen du
		 */
		JButton SortuPaketeaButton = new JButton("Sortu");
		SortuPaketeaButton.setBackground(new Color(111, 141, 158));
		SortuPaketeaButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		SortuPaketeaButton.setBounds(372, 113, 110, 40);
		SortuPaketeaButton.setFont(new Font("Arial", Font.BOLD, 15));
		SortuPaketeaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeaSortu sortu = new PaketeaSortu();
				sortu.setVisible(true);
			}
		});
		/*
		 * Paketea sortzeko botoia, aukeratutako paketea ezabatzen du (Zerrendan
		 * aukeratu)
		 */
		JButton EzabatuPaketeaButton = new JButton("Ezabatu");
		EzabatuPaketeaButton.setBackground(new Color(111, 141, 158));
		EzabatuPaketeaButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		EzabatuPaketeaButton.setBounds(372, 304, 110, 40);
		EzabatuPaketeaButton.setFont(new Font("Arial", Font.BOLD, 15));
		EzabatuPaketeaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aukeratutakoPaketea = (String) PaketeZerrenda.getSelectedValue();
				if (aukeratutakoPaketea == null) {
					JOptionPane.showMessageDialog(null, "Aukeratu pakete bat ezabatzeko");
				}
				String[] parts = aukeratutakoPaketea.split(" ");
				String id = parts[0];
				konexioa.ezabatuPaketa(id);
				lortuPaketeak();
				lortuPaketeakhistoriala();

			}
		});

		/*
		 * Paketea editatzeko botoia, frame-a irekitzen du
		 */
		JButton EditatupaketeaButtonButton = new JButton("Editatu");
		EditatupaketeaButtonButton.setBackground(new Color(111, 141, 158));
		EditatupaketeaButtonButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		EditatupaketeaButtonButton.setBounds(372, 502, 110, 40);
		EditatupaketeaButtonButton.setFont(new Font("Arial", Font.BOLD, 15));
		EditatupaketeaButtonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeaEditatu editatu = new PaketeaEditatu();
				editatu.setVisible(true);
			}
		});

		JPanel pnlPaketeaHistoriala = new JPanel();
		pnlPaketeaHistoriala.setBounds(492, 60, 492, 651);
		pnlPaketeaHistoriala.setLayout(null);

		/*
		 * Sortutako paketeen labela
		 */
		JLabel lblPaketeHistoriala = new JLabel("Pakete Historiala");
		lblPaketeHistoriala.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPaketeHistoriala.setBounds(10, 16, 259, 39);
		/*
		 * Sortutako paketeen zerrenda
		 */
		JScrollPane spPaketeHistoriala = new JScrollPane();
		spPaketeHistoriala.setBounds(0, 61, 362, 603);
		PaketeHistoriala = new JList<>();
		spPaketeHistoriala.setViewportView(PaketeHistoriala);
		lortuPaketeakhistoriala();
		PaketeHistoriala.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		PaketeHistoriala.setBounds(0, 61, 362, 590);

		/*
		 * Paketeak filtratzeko botoia, frame-a irekitzen du
		 */
		JButton FiltratuButton = new JButton("Filtratu");
		FiltratuButton.setBackground(new Color(111, 141, 158));
		FiltratuButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		FiltratuButton.setFont(new Font("Arial", Font.BOLD, 15));
		FiltratuButton.setBounds(372, 309, 110, 40);
		FiltratuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeakFiltratu frameFiltratu = new PaketeakFiltratu();
				frameFiltratu.setVisible(true);

			}
		});

		pnlGuztia.add(pnlMenu);
		pnlGuztia.add(pnlZerrendaPanela);
		pnlGuztia.add(pnlPaketeaHistoriala);

		pnlMenu.add(lblErabiltzailea);
		pnlMenu.add(btnBanatzailea);
		pnlMenu.add(btnPaketeaEsleitu);

		pnlZerrendaPanela.add(lblPaketeZerenda);
		pnlZerrendaPanela.add(SortuPaketeaButton);
		pnlZerrendaPanela.add(EzabatuPaketeaButton);
		pnlZerrendaPanela.add(EditatupaketeaButtonButton);
		pnlZerrendaPanela.add(spPaketeZerrenda);

		pnlPaketeaHistoriala.add(lblPaketeHistoriala);
		pnlPaketeaHistoriala.add(FiltratuButton);
		pnlPaketeaHistoriala.add(spPaketeHistoriala);

	}

	/**
	 * The Class PaketeaSortu.
	 */
	class PaketeaSortu extends JFrame {

		/**
		 * Instantiates a new paketea sortu.
		 */
		public PaketeaSortu() {
			setTitle("Paketea sortu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 345, 550);
			pnlGuztia = new JPanel();
			pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(pnlGuztia);
			pnlGuztia.setLayout(null);
			/*
			 * Bezeroaren telefono zenbakia label
			 */
			JLabel TelefonoaLabel = new JLabel("Telefonoa:");
			TelefonoaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TelefonoaLabel.setBounds(40, 11, 108, 22);

			/*
			 * Bezeroaren telefono zenbakia ezartzeko textfield-a
			 */
			TelefonoatextField = new JTextField();
			TelefonoatextField.setBounds(38, 32, 86, 20);
			TelefonoatextField.setColumns(10);

			/*
			 * Bezeroaren Helbidea label
			 */
			JLabel HelbideaLabel = new JLabel("Helbidea");
			HelbideaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			HelbideaLabel.setBounds(40, 63, 108, 22);
			/*
			 * Paketearen helbidea ezartzeko textfield-a
			 */
			HelbideatextField = new JTextField();
			HelbideatextField.setBounds(38, 84, 86, 20);
			HelbideatextField.setColumns(10);
			/*
			 * paketaren tamaina label
			 */
			JLabel TamainaLabel = new JLabel("Tamaina");
			TamainaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TamainaLabel.setBounds(35, 115, 89, 20);
			/*
			 * Paketearen tamaina aukeratzeko combobox-a
			 */
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Arial", Font.BOLD, 15));
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Txikia", "Ertaina" }));
			comboBox.setBounds(35, 146, 97, 22);
			/*
			 * Datu hauekin paketea sortzeko botoia
			 */
			JButton SortuButton = new JButton("SORTU");
			SortuButton.setFont(new Font("Arial", Font.BOLD, 15));
			SortuButton.setBounds(230, 477, 89, 23);
			SortuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String zenb = TelefonoatextField.getText();
					String helbidea = HelbideatextField.getText();
					String tamaina = (String) comboBox.getSelectedItem();
					konexioa.sortuPaketea(zenb, helbidea, tamaina);
					lortuPaketeak();
					lortuPaketeakhistoriala();
					dispose();
				}
			});

			pnlGuztia.add(TelefonoaLabel);
			pnlGuztia.add(HelbideaLabel);
			pnlGuztia.add(TamainaLabel);
			pnlGuztia.add(comboBox);
			pnlGuztia.add(SortuButton);

			pnlGuztia.add(TelefonoatextField);
			pnlGuztia.add(HelbideatextField);

		}
	}

	/**
	 * The Class PaketeaEditatu.
	 */
	class PaketeaEditatu extends JFrame {

		/**
		 * Instantiates a new paketea editatu.
		 */
		public PaketeaEditatu() {
			setTitle("Paketea editatu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 345, 550);
			pnlGuztia = new JPanel();
			pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(pnlGuztia);
			pnlGuztia.setLayout(null);
			/*
			 * Bezeroaren telefonoa label
			 */
			JLabel TelefonoaLabel = new JLabel("Telefonoa :");
			TelefonoaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TelefonoaLabel.setBounds(28, 23, 108, 14);
			/*
			 * Bezeroaren telefono berria ezartzeko textfield-a
			 */
			TelefonoatextField = new JTextField();
			TelefonoatextField.setBounds(28, 48, 86, 20);
			TelefonoatextField.setColumns(10);
			/*
			 * Paketaren helbidea label
			 */
			JLabel HelbideaLabel = new JLabel("Helbidea:");
			HelbideaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			HelbideaLabel.setBounds(28, 79, 86, 14);
			/*
			 * Paketaren helbidea aldatzeko textfield-a
			 */
			HelbideatextField = new JTextField();
			HelbideatextField.setBounds(28, 104, 86, 20);
			HelbideatextField.setColumns(10);
			/*
			 * Paketearen tamaina label
			 */
			JLabel TamainaLabel = new JLabel("Tamaina:");
			TamainaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TamainaLabel.setBounds(28, 146, 86, 14);

			/*
			 * paketearen tamaina aldatzeko combobox-a
			 */
			JComboBox TamainacomboBox = new JComboBox();
			TamainacomboBox.setFont(new Font("Arial", Font.BOLD, 15));
			TamainacomboBox.setModel(new DefaultComboBoxModel(new String[] { "Txikia", "Ertaina" }));
			TamainacomboBox.setBounds(28, 171, 121, 27);
			pnlGuztia.add(TamainacomboBox);
			/*
			 * Paketearen mota label
			 */
			JLabel MotaLabel = new JLabel("Mota:");
			MotaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			MotaLabel.setBounds(28, 209, 86, 14);
			pnlGuztia.add(MotaLabel);
			/*
			 * Paketearen egoera aldatzeko combobox-a
			 */
			JComboBox MotacomboBox_1 = new JComboBox();
			MotacomboBox_1.setFont(new Font("Arial", Font.BOLD, 15));
			MotacomboBox_1.setModel(new DefaultComboBoxModel(
					new String[] { "Entregatuta", "Entregatu Gabe", "Entregatu Gabe arazoekin" }));
			MotacomboBox_1.setBounds(28, 233, 121, 24);

			JButton EditatuButton = new JButton("Editatu");
			EditatuButton.setFont(new Font("Arial", Font.BOLD, 15));
			EditatuButton.setBounds(230, 312, 89, 23);
			EditatuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String zenb = TelefonoatextField.getText();
					String helbidea = HelbideatextField.getText();
					String tamaina = (String) TamainacomboBox.getSelectedItem();
					String mota = (String) MotacomboBox_1.getSelectedItem();
					String aukeratutakoPaketea = (String) PaketeZerrenda.getSelectedValue();
					String[] parts = aukeratutakoPaketea.split(" ");
					String id = parts[0];

					String luzeera = TelefonoatextField.getText();
					int luze = luzeera.length();
					konexioa.editatuPaketa(id, zenb, helbidea, tamaina, mota);
					lortuPaketeak();
					lortuPaketeakhistoriala();
					dispose();

				}
			});

			pnlGuztia.add(TelefonoaLabel);
			pnlGuztia.add(HelbideaLabel);
			pnlGuztia.add(TamainaLabel);
			pnlGuztia.add(MotacomboBox_1);
			pnlGuztia.add(EditatuButton);

			pnlGuztia.add(TelefonoatextField);
			pnlGuztia.add(HelbideatextField);

		}
	}

	/**
	 * The Class PaketeakFiltratu.
	 */
	class PaketeakFiltratu extends JFrame {

		/**
		 * Instantiates a new paketeak filtratu.
		 */
		public PaketeakFiltratu() {
			setTitle("Paketa filtratu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 355, 439);
			pnlGuztia = new JPanel();
			pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(pnlGuztia);
			pnlGuztia.setLayout(null);
			/*
			 * paketearen tamaina label
			 */
			JLabel MotaLabel = new JLabel("Mota:");
			MotaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			MotaLabel.setBounds(97, 49, 119, 39);

			/*
			 * Paketearen tamaina aukeratzeko combobox-a
			 */
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Arial", Font.BOLD, 15));
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Txikia", "Ertaina" }));
			comboBox.setBounds(75, 109, 156, 22);

			/*
			 * Aukeratutako tamainaren bidez filtratzeko botoia
			 */
			JButton FiltratuButton = new JButton("Filtratu");
			FiltratuButton.setFont(new Font("Arial", Font.BOLD, 15));
			FiltratuButton.setBounds(240, 366, 89, 23);
			FiltratuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					dispose();
					String tamainaString =	(String) comboBox.getSelectedItem();
					lortuPaketeakFiltratuta(tamainaString);

				}
			});

			pnlGuztia.add(MotaLabel);
			pnlGuztia.add(comboBox);
			pnlGuztia.add(FiltratuButton);
		}
	}

	public void lortuPaketeak() {
		ArrayList<String> paketeaklist = konexioa.lortuPaketeak();
		DefaultListModel<String> model = new DefaultListModel<>();

		for (String paketea : paketeaklist) {
			model.addElement(paketea);
		}

		PaketeZerrenda.setModel(model);
	}

	public void lortuPaketeakFiltratuta(String tamaina) {
		ArrayList<String> paketeaklist = konexioa.lortuPaketeakFiltratuta(tamaina);
		DefaultListModel<String> model = new DefaultListModel<>();

		for (String paketea : paketeaklist) {
			model.addElement(paketea);
		}

		PaketeZerrenda.setModel(model);
	}

	private void lortuPaketeakhistoriala() {
		ArrayList<String> paketeakhisto = konexioa.paketeHistorialaLortu();
		DefaultListModel<String> model = new DefaultListModel<>();

		for (String paketeahist : paketeakhisto) {
			model.addElement(paketeahist);
		}

		PaketeHistoriala.setModel(model);
	}

}
