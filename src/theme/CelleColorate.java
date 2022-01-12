package theme;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *  Classe che estende DefaultTableCellRenderer per personalizzare il colore delle celle della tabella
 *  
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class CelleColorate extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;

	/**
	 * Metodo per scegliere il colore della intera riga in base allo stato
	 * della spedizione 
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		this.setHorizontalAlignment(JLabel.CENTER);

		
		String stato = (String) table.getValueAt(row, 7);
		switch(stato) {
		
			case "IN PREPARAZIONE":{setBackground(Color.ORANGE);break;} //fase inziale di ogni pacco
			
			case "IN TRANSITO":{setBackground(Color.PINK);break;} //stato intermedio
			case "RIMBORSO RICHIESTO":{setBackground(Color.GRAY);break;} //stato intermedio 
			
			case "RICEVUTA":{setBackground(Color.GREEN);break;} //stato finale
			case "FALLITA":{setBackground(Color.RED);break;} //stato finale
			case "RIMBORSO EROGATO":{setBackground(Color.GREEN);break;}//stato finale
			
			default: {setBackground(Color.BLUE);break;}// non ci sara mai
		}
	return this;
	}

}

