import java.util.ArrayList;
public class Jobs {
    int number, duration;
    boolean assignment;
    ArrayList<Integer> dependencies;
    ArrayList<Integer> jobsDependedOn;

    public Jobs (int number, int duration, ArrayList<Integer> dependencies){
        this.number = number;
        this.duration = duration;
        jobsDependedOn = new ArrayList<>();
        //this.dependencies = new ArrayList<>();
        this.dependencies = dependencies;
        assignment = false;
    }

    public String toString(){
        return "This jobs number is: " + number + " its duration is: " + duration + " and the jobs that depend on it are: " + dependencies + " jobs it relies on are: " + jobsDependedOn;
    }

    public void addDependency(int jobNum){
        jobsDependedOn.add(jobNum);
    }


}
