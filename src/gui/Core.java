package gui;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Classe JFrame mpdificata che uso come base per creare la mia GUI
 * @author Janath Uthayakumar matricola 145610
 *
 */


public  class Core extends JFrame implements ActionListener, WindowListener  {
	

	private static final long serialVersionUID = 1L;


	public Core() {
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}


	   @Override
	    public void windowOpened(WindowEvent e) {}
	    @Override
	    public void windowClosing(WindowEvent e) {}
	    @Override
	    public void windowClosed(WindowEvent e) {}
	    @Override
	    public void windowIconified(WindowEvent e) {}
	    @Override
	    public void windowDeiconified(WindowEvent e) {}
	    @Override
	    public void windowActivated(WindowEvent e) {}
	    @Override
	    public void windowDeactivated(WindowEvent e) {}
		@Override
		public void actionPerformed(ActionEvent e) {}
	
	
}
