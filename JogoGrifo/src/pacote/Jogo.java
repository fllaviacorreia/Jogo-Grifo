package pacote;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Jogo extends JFrame {
	private JPanel painelJogo;	
	private JLabel crocodilo;
	private JLabel fundo;
	private JLabel fundo2;
	//private JLabel fundo3;
	private JLabel obs2;
	private JPanel painelInicio;	
	private JButton botaoPlay;
	private boolean pulo = false;
	
	public Jogo() {
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 584);
		setBackground(Color.BLACK);
		setVisible(true);
		setTitle("Crocodilo Perdido");
		setResizable(false);
		
		painelJogo = new JPanel();
		painelJogo.setLayout(null);
		setContentPane(painelJogo);
		painelJogo.setVisible(true);

		fundo = new JLabel();
		fundo.setBounds(0, 0, 2500, 1000);
		fundo.setIcon(new ImageIcon(getClass().getResource("img\\montanha.jpg")));

		fundo2 = new JLabel();
		fundo2.setBounds(1024, 0, 2500, 1000);
		fundo2.setIcon(new ImageIcon(getClass().getResource("img\\montanha.jpg")));	
		
		/*fundo3 = new JLabel();
		fundo3.setBounds(1024, 0, 1016, 595);
		fundo3.setIcon(new ImageIcon(getClass().getResource("img\\montanha1.jpg")));*/
		
		crocodilo = new JLabel();
		crocodilo.setBounds(0, 220, 160, 150);
		crocodilo.setIcon(new ImageIcon(getClass().getResource("img\\Grifo.gif")));

		obs2 = new JLabel();
		obs2.setBounds(1013, 290, 180, 90);
		obs2.setIcon(new ImageIcon(getClass().getResource("img\\obs.gif")));

		painelJogo.add(crocodilo);		
		painelJogo.add(obs2);
		painelJogo.add(fundo);
		painelJogo.add(fundo2);
		//painelJogo.add(fundo3);
		repaint();
		validate();
	}

	public void Fundogif(){
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			fundo.setBounds(fundo.getX() - 1, fundo.getY(), fundo.getWidth(), fundo.getHeight());
			fundo2.setBounds(fundo2.getX() - 1, fundo2.getY(), fundo2.getWidth(), fundo2.getHeight());
			//fundo3.setBounds(fundo3.getX() - 1, fundo3.getY(), fundo3.getWidth(), fundo3.getHeight());
			
			
			if (fundo.getX() == -1024) {
				fundo.setBounds(1024, fundo.getY(), fundo.getWidth(), fundo.getHeight());
			}
			if (fundo2.getX() == -1024) {
				fundo2.setBounds(1024, fundo2.getY(), fundo2.getWidth(), fundo2.getHeight());
			}
			/*if (fundo3.getX() == -1024) {
				fundo3.setBounds(1024, fundo3.getY(), fundo3.getWidth(), fundo3.getHeight());
			}*/

		}
	}

	public void obstaculo(){
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			obs2.setBounds(obs2.getX() - 4, obs2.getY(), obs2.getWidth(), obs2.getHeight());
			
			bateu();			
			if (obs2.getX() <= -1013) {
				obs2.setBounds(1013, obs2.getY(), obs2.getWidth(), obs2.getHeight());
			}
		}

	}

	public void movimentar() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_D) {
					getCrocodilo().setBounds(getCrocodilo().getX() + 10, getCrocodilo().getY(), 245,245 );					
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					getCrocodilo().setBounds(getCrocodilo().getX() - 10, getCrocodilo().getY(), 245, 245);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {

					if (getCrocodilo().getY() == 220 && pulo == false) {
						pulo=true;
						playSound("somPulo");
						new Thread() {
							public void run() {
								for (int i = 0; i < 180; i++) {
									getCrocodilo().setBounds(getCrocodilo().getX(), getCrocodilo().getY() - 1, 245,
											100);
									try {
										Thread.sleep(3, 6);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
								for (int i = 0; i < 170; i++) {
									getCrocodilo().setBounds(getCrocodilo().getX(), getCrocodilo().getY() + 1, 245,
											100);
									try {
										Thread.sleep(10, 6);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}    
						}.start();
						pulo=false;
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					getCrocodilo().setBounds(getCrocodilo().getX(), getCrocodilo().getY(), 245, 245);
				}
				if (e.getKeyCode() == KeyEvent.VK_D)
					getCrocodilo().setBounds(getCrocodilo().getX(), getCrocodilo().getY(), 245, 245);
			}
		});
	}
	
	public void playSound(String soundName) {
		URL url = getClass().getResource(soundName + ".wav");
		AudioClip audio = Applet.newAudioClip(url);
		audio.play();	
	}

	public void bateu() {
		if(crocodilo.getBounds().intersects(obs2.getBounds())) {
			new Thread() {
				public void run() {
					crocodilo.setBounds(getCrocodilo().getX(), 4000, 100, 100);
					crocodilo.setVisible(false);
					obs2.setVisible(false);
				}
			}.start();
			
			new Thread() {
				public void run() {
					playSound("GameOver");
				}
			}.start();
			
			new Thread() {
				
				public void run() {
					painelJogo.setBackground(Color.BLACK);
					fundo.setBounds(0, 0, 2000, 4000);
					fundo.setIcon(null);
					fundo2.setIcon(new ImageIcon(getClass().getResource("img\\game over.jpg")));
					fundo2.setBounds(300, -750, 2000, 2000);
					
				}
			}.start();
			
			new Thread() {
				public void run() {
					try {
						Thread.sleep(9000);
						System.exit(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
			
		}
	}
	
	public JLabel getCrocodilo() {
		return crocodilo;
	}

	public JLabel getObstaculo() {
		return obs2;
	}

	public JPanel getPainelJogo() {
		return painelJogo;
	}

	public JPanel getPainelInicio() {
		return painelInicio;
	}

	public JButton getBotaoPlay() {
		return botaoPlay;
	}

}
