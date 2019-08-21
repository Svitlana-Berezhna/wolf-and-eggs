package ua.sberezh;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class Wolf {	
	public static final int MAX_V = 200;
	public static final int MAX_LEFT = 45;
	public static final int MAX_RIGHT = 223;
	
	Image img_c = new ImageIcon(getClass().getClassLoader().getResource("res/Wolf.png")).getImage();
	Image img_lt = new ImageIcon(getClass().getClassLoader().getResource("res/Wolf_lt.png")).getImage();
	Image img_lb = new ImageIcon(getClass().getClassLoader().getResource("res/Wolf_lb.png")).getImage();
	Image img_rt = new ImageIcon(getClass().getClassLoader().getResource("res/Wolf_rt.png")).getImage();
	Image img_rb = new ImageIcon(getClass().getClassLoader().getResource("res/Wolf_rb.png")).getImage();
	
	Image img = img_lb; 
	int eggs_caught, eggs_broken;
	String side = "lb";
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 33, 33);
	}		
	
	int v = 1;
	int dv = 0;
	int s = 0;
	
	int x = 302;
	int y = 215;
	int dx = 0;
	
	int layer1 = 0;
	int layer2 = 680;
	
	public void move(){
		s += v;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (key == 65){
			img = img_lt; 
			side = "lt";
			x = 297;
			y = 219;
			dv = 1;
		}	
		if (key == 90){
			img = img_lb; 
			side = "lb";
			x = 302;
			y = 215;
			dv = -1;
		}	
		if (key == 75){
			img = img_rt; 
			side = "rt";
			x = 341;
			y = 220;
			dx = -3;
		}	
		if (key == 77){
			img = img_rb; 
			side = "rb";
			x = 342;
			y = 220;
			dx = 3;
		}		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
			dv = 0;
		}		
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
			dx = 0;
		}		
		
		if (key == 65){
			img = img_lt; 
			side = "lt";
		}	
		if (key == 90){
			img = img_lb; 
			side = "lb";
		}	
		if (key == 75){
			img = img_rt;
			side = "rt";
		}	
		if (key == 77){
			img = img_rb; 
			side = "rb";
		}			
	}	
}