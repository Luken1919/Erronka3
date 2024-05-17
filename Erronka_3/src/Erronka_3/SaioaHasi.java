package Erronka_3;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SaioaHasi {
	private static SaioaHasiUI saioaHasiUI;
	JTextField textField = saioaHasiUI.getTextField();
	JPasswordField passwordField = saioaHasiUI.getJPasswordField();
	String username = textField.getText();
	String password = new String(passwordField.getPassword());{

		if (DatuBasea.frogatuErabiltzailea(username, password)) {
			BanatzaileaUI frame = new BanatzaileaUI();
			frame.setVisible(true);
			saioaHasiUI.dispose();

		} else {
			JOptionPane.showMessageDialog(null, "Pasahitza edo erabiltzailea ez da existitzen");
		}
	}
}
