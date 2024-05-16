/**
 * 
 */
package Erronka_3;

import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.MatteBorder;

/**
 * 
 * @author Ioritz Lopetegi
 */
public class Paketea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TelefonoatextField;
	private JTextField HelbideatextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paketea frame = new Paketea();
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
	public Paketea() {
		setTitle("Paketeen informazioa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(253, 194, 116)));
		panel.setLayout(null);
		panel.setBounds(0, 0, 984, 60);
		contentPane.add(panel);

		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 120, 38);
		panel.add(lblErabiltzailea);

		JButton btnBanatzailea = new JButton("Banatzailea");
		btnBanatzailea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Banatzailea banatzailea = new Banatzailea();
				banatzailea.setVisible(true);
				dispose();
			}
		});
		btnBanatzailea.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnBanatzailea.setFont(new Font("Arial", Font.BOLD, 15));
		btnBanatzailea.setBackground(new Color(240, 240, 240));
		btnBanatzailea.setBounds(854, 11, 120, 38);
		panel.add(btnBanatzailea);

		JButton btnPaketeaEsleitu = new JButton("Paketeak Esleitu");
		btnPaketeaEsleitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Esleipena esleitu = new Esleipena();
				esleitu.setVisible(true);
				dispose();
			}
		});
		btnPaketeaEsleitu.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		btnPaketeaEsleitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketeaEsleitu.setBackground(new Color(240, 240, 240));
		btnPaketeaEsleitu.setBounds(677, 11, 151, 38);
		panel.add(btnPaketeaEsleitu);

		JPanel ZerrendaPanela = new JPanel();
		ZerrendaPanela.setBounds(0, 60, 492, 651);
		contentPane.add(ZerrendaPanela);
		ZerrendaPanela.setLayout(null);

		JLabel lblPaketeZerenda = new JLabel("Paketeen zerrenda");
		lblPaketeZerenda.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPaketeZerenda.setBounds(10, 11, 259, 39);
		ZerrendaPanela.add(lblPaketeZerenda);

		JList PaketeZerrenda = new JList();
		PaketeZerrenda.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		PaketeZerrenda.setBounds(0, 61, 362, 590);
		ZerrendaPanela.add(PaketeZerrenda);

		JButton SortuPaketeaButton = new JButton("Sortu");
		SortuPaketeaButton.setBackground(new Color(240, 240, 240));
		SortuPaketeaButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		SortuPaketeaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeaSortu sortu = new PaketeaSortu();
				sortu.setVisible(true);
			}
		});
		SortuPaketeaButton.setBounds(372, 113, 110, 40);
		ZerrendaPanela.add(SortuPaketeaButton);
		SortuPaketeaButton.setFont(new Font("Arial", Font.BOLD, 15));

		JButton EzabatuPaketeaButton = new JButton("Ezabatu");
		EzabatuPaketeaButton.setBackground(new Color(240, 240, 240));
		EzabatuPaketeaButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		EzabatuPaketeaButton.setBounds(372, 304, 110, 40);
		ZerrendaPanela.add(EzabatuPaketeaButton);
		EzabatuPaketeaButton.setFont(new Font("Arial", Font.BOLD, 15));

		JButton EditatupaketeaButtonButton = new JButton("Editatu");
		EditatupaketeaButtonButton.setBackground(new Color(240, 240, 240));
		EditatupaketeaButtonButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		EditatupaketeaButtonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeaEditatu editatu = new PaketeaEditatu();
				editatu.setVisible(true);
			}
		});
		EditatupaketeaButtonButton.setBounds(372, 502, 110, 40);
		ZerrendaPanela.add(EditatupaketeaButtonButton);
		EditatupaketeaButtonButton.setFont(new Font("Arial", Font.BOLD, 15));


		JPanel panel_Paketeak = new JPanel();
		panel_Paketeak.setBounds(492, 60, 492, 651);
		contentPane.add(panel_Paketeak);
		panel_Paketeak.setLayout(null);

		JLabel lblPaketeHistoriala = new JLabel("Pakete Historiala");
		lblPaketeHistoriala.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPaketeHistoriala.setBounds(10, 16, 259, 39);
		panel_Paketeak.add(lblPaketeHistoriala);

		JList PaketeHistoriala = new JList();
		PaketeHistoriala.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		PaketeHistoriala.setBounds(0, 61, 362, 590);
		panel_Paketeak.add(PaketeHistoriala);

		JButton OrdenatuButton = new JButton("Ordenatu");
		OrdenatuButton.setBackground(new Color(240, 240, 240));
		OrdenatuButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		OrdenatuButton.setFont(new Font("Arial", Font.BOLD, 15));
		OrdenatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		OrdenatuButton.setBounds(372, 399, 110, 40);
		panel_Paketeak.add(OrdenatuButton);

		JButton FiltratuButton = new JButton("Filtratu");
		FiltratuButton.setBackground(new Color(240, 240, 240));
		FiltratuButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(253, 194, 116)));
		FiltratuButton.setFont(new Font("Arial", Font.BOLD, 15));
		FiltratuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaketeakFiltratu frameFiltratu = new PaketeakFiltratu();
				frameFiltratu.setVisible(true);

			}
		});
		FiltratuButton.setBounds(372, 218, 110, 40);
		panel_Paketeak.add(FiltratuButton);

	}

	class PaketeaSortu extends JFrame{
		public PaketeaSortu() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 345, 550);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel TelefonoaLabel = new JLabel("Telefonoa:");
			TelefonoaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TelefonoaLabel.setBounds(40, 11, 108, 22);
			contentPane.add(TelefonoaLabel);

			TelefonoatextField = new JTextField();
			TelefonoatextField.setBounds(38, 32, 86, 20);
			contentPane.add(TelefonoatextField);
			TelefonoatextField.setColumns(10);

			JLabel HelbideaLabel = new JLabel("Helbidea");
			HelbideaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			HelbideaLabel.setBounds(40, 63, 108, 22);
			contentPane.add(HelbideaLabel);

			HelbideatextField = new JTextField();
			HelbideatextField.setBounds(38, 84, 86, 20);
			contentPane.add(HelbideatextField);
			HelbideatextField.setColumns(10);

			JLabel TamainaLabel = new JLabel("Tamaina");
			TamainaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TamainaLabel.setBounds(35, 115, 89, 20);
			contentPane.add(TamainaLabel);

			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Arial", Font.BOLD, 15));
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Txikia", "Ertaina" }));
			comboBox.setBounds(35, 146, 97, 22);
			contentPane.add(comboBox);

			JButton SortuButton = new JButton("SORTU");
			SortuButton.setFont(new Font("Arial", Font.BOLD, 15));
			SortuButton.setBounds(230, 477, 89, 23);
			contentPane.add(SortuButton);
		}
	}

	class PaketeaEditatu extends JFrame{
		public PaketeaEditatu() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 345, 550);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel TelefonoaLabel = new JLabel("Telefonoa :");
			TelefonoaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TelefonoaLabel.setBounds(28, 23, 108, 14);
			contentPane.add(TelefonoaLabel);

			TelefonoatextField = new JTextField();
			TelefonoatextField.setBounds(28, 48, 86, 20);
			contentPane.add(TelefonoatextField);
			TelefonoatextField.setColumns(10);

			JLabel HelbideaLabel = new JLabel("Helbidea:");
			HelbideaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			HelbideaLabel.setBounds(28, 79, 86, 14);
			contentPane.add(HelbideaLabel);

			HelbideatextField = new JTextField();
			HelbideatextField.setBounds(28, 104, 86, 20);
			contentPane.add(HelbideatextField);
			HelbideatextField.setColumns(10);

			JLabel TamainaLabel = new JLabel("Tamaina:");
			TamainaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			TamainaLabel.setBounds(28, 146, 86, 14);
			contentPane.add(TamainaLabel);

			JComboBox TamainacomboBox = new JComboBox();
			TamainacomboBox.setFont(new Font("Arial", Font.BOLD, 15));
			TamainacomboBox.setModel(new DefaultComboBoxModel(new String[] { "Txikia", "Ertaina" }));
			TamainacomboBox.setBounds(28, 171, 121, 27);
			contentPane.add(TamainacomboBox);

			JLabel MotaLabel = new JLabel("Mota:");
			MotaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			MotaLabel.setBounds(28, 209, 86, 14);
			contentPane.add(MotaLabel);

			JComboBox MotacomboBox_1 = new JComboBox();
			MotacomboBox_1.setFont(new Font("Arial", Font.BOLD, 15));
			MotacomboBox_1.setModel(
					new DefaultComboBoxModel(new String[] { "Entregatuta", "Entregatu Gabe", "Entregatu Gabe arazoekin" }));
			MotacomboBox_1.setBounds(28, 233, 121, 24);
			contentPane.add(MotacomboBox_1);

			JButton EditatuButton = new JButton("Editatu");
			EditatuButton.setFont(new Font("Arial", Font.BOLD, 15));
			EditatuButton.setBounds(230, 312, 89, 23);
			contentPane.add(EditatuButton);
		}
	}

	class PaketeakFiltratu extends JFrame{
		public PaketeakFiltratu() {
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 355, 439);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			JLabel MotaLabel = new JLabel("Mota:");
			MotaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
			MotaLabel.setBounds(97, 49, 119, 39);
			contentPane.add(MotaLabel);

			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Arial", Font.BOLD, 15));
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Txikia", "Ertaina" }));
			comboBox.setBounds(75, 109, 156, 22);
			contentPane.add(comboBox);

			JButton FiltratuButton = new JButton("Filtratu");
			FiltratuButton.setFont(new Font("Arial", Font.BOLD, 15));
			FiltratuButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					Paketea bueltatu = new Paketea();
					bueltatu.setVisible(rootPaneCheckingEnabled);

				}
			});
			FiltratuButton.setBounds(240, 366, 89, 23);
			contentPane.add(FiltratuButton);
		}
	}
}
