import javax.swing.JButton;

public class CellButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Position pos;
	
	public CellButton(String string, Position pos){
		this.setText(string);
		this.pos = pos;
	}
	
	public CellButton(Position pos){
		this.pos = pos;
	}


	public Position getPosition(){
		return pos;
	}
	
	public void clearLabel(){
		this.setText(null);
	}
}
