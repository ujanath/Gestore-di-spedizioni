package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import persone.persona;

/**
 * Classe che estende Core per creare un frame che fa da Menu principale del Cliente
 * @author Janath Uthayakumar matricola 145610
 *
 */

public class Schermataprincipale extends Core{

	private static final long serialVersionUID = 1L;
	
	/*
	 * Componenti Grafiche
	 */
	private JPanel contenitore , superiore , centrale , inferiore ;
	private JButton snorm , sass , storia , logout;
	private JLabel istruzioni;
	
	private persona creatore;
	
	
	/**
	 * Metodo Costruttore per creare una schermata HOME in base al parametro utente
	 * @param u parametro utente
	 */
	public Schermataprincipale(persona u) {
		super();
		
		contenitore = new JPanel();
		superiore  = new JPanel();
		centrale  = new JPanel();
		inferiore  = new JPanel();	
		contenitore.setLayout(new BorderLayout());
		
		System.out.println("creata nuova finestra : " + this.getClass());

		creatore = u;
	
		istruzioni = new JLabel("Bentornato " + u.getUsername() );

		//SUPERIORE
		
		superiore.add(istruzioni);
		
			//CENTRALE
			snorm = new JButton("Crea Spedizione Assicurata");
			snorm.addActionListener(this);
			sass = new JButton("Crea Spedizione normale");
			sass.addActionListener(this);
			storia = new JButton("Cronologia Spedizioni");
			storia.addActionListener(this);
			
			centrale.add(snorm);
			centrale.add(sass);
			centrale.add(storia);
			
		
				//INFERIORE
				logout = new JButton("Logout");
				logout.addActionListener(this);
				inferiore.add(logout);
				contenitore.add(superiore, BorderLayout.NORTH);
				contenitore.add(centrale, BorderLayout.CENTER);
				contenitore.add(inferiore, BorderLayout.SOUTH);
						
				
				
		this.add(contenitore);
		System.lineSeparator();	
	}
	

	@Override
	public void actionPerformed(ActionEvent Scelta) {
		String scelta = Scelta.getActionCommand();
		Core core;
		switch(scelta) {
		case "Crea Spedizione Assicurata":
			this.dispose();
			core = new SchermataSped(creatore , true);
			core.setVisible(true);
			core.setPreferredSize(new Dimension(600, 200));
			core.pack();
			core.setResizable(false);
			core.setLocationRelativeTo(null);
			break;
		case "Crea Spedizione normale":
			this.dispose();
			core = new SchermataSped(creatore , false);
			core.setVisible(true);
			core.setPreferredSize(new Dimension(600, 200));
			core.pack();
			core.setResizable(false);
			core.setLocationRelativeTo(null);
			break;
		case "Cronologia Spedizioni":
			this.dispose();
			core = new SchermataTabella(creatore);
			core.setVisible(true);
			core.pack();
			core.setLocationRelativeTo(null);
			break;
			
		case "Logout":
			this.dispose();
			core = new SchermataIniziale();
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
}
