package Erronka_3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class BanatzaileInterfazea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BanatzaileInterfazea frame = new BanatzaileInterfazea();
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
	public BanatzaileInterfazea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 984, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblErabiltzailea.setBounds(10, 11, 120, 38);
		panel.add(lblErabiltzailea);
		
		JButton btnPaketea = new JButton("Paketeak");
		btnPaketea.setBackground(new Color(255, 255, 255));
		btnPaketea.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketea.setBounds(854, 11, 120, 38);
		panel.add(btnPaketea);
		
		JButton btnPaketeaEsleitu = new JButton("Paketeak Esleitu");
		btnPaketeaEsleitu.setBackground(new Color(255, 255, 255));
		btnPaketeaEsleitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnPaketeaEsleitu.setBounds(677, 11, 151, 38);
		panel.add(btnPaketeaEsleitu);
		
		JPanel banatzaileak = new JPanel();
		banatzaileak.setBounds(0, 59, 492, 652);
		contentPane.add(banatzaileak);
		banatzaileak.setLayout(null);
		
		JLabel lblBanatzailea = new JLabel("Banatzaileak");
		lblBanatzailea.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblBanatzailea.setBounds(10, 11, 352, 38);
		banatzaileak.add(lblBanatzailea);
		
		JList Banatzaileak = new JList();
		Banatzaileak.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		Banatzaileak.setBounds(0, 60, 362, 592);
		banatzaileak.add(Banatzaileak);
		
		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.setBackground(new Color(255, 255, 255));
		btnGehitu.setFont(new Font("Arial", Font.BOLD, 15));
		btnGehitu.setBounds(372, 110, 110, 40);
		banatzaileak.add(btnGehitu);
		
		JButton btnKendu = new JButton("Kendu");
		btnKendu.setBackground(new Color(255, 255, 255));
		btnKendu.setFont(new Font("Arial", Font.BOLD, 15));
		btnKendu.setBounds(372, 299, 110, 40);
		banatzaileak.add(btnKendu);
		
		JButton btnEditatu = new JButton("Editatu");
		btnEditatu.setBackground(new Color(255, 255, 255));
		btnEditatu.setFont(new Font("Arial", Font.BOLD, 15));
		btnEditatu.setBounds(372, 499, 110, 40);
		banatzaileak.add(btnEditatu);
		
		JPanel historiala = new JPanel();
		historiala.setBounds(491, 59, 493, 652);
		contentPane.add(historiala);
		historiala.setLayout(null);
		
		JLabel lblHistoriala = new JLabel("Autatutako banatzailearen historiala");
		lblHistoriala.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblHistoriala.setBounds(10, 11, 352, 38);
		historiala.add(lblHistoriala);
		
		JList BanatzaileHistoriala = new JList();
		BanatzaileHistoriala.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(253, 194, 116)));
		BanatzaileHistoriala.setBounds(0, 62, 362, 590);
		historiala.add(BanatzaileHistoriala);
		
		JButton btnFiltratu = new JButton("Filtratu");
		btnFiltratu.setBackground(new Color(255, 255, 255));
		btnFiltratu.setFont(new Font("Arial", Font.BOLD, 15));
		btnFiltratu.setBounds(373, 199, 110, 40);
		historiala.add(btnFiltratu);
		
		JButton btnOrdenatu = new JButton("Ordenatu");
		btnOrdenatu.setBackground(new Color(255, 255, 255));
		btnOrdenatu.setFont(new Font("Arial", Font.BOLD, 15));
		btnOrdenatu.setBounds(373, 405, 110, 40);
		historiala.add(btnOrdenatu);
		
	}
}
