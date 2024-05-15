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
public class EditatupaketeaFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** Telefonoaren textfield-a. */
	private JTextField TelefonoatextField;

	/** Helbidearen Textfield-a. */
	private JTextField HelbideatextField_1;

	/**
	 * Paketea editatu frame-a sortu.
	 */
	public EditatupaketeaFrame() {
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

		HelbideatextField_1 = new JTextField();
		HelbideatextField_1.setBounds(28, 104, 86, 20);
		contentPane.add(HelbideatextField_1);
		HelbideatextField_1.setColumns(10);

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
