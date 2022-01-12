package theme;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import categorie.*;

/**
 * Classe che estende AbstractTableModel
 * @author Janath Uthayakumar matricola 145610
 *
 */
public class CoreTabella extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] Nomicolonna= {"Nome", "Codice", "Destinazione", "Peso", "Data","Assicurata" , "Valore Assicurato" , "Stato"};

	private Vector<spedizione> lista ;
	
	
	
	public CoreTabella(Vector<spedizione> listau ) {
		this.lista = listau;
		
	}

	
	public int getColumnCount() {
		return Nomicolonna.length;
	}


	public int getRowCount() {
		return lista.size();
	}


	public Object getValueAt(int row, int col) {

		spedizione rowItem = lista.elementAt(row);
        
         switch (col) {
            case 0 : return rowItem.getNomecreatore();
            case 1 : return rowItem.getCodice();
            case 2 : return rowItem.getDestinazione();
            case 3 : return rowItem.getPeso();
            case 4 : return rowItem.getDatacreazione();
            case 5 : return rowItem.getValore();
            case 6 : return rowItem.getAssicurata();
            case 7 : return rowItem.getStato();
            default : return null;
        }
	}
	   public void addRow(spedizione spedizione){
		   lista.add(spedizione);
	        fireTableChanged(new TableModelEvent(this));
	    }


	 @Override
	    public String getColumnName(int column) {
	        return Nomicolonna[column];
	    }

	 


}

