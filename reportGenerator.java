import java.io.*;
import java.util.*;

public class reportGenerator {

    private File report;
    private BufferedWriter writer;
    private String name;
    private ArrayList <PERT_Node> activities;
    private ArrayList <pathAndDuration> paths;

    public reportGenerator(String reportName, ArrayList reportActivities, ArrayList reportPaths) {
        name = reportName;
        activities = reportActivities;
        paths = reportPaths;

        try {
            report = new File(name + ".txt");
            writer = new BufferedWriter(new FileWriter(report));
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    public void createReport() {
        try{
            writer.write("Report name: " + name + "\n");
            writer.write("Time: " + java.time.LocalTime.now() + "\n");
            writer.write("Date: " + java.time.LocalDate.now() + "\n");
            writer.write("___________________________________________\n");
            writer.write("Activities: \n");
            //output activities, duration and dependencies
            for (int i = 0; i < activities.size(); i++) {
                writer.write(activities.get(i).getName() +" : "+  activities.get(i).getDuration()+ "\n");
                //loop through dependencies and write
                for (int j = 0; j < activities.get(i).dependencies.length; j++) {
                    writer.write("\t" + activities.get(i).dependencies[j] + "\n");
                }
                writer.write("\n");
            }

            //output paths and durations
            writer.write("___________________________________________\n");
            writer.write("Paths: \n");
            for (int i = 0; i < paths.size(); i++) {
                writer.write(paths.get(i).getPath() + " : " + paths.get(i).getDuration() + "\n");
            }


            writer.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
