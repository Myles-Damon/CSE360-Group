import javax.swing.*;    // to use JFrame and JButton
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame{
	
	
	private static void init(){
		
		final int APP_WIDTH = 800;
		final int APP_HEIGHT = 600;
		
		
		JFrame frame = new JFrame("Network Explorer");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tPane = new JTabbedPane();
		JButton quit = new JButton("Quit");
		
		quit.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {
		       frame.dispose();
		    }
		});
		
		ArrayList<Activity> actList;
		
		actList = new ArrayList<Activity>();
		
		
		MainPanel panel1 = new MainPanel(actList);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		panel.add(panel1);
		panel.add(quit);
		
		Container content = frame.getContentPane();
		
		content.add(panel);
		
		//content.add(panel1);
		//content.add(button1);
		
		frame.setSize(APP_WIDTH, APP_HEIGHT);
		frame.setVisible(true);
		
		class ActionListener
	    {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 
	        	 frame.dispose();
	         }
	    }
	
	}	
	
	
	public static void main(String [] args){
		
	//	makeWindow();
		init();
		
	}
	
	
}
