

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Driver
{
    private static int numRuns = 1000;
    private static int seed = 156438;
    private static int[] seeds = new int[1000];
    public static ArrayList createProcesses(int k, int n, int d ,int seed)
    {
        ArrayList<Process> processList = new ArrayList<>(); //n processes
        Random num = new Random(seed);
        int arrTime, totCPU;
        //making v 90% of d, results in CPU times being more spread out
        int v = (int) (0.9 * d);
        for(int i = 0; i < n; i ++)
        {
            //
            totCPU =  (int)num.nextGaussian() * v + d;
            if(totCPU<0){
                totCPU=-totCPU;
            }
            System.out.println("total CPU: " + totCPU);
            // ensure that at least one process arrives at time zero, and setting its active status to 1
            if(i == 0)
            {
                processList.add(new Process(1, 0, totCPU));
            }
            else {
                arrTime = num.nextInt(k);
                processList.add(new Process(0, arrTime, totCPU));
            }
            System.out.println(processList.get(i).getArrivalTime());
            // creating new process objects and putting them in array
            // for processes with an arrival time of 0, active set to 1
        }
        return processList;
    }

    public static void testFIFO(int k ,int n, int d)
    {
        for(int j = 0; j < numRuns; j++)
        {
            ArrayList<Process> testSet = createProcesses(k,n,d, seeds[j]);
            FIFO fifoSimulation = new FIFO(testSet);
            fifoSimulation.simulatesFIFO();
        }
    }

    public static void testSJF(int k ,int n, int d)
    {
        for(int j = 0; j < numRuns; j++)
        {
            ArrayList<Process> testSet = createProcesses(k,n,d, seeds[j]);
            SJF sjfSimulation = new SJF(testSet);
            sjfSimulation.SimulateSJF();
        }
    }

    public static void testSRT(int k ,int n, int d)
    {
        for(int j = 0; j < numRuns; j++)
        {
            ArrayList<Process> testSet = createProcesses(k,n,d, seeds[j]);
            SRT srtSimulation = new SRT(testSet);
            srtSimulation.simulatesSRT();
        }
    }

    public static void main(String[] args)
    {
        //creating seed array
        Random rand = new Random();
        for(int j = 0; j < 1000; j++)
        {
            seeds[j] = rand.nextInt(Integer.MAX_VALUE);
        }

        /****TEST SETS*****/

        // HYPOTHESIS CASE 1: frequency of process arrivals are greater than average total CPU time
        System.out.println("HYPOTHESIS CASE 1: K/N > D");
     
        // (k/n) = 35
        System.out.println("&&FIFO&&");
        testFIFO(35000,1000,15);
        System.out.println("&&SJF&&");
        testSJF(35000,1000,15);
        System.out.println("&&SRT&&");
        testSRT(35000,1000,15);

        // (k/n) = 30
        System.out.println("&&FIFO&&");
        testFIFO(45000,1500,10);
        System.out.println("&&SJF&&");
        testSJF(45000,1500,10);
        System.out.println("&&SRT&&");
        testSRT(45000,1500,10);
        
        // (k/n) = 18.1
        System.out.println("&&FIFO&&");
        testFIFO(90500,5000,3);
        System.out.println("&&SJF&&");
        testSJF(90500,5000,3);
        System.out.println("&&SRT&&");
        testSRT(90500,5000,3);

                
        // HYPOTHESIS CASE 2: frequency of process arrivals are less than average total CPU time
        System.out.println("HYPOTHESIS CASE 2: K/N < D");
        
        // (k/n) = 10
        System.out.println("&&FIFO&&");
        testFIFO(1000,100,15);
        System.out.println("&&SJF&&");
        testSJF(1000,100,15);
        System.out.println("&&SRT&&");
        testSRT(1000,100,15);
        
        // (k/n) = 25
        System.out.println("&&FIFO&&");
        testFIFO(25000,1000,50);
        System.out.println("&&SJF&&");
        testSJF(25000,1000,50);
        System.out.println("&&SRT&&");
        testSRT(25000,1000,50);
        
        // (k/n) = 33.33
        System.out.println("&&FIFO&&");
        testFIFO(50000,1500,2);
        System.out.println("&&SJF&&");
        testSJF(50000,1500,2);
        System.out.println("&&SRT&&");
        testSRT(50000,1500,2);

        
        // HYPOTHESIS CASE 3: convoy effect
        System.out.println("HYPOTHESIS CASE 3: CONVOY");
        
        // (k/n) = 10
        System.out.println("&&FIFO&&");
        testFIFO(10,100,30);
        System.out.println("&&SJF&&");
        testSJF(10,100,30);
        System.out.println("&&SRT&&");
        testSRT(10,100,30);
        
        // (k/n) = 25
        System.out.println("&&FIFO&&");
        testFIFO(50,1000,100);
        System.out.println("&&SJF&&");
        testSJF(50,1000,100);
        System.out.println("&&SRT&&");
        testSRT(50,1000,100);
        
        // (k/n) = 33.33
        System.out.println("&&FIFO&&");
        testFIFO(250,1500,300);
        System.out.println("&&SJF&&");
        testSJF(250,1500,300);
        System.out.println("&&SRT&&");
        testSRT(250,1500,300);
        
        


    }

}
