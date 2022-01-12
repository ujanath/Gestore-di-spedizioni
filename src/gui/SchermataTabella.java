package gui;

import javax.swing.*;


import categorie.spedizione;
import persone.persona;
import theme.Data;
import theme.CelleColorate;
import theme.CoreTabella;
import theme.ThreadSped;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
/**
 * Classe che estende Core per creare un frame che fa da Menu per la Tabella del  Cliente o Admin
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class SchermataTabella extends Core {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<spedizione> lista = new Vector<>();
	
	private Vector<spedizione> listautente = new Vector<>();
	private Vector<spedizione> listarestante = new Vector<>();
	
	private JPanel contenitore , superiore  , inferiore ;
	private JLabel istruzioni ;
	private JButton  avvio , indietro , logout , rimborso , elimina;
	
	private CoreTabella tableModel;
	private JTable tableSpedizioni;

	private boolean dicontrollo = false;
	private boolean statothread = false;
	
	private ThreadSped thread;
	private persona creatore;
	
	/**
	 * Metodo Costruttore per la classe Tabella
	 * chiamato per entrambe le varianti
	 */
	public SchermataTabella() {
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contenitore = new JPanel();
		superiore  = new JPanel();
		inferiore  = new JPanel();	
		
		
		avvio = new JButton("Inizializza le spedizioni");
		avvio.addActionListener(this);
		indietro = new JButton("Torna alla Schermata HOME");
		indietro.addActionListener(this);
		logout = new JButton("Logout");
		logout.addActionListener(this);
		rimborso = new JButton("Rimborsa casella selezionata");
		rimborso.addActionListener(this);
		elimina = new JButton("Elimina casella selezionata");
		elimina.addActionListener(this);
		
		contenitore.setLayout(new BorderLayout());
		
		contenitore.add(superiore, BorderLayout.NORTH);
		
		contenitore.add(inferiore, BorderLayout.SOUTH);
		
		Data.caricasped(lista);
	
		
	}
	/**
	 * Metodo costruttore primario per creare la tabella di uno specifico Cliente
	 * @param u si riferisce alla classe persona al quale e associata il Cliente  
	 */
	public SchermataTabella(persona u ) {
		
		this();
		System.out.println("creata nuova finestra : " + this.getClass());
		
		creatore = u;
		
		separasped(creatore);
		
		istruzioni = new JLabel("ciao user "+ u.getUsername());
		
		superiore.add(istruzioni);
		
		tableModel = new CoreTabella(listautente);
        tableSpedizioni = new JTable(tableModel);
        
        tableSpedizioni.setRowHeight(30);
        tableSpedizioni.getColumnModel().getColumn(7).setPreferredWidth(185);
        tableSpedizioni.getTableHeader().setReorderingAllowed(false);
        tableSpedizioni.setDefaultRenderer(Object.class, new CelleColorate());
        tableSpedizioni.setPreferredScrollableViewportSize(tableSpedizioni.getPreferredSize());
	      
        
	        
	        
		        JScrollPane pannelloTable = new JScrollPane(tableSpedizioni);
		        
		        contenitore.add(pannelloTable, BorderLayout.CENTER);
		        
		        inferiore.add(indietro);
		
		        inferiore.add(logout);
		        inferiore.add(rimborso);
		        
		        this.add(contenitore);
        
		        addWindowListener(new WindowAdapter(){
					 @Override
					 public void windowClosing(WindowEvent e) {
						 
						 
						 JOptionPane.showMessageDialog(null, "Salvando Dati sul database");
						 mergespedsave();
						System.exit(0);
					 }
				 }); 
		        
		        
		        
	}
	
	/**
	 * Metodo Costruttore Secondario per visualizzare la tabella completa come admin
	 * @param admin un boolean usato per controllare che sia l'admin
	 */
	
	public SchermataTabella(boolean admin) {
		this();
		
		dicontrollo = admin;
		
		
		
		istruzioni = new JLabel("ciao user Admin: inzializza pure le spedizioni");
		
		superiore.add(istruzioni);
		
		tableModel = new CoreTabella(lista);
        tableSpedizioni = new JTable(tableModel);
        
        tableSpedizioni.setRowHeight(30);
        
        tableSpedizioni.getColumnModel().getColumn(7).setPreferredWidth(185);
        tableSpedizioni.getTableHeader().setReorderingAllowed(false);
        tableSpedizioni.setDefaultRenderer(Object.class, new CelleColorate());
        tableSpedizioni.setPreferredScrollableViewportSize(tableSpedizioni.getPreferredSize());
        
      
        JScrollPane pannelloTable = new JScrollPane(tableSpedizioni);
        
        contenitore.add(pannelloTable, BorderLayout.CENTER);

        superiore.add(avvio);
        
        inferiore.add(logout);
        inferiore.add(elimina);
        
        this.add(contenitore);
        
        addWindowListener(new WindowAdapter(){
			 @Override
			 public void windowClosing(WindowEvent e) {
				 
				 
				 JOptionPane.showMessageDialog(null, "Salvando Dati sul database");
				 Data.supersalva(lista);
				System.exit(0);
			 }
		 }); 
	}
/**
 * Metodo per  seprare le spedizioni totali lista in 2 gruppi di spedizioni
 * in quelle del cliente specifico listautente
 * in quelle restanti listarestante  	
 * @param target
 * @return
 */
public boolean separasped( persona target) {
		int loop = lista.size();
		
		for(int i = 0 ; i < loop ; ++i ){
			if(lista.get(i).getNomecreatore().equals(target.getUsername())){
				listautente.add(lista.get(i));
					} else {listarestante.add(lista.get(i));}
		}
		if(!(listautente.size()>0)) {System.err.println("utente "+ target +" non ha spedizioni nel vector lista "); return false;}
		
		return true;
		
	}
/**
 * Metodo per unire le liste separate e salvarle dopo eventuali modifiche 
 * @return
 */
public boolean mergespedsave() {
		if(listautente.size()<0) 
		{return false;
			} else {
					listarestante.addAll(listautente);
					Data.supersalva(listarestante);
					return true;
			}
}

public Vector<spedizione> getsped(){
	return lista;
}

@Override
public void actionPerformed(ActionEvent Scelta) {
	String scelta = Scelta.getActionCommand();
	Core core;
	switch(scelta) {
		/*
		 *  Torno alla schermata precedente
		 */
	
		case "Torna alla Schermata HOME":
			this.dispose();
			core = new Schermataprincipale(creatore);
			core.setVisible(true);
			core.setPreferredSize(new Dimension(800, 150));
			core.pack();
			core.setResizable(false);
			core.setLocationRelativeTo(null);
			mergespedsave();
			break;
		/*
		 * Rimborso dal parte di utente
		 * possibile se solo se la spedizone in questione e una spedizionesicura in stato "FALLITA"
		 */
		case "Rimborsa casella selezionata":
			
			int index = tableSpedizioni.getSelectedRow();
			
			spedizione temporanea ;
			if(index != -1) {
			temporanea = listautente.get(index);
			
			
			if(temporanea.getAssicurata().equals("SI")&&temporanea.getStato().equals("FALLITA")) {
				System.out.println("valido per rimborso");
				temporanea.setStato("RIMBORSO RICHIESTO");
			
				listautente.set(index , temporanea);
				 JOptionPane.showMessageDialog(null, "Rimborso Richiesto con successo");
				tableModel.fireTableDataChanged();
				
			}else {System.err.println("non vlaido per rimborso");}
			
			
			
			break;
			}
			break;
			/*
			 * logout dalla sessione attuale ritrono alla schermata di partenza iniziale
			 */
		case"Logout":
			this.dispose();
			core = new SchermataIniziale();
			core.setVisible(true);
			core.setPreferredSize(new Dimension(800, 150));
			core.pack();
			core.setResizable(false);
			core.setLocationRelativeTo(null);
			if(!dicontrollo) {mergespedsave();}
			{Data.supersalva(lista);}
			break;
			
		// SCELTE DEL ADMIN
			/*
			 *  pulsante per inizializzare il thread 
			 */
		case"Inizializza le spedizioni":
			
			statothread = !statothread;
			
			if(statothread) {
				if(thread == null) {thread = new ThreadSped(this.lista , this.tableModel);}
				
				thread.start();
				
				avvio.setText("Ferma Spedizione");
				JOptionPane.showMessageDialog(null, "Il Thread e stato avviato");
				istruzioni.setText("Thread e stato avviato se vuoi fermarlo ripremi il pulsante :");
				break;
				
				
			}
			/*
			 * pulsante per fermare il thread 
			 */
		case"Ferma Spedizione":
			thread.interrupt();
			thread = null;
			avvio.setText("Inizializza le spedizioni");
			JOptionPane.showMessageDialog(null, "Il Thread e stato fermato");
			istruzioni.setText("Thread e stato fermato se vuoi riavviarlo ripremi il pulsante :");
			break;
			
			/*
			 * pulsante per eliminare la casella della spedizione se essa e fallita 
			 */
		case"Elimina casella selezionata":
			int ind = tableSpedizioni.getSelectedRow();
			
			spedizione temp = lista.get(ind);
			if(ind != -1) {
				/*
				*Si ricorda che se una spedizione ha gia raggiunto uno stato finale (RICEVUTA, RIMBORSO EROGATO), essa
					non puo cambiare stato.
					L admin puo cancellare dal sistema le spedizioni che si trovano in uno stato finale. La rimozione di una entry
					da parte dell admin rende tale spedizione non piu visibile dal cliente.
				*
				*/
				if(temp.getStato().equals("RICEVUTA")||temp.getStato().equals("RIMBORSO EROGATO")) {
					
					lista.remove(ind);
					JOptionPane.showMessageDialog(null, "Spedizione eliminata con successo");
					tableModel.fireTableDataChanged();
				
			}else {JOptionPane.showMessageDialog(null, "Spedizione non in stato finale");}
		
			break;
			}
			break;
			
	}
}
    @Override
    public void windowClosing(WindowEvent e) {
        System.err.println("banana");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("RAN EVENT HANDLER");
    }
}


