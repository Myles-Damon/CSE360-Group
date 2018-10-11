import java.util.*;

public class PseudoCodeNonsense
{
	
	ArrayList<String> arrayOfPaths;// = new String[20];
	ArrayList<PERT_Node> nodeList;
	ArrayList<PERT_Node> finalNodeList;
	PERT_Node tempNode;
	boolean isFinalNode;
	int numOfNodes;
	int counter = 0;
	String firstLoopString;
	String secondLoopString;
	int[] finalNodeIndexes;
	
	// constructor
	public PseudoCodeNonsense(int numberOfNodes, ArrayList<PERT_Node> nodeListIn)
	{
		this.finalNodeList = new ArrayList<PERT_Node>();
		this.arrayOfPaths = new ArrayList<String>();
		this.nodeList = new ArrayList<PERT_Node>();
		tempNode = nodeListIn.get(0);
		for (int i = 0; i < nodeListIn.size(); i++)
		{
			this.nodeList.add(nodeListIn.get(i).clone());
		}
		this.numOfNodes = numberOfNodes;
		//this.finalNodeIndexes = new int[];
		for(int j = 0; j < this.nodeList.size(); j++)
		{
			System.out.println("hi " + nodeList.get(j).name);
		}
		System.out.println("ok, at least the object was instantiated");
	}
//2^n is the MAX number of paths given n nodes, I think... I just put 20 here to test


	public void findFinalNodes()
	{
		if(this.nodeList == null)// || this.arrayOfPaths.get(0) == null)
		{
			System.out.println("give me a node list please");
			return;
		}
		
		// need to decide which nodes are final nodes
		for (int j = 0; j < numOfNodes; j++)
		{
			isFinalNode = true; // reset the flag for every loop; the default assumption is that it is a final node
			//firstLoopString = nodeList.get(j).name;//[j].name;
			for (int k = 0; k < numOfNodes; k++)
			{
				if (nodeList.get(k).dependencies[0] != null)
				{
					for (int s = 0; s < nodeList.get(k).dependencies.length; s++)
					{
						if (nodeList.get(j).name.equals(nodeList.get(k).dependencies[s]))
						{
							isFinalNode = false; 	// set flag making note that the node is listed as 
									// a dependency and so it's not a final node
						}
					}
				}
			}
			// If the node isn't listed as a dependency anywhere
			// add it to the list of "final nodes"
			if(isFinalNode == true)
			{
				System.out.println(j);
				System.out.println("found a final node");
				//finalNodeIndexes[counter] = j;
				//counter++;
				finalNodeList.add(nodeList.get(j).clone());
			}
		}
		System.out.println(finalNodeList.get(0).name);
		System.out.println("All final nodes found");
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
			traceAllPathsHelper(finalNodeList.get(j), /*finalNodeList.get(j).name*/"");
			// checks for edge case where a node is independent (starting and final node)
			/*
			if(finalNodeList.get(j).dependencies == null)
			{
				arrayOfPaths.add(finalNodeList.get(j).name);
			}
			else
			*/
			System.out.println("final node " + finalNodeList.get(j).name + " evaluated");	
		}
		System.out.println("trace all paths completed");
		return;
	}
	
	public void traceAllPathsHelper(PERT_Node Node, String pathSoFar)
	{
		System.out.println(Node.dependencies[0]);
		// when recursion bottoms out, add the completed path to the arrayOfPaths and return
		if(Node.dependencies[0] == null || Node.dependencies[0].equals(""))
		{
			pathSoFar = pathSoFar + " -> " + Node.name;
			System.out.println(pathSoFar);
			arrayOfPaths.add(pathSoFar);
			System.out.println("path added");
			return;
		}
		// continue depth-first recursion 
		else
		{
			for(int j = 0; j < Node.dependencies.length; j++)
			{
				for(int k = 0; k < nodeList.size(); k++)
				{
					if(nodeList.get(k).name.equals(Node.dependencies[j]))
					{
						pathSoFar = pathSoFar + " -> " + Node.name;
						System.out.println("recursion taken, path so far: " + pathSoFar);
						traceAllPathsHelper(nodeList.get(k), pathSoFar);
					}
				}
			}
		}
		System.out.println("finished tracing all paths");
		return;
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