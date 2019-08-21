package ua.sberezh;
import javax.swing.JFrame;
public class Main 
{
	public static void main(String[] args) 
	{
	    final String TITLE_OF_PROGRAM = "Wolf and eggs";
	    final int WIN_WIDTH = 771;  
	    final int WIN_HEIGHT = 526;  
	    
		JFrame f = new JFrame(TITLE_OF_PROGRAM);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(WIN_WIDTH, WIN_HEIGHT);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.add(new Field());
		f.setVisible(true);
	}
}