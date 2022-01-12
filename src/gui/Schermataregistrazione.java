package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import persone.persona;
import theme.Data;

/**
 *  Classe che estende Core per creare un frame che fa da Menu per la registrazione  del  Cliente
 * @author Janath Uthayakumar matricola 145610
 *
 */

public class Schermataregistrazione extends Core {

	private static final long serialVersionUID = 1L;

	/*
	 *	Componenti principali per la parte grafica 
	 */
	private JLabel istruzioni, username , pw1 , address , pw2;
	private JButton registrati , indietro ;
	private JTextField nomeutente , indirizzo;
	private JPasswordField password1, password2;
	
	private JPanel contenitore , superiore , centrale , inferiore ;
	
	/*
	 *  Metodo Costruttore per la creazione della Schermataregistrazione
	 */
	
	public Schermataregistrazione() {
		
		super();
		
		System.out.println("creata nuova finestra : " + this.getClass());
		
		contenitore = new JPanel();
		superiore  = new JPanel();
		centrale  = new JPanel();
		inferiore  = new JPanel();	
		contenitore.setLayout(new BorderLayout());
		
		
		//PARTE SUPERIORE
		istruzioni = new JLabel("registrati inserendo nomeutente, passsword e il tuo indirizzo");
		
		superiore.add(istruzioni);
		
		//PARTE CENTRALE
		indirizzo = new JTextField("",40);
		nomeutente = new JTextField("",15);
		password1 = new JPasswordField("",15);
		password2 = new JPasswordField("",15);
		username = new JLabel("   Nome Utente:");
		pw1 = new JLabel("   Password:");
		pw2 = new JLabel("   Conferma Password:");
		address =  new JLabel("   Indirizzo");
		
		centrale.setLayout(new GridLayout(4, 2));
		centrale.add(username);
		centrale.add(nomeutente);
		centrale.add(pw1 );
		centrale.add(password1);
		centrale.add(pw2 );
		centrale.add(password2);
		centrale.add(address);
		centrale.add(indirizzo);
		
		
		
		//PARTE INFERIORE
		 registrati = new JButton("Registrati");
		 registrati.addActionListener(this);
		 indietro = new JButton("Schermata principale");
		 indietro.addActionListener(this);
		
		 inferiore.add(indietro);
		 inferiore.add(registrati);
		 
		//UNIONE DEI COMPONENTI 
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
	
			case "Registrati":
				
				String nome = nomeutente.getText();
				String addrs = indirizzo.getText();
				
				char[] passarray1 = password1.getPassword();
				
				String pass1 = "";
				for (char parole : passarray1) { pass1 = pass1 + parole; }
				
				String pass2 = "";
				char[] passarray2 = password1.getPassword();
				
				for (char parole : passarray2) { pass2 = pass2 + parole; }
				
			
			
				/*
				 * Controllo banalmente se i campi sono vuoti
				 * fermo il programma se lo sono
				 */
				if(nome.equals("")||addrs.equals("")||pass1.equals("")||pass2.equals("") ) {
					JOptionPane.showMessageDialog(this, "Errore: risultano dei campi vuoti", "Errore", JOptionPane.WARNING_MESSAGE);
					nomeutente.setText("");
					password1.setText("");
					password2.setText("");
					indirizzo.setText("");
					break;
				}
				/*
				 * Controllo se � stato usato il carattere "|" , usato da me per la creazione del programma 
				 * notifico l'utente se lo � 
				 */
				if(Data.controllo(nome) ||Data.controllo(addrs) || Data.controllo(pass1) || Data.controllo(pass2) ) {
					JOptionPane.showMessageDialog(this, "Errore: presenza di carattere non idoneo:     |    [VERTICAL LINE]     ", "Errore", JOptionPane.WARNING_MESSAGE);
					nomeutente.setText("");
					password1.setText("");
					password2.setText("");
					indirizzo.setText("");
					break;
				}
				/*
				 * Controllo se il nome utente � gia presente nel database
				 * notifico l'utente se lo � 
				 */
				if(!Data.controllonome(nome)) {
					System.err.println("NOMEUTENTE GIA PRESENTE");
					JOptionPane.showMessageDialog(this, "nome utente gia revocato , scegline un altro per proseguire", "Errore", JOptionPane.ERROR_MESSAGE);
					nomeutente.setText("");
					password1.setText("");
					password2.setText("");
					indirizzo.setText("");
					break;
					
				}
				/*
				 * Superati tutti i controlli sono sicuro che il prgramma funziona 
				 */
				persona nuovo = new persona(nome,pass1,addrs);
				
				if(!Data.salvautente(nuovo)) {System.err.println("ERRORE NELL'AGGIUNTA ");
				} else {System.out.println("UTENTE CREATO CON SUCCESSO  ");}
				
				JOptionPane.showMessageDialog(this, "TI SEI REGISTRATO CON SUCCESSO , EFFETTUA IL LOGIN", "ANDATO BENE", JOptionPane.INFORMATION_MESSAGE);
				
				this.dispose();
				core = new SchermataIniziale();
				core.setVisible(true);
				core.setPreferredSize(new Dimension(800, 150));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				break;
			case "Schermata principale":
				this.dispose();
				core = new SchermataIniziale();
				core.setVisible(true);
				core.setPreferredSize(new Dimension(800, 150));
				core.pack();
				core.setResizable(false);
				core.setLocationRelativeTo(null);
				
				break;
			default:
				break;
		}
	}
}
