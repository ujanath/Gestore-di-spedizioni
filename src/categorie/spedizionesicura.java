package categorie;
import persone.*;

/**
 * Metodo Figlio di spedizione che va descrivere una spedizionesicura (Spedizione Assicurata)
 * @author Janath Uthayakumar matricola 145610
 *
 */

public class spedizionesicura extends spedizione {
	/**
	 * Metodo costruttore principale per la classe spedizionesicura (Spedizione Assicurata) , utilizzata per crearla dall'interno del programma
	 * @param a rappresenta la persona che ha creato la spedizionesicura (Spedizione Assicurata)
	 * @param dest rappresenta la destinazione  della spedizionesicura
	 * @param weight rappresenta il peso della spedizionesicura
	 * @param valore rappresenta il valore assicurato della spedizionesicura
	 */
	public spedizionesicura(persona a, String dest, int weight , int valore) {
		super(a, dest, weight);
		setValore(valore);
		this.setAssicurata("SI");
	}

	/**
	 * Metodo costruttore secondario per la classe spedizionesicura (Spedizione Assicurata)
	 * @param nome rappresenta il nome utente della persona che ha creato la spedizionesicura (Spedizione Assicurata)
	 * @param cod rappresenta il codice alfanumerico unico che caraterizza spedizionesicura
	 * @param dest rappresenta la destinazione della spedizionesicura
	 * @param weight rappresenta il peso della spedizionesicura
	 * @param data rappresenta la data di creazione della della spedizionesicura
	 * @param valore rappresenta il valore assicurato della spedizionesicura
	 * @param status rappresenta lo stato della spedizionesicura
	 */
	
	public spedizionesicura(String nome, String cod, String dest, int weight, String data , int valore , String status) {
		super(nome, cod, dest, weight, data, status);
		setValore(valore);
		this.setAssicurata("SI");
	}
	/**
	 * Metodo che fa avanzare lo stato di una spedizione di uno stadio . nel caso della spedizionesicura 
	 * si aggiunge la possibilita di richiedere una richiesta  rimborso in caso di fallimento e 
	 * di erogare un rimborso nel caso di una richesta di rimborso 
	 */
	@Override
	public void updatesped() {
		super.updatesped();
		if("RIMBORSO RICHIESTO".equals(getStato())) {
			setStato("RIMBORSO EROGATO");
		}
	}
	/**
	 *  Metodo che trasforma il contenuto di una spedizionesicura in stringa
	 *  @return una stringa dei contenuti della spedizionesicura spazziati dal carattere "|"
	 */
	@Override
  public String salvastringa() {
		return (getNomecreatore() + "|" + getCodice() + "|" + getDestinazione() + "|" + getPeso() + "|" + getDatacreazione ()+ "|"  + "SI" + "|" +  getValore()  + "|" + getStato() + "\n") ;
		
	}
	
}
