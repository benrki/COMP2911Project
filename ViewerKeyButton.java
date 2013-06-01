import javax.swing.JButton;


public class ViewerKeyButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String label;
	
	public ViewerKeyButton(String label){
		this.label = label;
		this.setText(label);
	}
	
	public String getLabel(){
		return label;
	}
}
