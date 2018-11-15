import java.util.ArrayList;

import java.util.Arrays;

public class Activity{

    private String name;
    private String [] dependency;
    private int duration;

    public Activity(){

        this.name = "?";
        this.dependency = new String [0];
        this.duration = 0;

    }

    public String getName(){

        return name;

    }
    public void setName(String name){

        this.name = name;

    }

    public int getDuration(){

        return duration;
    }
    public void setDuration(int duration){

        this.duration = duration;
    }

    public String[] getDependency() {
        return dependency;
    }

    public void setDependency(String[] dependency){

        this.dependency = dependency;

    }

    public String toString()
    {

        String result = " \n Name: \t"
                + getName()+" \n "
                + "Dependencies: \t"+ Arrays.toString(getDependency())+" \n "
                + "duration: \t" + getDuration() + "\n\n";

        return result;
    }


}
