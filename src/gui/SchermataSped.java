package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import categorie.spedizione;
import categorie.spedizionesicura;
import persone.persona;
import theme.Data;
/**
 * Classe che estende Core per creare un frame che fa da Menu per le spedizioni del Cliente
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class SchermataSped extends Core  {

	
	private static final long serialVersionUID = 1L;
	
	private JLabel  destinazione , peso , assicurato , istruzioni;
	private JButton conferma , indietro , cambio;
	private JTextField det , pes , valass;
	private boolean check;
	
	private JPanel contenitore , superiore , centrale , inferiore ;
	persona creatore;
	
	/**
	 * Metodo di creazione per la classe SchermataSped
	 * @param u si riferisce al Cliente loggato attualmente chev vuole creare la spedizione
	 * @param scelta usato per differenziare tra spedizione e spedizionesicura
	 */
	public SchermataSped(persona u , boolean scelta) {
		super();
		
		System.out.println("creata nuova finestra : " + this.getClass());
		
		contenitore = new JPanel();
		superiore  = new JPanel();
		centrale  = new JPanel();
		inferiore  = new JPanel();
		check = scelta;
		creatore = u;
		contenitore.setLayout(new BorderLayout());
		
			//PARTE SUPERIORE
		
			istruzioni = new JLabel("crea una SPEDIZIONE NORMALE");
			if(check) {istruzioni = new JLabel(" crea una SPEDIZIONE ASSICURATA" );}
			
			superiore.add(istruzioni);
			
					//PARTE CENTRALE
					
				
					destinazione = new JLabel("               Inserisci la Destinazione");			
					peso = new JLabel("               Inserisci il peso");
					
					assicurato = new JLabel("               Inserisci il valore d'assicurare");
					det = new JTextField("",40);
					det.setPreferredSize(getPreferredSize());
					pes = new JTextField("",6);
					pes.setPreferredSize(getPreferredSize());
					valass = new JTextField("",6);
					valass.setPreferredSize(getPreferredSize());
					
					
				
					// DISATTIVO LA CASELLA VAL ASSICURATO
					if(!check) {valass.setEditable(false); }
							
					centrale.setLayout(new GridLayout(3, 2));
					
					centrale.add(destinazione);
					centrale.add(det);
					
					centrale.add(peso);
					centrale.add(pes);
					
					if(check) {centrale.add(assicurato);
					centrale.add(valass);}
					
						
							//PARTE INFERIORE
							 conferma = new JButton("Conferma");
							 conferma.addActionListener(this);
							 cambio = new JButton("");
							 cambio.addActionListener(this);
							 indietro = new JButton("Schermata percedente");
							 indietro.addActionListener(this);
							 
							 cambio.setText("Cambia in Assicurata");
							 if(check) {cambio.setText("Cambia in Non Assicurata");}
							
							 inferiore.add(indietro);
							 inferiore.add(cambio);
							 inferiore.add(conferma);
							 
					 
		//COMPLETA
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
	
			case "Conferma":
				// 	COMPONENTI
				String inputpeso = pes.getText();
				String inputindirizzo = det.getText();
				String inputval = valass.getText();
				spedizione nuovasped;
				
				/*
				 * Controllo la presenza di campi vuoti
				 * se presenti lo segnalo
				 */
				if(inputpeso.equals("")||inputindirizzo.equals("")) { 
					pes.setText("");
					det.setText("");
					JOptionPane.showMessageDialog(this, "ERRORE: sono presenti campi vuoti ", "ERRORE", JOptionPane.WARNING_MESSAGE);
					break;
				}
				if(check) { if(inputval.equals("")) {
					valass.setText("");
					JOptionPane.showMessageDialog(this, "ERRORE: sono presenti campi vuoti", "ERRORE", JOptionPane.WARNING_MESSAGE);
				}}
				
				/*
				 * Converto il peso isnerito ad un intero positivo
				 * se impossibile lo segnalo per richiedere un valore convertibile 
				 */
				int intpeso;
				try {
					   intpeso = Integer.parseInt(inputpeso);
					
					}catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this, "ERRORE: peso inserito non e un numero intero positivo", "PESO NON VALIDO", JOptionPane.ERROR_MESSAGE);
						pes.setText("");
						break;
					}
				
				if(intpeso>999999){	
						JOptionPane.showMessageDialog(this, "ERRORE: peso olte il limite massimo 6 cifre", "PESO NON VALIDO", JOptionPane.ERROR_MESSAGE);
						pes.setText("");
						break;
					}

				
				
				if(!check) {nuovasped = new spedizione(creatore , inputindirizzo , intpeso);
					System.out.println("creata nuova spedizione "+ nuovasped.salvastringa());
					
					if(!Data.salvasped(nuovasped)) {System.err.println("spedizione non salvata");}
						else {JOptionPane.showMessageDialog(this, "Spedizione creata con sucesso , creane un altra o semplicemente torna nel menu princiaple", "CREATO CON SUCCESSO", JOptionPane.INFORMATION_MESSAGE);
						pes.setText("");
						det.setText("");
						break;
						}
					
					
					}
				/*
				 * Converto il valore inserito ad un intero positivo
				 * se impossibile lo segnalo per richiedere un valore convertibile 
				 */
				int intval = 0;
				
				if(check) {
				try {
					   intval = Integer.parseInt(inputval);

					}catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this, "ERRORE: valore assicurato inserito non e un numero intero positivo", "VALORE ASSICURATO NON VALIDO", JOptionPane.ERROR_MESSAGE);
						valass.setText("");
						break;
					}

				if(intval>9999){	
						JOptionPane.showMessageDialog(this, "ERRORE: valore assicurato olte il limite massimo 4 cifre", "PESO NON VALIDO", JOptionPane.ERROR_MESSAGE);
						valass.setText("");
						break;
						}

					/*
					 * Dopo che ha passato tutti i check creo la spedizione 
					 */
					nuovasped = new spedizionesicura(creatore , inputindirizzo , intpeso , intval);  
					if(!Data.salvasped(nuovasped)) {System.err.println("spedizione assicurata non salvata");}
					else {JOptionPane.showMessageDialog(this, "Spedizione creata con sucesso , creane un altra o semplicemente torna nel menu princiaple", "CREATO CON SUCCESSO", JOptionPane.INFORMATION_MESSAGE); }
					pes.setText("");
					det.setText("");
					valass.setText("");
						break;
				}
				
				break;
			case "Cambia in Assicurata":
				
				this.dispose();
				core = new SchermataSped(creatore , true);
				core.setVisible(true);
				core.setPreferredSize(new Dimension(600, 200));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				break;
				
			case "Cambia in Non Assicurata":
				
				
				this.dispose();
				core = new SchermataSped(creatore , false);
				core.setVisible(true);
				core.setPreferredSize(new Dimension(600, 200));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				break;
				
			case "Schermata percedente":
				core = new Schermataprincipale(creatore);
				this.dispose();
				core.setVisible(true);
				core.setPreferredSize(new Dimension(600, 150));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				
				break;
		}
	}


}
