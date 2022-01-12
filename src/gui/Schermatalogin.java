package gui;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import theme.Data;

/**
 * Classe che  estende Core per creare un frame che si occupa del login
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class Schermatalogin extends Core {

	
	private static final long serialVersionUID = 1L;
	
	// compoentni per la parte grafica
	private JLabel istruzioni, username , pw ;
	private JButton accedi , indietro ;
	private JTextField nomeutente;
	private JPasswordField password;
	private JPanel contenitore , superiore , centrale , inferiore ;
	
	/**
	 * Metodo costruttore della Schermatalogin adebita al login 
	 * @param val se ture , Puó accedere solo l'admin ; se false , gli utenti normali
	 */
	public Schermatalogin(boolean val) {
		
		super();
		
		System.out.println("creata nuova finestra : " + this.getClass());
		
		boolean valore = val;
		
		contenitore = new JPanel();
		superiore  = new JPanel();
		centrale  = new JPanel();
		inferiore  = new JPanel();	
		contenitore.setLayout(new BorderLayout());
	
	//parte superiore
		
		
		if(valore == true) {istruzioni = new JLabel("Bentornato Amministratore");} 
		if(valore == false){ istruzioni = new JLabel("Ciao Utente , effettua pure il login");}	
		superiore.add(istruzioni);
		
		
	//parte centrale
		
		nomeutente = new JTextField("",15);
		password = new JPasswordField("",15);
		username = new JLabel("               Nome Utente:");
		pw = new JLabel("               Password:");
		
		centrale.setLayout(new GridLayout(2, 2));
		centrale.add(username);
		centrale.add(nomeutente);
		centrale.add(pw );
		centrale.add(password);
		
	// parte ineriore
		
		 accedi = new JButton("Accedi");
		 accedi.addActionListener(this);
		 indietro = new JButton("Schermata principale");
		 indietro.addActionListener(this);
		
		 inferiore.add(indietro);
		 inferiore.add(accedi);	
		 
    //unione dei micro componenti 		
	
		contenitore.add(superiore, BorderLayout.NORTH);
		contenitore.add(centrale, BorderLayout.CENTER);
		contenitore.add(inferiore, BorderLayout.SOUTH);
				
				
				
		this.add(contenitore);
		System.lineSeparator();	
		}
		
	@Override
	public void actionPerformed(ActionEvent Scelta) {
		String scelta = Scelta.getActionCommand();
		Core Core;
		switch(scelta) {
	
			case "Accedi":
				String pass = "";
				String nome = nomeutente.getText();
				char[] passarray = password.getPassword();
				
				for (char parole : passarray) {
					pass = pass + parole;
				}

				
				
				/* controllo se NOMEUTENTE O PASSWORD risultano vuoti
				 * se lo sono fermo qui il programma e lo segnalo all'utente
				 */
				if(pass.equals("")||nome.equals("")) {
					JOptionPane.showMessageDialog(this, "nome utente o password risultano vuoti , controlla e riprova", "Tentativo fallito", JOptionPane.ERROR_MESSAGE);
				
					break;
				}
						/* controllo se NOMEUTENTE O PASSWORD appartengono all'Admin
						 * se lo sono fermo qui il programma e lo segnalo all'utente
						 */
						if(pass.equals("admin")||nome.equals("admin")) {
							JOptionPane.showMessageDialog(null, "Buonrientro Amministratore");
							this.dispose();
							Core = new SchermataTabella(true);
							Core.setVisible(true);
							Core.pack();
							Core.setLocationRelativeTo(null);
							break;
					
					
					
				}
				/* controllo aggiuntivo sul NOMEUTENTE se é attualmente registrato
				 * utile per capire se qualcuno ha sbaglia solo la password o anche il nome utente
				 */
				if(Data.controllonome(nome)) {
					System.err.println("NOMEUTENTE NON PRESENTE");
					JOptionPane.showMessageDialog(this, "nome utente non presente nel sistema sicuro di esserti registrato ?", "Tentativo fallito", JOptionPane.ERROR_MESSAGE);
					nomeutente.setText("");
					password.setText("");
					break;
					
				
				}
				/* controllo se NOMEUTENTE O PASSWORD risultano presenti nel databse
				 * avendo passato il controllo precedente sono sicuro che il noem é nel database 
				 * ma l'utente sta effettivamente sbagliando la password
				 */
				if(!Data.login(nome, pass)) {
					System.err.println("NOMEUTENTE PRESENTE MA PASSWORD SBAGLIATA");
					JOptionPane.showMessageDialog(this, "nome utente o password sono sbagliati!", "Tentativo fallito", JOptionPane.ERROR_MESSAGE);
					nomeutente.setText("");
					password.setText("");
					
				
				}else {
					/* Dopo tutti i controlli sono sicuro che vada bene 
					 * carico i dati della persona e li metto 
					 */
					
					System.out.println("NOMEUTENTE E PASSWORD GIUSTE , UTENTE AUTENTICATO ");
					Core = new Schermataprincipale(Data.nomeinuser(nome));
					this.dispose();
					Core.setVisible(true);
					Core.setPreferredSize(new Dimension(600, 150));
					Core.pack();
					Core.setResizable(false);
					Core.setLocationRelativeTo(null);

				}
				
				break;
				
				/* 
				 * Utente sceglie di tornare alla shcermata precedente 
				 * 
				 */
		
			case "Schermata principale":
				this.dispose();
				Core = new SchermataIniziale();
				Core.setVisible(true);
				Core.setPreferredSize(new Dimension(600, 150));
				Core.pack();
				Core.setResizable(false);
				Core.setLocationRelativeTo(null);
				
				break;
			default:
				break;
			
				
				
		}
	}
		
		
		
	}


