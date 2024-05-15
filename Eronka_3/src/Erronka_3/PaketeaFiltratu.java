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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Ioritz Lopetegi
 */
public class PaketeaFiltratu extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/**
	 * FiltratuFrame-a sortu
	 */
	public PaketeaFiltratu() {
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
		FiltratuButton.setBounds(240, 366, 89, 23);
		contentPane.add(FiltratuButton);
	}
}
