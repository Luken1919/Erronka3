/*
 * 22 may 2024
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
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;

/**
 * Class Banatzailea.
 */
public class BanatzaileaUI extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Content pane. */
	private JPanel pnlGuztia;

	/** Izena textfield. */
	private JTextField Izena;

	/** Abizena textfield. */
	private JTextField Abizena;

	/** Erabiltzaile izena textfield. */
	private JTextField ErabiltzaileIzena;

	/** Pasahitza passwordfield. */
	private JPasswordField PasahitzaField;

	/** Izen abizena. Erabiltzailearen izena osatzeko */
	private String IzenAbizena = DatuBasea.erabIzena;

	/** Banatzaileen Zerrenda. */
	private JList<String> listBanatzailea;

	/** Banatzaile historiala Bakoitzaren paketeak. */
	private JList<String> BanatzaileHistoriala;

	/** Datubaseko konexioa. */
	private DatuBasea konexioa = new DatuBasea();

	/** Zerrendako testuaren zatiak. */
	String[] parts;


	/**
	 * Banatzailearen frame-a sortu.
	 */
	public BanatzaileaUI() {
		setTitle("Banatzaileen informazioa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		pnlGuztia = new JPanel();
		pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlGuztia);
		pnlGuztia.setLayout(null);

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(253, 194, 116)));
		pnlMenu.setBounds(0, 0, 984, 60);
		pnlMenu.setLayout(null);

		/*
		 * Erabiltzaile izenaren label-a
		 */
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea: " + IzenAbizena);
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 265, 38);

		/*
		 * Paketera joateko botoia
		 */
		JButton btnPaketea = new JButton("Paketeak");
		btnPaketea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketea.setBackground(new Color(111, 141, 158));
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
		 * Paketeak esleitu joateko botoia
		 */
		JButton btnPaketeaEsleitu = new JButton("Paketeak Esleitu");
		btnPaketeaEsleitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketeaEsleitu.setBackground(new Color(111, 141, 158));
		btnPaketeaEsleitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketeaEsleitu.setBounds(638, 11, 190, 38);
		btnPaketeaEsleitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EsleipenaUI esleitu = new EsleipenaUI();
				esleitu.setVisible(true);
				dispose();
			}
		});

		/*
		 * Banatzailearen panela
		 */
		JPanel pnlBanatzailea = new JPanel();
		pnlBanatzailea.setBounds(0, 59, 492, 652);
		pnlBanatzailea.setLayout(null);

		JLabel lblBanatzailea = new JLabel("Banatzaileak");
		lblBanatzailea.setBounds(10, 11, 352, 38);
		lblBanatzailea.setFont(new Font("Arial Black", Font.BOLD, 15));

		/*
		 * Banatzaileak erakusten ditu
		 */
		JScrollPane spBanatzailea = new JScrollPane();
		spBanatzailea.setBounds(0, 49, 362, 603);
		listBanatzailea = new JList<>();
		listBanatzailea.setFixedCellHeight(20);
		spBanatzailea.setViewportView(listBanatzailea);
		listBanatzailea.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));

		/*
		 * Aukeratutako banatzailearen id-a atera eta
		 * entregatutako paketake guztiak erkutsi.
		 */
		listBanatzailea.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String aukeratutakoa = listBanatzailea.getSelectedValue();
					if (aukeratutakoa != null) {
						parts = aukeratutakoa.split(" ");
					} else if (aukeratutakoa == null) {

					}

					String id = parts[parts.length - 1];
					lortuBanatzaileakHistoriala(id);
				}
			}
		});
		lortuBanatzaileak();

		/*
		 * Banatzaileak sortzeko irikitzen duen botoia
		 */
		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.setBounds(372, 110, 110, 40);
		btnGehitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnGehitu.setBackground(new Color(111, 141, 158));
		btnGehitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaGehitu gehitu = new BanatzaileaGehitu();
				gehitu.setVisible(true);
			}
		});

		/*
		 * Banatzaileak kentzeko irikitzen duen botoia
		 */
		JButton btnKendu = new JButton("Kendu");
		btnKendu.setBounds(372, 299, 110, 40);
		btnKendu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnKendu.setBackground(new Color(111, 141, 158));
		btnKendu.setFont(new Font("Arial", Font.BOLD, 15));
		btnKendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaKendu kendu = new BanatzaileaKendu();
				kendu.setVisible(true);
			}
		});

		/*
		 * Banatzaileak editatu irikitzen duen botoia
		 */
		JButton btnEditatu = new JButton("Editatu");
		btnEditatu.setBounds(372, 499, 110, 40);
		btnEditatu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnEditatu.setBackground(new Color(111, 141, 158));
		btnEditatu.setFont(new Font("Arial", Font.BOLD, 15));
		btnEditatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BanatzaileaEditatu editatu = new BanatzaileaEditatu();
				editatu.setVisible(true);
			}
		});



		/*
		 * Sortutako banatzaileak erakutsiko diren panela
		 */
		JPanel pnlHistoriala = new JPanel();
		pnlHistoriala.setBounds(491, 59, 493, 652);
		pnlHistoriala.setLayout(null);

		/*
		 * Aukeratutako banatzailearen historiala label-a
		 */
		JLabel lblHistoriala = new JLabel("Autatutako banatzailearen historiala");
		lblHistoriala.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblHistoriala.setBounds(10, 11, 352, 38);

		/*
		 * Aukeratutako banatzailearen historiala erakusten du
		 */
		JScrollPane spBanatzaileakHistoriala = new JScrollPane();
		spBanatzaileakHistoriala.setBounds(0, 49, 362, 603);
		pnlHistoriala.add(spBanatzaileakHistoriala);
		BanatzaileHistoriala = new JList<>();
		BanatzaileHistoriala.setFixedCellHeight(20);
		spBanatzaileakHistoriala.setViewportView(BanatzaileHistoriala);
		BanatzaileHistoriala.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));

		/*
		 * Label-a izenare bidez ordenatzeko
		 */
		JLabel lblIzenBidez = new JLabel("Izen Bidez");
		lblIzenBidez.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIzenBidez.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzenBidez.setBounds(372, 248, 98, 38);

		/*
		 * Banatzaileak izenen bidez ordenatzeko botoia
		 */
		JButton btnOrdenatuIzenaBidez = new JButton("Ordenatu\r\n ");
		btnOrdenatuIzenaBidez.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnOrdenatuIzenaBidez.setBackground(new Color(111, 141, 158));
		btnOrdenatuIzenaBidez.setFont(new Font("Arial", Font.BOLD, 15));
		btnOrdenatuIzenaBidez.setBounds(372, 282, 98, 74);
		btnOrdenatuIzenaBidez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lortuBanatzaileakOrdenatutaizenez();

			}
		});

		/*
		 * Label-a id bidez ordenatzeko
		 */
		JLabel lblIdBidez = new JLabel("ID Bidez");
		lblIdBidez.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdBidez.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdBidez.setBounds(372, 367, 98, 38);

		/*
		 * Banatzailearen id bidez ordenatzeko botoia
		 */
		JButton btnOrdenatuIdBidez = new JButton("Ordenatu\r\n ");
		btnOrdenatuIdBidez.setFont(new Font("Arial", Font.BOLD, 15));
		btnOrdenatuIdBidez.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnOrdenatuIdBidez.setBackground(new Color(111, 141, 158));
		btnOrdenatuIdBidez.setBounds(372, 416, 98, 74);
		btnOrdenatuIdBidez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lortuBanatzaileakOrdenatutaID();
			}
		});


		/*
		 * Panelean elementuak gehitu
		 */
		pnlGuztia.add(pnlMenu);
		pnlGuztia.add(pnlBanatzailea);
		pnlGuztia.add(pnlHistoriala);

		pnlMenu.add(lblErabiltzailea);
		pnlMenu.add(btnPaketea);
		pnlMenu.add(btnPaketeaEsleitu);

		pnlBanatzailea.add(lblBanatzailea);
		pnlBanatzailea.add(btnGehitu);
		pnlBanatzailea.add(btnKendu);
		pnlBanatzailea.add(btnEditatu);
		pnlBanatzailea.add(spBanatzailea);

		pnlHistoriala.add(lblHistoriala);
		pnlHistoriala.add(spBanatzaileakHistoriala);
		pnlHistoriala.add(lblIzenBidez);
		pnlHistoriala.add(btnOrdenatuIzenaBidez);
		pnlHistoriala.add(lblIdBidez);
		pnlHistoriala.add(btnOrdenatuIdBidez);


	}

	/**
	 * Class BanatzaileaGehitu.
	 */
	class BanatzaileaGehitu extends JFrame {

		/**
		 * Instantiates a new banatzailea gehitu.
		 */
		public BanatzaileaGehitu() {
			setTitle("Banatzailea gehitu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 399, 500);
			pnlGuztia = new JPanel();
			pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(pnlGuztia);
			pnlGuztia.setLayout(null);

			/*
			 * Tituluaren Label-a
			 */
			JLabel lblTitulua = new JLabel("SARTU INFORMAZIO BERRIA");
			lblTitulua.setFont(new Font("Arial Black", Font.BOLD, 20));
			lblTitulua.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulua.setBounds(10, 11, 363, 29);

			/*
			 * Banatzailearen izena zehazteko label-a
			 */
			JLabel lblIzena = new JLabel("Izena:");
			lblIzena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblIzena.setBounds(30, 51, 200, 29);

			/*
			 * Idatzitako izena TextFild-ean gorde
			 */
			Izena = new JTextField();
			Izena.setBounds(30, 91, 200, 34);
			Izena.setColumns(10);

			/*
			 * Banatzailearen abizena zehazteko label-a
			 */
			JLabel lblAbizena = new JLabel("Abizena:");
			lblAbizena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblAbizena.setBounds(30, 136, 200, 29);

			/*
			 * Idatzitako abizena TextFild-ean gorde
			 */
			Abizena = new JTextField();
			Abizena.setColumns(10);
			Abizena.setBounds(30, 176, 200, 34);

			/*
			 * Banatzailearen pasahitza zehazteko label-a
			 */
			JLabel lblPasahitza = new JLabel("Pashitza:");
			lblPasahitza.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblPasahitza.setBounds(30, 221, 200, 29);

			/*
			 * Idatzitako pasahitza TextFild-ean gorde
			 */
			PasahitzaField = new JPasswordField();
			PasahitzaField.setColumns(10);
			PasahitzaField.setBounds(30, 261, 200, 34);

			/*
			 * Banatzailea sortzeko botoia, sartutako datuekin sortuko da
			 */
			JButton btnGehitu = new JButton("Sortu");
			btnGehitu.setBounds(250, 400, 89, 29);
			btnGehitu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String IzenaSortu 		= Izena.getText();
					String AbizenaSortu 	= Abizena.getText();
					String PasahitzaSortu 	= PasahitzaField.getText();

					konexioa.sortuBanatzailea(IzenaSortu, AbizenaSortu, PasahitzaSortu);
					lortuBanatzaileakzerrenda();
					dispose();
				}
			});

			/*
			 * Panelean elementuak gehitu
			 */
			pnlGuztia.add(lblTitulua);
			pnlGuztia.add(lblIzena);
			pnlGuztia.add(lblAbizena);
			pnlGuztia.add(lblPasahitza);
			pnlGuztia.add(btnGehitu);

			pnlGuztia.add(Izena);
			pnlGuztia.add(Abizena);
			pnlGuztia.add(PasahitzaField);
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
			setTitle("Banatzailea kendu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 362, 458);
			pnlGuztia = new JPanel();
			pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(pnlGuztia);
			pnlGuztia.setLayout(null);

			/*
			 * Erabiltzailea aukeratzeko Label-a
			 */
			JLabel ErabIzenaLabel = new JLabel("Erabiltzailea");
			ErabIzenaLabel.setFont(new Font("Arial", Font.BOLD, 15));
			ErabIzenaLabel.setBounds(123, 24, 90, 36);

			/*
			 * Banatzaileak erakusten dituen combobox-a
			 */
			BanatzaileaUI bantzaileBanatzaileaUI = new BanatzaileaUI();
			JComboBox comboBox = new JComboBox();
			ArrayList<String> historial = bantzaileBanatzaileaUI.lortuBanatzaileakzerrenda();
			for (String item : historial) {
				comboBox.addItem(item);
			}
			comboBox.setBounds(81, 68, 158, 22);

			/*
			 * Aukeratutako banatzailea ezabatzeko botoia
			 */
			JButton EzabatuButton = new JButton("Ezabatu");
			EzabatuButton.setFont(new Font("Arial Black", Font.BOLD, 15));
			EzabatuButton.setBounds(210, 363, 126, 45);
			EzabatuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String aukeratutakoerabiltzailea = (String) comboBox.getSelectedItem();
					String[] parts = aukeratutakoerabiltzailea.split(" ");
					String id = parts[parts.length - 1];
					konexioa.ezabatuBanatzailea(id);

					dispose();
					lortuBanatzaileakzerrenda();

				}
			});

			/*
			 * Panelean elementuak gehitu
			 */
			pnlGuztia.add(ErabIzenaLabel);
			pnlGuztia.add(comboBox);
			pnlGuztia.add(EzabatuButton);
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
			setTitle("Banatzailea editatu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 399, 500);
			pnlGuztia = new JPanel();
			pnlGuztia.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(pnlGuztia);
			pnlGuztia.setLayout(null);

			/*
			 * Tituluaren Label-a
			 */
			JLabel lblTitulua = new JLabel("SARTU INFORMAZIO BERRIA");
			lblTitulua.setFont(new Font("Arial Black", Font.BOLD, 20));
			lblTitulua.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulua.setBounds(10, 11, 363, 29);

			/*
			 * Banatzailearen izena editatzeko label-a
			 */
			JLabel lblIzena = new JLabel("Izena:");
			lblIzena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblIzena.setBounds(30, 51, 200, 29);

			/*
			 * Idatzitako izena TextFild-ean gorde
			 */
			Izena = new JTextField();
			Izena.setBounds(30, 91, 200, 34);
			Izena.setColumns(10);

			/*
			 * Banatzailearen abizena editatzeko label-a
			 */
			JLabel lblAbizena = new JLabel("Abizena:");
			lblAbizena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblAbizena.setBounds(30, 136, 200, 29);

			/*
			 * Idatzitako abizena TextFild-ean gorde
			 */
			Abizena = new JTextField();
			Abizena.setColumns(10);
			Abizena.setBounds(30, 176, 200, 34);

			/*
			 * Banatzailearen erabiltzaile izena editatzeko label-a
			 */
			JLabel lblErabiltzaileIzena = new JLabel("Erabiltzaile Izena:");
			lblErabiltzaileIzena.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblErabiltzaileIzena.setBounds(30, 221, 200, 29);

			/*
			 * Idatzitako erabiltzaile izena TextFild-ean gorde
			 */
			ErabiltzaileIzena = new JTextField();
			ErabiltzaileIzena.setColumns(10);
			ErabiltzaileIzena.setBounds(30, 261, 200, 34);

			/*
			 * Banatzailearen pasahitza editatzeko label-a
			 */
			JLabel lblPasahitza = new JLabel("Pasahitza:");
			lblPasahitza.setFont(new Font("Arial Black", Font.BOLD, 15));
			lblPasahitza.setBounds(30, 306, 200, 29);

			/*
			 * Idatzitako pasahitzan TextFild-ean gorde
			 */
			PasahitzaField = new JPasswordField();
			PasahitzaField.setColumns(10);
			PasahitzaField.setBounds(30, 346, 200, 34);

			/*
			 * Sartutako informazioa editatzeko botoia
			 */
			JButton btnEditatu = new JButton("Editatu");
			btnEditatu.setBounds(250, 400, 89, 29);
			pnlGuztia.add(btnEditatu);
			btnEditatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String izenaString 		= Izena.getText();
					String abizenaString 	= Abizena.getText();
					String pasahitza 		= PasahitzaField.getText();
					String erabizenaString 	= ErabiltzaileIzena.getText();

					String aukeratutakoerabiltzailea = (String) listBanatzailea.getSelectedValue();
					if (aukeratutakoerabiltzailea == null) {
						JOptionPane.showMessageDialog(null, "Aukeratu erabiltzaile bat");
					} else {
						String[] parts 	= aukeratutakoerabiltzailea.split(" ");
						String id 		= parts[parts.length - 1];

						konexioa.editatuBanatzailea(id, izenaString, abizenaString, pasahitza, erabizenaString);
						lortuBanatzaileakzerrenda();
						dispose();
					}

				}
			});

			/*
			 * Panelean elementuak gehitu
			 */
			pnlGuztia.add(lblTitulua);
			pnlGuztia.add(lblIzena);
			pnlGuztia.add(lblAbizena);
			pnlGuztia.add(lblErabiltzaileIzena);
			pnlGuztia.add(lblPasahitza);

			pnlGuztia.add(Izena);
			pnlGuztia.add(Abizena);
			pnlGuztia.add(ErabiltzaileIzena);
			pnlGuztia.add(PasahitzaField);
		}
	}

	/**
	 * Lortu banatzaileak.Datubaseko banatzaileak zerrendan gordetzen dira
	 * 
	 * @return banatzaileakList
	 */
	public ArrayList<String> lortuBanatzaileakzerrenda() {
		ArrayList<String> banatzaileakList 	= DatuBasea.lortuBanatzaileak();
		DefaultListModel<String> model 		= new DefaultListModel<>();

		for (String banatzailea : banatzaileakList) {
			model.addElement(banatzailea);
		}

		listBanatzailea.setModel(model);
		return banatzaileakList;
	}
	
	/*
	 * Banatzaileak lortzeko metodoa
	 */
	private void lortuBanatzaileak() {
		ArrayList<String> banatzaileakList 	= DatuBasea.lortuBanatzaileak();
		DefaultListModel<String> model 		= new DefaultListModel<>();

		for (String banatzailea : banatzaileakList) {
			model.addElement(banatzailea);
		}

		listBanatzailea.setModel(model);
	}

	/*
	 * Id-aren arabera, banatzaileak ordenatzeko metodoa
	 */
	private void lortuBanatzaileakOrdenatutaizenez() {
		ArrayList<String> banatzaileakList 	= DatuBasea.lortuBanatzaileakOrdenatutaIzenez();
		DefaultListModel<String> model 		= new DefaultListModel<>();

		for (String banatzailea : banatzaileakList) {
			model.addElement(banatzailea);
		}

		listBanatzailea.setModel(model);
	}

	
	/*
	 * Id-aren arabera, banatzaileak ordenatzeko metodoa
	 */
	private void lortuBanatzaileakOrdenatutaID() {
		ArrayList<String> banatzaileakList 	= DatuBasea.lortuBanatzaileakOrdenatutaId();
		DefaultListModel<String> model 		= new DefaultListModel<>();

		for (String banatzailea : banatzaileakList) {
			model.addElement(banatzailea);
		}

		listBanatzailea.setModel(model);
	}

	/**
	 * Lortu banatzaile historiala.
	 *
	 * @param id banatzailearen id-a 
	 */
	private void lortuBanatzaileakHistoriala(String id) {
		ArrayList<String> banatzaileakhistorialaList 	= konexioa.lortuBanatzaielarenHistoriala(id);
		DefaultListModel<String> model 					= new DefaultListModel<>();

		for (String banatzailea : banatzaileakhistorialaList) {
			model.addElement(banatzailea);
		}

		BanatzaileHistoriala.setModel(model);
	}
}
