//to contain the path string and path duration in one structure for easier manipulation/sorting
public class pathAndDuration {
    private String path;
    private int duration;

    //constructor
    public pathAndDuration(String pathString, int pathDuration) {
        path = pathString;
        duration = pathDuration;
    }

    //getters and setters
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
