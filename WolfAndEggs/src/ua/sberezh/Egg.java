package ua.sberezh;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Egg {
	int x;
	int y;
	double v;
	String side;
	Image img_egg = new ImageIcon(getClass().getClassLoader().getResource("res/egg.png")).getImage();
	Image img_broken = new ImageIcon(getClass().getClassLoader().getResource("res/egg_broken.png")).getImage();
	Image img = img_egg; 
	Field field;
	
	final int max_left_x = 287;
	final int min_right_x = 456; 
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 33, 33);
	}
	
	public Egg(int x, int y, double v, String side, Field field){
		this.x = x;
		this.y = y;
		this.v = v;
		this.side = side;
		this.field = field;
	}
	
	public void move(){
		y = (int)Math.ceil(y + 0.0001);  
		if (y > 370){
			y = 370;
			img = img_broken;
		}		
		if (y != 370){
			if ((side == "lt") || (side == "lb")){
				if (x < max_left_x){
					x = (int)Math.ceil(x + 2.95);
				}
				else{
					x = max_left_x;
				}
			}else{
				if (x > min_right_x){
					x = (int)Math.ceil(x - 2.95);
				}
				else{
					x = min_right_x;
				}				
			}
		}
	}
}