package Avvio;
import java.awt.Dimension;

import gui.*;

/**
 * Classe Main che si occupa di creare il JFrame inziale 
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class Avvio {
	public static void main(String[] args) {
		
		Core start = new SchermataIniziale();
		start.setVisible(true);
		start.setPreferredSize(new Dimension(600, 150));
		start.pack();
		start.setResizable(false);
		start.setLocationRelativeTo(null);
}
}