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
    ArrayList<Integer> arrayOfPathDurations;
    ArrayList<pathAndDuration>arrayListOfPaths;

    // constructor
    public PseudoCodeNonsense(int numberOfNodes, ArrayList<PERT_Node> nodeListIn)
    {
        this.finalNodeList = new ArrayList<PERT_Node>();
        this.arrayOfPaths = new ArrayList<String>();
        this.nodeList = new ArrayList<PERT_Node>();
        arrayOfPathDurations = new ArrayList<Integer>();
        this.arrayListOfPaths = new ArrayList<pathAndDuration>();
        tempNode = nodeListIn.get(0);
        for (int i = 0; i < nodeListIn.size(); i++)
        {
            this.nodeList.add(nodeListIn.get(i).clone());
        }
        this.numOfNodes = numberOfNodes;
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
            traceAllPathsHelper(finalNodeList.get(j), "", 0);

            System.out.println("final node " + finalNodeList.get(j).name + " evaluated");
        }
        System.out.println("trace all paths completed");
        return;
    }

    public void traceAllPathsHelper(PERT_Node Node, String pathSoFar, int pathDuration)
    {
        System.out.println(Node.dependencies[0]);
        // when recursion bottoms out, add the completed path to the arrayOfPaths and return
        if(Node.dependencies[0] == null || Node.dependencies[0].equals(""))
        {
            pathSoFar = Node.name + " -> " + pathSoFar;
            pathDuration += Node.duration;
            System.out.println(pathSoFar);
            arrayOfPaths.add(pathSoFar);
            arrayOfPathDurations.add(pathDuration);
            //create new pathAndDuration from data and add to list
            pathAndDuration newPath = new pathAndDuration(pathSoFar, pathDuration);
            arrayListOfPaths.add(newPath);
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
                        System.out.println("recursion taken, path so far: " + pathSoFar);
                        traceAllPathsHelper(nodeList.get(k), Node.name + " -> " + pathSoFar, pathDuration + Node.duration);
                    }
                }
            }
        }
        System.out.println("finished tracing all paths");
        return;
    }

    //for sorting pathList, quicksort, should be O(nlgn)-ish
    public void sortPathList(ArrayList<pathAndDuration> pathList) {
        for (int i = 1; i < pathList.size(); i++) {
            pathAndDuration key = new pathAndDuration(pathList.get(i).getPath(), pathList.get(i).getDuration());
            int j = i - 1;

            while (j < 0 && pathList.get(j).getDuration() > key.getDuration()) {
                pathList.set(j+1, pathList.get(j));
                j--;
            }
            pathList.set(j+1, key);
        }
    }

/*
Need to fix algorithm so that it checks:
--------------------------------------------

1. If a node lists another node as a dependency when that other node doesn't exist

//(Pseudo-code)
for (each node in node list)
{
	for (each dependency in the nodeA.dependencies)
	{
		dependecyExistsBool = false;
		for (each node in node list)
		{
			if (node.name = nodeA.dependency)
			{
				dependecyExistsBool = true;
			}
		}
		if (dependecyExistsBool = false)
			throw error;
			return error;
	}
}
return happily; // :D

			

2. That a node isn't listed as a dependency by a node upon which it (or any future child node) depends (IE no node loops) [Also solved by checking to make sure that final nodes exist.]

	> Could take any node we're adding to the tree and check to see if its name is listed as a dependency by any previous nodes. Would be really easy if we could make the trees (at least temporarily)
	> Linked-Lists so that traversal is O(1) instead of O(n) every time.

3a. Need to make sure there's at least one starting node
	
	> Could make a list of all starting nodes, DFS from them and mark every searched node as black, and then iterate through the node list one last time to make sure every node is black.
	> If not all nodes are black, then there are some nodes which aren't starting nodes and aren't connected to any nodes
	
3b. Need to make sure that every non-starting node eventually can trace its ancestry back to a starting node, or a node which exists (1.).


*/
	
	
	
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