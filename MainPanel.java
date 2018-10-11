
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainPanel extends JPanel {

	private JButton add;
	private JButton help;
	private JButton process;
	private JButton about;
	private JButton reset;
	private JButton quit;
	private JTextField name;
	private JTextField depend;
	private JTextField time;
	private JTextArea area;
	private JPanel inputPanel;
	private JPanel buttonPanel;
	private JPanel inputAndButtons;
	private Activity activity;
	private ArrayList<Activity> actList;
	private PseudoCodeNonsense objectForParsing;
	
	private PERT_Node activityNode;
	public ArrayList<PERT_Node> nodeList;
	public int numberOfNodes = 0;
	
	public MainPanel(ArrayList<Activity> actList, ArrayList<PERT_Node> nodeList){
		
		this.actList = actList;
		this.nodeList = new ArrayList<PERT_Node>();
		//int numberOfNodes = 0;
		
		JLabel actName = new JLabel("<html>" + "Enter name of activity" + "</html>");
		JLabel actDep = new JLabel("<html>" +"Enter dependencies of activity. Enter a comma between activities" + "</html>");
		JLabel actTime = new JLabel("<html>" + "Enter the duration of the activity. (Positive, integer)" + "</html>");
		
		
		name = new JTextField();	
		depend = new JTextField();
		time = new JTextField();
		
		 
	      inputPanel = new JPanel();
	      inputPanel.setLayout(new GridLayout(3,2));
	      
	      inputPanel.add(actName);
	      inputPanel.add(name);
	      
	      inputPanel.add(actDep);
	      inputPanel.add(depend);   
	      
	      inputPanel.add(actTime);
	      inputPanel.add(time);
		
	      add = new JButton("<html>"+ "Add Activity" +"</html>");
	      add.addActionListener(new ButtonListener());
	      
	      help = new JButton("<html>"+ "help" +"</html>");
	      help.addActionListener(new ButtonListener());
	      
	      about = new JButton("<html>"+ "about" +"</html>");
	      about.addActionListener(new ButtonListener());
	      
	      process = new JButton("<html>"+ "process" +"</html>");
	      process.addActionListener(new ButtonListener());
	      
	      reset = new JButton("<html>"+ "reset" +"</html>");
	      reset.addActionListener(new ButtonListener());
	      
	      quit = new JButton("<html>"+ "quit" +"</html>");
	      quit.addActionListener(new ButtonListener());
	      
	      
	      area = new JTextArea();
	      JScrollPane scroll = new JScrollPane(area);
	      
	      buttonPanel = new JPanel();
	      buttonPanel.setLayout(new FlowLayout());
	      
	      buttonPanel.add(help);
	      buttonPanel.add(about);
	      buttonPanel.add(reset);
	      buttonPanel.add(process);
	      
	      
	 //   inputAndButtons = new JPanel();
	    
	   // inputAndButtons.setLayout(new GridLayout(2,1));
	    
	   // inputAndButtons.add(inputPanel);
	   // inputAndButtons.add(add);
	    
	    JPanel inAddButton = new JPanel();
	    inAddButton.setLayout(new GridLayout(3,1));
	    
	    JPanel addButton = new JPanel();
	    addButton.setLayout(new FlowLayout());
	    addButton.add(add);
	    
	    
	    
	    inAddButton.add(inputPanel);
	    inAddButton.add(addButton);
	    inAddButton.add(buttonPanel);
	     
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new GridLayout(1,1));
	   
	    mainPanel.add(inAddButton);
	  //  mainPanel.add(inputAndButtons);
	
	    setLayout(new GridLayout(1,1));
	    //setLayout(new FlowLayout());
	    add(mainPanel);
	    add(scroll);

	}

	 private class ButtonListener implements ActionListener
	    {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 if(event.getSource() == help){
	        		 
	        		 JFrame helpF = new JFrame("Help");
	        		 
	        		helpF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        		 
	        		 
	        	 }
	        	 if(event.getSource() == about){
	        		 
	        		 
	        	 }
	        	 if(event.getSource() == reset){
	        		 
	        		 actList.clear();
	        		 area.setText("");
	        		 
	        		 name.setText("");
	        		 depend.setText("");
	        		 time.setText("");
	        	 }
	        	 if(event.getSource() == process){
	        		 
					 objectForParsing = new PseudoCodeNonsense(numberOfNodes, nodeList);
					 //System.out.println("hello");
	        		 area.setText("");
	        		 /*
					 System.out.println("This is how many activities are in the list" + actList.size());
	        		 for(int i = 0; i< actList.size(); i++)
					 {
	        			 
	        			 area.setText(area.getText() + actList.get(i).toString());
	        			 System.out.println(area.getText() + actList.get(i).toString());
	        		 }
					 */
					 objectForParsing.findFinalNodes();
					 objectForParsing.traceAllPaths();
					 System.out.println("finished finding and tracing");
					 /*
					 if (objectForParsing.arrayOfPaths == null)
					 {
						 System.out.println("this is your error");
					 }
					 System.out.println(objectForParsing.arrayOfPaths.get(0));
					 */
					for (int i = 0; i < objectForParsing.arrayOfPaths.size(); i++)
					{
						System.out.println(objectForParsing.arrayOfPaths.get(i));
					}
	        		
	        	 }//end of "process" event handler
	        	  
	        	 if(event.getSource() == add){
	        		 //read the information from the text fields
	       
	        		 String actName;
	        		 String[] actDep;
	        		 String actTime;
	        		 int duration;
					 
					 
	        		 
	        		 actName = name.getText();
	        		 actDep = depend.getText().split(",");
					 //System.out.println(actDep);
	        		 //Must first read in time as a string and then parse as integer
	        		 actTime = time.getText();
	        		 
	        		 try{
	        			Integer.parseInt(actTime); 
	        			
	        		 }
	        		 catch(NumberFormatException numExcept){
	        			 JOptionPane.showMessageDialog(null, "Please enter a positive integer in the field 'duration'");
	     	        	
	        			 
	        		 }
	        		 
	        	
	        		 duration = Integer.parseInt(actTime);
	        		 //instantiate new activity object
	        		 activity = new Activity();
	        		 activity.setName(actName);
	        		 activity.setDependency(actDep);
					 System.out.println(actName + " is dependent on " + actDep[0]);
	        		 activity.setDuration(duration);
					 
					 activityNode = new PERT_Node(actName, actDep, duration);
					 activityNode.setName(actName);
					 activityNode.setDependencies(actDep[0], 0);
					 nodeList.add(activityNode.clone());			 
					 actList.add(activity);
					 numberOfNodes++;					 

	        		 name.setText("");
	        		 depend.setText("");
	        		 time.setText("");					 
	        	 
	        	 //area.setText(area.getText() + activity.toString());
	        	 }//end of "add" event handler

	         } //end of actionPerformed method
	    } //end of ButtonListener class
	

	
}
