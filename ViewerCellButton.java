import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ViewerCellButton extends JButton {
	
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
	private boolean given;
	private boolean selected;
	private boolean valid;
	private boolean correct;

	private final static int MAX_NUM_CANDIDATES = 9;
	private final static Font NUMBER_FONT = new Font("sansserif",Font.BOLD,18);
	private final static Font CANDIDATES_FONT = new Font("sansserif",Font.BOLD,10);
	
	private final static Color SELECTED_BACKGROUND_COLOR = Color.YELLOW;
	private final static Color INVALID_BACKGROUND_COLOR = Color.RED;
	private final static Color INCORRECT_NUMBER_COLOR = Color.MAGENTA;
	private final static Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	private final static Color GIVEN_NUMBER_COLOR = Color.BLACK;
	private final static Color SET_NUMBER_COLOR = Color.BLUE;
	private final static Color DEFAULT_CANDIDATES_COLOR = Color.BLUE;
	
	
	
	public ViewerCellButton(Position pos){
		this.pos = pos;
		this.selected = false;
		this.valid = true;
		this.given = false;
		this.correct = true;
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
		this.setMargin(new Insets(0,0,0,0));
		updateColors();
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
	
	public void updateColors(){
		if(given == true){
			number.setForeground(GIVEN_NUMBER_COLOR);
		}else if(correct == false){
			number.setForeground(INCORRECT_NUMBER_COLOR);
		}else{
			number.setForeground(SET_NUMBER_COLOR);
		}
		if(selected == true){
			this.setBackground(SELECTED_BACKGROUND_COLOR);
		}else if(valid == true){
			this.setBackground(DEFAULT_BACKGROUND_COLOR);
		}else{
			this.setBackground(INVALID_BACKGROUND_COLOR);
		}
		
		canLine1.setForeground(DEFAULT_CANDIDATES_COLOR);
		canLine2.setForeground(DEFAULT_CANDIDATES_COLOR);
		canLine3.setForeground(DEFAULT_CANDIDATES_COLOR);
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
		updateColors();
	}
	
	private void candidatesVisible(boolean b){
		canLine1.setVisible(b);
		canLine2.setVisible(b);
		canLine3.setVisible(b);
	}
	
	private void numberVisible(boolean b){
		number.setVisible(b);
	}
	
	public void setSelected(boolean b){
		this.selected = b;
		updateColors();
	}
	
	public boolean getSelected(){
		return this.selected;
	}
	
	public void setValid(boolean b){
		this.valid = b;
		updateColors();
	}
	
	public void setGiven(boolean b){
		this.given = b;
		updateColors();
	}
	
	public void setCorrect(boolean b){
		this.correct = b;
		updateColors();
	}
}
