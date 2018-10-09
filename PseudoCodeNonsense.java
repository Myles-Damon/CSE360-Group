public class PseudoCodeNonsense
{
	String[] arrayOfpaths = new String[20]; 
//2^n is the MAX number of paths given n nodes, I think... I just put 20 here to test

	int i = 0;


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