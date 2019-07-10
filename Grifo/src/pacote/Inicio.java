package pacote;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Inicio extends JFrame {
	private JPanel GameOver;
	private JLabel fundo;
//	private JButton inicio;
	
	public Inicio() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 700);
		setBackground(Color.BLACK);
		setVisible(true);
		setTitle("Grifo");
		setResizable(false);

		GameOver = new JPanel();
		GameOver.setLayout(null);
		setContentPane(GameOver);
		GameOver.setVisible(true);
		
		fundo = new JLabel();
		fundo.setBounds(0, 0, 1024, 702);
		fundo.setIcon(new ImageIcon(getClass().getResource("img\\fundo4.jpg")));

		GameOver.add(fundo);

		repaint();
		validate();
	}

	public void movimentar() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					final Jogo jogo = new Jogo();			
					dispose();
					
//					new Thread() {
//						public void run() {
//							jogo.playSound("somFundo");
//						}
//					}.start();

					new Thread() {
						public void run() {
							jogo.movimentar();
						}
					}.start();

					new Thread() {
						public void run() {
							jogo.basilisco();
						}
					}.start();
					
					new Thread() {
						public void run() {
							jogo.flecha();
						}
					}.start();

					new Thread() {
						public void run() {
							jogo.Fundogif();
						}
					}.start();

				}
			}

			public void keyReleased(KeyEvent e) {

			}
		});
	}
}
