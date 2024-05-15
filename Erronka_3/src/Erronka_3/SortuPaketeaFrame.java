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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Ioritz Lopetegi
 */
public class SortuPaketeaFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/**  Telefonoa textfield. */
	private JTextField TelefonoatextField;
	
	/**  Helbidea textfield . */
	private JTextField HelbideatextField_1;

	

	/**
	 * Sortupaketea frame-a sortu.
	 */
	public SortuPaketeaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		HelbideatextField_1 = new JTextField();
		HelbideatextField_1.setBounds(38, 84, 86, 20);
		contentPane.add(HelbideatextField_1);
		HelbideatextField_1.setColumns(10);

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
