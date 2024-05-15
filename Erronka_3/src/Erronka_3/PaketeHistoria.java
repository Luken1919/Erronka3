/*
 * 15 may 2024
 * Ioritz Lopetegi
 */
package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Ioritz Lopetegi
 */
public class PaketeHistoria extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;

	/**
	 * Pakete Historia Frame-a sortu.
	 */
	public PaketeHistoria() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 964, 63);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel ErabiltzaileaLabel = new JLabel("Erabiltzailea:");
		ErabiltzaileaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		ErabiltzaileaLabel.setBounds(443, 5, 139, 27);
		panel.add(ErabiltzaileaLabel);

		JLabel ErabIzenaLabel = new JLabel("Erabiltzailea");
		ErabIzenaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		ErabIzenaLabel.setBounds(443, 32, 109, 20);
		panel.add(ErabIzenaLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 74, 964, 626);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JList list = new JList();
		list.setBounds(10, 11, 944, 604);
		panel_1.add(list);
	}

}
