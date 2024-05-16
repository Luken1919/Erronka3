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
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 * 
 * @author Ioritz Lopetegi
 */
public class Kendubanatzailea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kendubanatzailea frame = new Kendubanatzailea();
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
	public Kendubanatzailea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ErabIzenaLabel = new JLabel("Erabiltzailea");
		ErabIzenaLabel.setFont(new Font("Arial", Font.BOLD, 15));
		ErabIzenaLabel.setBounds(123, 24, 90, 36);
		contentPane.add(ErabIzenaLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(81, 68, 158, 22);
		contentPane.add(comboBox);
		
		JButton EzabatuButton = new JButton("Ezabatu");
		EzabatuButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		EzabatuButton.setBounds(210, 363, 126, 45);
		contentPane.add(EzabatuButton);
	}
}
