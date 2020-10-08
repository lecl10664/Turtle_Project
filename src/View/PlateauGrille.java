package View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlateauGrille extends JPanel {

	public PlateauGrille() {

	}


	public void paintComponent(Graphics g) {
		Image imagePlateau;
		try {
			imagePlateau = ImageIO.read(new File("res/images/plateau.jpeg"));
			g.drawImage(imagePlateau, 0, 0, this.getWidth(), this.getHeight(), this);

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
