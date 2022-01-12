package persone;
/**
 * Classe che descrive una persona (Cliente che utilizza questo servizio)
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class persona {
	/**
	 * nome utente del cliente scelto in fase di registrazione unico non cambiabile
	 */
	private final String username;
	/**
	 * password del cliente scelto in fase di registrazione unico non cambiabile
	 */
	private final String password;
	/**
	 * indirizzo del cliente scelto in fase di registrazione unico non cambiabile
	 */
	private final String indirizzo;
	
	/**
	 * Metodo costruttore per creare una persona 
	 * @param u username scelta dal cliente
	 * @param p password scelta dal cleinte 
	 * @param i indirizzo scelto dal cleiente 
	 */
	
	public persona ( String u , String p , String i) {
		this.username = u ;
		this.password = p ;
		this.indirizzo = i;
	}
	
	
	/**
	 * Metodo per ottenere il nomeutente di una persona persona
	 * @return
	 */
	
	public String getUsername() {
		return username;
	}

	/**
	 * Metodo per salvare i dati di una persona in formato stringa 
	 * @return una stringa che contiene tutti i dati della persona spazziati dal carattere "|"
	 */
	 public String salvastringa() {
	        return (username + "|" + password + "|" + indirizzo + "\n" );
	 }
	 }  
	 



