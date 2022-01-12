package theme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;


import categorie.spedizione;
/**
 * Classe estensione di Thread per automatizzare il cambio di stato da parte del admin
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class ThreadSped extends Thread {

	/*creare un thread che periodicamente (ogni x secondi) cambi lo stato di una spedizione scelta in maniera
	casuale. Una volta scelta una spedizione, se essa è in transito si implementi una logica attraverso la quale la
	spedizione fallirà con una certa probabilità (ci si aiuti con un generatore di numeri casuali)*/
	
	private Vector<spedizione> lista ;
	
	private CoreTabella tablemodel;
	/**
	 * Costruttore principale di ThreadSped
	 * @param lista un vector con tutte le spedizoni presenti nello schermo del admin
	 * @param tablemodel la tabella sul quale lavorare
	 */
public ThreadSped(Vector<spedizione> lista , CoreTabella tablemodel) {
	this.lista = lista;
	this.tablemodel = tablemodel;
}	

	/**
	 * Metodo run che fa partire il Thread
	 */
	@Override
	public void run() {
		
		int lunghezzalista = lista.size();
		int contatorefatte = 0;
		
		ArrayList<Integer> randomindex = new ArrayList<>();
	
		for (int i = 0; i < lunghezzalista; i++) randomindex.add(i);
	
		Collections.shuffle(randomindex);
	
		while(contatorefatte < lunghezzalista) {
	
	        for (int i = 0; i < lunghezzalista; i++) {
	            spedizione temp = lista.elementAt(randomindex.get(i));
	            
	            if (temp.getStato().equals("FALLITA")) {contatorefatte++; continue;}
	            if (temp.getStato().equals("RIMBORSO EROGATO")) {contatorefatte++; continue;}
	            if (temp.getStato().equals("RICEVUTA")) {contatorefatte++; continue;}
	          
	            try {sleep(5000); } catch (InterruptedException e) {return;}
	           
	            temp.updatesped();
	            lista.set(randomindex.get(i), temp);
	          
	            tablemodel.fireTableDataChanged();
            }
			
		}
		
						
		
	}
	
}
