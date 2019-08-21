package ua.sberezh;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AddOpenButtons extends JButton {

	public AddOpenButtons() {
	    JButton buttonAdd = new JButton("New Button");
	    buttonAdd.setSize(new Dimension(200, 200));
	    ImageIcon icon1 = createIcon("res/btnGame.png");	    
	    buttonAdd.setIcon(icon1);
	    buttonAdd.setHorizontalTextPosition(SwingConstants.LEFT);
	    
	}
	protected ImageIcon createIcon(String path) {
	    URL imgURL = AddOpenButtons.class.getResource(path);
	    if (imgURL != null) {
	    	System.out.println("dgsetr");
	        return new ImageIcon(imgURL);
	        
	    } else {
	        System.err.println("File not foundl " + path);
	        System.out.println("dgsetr9");
	        return null;
	    }
	}
}