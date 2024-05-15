/**
 * 
 */
package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 * 
 * @author Ioritz Lopetegi
 */
public class Sortubanatzailea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField IzenatextField;
	private JTextField textField_1;
	private JPasswordField PasahitzaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sortubanatzailea frame = new Sortubanatzailea();
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
	public Sortubanatzailea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel AbizenaLabel = new JLabel("Abizena");
		AbizenaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		AbizenaLabel.setBounds(118, 65, 108, 22);
		contentPane.add(AbizenaLabel);
		
		IzenatextField = new JTextField();
		IzenatextField.setColumns(10);
		IzenatextField.setBounds(104, 39, 86, 20);
		contentPane.add(IzenatextField);
		
		JLabel Pasahitzalabel = new JLabel("Pasahitza");
		Pasahitzalabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		Pasahitzalabel.setBounds(111, 129, 108, 22);
		contentPane.add(Pasahitzalabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(102, 99, 86, 20);
		contentPane.add(textField_1);
		
		JLabel IzenaLabel = new JLabel("Izena");
		IzenaLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		IzenaLabel.setBounds(125, 11, 89, 20);
		contentPane.add(IzenaLabel);
		
		JButton SortuButton = new JButton("SORTU");
		SortuButton.setFont(new Font("Arial", Font.BOLD, 15));
		SortuButton.setBounds(230, 415, 89, 23);
		contentPane.add(SortuButton);
		
		PasahitzaField = new JPasswordField();
		PasahitzaField.setBounds(104, 168, 93, 20);
		contentPane.add(PasahitzaField);
	}
}
