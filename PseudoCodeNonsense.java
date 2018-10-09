import java.util.*;

public class PseudoCodeNonsense
{
	
	ArrayList<String> arrayOfPaths;// = new String[20];
	ArrayList<PERT_Node> nodeList;
	ArrayList<PERT_Node> finalNodeList;
	int i;
	int numOfNodes;
	String firstLoopString;
	String secondLoopString
	
	// constructor
	public PseudoCodeNonsense(int numberOfNodes, ArrayList<PERT_Node> nodeList)
	{
		//this.arrayOfpaths = new String[2**numberOfNodes];
		this.nodeList = nodeList;
		this.numOfNodes = numberOfNodes;
		i = 0;
	}
//2^n is the MAX number of paths given n nodes, I think... I just put 20 here to test

	//int i = 0;

	public void findFinalNodes()
	{
		if(this.nodeList.get(0) == null)// || this.arrayOfPaths.get(0) == null)
			System.out.println("give me a node list please");
			return;
		
		// need to decide which nodes are final nodes
		for (int j = 0; j < numOfNodes; j++)
		{
			i = 0; // reset the flag for every loop; the default assumption is that it is a final node
			firstLoopString = nodeList.get(j).name;//[j].name;
			for (int k = 0; k < numOfNodes; k++)
			{
				if nodeList.get(k).dependencies != null
				{
					for (int s = 0; s < nodeList.get(k).dependencies.length; s++)
					{
						if(firstLoopString == nodeList.get(k).dependencies[s])
						{
							i = 1; 	// set flag making note that the node is listed as 
									// a dependency and so it's not a final node
						}
					}
				}
			}
			// If the node isn't listed as a dependency anywhere
			// add it to the list of "final nodes"
			if(i == 0)
			{
				finalNodeList.add(nodeList.get(j));
			}
		}
		return;
	}	
	
	public void traceAllPaths()
	{
		// checks to make sure we're not processing an empty list
		if(finalNodeList.get(0) == null || nodeList.get(0) == null)
		{
			return;
		}
		for(int j = 0; j < finalNodeList.size(); j++)
		{
			if(finalNodeList.get(j).dependencies == null)
			{
				arrayOfPaths.add(finalNodeList.get(j).name);
			}
		}
	}
/*		Pseudo-code for tracing all possible paths	*/

// when searching for a node which is used as a dependency by another node, I'll need to iterate through all of the nodes and just 
// check which one has the name attribute which matches the one referenced by the dependent node
//
//
//for all final nodes
//	String path = "finalNode.name";
//	Backtrack(finalNode, path);
//
//
//Backtrack(PERT_Node node, String path){
//	if node.dependencies == null
//		arrayOfpaths[i] = path;
//		i++;
//		return;
//	for dependencies in node
//		String Backpath = String.copy(path);
//		Backpath.prepend(dependency.name);
//		Backtrack(dependency, Backpath);
//	return;
}