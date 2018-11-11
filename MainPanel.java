
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
	private Activity activity;
	private ArrayList<Activity> actList;
	private PseudoCodeNonsense objectForParsing;
	
	private PERT_Node activityNode;
	public ArrayList<PERT_Node> nodeList;
	public int numberOfNodes = 0;

	public MainPanel(ArrayList<Activity> actList){
		
		this.actList = actList;
		this.nodeList = new ArrayList<PERT_Node>();
			
		JLabel actName = new JLabel("<html>" + "NAME:" + "<br/>" + "Enter name of activity\n " + "</html>");
		JLabel actDep = new JLabel("<html>" +"DEPENDENCIES:" + "<br/>" + "Enter dependencies of activity. Enter a comma between activities" + "</html>");
		JLabel actTime = new JLabel("<html>" + "DURATION" + "<br/>" + "Enter the duration of the activity. (Positive, integer)" + "</html>");
		
		
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
	      area.setEditable(false);
	      JScrollPane scroll = new JScrollPane(area);
	      
	      buttonPanel = new JPanel();
	      buttonPanel.setLayout(new FlowLayout());
	      
	      buttonPanel.add(help);
	      buttonPanel.add(about);
	      buttonPanel.add(reset);
	      buttonPanel.add(process);
	      
	    //panel to hold input & add button
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

	    setLayout(new GridLayout(1,1));

	    add(mainPanel);
	    add(scroll);

	}

	 private class ButtonListener implements ActionListener
	    {
	         public void actionPerformed(ActionEvent event)
	         {
	        	 if(event.getSource() == help){
	        		 
	        		 JLabel inputH = new JLabel("<html>" + "INPUT HELP:" + "</html>");
	        		 inputH.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
	        		 inputH.setForeground(Color.BLUE);
	        		 
	        		 JLabel nameH = new JLabel("<html>" + "NAME: In the 'NAME' field, enter a name for the activity. The name can be multiple characters." + "</html>");
	        		 JLabel dependencyH = new JLabel("<html>" + "DEPENDENCIES: In the 'DEPENDENCIES' field, enter the dependencies of the activity. Each dependency can be multiple characters. Enter a comma between dependencies. If the activity has no dependencies, leave the field blank." + "</html>");
	        		 JLabel durationH = new JLabel("<html>" + "DURATION: in the 'DURATION' field, enter the time it takes to complete the activity. ONLY positive integers are accepted. The duration MUST be greater than or equal to 1" + "</html>");
	        		 JLabel cycle = new JLabel("<html>" + "A cycle is created when activities are dependent on each other. Example: A depends on B. B depends on A. A->B->A->B....etc., creating an endless loop known as a cycle." + "</html>");
	        		 JLabel indepAct = new JLabel("<html>" + "An independent activity is an activity that no other activities depend on AND has no dependencies itself. Ensure that every activity has at least one (unless it is a start activity) and that it at least one other activity depends on it." + "</html>");
	        		
	        		 //open a new help window
	        		 JFrame helpF = new JFrame("Help");
	        		 
	        		 //contains everything pertaining to input help section
	        		 JPanel inputHPanel = new JPanel();
	        		
	        		 inputHPanel.add(nameH);
	        		 inputHPanel.add(dependencyH);
	        		 inputHPanel.add(durationH);
	        		 
	        		 inputHPanel.setLayout(new GridLayout(4,1));
	        		 
	        		 //contains everything pertaining to the common issues section
	        		 JPanel errorPanel = new JPanel();
	        		 errorPanel.setLayout(new GridLayout(3,1));
	        		 
	        		 JLabel errorH = new JLabel("<html>" + "COMMON ISSUES:" + "</html>");
	        		 errorH.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
	        		 errorH.setForeground(Color.RED);
	        		 
	        		 
	        		 errorPanel.add(cycle);
	        		 errorPanel.add(indepAct);
	        		 
	        		 
	        		 //topPanel combines input & error panels into one panel
	        		 JPanel topPanel = new JPanel();
	        		 topPanel.setLayout(new GridLayout(4,1));
	        		 
	        		 topPanel.add(inputH);
	        		 topPanel.add(inputHPanel);
	        		 topPanel.add(errorH);
	        		 topPanel.add(errorPanel);
	        		 
	        		 //Puts topPanel on the JFrame
	        		 Container content = helpF.getContentPane();
	        		 content.add(topPanel);
	        		 
	        		 //set minimum dimension for the window
	        		 Dimension minDim = new Dimension(750,600);
	        		 
	        		 helpF.setSize(800, 600);
	        		 helpF.setMinimumSize(minDim);
	        		 helpF.setVisible(true);
	        		  
	        		 //close help screen but keep main window open
	        		 helpF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        		 
	        	 }// end of "help" event handler
	        	 if(event.getSource() == about){
	        		 
	        		 //open a new about window
	        		 //set Frame size, minimum size, and default close operation, and set to visible
	        		 JFrame aboutF = new JFrame("About");
	        		 Dimension dim = new Dimension(400,300);
	        		 aboutF.setSize(400,300);
	        		 aboutF.setMinimumSize(dim);
	        		 aboutF.setVisible(true);
	        		 
	        		 //close help screen but keep main window open
	        		 aboutF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        		 
	        		 //set up labels and a Jpanel for labels
	        		 JLabel jVer = new JLabel("<html>"+ "VERSION:" + "</html>");
	        		 JLabel ver = new JLabel("<html>" + "1.2.10.09.18" + "<br/>" + "</html>");
 	        		 JLabel jDevs = new JLabel("<html>" + "DEVELOPERS:" + "</html>");
 	        		 JLabel created = new JLabel("<html>" + "Created in 2018" + "</html>");
 	        		 JLabel update = new JLabel("<html>" + "Last updated in 2018" + "</html>");
	        		 JLabel devsName = new JLabel("<html>" + "<br/>" + "Myles Damon" + "<br/>" + "Lucia Romero" + "<br/>" + "Garrett Tang" + "<br/>" + "Trent Hall" + "</html>");
	        		 devsName.setForeground(Color.BLUE);
	        		 
	        		 JLabel empty = new JLabel("-----------------------------------------------------------------------------------------------");
	        		 JPanel info = new JPanel();
	        		 
	        		 info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
	        		 
	        		
	        		 info.add(jVer);
	        		 info.add(ver);
	        		 info.add(created);
	        		 info.add(update);
	        		 info.add(empty);
	        		 info.add(jDevs);
	        		 info.add(devsName);

	        		 Container contain = aboutF.getContentPane();
	        		 
	        		 contain.add(info);
	        		 
	        	 }// end of "about" event handler
	        	 if(event.getSource() == reset){
	        		 
	        		 if(actList.isEmpty() && name.getText().equals("") && depend.getText().equals("") && time.getText().equals("") ){
	        			 JOptionPane.showMessageDialog(null, "There is no data to reset", "No Data", JOptionPane.INFORMATION_MESSAGE);	 
	        		 }
	        		 else{
	        		 //ask user if they are sure they want to reset the data
	        		 if(JOptionPane.showConfirmDialog(null, "Are you sure you want to reset? All current data will be deleted.", "Reset", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
	        			
	        			 //clear the textAra, text fields, and the activity list 
	        			 actList.clear();
		        		 area.setText("");
		        		 name.setText("");
		        		 depend.setText("");
		        		 time.setText("");
	 		    	}
	        	 }
	        		
	        	 if(event.getSource() == process){
	        		
					
					if(actList.isEmpty()){
	        			 
	        			 JOptionPane.showMessageDialog(null, "Please enter at least one activity before processing data.", "Error: No data to process", JOptionPane.ERROR_MESSAGE);
	        		}
	        		else{
						
						objectForParsing = new PseudoCodeNonsense(numberOfNodes, nodeList);
	        		
						area.setText("");
		        		
						objectForParsing.findFinalNodes();
						objectForParsing.traceAllPaths();
						System.out.println("finished finding and tracing");

						String message = "";
						for (int i = 0; i < objectForParsing.arrayOfPaths.size(); i++)
						{
							message += objectForParsing.arrayOfPaths.get(i) + " : " +objectForParsing.arrayOfPathDurations.get(i) + "\n";
							System.out.println(objectForParsing.arrayOfPaths.get(i));
						}

						JOptionPane.showMessageDialog(null, message, "output", JOptionPane.PLAIN_MESSAGE);

						// what is this for loop below for? - Myles
	        		 	for(int i = 0; i< actList.size(); i++){
		        			 
		        			 area.setText(area.getText() + actList.get(i).toString());
		        			// System.out.println(actList.get(i).toString());
	        		 	}
	        		}
	        		    		
	        	 }
	
	        	 if(event.getSource() == add){
	        		
	        		 String actName;
	        		 String[] actDep;
	        		 String[] testDep;
	        		 int duration = 0;
	        		 int flag = 0;
	        		 
	        		 testDep = depend.getText().split(",");
	        		 //conditions for all the different error messages
	        		 try{
	        			 
	        			 //checks if the dependencies are all valid -- Dependency field can be empty ONLY IF THERE ARE NO DEPENDENCIES. If there is one dependency, subsequent dependencies CANNOT BE EMPTY
	        			
	        			 for(int i = 1; i < testDep.length ; i++){        				 
	        					 if(testDep[i].equals("")){
		        				flag++;	      	 
	        				 }
		        			 
	        			 }
	        			 
		         		if(name.getText().equals("") && time.getText().equals("")){
	 	        			JOptionPane.showMessageDialog(null, "Please enter a name in 'NAME' and a time in 'DURATION'", "Error: Incomplete Data", JOptionPane.ERROR_MESSAGE);
	 	        			 }
	 	        		else if(name.getText().equals("")){
	 	        			 	
	 	        				 JOptionPane.showMessageDialog(null, "Please enter a name for the activity in the field 'NAME'", "Error: Incomplete Data", JOptionPane.ERROR_MESSAGE);
	 	        			 }
	 	        		else if(time.getText().equals("")){
	 	        				JOptionPane.showMessageDialog(null, "Please enter a positive integer in the field 'DURATION'", "Error: Incomplete Data", JOptionPane.ERROR_MESSAGE);
	 	        			 }
	 	        			        			      			
	 	        		else if(Integer.parseInt(time.getText()) < 1){
	        				 
	        				 JOptionPane.showMessageDialog(null, "Please enter a positive integer in the field 'duration'", "Error: Negative Number", JOptionPane.ERROR_MESSAGE);	        				
	 	        			 }
	 	        		 else if(flag != 0){
			         			JOptionPane.showMessageDialog(null, "Please ensure that each dependency is at least one character. Empty Strings are not permitted as dependencies.", "ERROR: Invalid Dependency", JOptionPane.ERROR_MESSAGE);		      
			         		} 
	        			else{
	        				
	        	        	 //if there are no errors, read the information from the text fields
	        				 actName = name.getText();
			        		 actDep = depend.getText().split(",");     
			        		
		        			 duration = Integer.parseInt(time.getText());		 
			        	
		        			 //instantiate new activity object & fill in attributes
			        		 activity = new Activity();
			        		 activity.setName(actName);
			        		 activity.setDependency(actDep);
			        		 activity.setDuration(duration);
			        		 
							 //Actual PERT_Node stuff
							 activityNode = new PERT_Node(actName, actDep, duration);
							 activityNode.setName(actName);
							 activityNode.setDependencies(actDep, actDep.length - flag, true);
							 nodeList.add(activityNode.clone());
							 numberOfNodes++;
							 
			        		 area.setText(area.getText() + activity.toString());
			        		 actList.add(activity);		
			        		 name.setText("");
			        		 depend.setText("");
			        		 time.setText("");
			        		 
	        			 }
	        		 }
	        		 catch(NumberFormatException numExcept){
	        			 
	        			 //if an integer is not entered
	        			 if(!time.getText().equals("")){
	        			 JOptionPane.showMessageDialog(null, "Please enter a positive integer in the field 'duration'", "Error: Incorrect Data Type", JOptionPane.ERROR_MESSAGE);	        	
	        			 }	
	        		 }
	        	 }
	        		 
	         } //end of actionPerformed method
	    } //end of ButtonListener class
	
}
