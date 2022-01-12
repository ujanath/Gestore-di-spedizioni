package categorie;
import persone.*;
import theme.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 	Classe che descrive una spedizione (Spedizione normale)
 * @author Janath Uthayakumar matricola 145610
 *
 */

public class spedizione {
	/**
	 * nome del cliente che ha creato la spedizione
	 */
	private final String nomecreatore;
	/**
	 *  codice alfa-numerico unico che rappresneta la spedizione
	 */
	private  String codice;
	/**
	 *  nome delle destinazione
	 */
	private final String destinazione;
	/**
	 * peso della spedizone
	 */
	private final int peso;
	/**
	 * data in cui é stata creata la spedizione
	 */
	private final String datacreazione;
	/**
	 *  un affermazione o negazione per categorizzare la spedizione
	 */
	private String assicurata = "NO";
	/**
	 * valore assicurato di una spedizione , di base a 0 perché non é assicurata
	 */
	private  int valore = 0 ;
	/**
	 * stato attuale della spedzione
	 */
	private String stato ;
	/**
	 * Metodo costruttore primario per creare una spedizione 
	 * @param p rappresenta la persona che ha creato la spedizione
	 * @param dest rappresenta la destinazione della spedizione
	 * @param weight rappresenta il peso della spedizione 
	 */
	
	
	public spedizione (persona p , String dest , int weight ) {
		this.nomecreatore = p.getUsername();
		this.destinazione = dest;
		this.peso = weight;
		
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		String cattura = sf.format(new Date());
		this.datacreazione = cattura;
		
		this.codice = Data.generacodice();
		
		
		while(!Data.controllocodice(this.codice)) {this.codice = Data.generacodice();};
		
		this.stato = "IN PREPARAZIONE";
	
		
		
		
	}
	/**
	 * Metodo costruttore secondario per creare una spedizione dall'esterno del programma leggendo i datu su un file
	 * @param nome il nome utente del creatore della spedizione
	 * @param cod il codice della spedizione
	 * @param dest la destinazione della spedizione
	 * @param weight il peso della spedizione
	 * @param data  data creazione della spedizione
	 * @param status stato della spedizione 
	 */
			
	public spedizione (String nome , String cod , String dest , int weight , String data , String status) {
		this.nomecreatore = nome;
		this.codice = cod;
		this.destinazione = dest;
		this.peso = weight;
		this.datacreazione = data;
		this.stato = status;
		
	}
	/**
	 * Metodo che fa avanzare lo stato di una spedizione di uno stadio
	 */
	
	public void updatesped() {
		switch(this.stato) {
		case "IN PREPARAZIONE":
			this.stato = "IN TRANSITO";
			break;
		case "IN TRANSITO":
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(99);
			if( randomInt > 60 ) { this.stato = "FALLITA" ;
				break;
			}
			this.stato="RICEVUTA";
			break;
		case "FALLITA":
			break;
		default: 
			break;
		
		}
	}
	/**
	 *  Metodo che trasforma il contenuto di una spedizione in stringa
	 * @return una stringa dei contenuti della spedizione spazziati dal carattere "|"
	 */
	
	public String salvastringa() {
		return (getNomecreatore() + "|" + getCodice() + "|" + getDestinazione() + "|" +
				getPeso() + "|" + getDatacreazione ()+ "|"  + "NO" + "|" +  "0"  + "|" + getStato() + "\n" ) ;
		
	}
	/**
	 * Metodo per ottenere il nome del creatore della spedizione
	 * @return il nome del creatore della spedizione
	 */

	public String getNomecreatore() {
		return nomecreatore;
	}
	/**
	 * metodo per ottenere il codice alfa-numerico della spedizione 
	 * @return il codice alfa-numerico della spedizione
	 */
	public String getCodice() {
		return codice;
	}
	/**
	 * metodo per ottenere la destinazione della spedizione 
	 * @return la destinazione della spedizione
	 */
	public String getDestinazione() {
		return destinazione;
	}
	/**
	 * metodo per ottenere il peso della spedizione 
	 * @return il peso della spedizione
	 */
	public int getPeso() {
		return peso;
	}
	/**
	 * metodo per ottenere la data di creazione della spedizione 
	 * @return la data di creazione della spedizione 
	 */
	public String getDatacreazione() {
		return datacreazione;
	}
	/**
	 * metodo per ottenere lo stato di creazione della spedizione 
	 * @return lo stato di creazione della spedizione 
	 */
	public String getStato() {
		return stato;
	}
	/**
	 * Metodo per impostare lo stato della spedizione
	 * @param stato lo stato da impostare per la spedizione
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}
	/**
	 * Metodo per ottenere la categoria della spedizione 
	 * @return categoria della spedizione si se assicura no se non lo é 
	 */
	public String getAssicurata() {
		return assicurata;
	}
	/**
	 * metodo per ottenere il valore della spedizione 
	 * @return il valore della spedizione
	 */
	
	public int getValore() {
		return valore;
	}
	/**
	 * metodo per impostare il valore della spedizione 
	 * @param valore il valore da settare 
	 */
	public void setValore(int valore) {
		this.valore = valore;
	}
	/**
	 * metodo per impostare la categoria della spedzione
	 * @param s é un "SI" per assicurate e un "NO" per le normali
	 */
	public void setAssicurata(String s) {
		this.assicurata = s;
	}
	
	
}
