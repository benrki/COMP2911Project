import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class MenuBarController {
	private MenuBar menuBar;
	
	public MenuBarController(MenuBar menuBar) {
		this.menuBar = menuBar;
		this.menuBar.addEasyGameListener(new easyGameListener());
		this.menuBar.addMediumGameListener(new mediumGameListener());
		this.menuBar.addHardGameListener(new hardGameListener());
		this.menuBar.addCustomGameListener(new customGameListener());
		this.menuBar.addOptionListener(new optionListener());
	}
	
	class easyGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class mediumGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	class hardGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	class customGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	class optionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Option");
		}
		
	}
	
	
}
