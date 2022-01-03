import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        TopologicalSort theSort = new TopologicalSort();


        String userInput = "exampleInput.txt";

        try{
            FileInputStream fstream = new FileInputStream(userInput);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            Jobs tempJob;
            while ((strLine = br.readLine()) != null)   {
                String[] tempArray = strLine.split(" ");
                //System.out.println(temp[0]);
                int tempNumber = 0;
                int tempDuration = 0;
                ArrayList<Integer> tempDepends = new ArrayList<>();
                int counter = 0;
                for (String s : tempArray){
                    int convert = Integer.parseInt(s);
                    if (counter == 0){
                        tempNumber = convert;
                    } else if (counter == 1){
                        tempDuration = convert;
                    } else {
                        tempDepends.add(convert);
                    }
                    counter++;
                }

                tempJob = new Jobs(tempNumber, tempDuration, tempDepends);
                theSort.addJob(tempJob);
                //System.out.println(tempJob);
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        theSort.populateDependencies();
        theSort.generateBlocks();
        theSort.setTimeAndProcessors();

        System.out.println("The Operation time is: " + theSort.operationTime + " and the number of processors: " + theSort.processors);
        System.out.println(theSort.blocks);



        for(Jobs j : theSort.jobsArray){
            System.out.println(j);
        }





    }
}
