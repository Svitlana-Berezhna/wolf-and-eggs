package ua.sberezh;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Field extends JPanel implements ActionListener, Runnable
{
	Timer mainTimer = new Timer(20, this);

	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/fieldWithoutEggs.png")).getImage();
	Image imgField = new ImageIcon(getClass().getClassLoader().getResource("res/field.png")).getImage();
	Image imgTitle = new ImageIcon(getClass().getClassLoader().getResource("res/title.png")).getImage();
	Image imgHare = new ImageIcon(getClass().getClassLoader().getResource("res/Hare.png")).getImage();
	Image imgRules = new ImageIcon(getClass().getClassLoader().getResource("res/Rules.png")).getImage();
	Image imgControl = new ImageIcon(getClass().getClassLoader().getResource("res/Control.png")).getImage();
	
	Image imgAPressed = new ImageIcon(getClass().getClassLoader().getResource("res/btnAkeyPressed.png")).getImage();
	Image imgAReleased = new ImageIcon(getClass().getClassLoader().getResource("res/btnAkeyReleased.png")).getImage();
	Image imgZPressed = new ImageIcon(getClass().getClassLoader().getResource("res/btnZkeyPressed.png")).getImage();
	Image imgZReleased = new ImageIcon(getClass().getClassLoader().getResource("res/btnZkeyReleased.png")).getImage();
	Image imgKPressed = new ImageIcon(getClass().getClassLoader().getResource("res/btnKkeyPressed.png")).getImage();
	Image imgKReleased = new ImageIcon(getClass().getClassLoader().getResource("res/btnKkeyReleased.png")).getImage();
	Image imgMPressed = new ImageIcon(getClass().getClassLoader().getResource("res/btnMkeyPressed.png")).getImage();
	Image imgMReleased = new ImageIcon(getClass().getClassLoader().getResource("res/btnMkeyReleased.png")).getImage();
	
	Image imgPauseWithoutText = new ImageIcon(getClass().getClassLoader().getResource("res/btnPauseWithoutText.png")).getImage();
	Image imgPause = new ImageIcon(getClass().getClassLoader().getResource("res/btnPause.png")).getImage();
	Image imgPlay = new ImageIcon(getClass().getClassLoader().getResource("res/btnPlay.png")).getImage();
	Image imgMainMenu = new ImageIcon(getClass().getClassLoader().getResource("res/btnMainMenu.png")).getImage();
	
	Image imgText, imgA, imgZ, imgK, imgM, imgPausePlay, imgMenu;
	
	JButton btnGame = new JButton("btnGame");
	JButton btnRules = new JButton("btnRules");
	JButton btnControl = new JButton("btnControl");
	JButton btnBack = new JButton("btnBack");
	
	Wolf w;
	Thread eggsFactory;
	List<Egg> eggs;
	Iterator<Egg> i;
	Thread audioThread = new Thread(new AudioThread());
	Robot r;

	public Field(){
		mainTimer.start();
		audioThread.start();
		addKeyListener(new MyKeyAdapter());	
		addMouseListener(new MyMouseAdapter());
		
		imgA = imgAReleased;
		imgZ = imgZReleased;
		imgK = imgKReleased;
		imgM = imgMReleased;
		
		imgPausePlay = imgPauseWithoutText;
		
		try {
			r = new Robot();
		} catch (AWTException e1) {			
			e1.printStackTrace();
		};		
        
		setLayout(null);
		
	    btnGame.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnGame.png")));
	    btnGame.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnGameRollover.png")));
	    btnGame.setBorderPainted(false);
	    btnGame.setMargin(new Insets(-3, -3, -3, -43));
	    btnGame.addActionListener(this);
	    btnGame.setBounds(304, 190, 175,55 );
	    add(btnGame);
	    
	    btnRules.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnRules.png")));
	    btnRules.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnRulesRollover.png")));
	    btnRules.setBorderPainted(false);
	    btnRules.setMargin(new Insets(-3, -3, -3, -43));
	    btnRules.addActionListener(this);
	    btnRules.setBounds(304, 250, 175,55 );
	    add(btnRules);
	    
	    btnControl.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnControl.png")));
	    btnControl.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnControlRollover.png")));
	    btnControl.setBorderPainted(false);
	    btnControl.setMargin(new Insets(-3, -3, -3, -43));
	    btnControl.addActionListener(this);
	    btnControl.setBounds(304, 310, 175,55 );
	    btnControl.setBackground(null);
	    add(btnControl);
	    
	    btnBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnBack.png")));
	    btnBack.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("res/btnBackRollover.png")));
	    btnBack.setBorderPainted(false);
	    btnBack.setMargin(new Insets(-3, -3, -3, -43));
	    btnBack.addActionListener(this);
	    btnBack.setBounds(304, 341, 175,55 );
	    add(btnBack);
	    btnBack.setVisible(false);
	    
	    setFocusable(true);
	}
	
	private class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			w.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e){
			w.keyReleased(e);
		}
	}
	
    public class MyMouseAdapter extends MouseAdapter {
    	public void mousePressed(MouseEvent e) {
    		if (img == imgField){
	    		int x = e.getX(); 
	        	int y = e.getY();
	        	int keyCode = 0; 
	        	if ((x - 61)*(x - 61) + (y - 333)*(y - 333) <= 324){
	            	imgA = imgAPressed;
	            	keyCode = KeyEvent.VK_A; 
	            	r.keyPress(keyCode);
	            	r.keyRelease(keyCode);  
	        	}
	        	else if ((x - 61)*(x - 61) + (y - 413)*(y - 413) <= 324){
	            	imgZ = imgZPressed;
	            	keyCode = KeyEvent.VK_Z; 
	            	r.keyPress(keyCode);
	            	r.keyRelease(keyCode);  
	        	}
	        	else if ((x - 708)*(x - 708) + (y - 333)*(y - 333) <= 324){
	            	imgK = imgKPressed;
	            	keyCode = KeyEvent.VK_K; 
	            	r.keyPress(keyCode);
	            	r.keyRelease(keyCode);  
	        	}
	        	else if ((x - 708)*(x - 708) + (y - 413)*(y - 413) <= 324){
	            	imgM = imgMPressed;
	            	keyCode = KeyEvent.VK_M; 
	            	r.keyPress(keyCode);
	            	r.keyRelease(keyCode);  
	        	}      
    		}
        }

        public void mouseReleased(MouseEvent e) {
        	if (img == imgField){
	        	imgA = imgAReleased;
	            imgZ = imgZReleased;
	            imgK = imgKReleased;
	            imgM = imgMReleased;           
        	}
        }
   }
	
    public void paintComponent(Graphics g){
		g = (Graphics2D) g;
		g.drawImage(img, 0, 0, null);
		g.drawImage(imgTitle, 195, 60, null);
		g.drawImage(imgHare, 125, 49, null);
		g.drawImage(imgText, 133, 71, null);
		
		
		g.drawImage(imgA, 27, 285, null);
		g.drawImage(imgZ, 27, 367, null);
		g.drawImage(imgK, 683, 285, null);
		g.drawImage(imgM, 683, 367, null);
		
		g.drawImage(imgPausePlay, 675, 70, null);
		g.drawImage(imgMenu, 675, 143, null);
		
		if (w != null){
			g.drawImage(w.img, w.x, w.y, null);
		
			double v = (200/Wolf.MAX_V) * w.v;
			g.setColor(Color.BLUE);
			Font font = new Font("Arial", Font.BOLD, 18);
			g.setFont(font);
			g.drawString("X " + w.eggs_caught, 515, 83);
			g.setColor(Color.RED);
			g.drawString("X " + w.eggs_broken, 515, 110);			
		
			i = eggs.iterator();
			while (i.hasNext()){
				Egg e = i.next();
				if (e.y == 370){
					e.img = e.img_broken;
					g.drawImage(e.img, e.x, e.y, null);
					w.eggs_broken++;
					i.remove();
				}else{
					e.move();
					g.drawImage(e.img, e.x, e.y, null);
					if ((e.y >= 220) && (e.y <= 230) && (e.side == "lt") && (w.side == "lt"))
					{
						w.eggs_caught++;
						i.remove();
					}
					if ((e.y >= 220) && (e.y <= 230) && (e.side == "rt") && (w.side == "rt"))
					{
						w.eggs_caught++;
						i.remove();
					}
					if ((e.y >= 270) && (e.y <= 280) && (e.side == "lb") && (w.side == "lb"))
					{
						w.eggs_caught++;
						i.remove();
					}	
					if ((e.y >= 270) && (e.y <= 280) && (e.side == "rb") && (w.side == "rb"))
					{
						w.eggs_caught++;
						i.remove();
					}									
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
	   if (e.getSource().equals(btnGame)){
		   btnGame.setVisible(false);
		   btnRules.setVisible(false);
		   btnControl.setVisible(false);		   
		   imgText = null;
		   btnBack.setVisible(false);	
		   imgTitle = null;
		   imgHare = null;
		   img = imgField;
		   imgPausePlay = imgPause;
		   imgMenu = imgMainMenu;
		   
		   	w = new Wolf();
			eggsFactory= new Thread(this);
			eggs = new ArrayList<Egg>();
			eggsFactory.start();
			setFocusable(true);
	   }
	   else if (e.getSource().equals(btnRules)){
		   btnGame.setVisible(false);
		   btnRules.setVisible(false);
		   btnControl.setVisible(false);		   
		   imgText = imgRules;
		   btnBack.setVisible(true);		   
	   }
	   else if (e.getSource().equals(btnControl)){
		   btnGame.setVisible(false);
		   btnRules.setVisible(false);
		   btnControl.setVisible(false);		   
		   imgText = imgControl;
		   btnBack.setVisible(true);		   
	   }
	   else if (e.getSource().equals(btnBack)){
		   imgText = null;
		   btnBack.setVisible(false);		   
		   btnGame.setVisible(true);
		   btnRules.setVisible(true);
		   btnControl.setVisible(true);
	   }	   
	   repaint();
	}
	
	@Override
	public void run() {
	    int arrx[] = {182, 552};
	    int arry[] = {175, 240};
		Image img = new ImageIcon(getClass().getClassLoader().getResource("res/egg.png")).getImage();
		while(true){
			Random rand = new Random();
			int x = arrx[rand.nextInt(arrx.length)];
			int y = arry[rand.nextInt(arry.length)];
			String side = "";
			if (x == 182){
				if (y == 175){
					side = "lt";
				}
				else{
					side = "lb";
				}					
			}
			else{
				if (y == 175){
					side = "rt";
				}
				else{
					side = "rb";
				}				}
			
			if (eggs != null)
		    eggs.add(new Egg(x, y, 0.0, side, this));

			try {
				Thread.sleep(rand.nextInt(10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
