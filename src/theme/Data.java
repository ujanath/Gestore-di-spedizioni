package theme;

import java.io.*;
import java.util.Vector;


import categorie.spedizione;
import categorie.spedizionesicura;
import persone.*;
/**
 * Classe astratta che contiene gli input e output di dati su txt e viceversa
 * @author Janath Uthayakumar matricola 145610
 *
 */
public abstract class Data {
	
	/**
	 * Metodo che prende dentro di se un codice e controllo se esso sia gia stato assegnato
	 * ad un pacco , molto probabile che non avro mai un codice doppione  
	 * @param codice prende il codice precedentemente generato 
	 * @return ritorna un boolean per vedere se é unico in tutto il database
	 */
	@SuppressWarnings("resource")
	public static boolean controllocodice(String codice){
		System.out.println("controllo se "+ codice +" sia disponibile come nome utente ");
		 InputStreamReader ponte;
	     BufferedReader lettore;
	     File file;
	     File f = new File("");
	        try {
	        		file = new File(f+"Datispedizioni.txt");
	        		if(!file.exists()) return true;
	        		ponte = new InputStreamReader(new FileInputStream(file));
	        		lettore = new BufferedReader(ponte);
	        		String Temporanea;
	        		String[] dati;
	        		do {
	        				Temporanea = lettore.readLine();
	        				if(Temporanea == null) return true;
	        				dati = Temporanea.split("\\|");
	        				if((dati[1]).equals(codice)) {return false;}
	        			
	        		 	 } while (lettore.ready());
	        		
	        } catch(IOException e) {
	        	System.err.println("Errore: Controllo Codice");
	        	return false;
	        } 
	       return true;       
	}
	/**
	 * Metodo per generare codice , genera casualmente 8 caratteri da usare come codice univoco
	 * @return il codice generato randomicamente
	 */
	public static String generacodice()
    {	
		int codice = 8;
        String Index = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  
        StringBuilder sb = new StringBuilder(codice);
  
        for (int i = 0; i < codice; i++) {
            int index = (int)(Index.length() * Math.random());
            sb.append(Index.charAt(index));}
        return sb.toString();
    }
    
	/**
	 * Metodo che controlla se il nome é gia presente nel database
	 * @param nomedacontrollare il nome che dobbiamo controllare
	 * @return un boolean vero se é libero ; falso se é presente
	 */
	@SuppressWarnings("resource")
	public static boolean controllonome(String nomedacontrollare){
		System.out.println("controllo se "+ nomedacontrollare +" sia disponibile come nome utente ");
		 InputStreamReader ponte;
	     BufferedReader lettore;
	     File fileutente;
	    
	     
	     File f = new File("");
	  
	 
	        try {
	        		fileutente = new File(f+"Datiutenti.txt");
	        		if(!fileutente.exists()) {return true;}
	        		ponte = new InputStreamReader(new FileInputStream(fileutente));
	        		lettore = new BufferedReader(ponte);
	        		String Temporanea;
	        		String[] dati;
	        	
	        		do {
	        				Temporanea = lettore.readLine();
	        				if(Temporanea == null) return true;
	        				dati = Temporanea.split("\\|");
	        				if((dati[0]).equals(nomedacontrollare)) {return false;}
	        			
	        		 	 } while (lettore.ready());
	        	
	        
	        	
	        } catch(IOException e) {
	        	System.err.println("registrazione , fallimento nell'apertura del file user");
	        	return false;
	        }
	        
	       
	       return true;
	        
	        
	}
	/**
	 * Metodo che ricrea la classe persona partendo dal nomeutente 
	 * @param nomedacontrollare  nome della persona
	 * @return la persona 
	 */
	@SuppressWarnings("resource")
	public static persona nomeinuser(String nomedacontrollare){
		System.out.println("controllo se "+ nomedacontrollare +" sia disponibile come nome utente ");
		 InputStreamReader ponte;
	     BufferedReader lettore;
	     File fileutente;
	    
	     
	     File f = new File("");
	  
	     persona errore = new persona("errore" ,"errore" ,"errore"  );
	 
	        try {
	        		fileutente = new File(f+"Datiutenti.txt");
	        		if(!fileutente.exists()) return errore;
	        	
	        		ponte = new InputStreamReader(new FileInputStream(fileutente));
	        		lettore = new BufferedReader(ponte);
	        		String Temporanea;
	        		String[] dati;
	        	
	        		do {
	        				Temporanea = lettore.readLine();
	        				if(Temporanea == null) return errore ;
	        		 
	        				dati = Temporanea.split("\\|");
	        				

	        				if((dati[0]).equals(nomedacontrollare)) {
	        					
	        					
	        					
	        					persona utenteloggato = new persona( dati[0] ,dati[1] ,dati[2]  );
	        					return utenteloggato;
	        				}
	        			
	        		 	 
	        	} while (lettore.ready());
	        	
	        
	        	
	        } catch(IOException e) {
	        	System.err.println("registrazione , fallimento nell'apertura del file user");
	        	return errore;
	        }
	       return errore;
	}
	
	/**
	 * Metodo che va a controllare se il nomeutente e password inseriti dek utente della 
	 * sessione corrente siano giusti
	 * @param nomedacontrollare stringa contenente il nomeutente
	 * @param passwordimessa stringa contenente la password
	 * @return un boolean vero se sono giusti ; falso se sono errati 
	 */
	@SuppressWarnings("resource")
	public static boolean login(String nomedacontrollare , String passwordimessa){
		
		System.out.println("controllo se "+ nomedacontrollare +" e "+ passwordimessa +" sono nel database ");
		 InputStreamReader ponte;
	     BufferedReader lettore;
	     File fileutente;
	     String Temporanea;
	     
	     File f = new File("");
	    
   
	        try {
	        	fileutente = new File(f+"Datiutenti.txt");
	        	if(!fileutente.exists()) { 
	        		 System.out.println("login , file non rilveato");
	        		 return false;
	        	}
	        	ponte = new InputStreamReader(new FileInputStream(fileutente));	
	        	lettore = new BufferedReader(ponte);
	        	
	        	do {
	        		 Temporanea = lettore.readLine();
	        		 if(Temporanea != null) {
	        			 String[] dati = Temporanea.split("\\|");
	        			 if((dati[0]).equals(nomedacontrollare)&&dati[1].equals(passwordimessa));
	        			 {
	        				 System.out.println("login , nome e password coincidono ");
	        				 return true;
	        			 }
	        		 }	 
	        	} while (lettore.ready());	
	        } catch(IOException e) {
	        	System.err.println("fallimento nell'apertura del file user");
	        	return false;
	        }
	        
	        System.out.println("login, password sbagliata");
	        return false;
      
	}
	/**
	 * Metodo usato per salvare un nuovo Cliente appena registrato 
	 * @param nuovoutente prende in ingresso una persona
	 * @return boolean vero se é stato salvato con successo ; flaso se non é stato possibile 
	 */
	public static boolean salvautente(persona nuovoutente) {
		FileWriter scrittore;
		File fileutente;
		 File f = new File("");

		try {
			fileutente= new File(f+"Datiutenti.txt");
			if(fileutente.createNewFile()) {
					System.out.println("File creato come nuovo");}
			else {
				System.out.println("File giá esistente nel sistema");}
			 scrittore = new FileWriter(fileutente, true); 
			
			} catch(IOException e){System.err.println("salvautente , errore nella scrittura file");
			return false;}
		
		
		try {
			scrittore.write(nuovoutente.salvastringa());
			scrittore.close();
			
		} catch(IOException e){System.err.println("salvautente , errore nella scrittura file");
		return false;}
		
	
		
		return true;
	}
	/**
	 * Metodo per salavare le spedizioni sul database 
	 * @param nuovasped la spedizione da salvare
	 * @return   boolean vero se é stato salvato con successo ; flaso se non é stato possibile 
	 */
	public static boolean salvasped(spedizione nuovasped) {
		FileWriter scrittore;
		File fileutente;
		 File f = new File("");
		try {
			fileutente= new File(f+"Datispedizioni.txt");
			if(fileutente.createNewFile()) {
					System.out.println("File creato come nuovo");}
			else {
				System.out.println("File giá esistente nel sistema");}
			 scrittore = new FileWriter(fileutente, true); 
			
			} catch(IOException e){System.err.println("salvautente , errore nella scrittura file");
			return false;}
		
		
		try {
			scrittore.write(nuovasped.salvastringa());
			scrittore.close();
			
		} catch(IOException e){System.err.println("salvautente , errore nella scrittura file");
		return false;}
		return true;
	}
	/**
	 * Metodo usato per controllare se il carattere speciale "|" é presente nella stringa inserita
	 * @param check la stringa da controllare
	 * @return  boolean vero se é presente ; flaso se non é presente 
	 */
	public static boolean controllo(String check ){
		
		if(check.contains("|")) {return true;}
		else return false;
	
	}
	/**
	 * Metodo usato per caricare le spedizioni su un Vector 
	 * @param sped vector di destinazione 
	 * @return ritorna il vector con dentro le spedizioni
	 */
	@SuppressWarnings("resource")
	public static boolean caricasped(Vector <spedizione> sped) {
		System.out.println("carico i file sul vector " + sped);
		 InputStreamReader ponte;
	     BufferedReader lettore;
	     File file;
	    
	     
	     File f = new File("");
	  
	 
	        try {
	        		file = new File(f+"Datispedizioni.txt");
	        		if(!file.exists()) return false;
	        		ponte = new InputStreamReader(new FileInputStream(file));
	        		lettore = new BufferedReader(ponte);
	        		String Temporanea;
	        		String[] dati;
	        		int intpeso;
					int intvalass;
	        	
	        		do{
	        				Temporanea = lettore.readLine();
	        				if(Temporanea == null) return false;
	        				
	        				dati = Temporanea.split("\\|");
	        				
	        				 
	        				
	        				if(dati[5].equals("SI")) {
	        					
	        					try {intpeso = Integer.parseInt(dati[3]);}
	        						catch (NumberFormatException e) {intpeso = 0;}
	        					
	        				
	        					try { intvalass = Integer.parseInt(dati[6]);}
	        						catch (NumberFormatException e) {intvalass = 0;}
	        					
	        				spedizionesicura temp1 = new spedizionesicura(dati[0] , dati[1] ,dati[2] , intpeso ,dati[4] , intvalass , dati[7] );
	        				sped.add(temp1);
	        				}
	        				
	        				if(dati[5].equals("NO")) {
	        					
	        					
	        					try {intpeso = Integer.parseInt(dati[3]);}
        						catch (NumberFormatException e) {intpeso = 0;}
	        					
	        					
	        					spedizione temp2 = new spedizione(dati[0] , dati[1] ,dati[2] , intpeso ,dati[4] , dati[7] );	
	        					sped.add(temp2);
	        					
	        				}
	        		 	 } while (lettore.ready());
	        		
	        } catch(IOException e) {
	        	System.err.println("Codice giá presente");
	        	return false;
	        }
	        
	       
	       return true;
	}
	/**
	 * Metodo per salvare tutte le spedizioni modificate nel databse sovrascrivendo le vecchie 
	 * @param sped un vector spedizione 
	 * @return  boolean vero se é stato salvato con successo ; flaso se non é stato possibile 
	 */
	public static boolean supersalva(Vector <spedizione> sped) {
		FileWriter scrittore;
		File fileutente;
		File elimina;
		File f = new File("");

		try {
			
			elimina = new File(f+"Datispedizioni.txt");
			PrintWriter writer = new PrintWriter(elimina);
			writer.print("");
			writer.close();
			fileutente= new File(f+"Datispedizioni.txt");
			if(fileutente.createNewFile()) {
					System.out.println("File creato come nuovo");}
			else {
				System.out.println("File giá esistente nel sistema");}
			 scrittore = new FileWriter(fileutente, true); 
			
			} catch(IOException e){System.err.println("salvautente , errore nella scrittura file");
			return false;}
		
		
		try {
			for (spedizione array : sped) {scrittore.write(array.salvastringa());}
			
			scrittore.close();
		
			} catch(IOException e){System.err.println("salvautente , errore nella scrittura file");
									return false;}
		
		return true;
	}

	
} 
