


public class PERT_Node
{
	String name;
	String[] dependencies;
	int duration;
	//boolean isFinalNode;
	
	
	public PERT_Node(String name, String[] dependencies, int duration)
	{
		this.name = name;
		this.dependencies = dependencies;
		this.duration = duration;
		System.out.println("PERT Node made!");
		//this.isFinalNode = isFinalNode;
		/*

	*/
	}
	

}

