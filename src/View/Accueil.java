package View;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Accueil extends JPanel {
		
		
	
	
	public void paintComponent(Graphics g) {
		Image imageFond;
		try {
			imageFond = ImageIO.read(new File("res/images/background.jpeg"));
			g.drawImage(imageFond, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	    
	    
		
		
		



		
		

		
		
		
	   
