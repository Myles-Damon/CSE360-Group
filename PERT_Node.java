


public class PERT_Node
{
	String name;
	String dependencies;
	int duration;
	boolean isStartingNode;
	
	
	public void PERT_Node(String name, String dependencies, int duration, boolean isStartingNode)
	{
		name = name;
		dependencies = dependencies;
		duration = duration;
		if(name != null && !dependencies.isEmpty()) 
			isStartingNode = false;
		else 
			isStartingNode = true;
	}
	

}

