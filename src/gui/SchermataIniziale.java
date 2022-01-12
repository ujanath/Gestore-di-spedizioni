package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * Classe Figlia di core che rappresenta la schermata principale del programma dove si puo decidere
 * se fare un login come utente o adming o registrarsi 
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class SchermataIniziale extends Core {
	private static final long serialVersionUID = 1L;
	
	
	private JLabel istruzionibot , titolo ;
	private JButton adminlogin , utentelogin , registrazione;
	private JPanel contenitore , superiore , centrale , inferiore ;
	
	/**
	 * Metodo Costruttore per creare la schermata inziale 
	 */
	public SchermataIniziale() {
		
		super();
		contenitore = new JPanel();
		superiore  = new JPanel();
		centrale  = new JPanel();
		inferiore  = new JPanel();	
		contenitore.setLayout(new BorderLayout());
		
		System.out.println("creata nuova finestra : " + this.getClass());
		
		
		//PARTE SUPERIORE
		
			titolo = new JLabel("");
			titolo.setText("Gestore Spedizioni matricola 145610");
			
			superiore.add(titolo);
			
		//PARTE CENTRALE
			
			adminlogin = new JButton("Area Admin");
			adminlogin.addActionListener(this);
			
			utentelogin = new JButton("Accedi come Utente");
			utentelogin.addActionListener(this);
			
			centrale.add(utentelogin);
			centrale.add(adminlogin);
			
		//PARTE INFERIORE
	
			istruzionibot = new JLabel("Sei un nuovo Cliente ?");
			
			registrazione = new JButton("Registrati");
			registrazione.addActionListener(this);
				
			inferiore.add(istruzionibot);
			inferiore.add(registrazione);
				
		//MICROCOMPONENTI
			
			contenitore.add(superiore, BorderLayout.NORTH);
			contenitore.add(centrale, BorderLayout.CENTER);
			contenitore.add(inferiore, BorderLayout.SOUTH);
					
			this.add(contenitore);
				
	}

	

	@Override
	public void actionPerformed(ActionEvent Scelta) {
		String scelta = Scelta.getActionCommand();
		Core core;
		
		System.lineSeparator();
		switch(scelta) {
		
			/*
			 * Bottone per entrare nell'area riservata all'admin
			 */
		
			case "Area Admin":
				
				this.dispose();
				core = new Schermatalogin(true); // admin
				core.setVisible(true);
				core.setPreferredSize(new Dimension(600, 150));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				break;
				/*
				 * Bottone per registrarsi  se non lo si � 
				 */
				
			case "Registrati":
				
				this.dispose();
				core = new Schermataregistrazione(); 
				core.setVisible(true);
				core.setPreferredSize(new Dimension(600, 200));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				
				break;
				/*
				 * Bottone per accedere con le proprie credenziali
				 */
				
			case "Accedi come Utente":
				
				this.dispose();
				core = new Schermatalogin(false); 
				core.setVisible(true);
				core.setPreferredSize(new Dimension(600, 150));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				break;
				
				
				
			default:
				break;
			
				
				
		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
