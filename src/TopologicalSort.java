import java.util.ArrayList;


public class TopologicalSort{
    int operationTime, processors;
    ArrayList<Jobs> jobsArray;
    ArrayList<ArrayList<Integer>> blocks;

    public TopologicalSort(){
        jobsArray = new ArrayList<>();
        blocks = new ArrayList<>();
        operationTime = 0;
        processors = 0;
    }

    public void addJob(Jobs theJob){
        jobsArray.add(theJob);
    }

    public void populateDependencies(){
        for (Jobs j : jobsArray){
            for(int i : j.dependencies){
                jobsArray.get(i).addDependency(j.number);
            }


        }
    }

    public ArrayList<Integer> subRoutine(Counter counter){
        ArrayList<Integer> temp = new ArrayList<>();
        for (Jobs j : jobsArray){
            if (!j.assignment) {
                boolean tempbool = true;
                for (int i : j.jobsDependedOn){
                    if (!jobsArray.get(i).assignment){
                        tempbool = false;
                    }
                }
                if (tempbool){
                    temp.add(j.number);
                    //j.assignment = true;
                    counter.count --;
                }
            }
        }

        return temp;
    }

    /*
    public ArrayList<Integer> subRoutinev2(Counter counter){
        ArrayList<Integer> temp = new ArrayList<>();
        for (Jobs j : jobsArray){
            if (!j.assignment){
                boolean tempbool = true;
                for (int i : j.jobsDependedOn){

                }
            }
        }
    }
    */

    public void generateBlocks(){
        Counter count = new Counter(jobsArray.size());
        ArrayList<Integer> temp = new ArrayList<>();
        for (Jobs j : jobsArray){
            if (j.jobsDependedOn.size() == 0){
                temp.add(j.number);
                j.assignment = true;
                count.count --;
            }
        }
        blocks.add(temp);
        while(count.count != 0){
            temp = subRoutine(count);
            for (int i : temp){
                jobsArray.get(i).assignment = true;
            }
            blocks.add(temp);
            //blocks.add(subRoutine(count));
        }
    }

    public void setTimeAndProcessors(){
        int testProc = 0;
        int totalTime = 0;
        int testTime = 0;
        for (int i = 0; i < blocks.size(); i++){
            if (blocks.get(i).size() > testProc){
                testProc = blocks.get(i).size();
            }
        }
        for (int i = 0; i < blocks.size(); i++){
            for(int j : blocks.get(i)){
                if (jobsArray.get(j).duration > testTime){
                    testTime = jobsArray.get(j).duration;
                }
            }
            totalTime = totalTime + testTime;
            testTime = 0;
        }
        operationTime = totalTime;
        processors = testProc;
    }


    class Counter{
        int count;
        public Counter(int input){
            count = input;
        }
    }

}
