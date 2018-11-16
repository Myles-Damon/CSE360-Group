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
        sortActivities(activities);
        try{
            writer.write("Report name: " + name + "\n");
            writer.write("Time: " + java.time.LocalTime.now() + "\n");
            writer.write("Date: " + java.time.LocalDate.now() + "\n");
            writer.write("___________________________________________\n");
            writer.write("Activities: \n");
            //output activities, duration and dependencies
            for (int i = 0; i < activities.size(); i++) {
                writer.write(activities.get(i).getName() +" : "+  activities.get(i).getDuration()+ "\n");
                writer.write("\t[");
                //loop through dependencies and write
                for (int j = 0; j < activities.get(i).dependencies.length; j++) {
                    writer.write(activities.get(i).dependencies[j]);
                    if (j != activities.get(i).dependencies.length - 1) {
                        writer.write(", ");
                    }
                }
                writer.write("]");
                writer.write("\n\n");
            }

            //output paths and durations
            writer.write("___________________________________________\n");
            writer.write("Paths: \n");
            for (int i = 0; i < paths.size(); i++) {
                writer.write(paths.get(i).getPath() + " : " + paths.get(i).getDuration() + "\n\n");
            }


            writer.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void sortActivities(ArrayList <PERT_Node> list) {
        for (int i = 0; i < list.size(); i++) {
            int key = (int)list.get(i).getName().charAt(0);
            PERT_Node keyNode = new PERT_Node(list.get(i).getName(),list.get(i).dependencies,  list.get(i).getDuration());
            int j = i - 1;
            while (j >= 0 && (int)list.get(j).getName().charAt(0) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, keyNode);
        }
    }
}
