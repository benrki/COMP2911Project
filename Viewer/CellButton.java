import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CellButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Position pos;
	private JLabel number;
	private ArrayList<Integer> candidates;
	private JLabel canLine1;
	private JLabel canLine2;
	private JLabel canLine3;

	private final static int MAX_NUM_CANDIDATES = 9;
	private final static Font NUMBER_FONT = new Font("sansserif",Font.BOLD,18);
	private final static Font CANDIDATES_FONT = new Font("sansserif",Font.BOLD,10);
	
	
	public CellButton(Position pos){
		this.pos = pos;
		this.number = new JLabel();
		this.number.setFont(NUMBER_FONT);
		this.number.setText(null);
		this.canLine1 = new JLabel();
		this.canLine2 = new JLabel();
		this.canLine3 = new JLabel();
		this.canLine1.setFont(CANDIDATES_FONT);
		this.canLine2.setFont(CANDIDATES_FONT);
		this.canLine3.setFont(CANDIDATES_FONT);
		this.canLine1.setText(null);
		this.canLine2.setText(null);
		this.canLine3.setText(null);
		candidatesVisible(false);
		numberVisible(false);
		this.setLayout(new GridBagLayout());
		GridBagConstraints line1c = new GridBagConstraints();
		GridBagConstraints line2c = new GridBagConstraints();
		GridBagConstraints line3c = new GridBagConstraints();
		GridBagConstraints numberc = new GridBagConstraints();
		line1c.gridy = 0;
		line2c.gridy = 1;
		line3c.gridy = 2;
		numberc.gridy = 1;
		this.add(number, numberc);
		this.add(canLine1, line1c);
		this.add(canLine2, line2c);
		this.add(canLine3, line3c);
		this.candidates = null;
		this.setMargin(new Insets(1,1,1,1));
	}


	public Position getPosition(){
		return pos;
	}
	
	public void clearNumberLabel(){
		this.number.setText(null);
		this.updateLabel();
	}
	
	public void clearCandidateLabel(){
		this.candidates = null;
		this.canLine1.setText(null);
		this.canLine2.setText(null);
		this.canLine3.setText(null);
		this.updateLabel();
	}
	
	public void setNumberLabel(String string){
		this.number.setText(string);
		this.updateLabel();
	}
	
	public void setCandidateLabel(ArrayList<Integer> candidates){
		this.candidates = candidates;
		System.out.println(candidates.toString());
		StringBuffer line1 = new StringBuffer();
		StringBuffer line2 = new StringBuffer();
		StringBuffer line3 = new StringBuffer();
		for(int i = 1; i <= MAX_NUM_CANDIDATES; i ++){
			if(candidates.contains(i)){
				if(i <= 3){
					line3.append(i + "   ");
				}else if(i <= 6){
					line2.append(i + "   ");
				}else{
					line1.append(i + "   ");
				}
			}else{
				if(i <= 3){
					line3.append("     ");
				}else if(i <= 6){
					line2.append("     ");
				}else{
					line1.append("     ");
				}
			}
		}
		this.canLine1.setText(line1.substring(0, line1.length()-3));
		this.canLine2.setText(line2.substring(0, line2.length()-3));
		this.canLine3.setText(line3.substring(0, line3.length()-3));
		this.updateLabel();
	}
	
	public void setNumberColor(Color c){
		number.setForeground(c);
	}
	
	public void setCandidateColor(Color c){
		canLine1.setForeground(c);
		canLine2.setForeground(c);
		canLine3.setForeground(c);
	}

	private void updateLabel(){
		if(number.getText() == null){
			if(candidates != null){
				if(candidates.size() > 0){
					candidatesVisible(true);
					numberVisible(false);
				}else{
					candidatesVisible(false);
					numberVisible(false);
				}
			}else{
				candidatesVisible(false);
				numberVisible(false);
			}
		}else{
			candidatesVisible(false);
			numberVisible(true);
		}
	}
	
	private void candidatesVisible(boolean b){
		canLine1.setVisible(b);
		canLine2.setVisible(b);
		canLine3.setVisible(b);
	}
	
	private void numberVisible(boolean b){
		number.setVisible(b);
	}
	
}
