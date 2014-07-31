package ca.bcit.comp2613.battleship.model;
import javax.swing.table.DefaultTableModel;

public class SwingPlayerModel extends DefaultTableModel {
	
	@Override
	public boolean isCellEditable (int row, int column) {
		//all cells false
		return false;
	}
	
	

}
