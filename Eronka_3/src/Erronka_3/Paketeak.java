/**
 * 
 */
package Erronka_3;

import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import java.awt.Font;

/**
 * 
 * @author Ioritz Lopetegi
 */
public class Paketeak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField AukeratutakoPaketeatextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Paketeak frame = new Paketeak();
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
	public Paketeak() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 832, 53);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel OngiEtorriLabel = new JLabel("Ongi Etorri");
		OngiEtorriLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		OngiEtorriLabel.setBounds(10, 0, 222, 27);
		panel.add(OngiEtorriLabel);

		JLabel ErabiltzaileIzenaLabel = new JLabel("Erabiltzailea: ");
		ErabiltzaileIzenaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		ErabiltzaileIzenaLabel.setBounds(10, 28, 153, 14);
		panel.add(ErabiltzaileIzenaLabel);

		JButton PaketeakEsleituBotoia = new JButton("Paketeak Esleitu");
		PaketeakEsleituBotoia.setFont(new Font("Arial", Font.BOLD, 15));
		PaketeakEsleituBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

//				PaketeHistoria framehistoriapakete = new PaketeHistoria();
//				framehistoriapakete.setVisible(true);
			}

		});
		PaketeakEsleituBotoia.setBounds(629, 0, 193, 53);
		panel.add(PaketeakEsleituBotoia);

		JPanel ZerrendaPanela = new JPanel();
		ZerrendaPanela.setBounds(0, 51, 279, 660);
		contentPane.add(ZerrendaPanela);
		ZerrendaPanela.setLayout(null);

		JList PaketeZerrendaJlist = new JList();
		PaketeZerrendaJlist.setBounds(10, 0, 267, 656);
		PaketeZerrendaJlist.setModel(new AbstractListModel() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		ZerrendaPanela.add(PaketeZerrendaJlist);

		JPanel panel_Paketeak = new JPanel();
		panel_Paketeak.setBounds(278, 51, 696, 660);
		contentPane.add(panel_Paketeak);
		panel_Paketeak.setLayout(null);

		JButton OrdenatuButton = new JButton("Ordenatu");
		OrdenatuButton.setFont(new Font("Arial", Font.BOLD, 15));
		OrdenatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		OrdenatuButton.setBounds(0, 524, 121, 34);
		panel_Paketeak.add(OrdenatuButton);

		JButton FiltratuButton = new JButton("Filtratu");
		FiltratuButton.setFont(new Font("Arial", Font.BOLD, 15));
		FiltratuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PaketeaFiltratu frameFiltratu = new PaketeaFiltratu();
				frameFiltratu.setVisible(rootPaneCheckingEnabled);

			}
		});
		FiltratuButton.setBounds(0, 556, 121, 40);
		panel_Paketeak.add(FiltratuButton);

		JButton PaketeHistoriaButton = new JButton("Pakete Historia");
		PaketeHistoriaButton.setFont(new Font("Arial", Font.BOLD, 15));
		PaketeHistoriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				PaketeHistoria framehistoriapakete = new PaketeHistoria();
				framehistoriapakete.setVisible(true);

			}
		});
		PaketeHistoriaButton.setBounds(0, 620, 141, 40);
		panel_Paketeak.add(PaketeHistoriaButton);

		AukeratutakoPaketeatextField = new JTextField();
		AukeratutakoPaketeatextField.setBounds(490, 556, 196, 20);
		panel_Paketeak.add(AukeratutakoPaketeatextField);
		AukeratutakoPaketeatextField.setColumns(10);

		JButton EzabatuPaketeaButton = new JButton("Ezabatu Paketea");
		EzabatuPaketeaButton.setFont(new Font("Arial", Font.BOLD, 15));
		EzabatuPaketeaButton.setBounds(490, 584, 196, 23);
		panel_Paketeak.add(EzabatuPaketeaButton);

		JButton EditatupaketeaButtonButton = new JButton("Editatu Paketea");
		EditatupaketeaButtonButton.setFont(new Font("Arial", Font.BOLD, 15));
		EditatupaketeaButtonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				EditatupaketeaFrame frameeditatupakete = new EditatupaketeaFrame();
				frameeditatupakete.setVisible(true);
			}
		});
		EditatupaketeaButtonButton.setBounds(490, 609, 196, 23);
		panel_Paketeak.add(EditatupaketeaButtonButton);

		JButton SortuPaketeaButton = new JButton("Sortu Paketea");
		SortuPaketeaButton.setFont(new Font("Arial", Font.BOLD, 15));
		SortuPaketeaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				SortuPaketeaFrame framesortuapakete = new SortuPaketeaFrame();
				framesortuapakete.setVisible(true);

			}
		});
		SortuPaketeaButton.setBounds(490, 637, 196, 23);
		panel_Paketeak.add(SortuPaketeaButton);
		
				JButton BanatzaileakBotoia = new JButton("Banatzaileak");
				BanatzaileakBotoia.setBounds(801, 0, 173, 53);
				contentPane.add(BanatzaileakBotoia);
				BanatzaileakBotoia.setFont(new Font("Arial", Font.BOLD, 15));
	}
}
