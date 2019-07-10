package pacote;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Jogo extends JFrame {
	private JPanel painelJogo;
	private JLabel grifo;
	private JLabel flecha;
	private JLabel fundo;
	private JLabel fundo2;
	private JLabel basilisco;
//	private ImageIcon fundo1;
	private ImageIcon flecha1;
//	private JPanel painelInicio;	
//	private JButton botaoPlay;
//	private boolean pulo = false;
	private int vidas;
	
	public Jogo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 600);
		setBackground(Color.BLACK);
		setVisible(true);
		setTitle("Grifo");
		setResizable(false);
		vidas = 3;
		painelJogo = new JPanel();
		painelJogo.setLayout(null);
		painelJogo.setVisible(true);
		painelJogo.add(getGrifo());		
		painelJogo.add(getBasilisco());
		painelJogo.add(getFlecha());
		painelJogo.add(getFundo());
		painelJogo.add(getFundo2());
		setContentPane(painelJogo);
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

	public void basilisco(){
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			basilisco.setBounds(basilisco.getX() - 4, basilisco.getY(), basilisco.getWidth(), basilisco.getHeight());
			
			bateu();			
			if (basilisco.getX() <= -1013) {
				basilisco.setBounds(1013, basilisco.getY(), basilisco.getWidth(), basilisco.getHeight());
			}
		}

	}
	
	public void flecha() {
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			getFlecha().setBounds(getFlecha().getX() - 3, getFlecha().getY(), flecha.getWidth(), flecha.getHeight());
		//	bateu();
			if(flecha.getX() <= 1020) {
				if(getGrifo().getY() == 0)
					getFlecha().setBounds(1003, 20, flecha.getWidth(), flecha.getHeight());
				else if(getGrifo().getY() == 190)
					getFlecha().setBounds(1003, 210, flecha.getWidth(), flecha.getHeight());
				else
					getFlecha().setBounds(1003, 390, flecha.getWidth(), flecha.getHeight());
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

				if (e.getKeyCode() == KeyEvent.VK_W) {
					System.out.println("no w");
					if(getGrifo().getY() == 190) {
						grifo.setBounds(getGrifo().getX(), 0, 245, 245 );	
					}else if(getGrifo().getY() == 370) {
						grifo.setBounds(getGrifo().getX(), 190, 245, 245 );	
					}
									
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("no s");
					if(getGrifo().getY() == 190) {
						grifo.setBounds(getGrifo().getX(), 370, 245, 245 );	
					}else if(getGrifo().getY() == 0) {
						grifo.setBounds(getGrifo().getX(), 190, 245, 245 );	
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_W) {
//					getGrifo().setBounds(getGrifo().getX(), getGrifo().getY(), 245, 245);
//				}
//				if (e.getKeyCode() == KeyEvent.VK_S)
//					getGrifo().setBounds(getGrifo().getX(), getGrifo().getY(), 245, 245);
			}
		});
	}
	
	public void playSound(String soundName) {
		URL url = getClass().getResource(soundName + ".wav");
		AudioClip audio = Applet.newAudioClip(url);
		audio.play();	
	}

	public void bateu() {
		if(grifo.getBounds().intersects(basilisco.getBounds()) || 
				grifo.getBounds().intersects(flecha.getBounds())) {
			if(vidas > 0) {
				vidas--;
				new Thread() {
					public void run() {
						grifo.setBounds(getGrifo().getX(), 4000, 100, 100);
						grifo.setVisible(false);
						basilisco.setVisible(false);
						}
				}.start();
				
				new Thread() {
					public void run() {
						playSound("somCoracaoPartido");
					}
				}.start();	
				
				new Thread() {
					public void run() {
						painelJogo.setBackground(Color.BLACK);
						fundo.setBounds(0, 0, 1000, 400);
						fundo.setIcon(null);
						fundo2.setIcon(new ImageIcon(getClass().getResource("img\\coracaoPartido.gif")));
						fundo2.setBounds(0, 0, 2000, 2000);
						}
				}.start();
						
				new Thread() {
					public void run() {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
				
				new Thread() {
					public void run() {
						grifo.setBounds(getGrifo().getX(), 190, 160, 150);
						grifo.setVisible(true);
						basilisco.setVisible(true);
						fundo.setBounds(0, 0, 2500, 1000);
						fundo.setIcon(new ImageIcon(getClass().getResource("img\\montanha.jpg")));
						fundo2.setBounds(1024, 0, 2500, 1000);
						fundo2.setIcon(new ImageIcon(getClass().getResource("img\\montanha.jpg")));
					}
				}.start();	
				
				movimentar();
			}
			else {
				new Thread() {
					public void run() {
						grifo.setBounds(getGrifo().getX(), 4000, 100, 100);
						grifo.setVisible(false);
						basilisco.setVisible(false);
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
	}
	
	public JLabel getGrifo() {
		if(grifo == null) {
			grifo = new JLabel();
			grifo.setBounds(0, 220, 160, 150);
			grifo.setIcon(new ImageIcon(getClass().getResource("img\\Grifo.gif")));
		}
		return grifo;
	}

	public JLabel getFlecha() {
		if(flecha == null) {
			flecha1 = new ImageIcon("img\\flecha.jpeg");
			flecha1.setImage(flecha1.getImage().getScaledInstance(200, 100, 100));
			flecha = new JLabel();
		//	flecha.setIcon(new ImageIcon(getClass().getResource("img\\flecha.jpeg")));
			flecha.setIcon(flecha1);
			flecha.setBounds(1003, 190, 200, 100);
		}
		return flecha;
	}

	public JLabel getFundo() {
		if(fundo == null) {
			fundo = new JLabel();
			fundo.setBounds(0, 0, 2500, 1000);
			fundo.setIcon(new ImageIcon(getClass().getResource("img\\montanha.jpg")));
		}
		return fundo;
	}

	public JLabel getFundo2() {
		if(fundo2 == null) {
			fundo2 = new JLabel();
			fundo2.setBounds(1024, 0, 2500, 1000);
			fundo2.setIcon(new ImageIcon(getClass().getResource("img\\montanha.jpg")));	
			
		}
		return fundo2;
	}

	public JLabel getBasilisco() {
		if(basilisco == null) {
			basilisco = new JLabel();
			basilisco.setBounds(1013, 290, 180, 90);
			basilisco.setIcon(new ImageIcon(getClass().getResource("img\\obs.gif")));
		}
		return basilisco;
	}

}
