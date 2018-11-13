


public class PERT_Node implements Cloneable
{
	String[] dependencies = new String[10];
	String name;
	int duration;
	
	
	public PERT_Node()
	{
		System.out.println("Empty PERT Node made!");
	}
	
	public void setName(String inName)
	{
		this.name = inName;
	}
	
	public void setDependencies(String dependency, int index)
	{
		this.dependencies[index] = dependency;
	}
	
	public void setDependencies(String[] dependenciesIn, int numD, boolean boo)
	{
		for (int i = 0; i < numD; i++)
		{
			this.dependencies[i] = dependenciesIn[i];
		}
	}
	
	public PERT_Node(String name, String[] dependencies, int duration)
	{
		this.name = name;
		this.dependencies = dependencies.clone();
		this.duration = duration;
		System.out.println("PERT Node made!");
	}
	
	public PERT_Node clone()
	{
		PERT_Node that = null;
		try{
			that = (PERT_Node)super.clone();
			that.name = this.name;
			that.dependencies = this.dependencies.clone();
			that.duration = this.duration;
			return that;
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return that;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDependency(int i)
	{
		return dependencies[i];
	}

}

