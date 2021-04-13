

import java.util.ArrayList;
import java.util.Random;

public class Driver
{
    private static int seed = 156438;
    public static ArrayList createProcesses(int k, int n, int d /*,int seed*/)
    {
        ArrayList<Process> processList = new ArrayList<>(); //n processes
        Random num = new Random(seed);
        int arrTime, totCPU;
        //making v 90% of d, results in CPU times being more spread out
        int v = (int)0.9 * d;
        for(int i = 0; i < n; i ++)
        {
            //
            totCPU =  (int)num.nextGaussian() * v + d;
            if(totCPU<0){
                totCPU=-totCPU;
            }
            //System.out.println("total CPU: " + totCPU);
            // ensure that at least one process arrives at time zero, and setting its active status to 1
            if(i == 0)
            {
                processList.add(new Process(1, 0, totCPU));
            }
            else {
                arrTime = num.nextInt(k);
                processList.add(new Process(0, arrTime, totCPU));
            }
            //System.out.println(processList.get(i).getArrivalTime());
            // creating new process objects and putting them in array
            // for processes with an arrival time of 0, active set to 1
        }
        return processList;
    }

    public static void main(String[] args)
    {
        /****TEST SETS*****/

        /****frequency of process arrivals are greater than average total CPU time****/
        //set 1:
        // (k/n) = 35
        ArrayList<Process> test1 = createProcesses(35000,1000,15);
        ArrayList<Process> test2 = createProcesses(35000,1000,15);
        ArrayList<Process> test3 = createProcesses(35000,1000,15);

        //set 2:
        // (k/n) = 30
        ArrayList<Process> test4 = createProcesses(45000,1500,10);
        ArrayList<Process> test5 = createProcesses(45000,1500,10);
        ArrayList<Process> test6 = createProcesses(45000,1500,10);

        //set 3:
        // (k/n) = 18.1
        ArrayList<Process> test7 = createProcesses(90500,5000,3);
        ArrayList<Process> test8 = createProcesses(45000,1500,3);
        ArrayList<Process> test9 = createProcesses(45000,1500,3);

        Random rand = new Random();
        int seed = rand.nextInt(Integer.MAX_VALUE);
        int[] seeds = new int[1000];
        for(int j = 0; j < 1000; j++)
        {
            seeds[j] = seed;
        }

        ArrayList<Process> tester1 = new ArrayList<>();
        ArrayList<Process> tester2 = new ArrayList<>();
        ArrayList<Process> tester3 = new ArrayList<>();
        Process p1 = new Process(0, 0, 6);
        tester1.add(p1);
        tester2.add(p1);
        tester3.add(p1);
        Process p2 = new Process(0, 1, 4);
        tester1.add(p2);
        tester2.add(p2);
        tester3.add(p2);
        Process p3 = new Process(0, 2, 2);
        tester1.add(p3);
        tester2.add(p3);
        tester3.add(p3);

        SRT SRTsimulation1 = new SRT(tester1);
        SRTsimulation1.simulatesSRT();

//        SJFSimulator SJFsimulation1 = new SJFSimulator(tester2);
//        SJFsimulation1.SimulateSJF();
//
//        FIFO FIFOsimulation1 = new FIFO(tester3);
//        FIFOsimulation1.simulatesFIFO();




//        Random random = new Random();
//        for(int i = 0; i < 3; i++)
//        {
//            int seed = random.nextInt(100000);
//            System.out.println("seed: " + seed);
//            ArrayList<Process> tester = createProcesses(100,3,3, seed);
//            for(Process p: tester)
//            {
//                System.out.println(
//                        "\nTotal CPU time: " + p.getTotCPUTime() +
//                                "\nArrival time: " + p.getArrivalTime() +
//                                "\nActive status: " + p.getActive());
//            }
//        }




//        System.out.println("test3: ");
//        for(Process p: test3)
//        {
//            System.out.println(
//                    "\nTotal CPU time: " + p.getTotCPUTime() +
//                            "\nArrival time: " + p.getArrivalTime() +
//                            "\nActive status: " + p.getActive());
//        }
//        System.out.println("test6: ");
//        for(Process p: test6)
//        {
//            System.out.println(
//                    "\nTotal CPU time: " + p.getTotCPUTime() +
//                            "\nArrival time: " + p.getArrivalTime() +
//                            "\nActive status: " + p.getActive());
//        }
//        System.out.println("test9: ");
//        for(Process p: test9)
//        {
//            System.out.println(
//                    "\nTotal CPU time: " + p.getTotCPUTime() +
//                            "\nArrival time: " + p.getArrivalTime() +
//                            "\nActive status: " + p.getActive());
//        }

//
//        SRT SRTsimulation = new SRT(test1);
//        SRTsimulation.simulatesSRT(1000);
//
//        SJFSimulator SJFsimulation = new SJFSimulator(test2);
//        SJFsimulation.SimulateSJF();
//
//        FIFO FIFOsimulation = new FIFO(test3);
//        FIFOsimulation.simulatesFIFO();
///////////////////////////////////////////////////////
//
//        SRT SRTsimulation1 = new SRT(test4);
//        SRTsimulation1.simulatesSRT(1000);
//
//        SJFSimulator SJFsimulation1 = new SJFSimulator(test5);
//        SJFsimulation1.SimulateSJF();
//
//        FIFO FIFOsimulation1 = new FIFO(test6);
//        FIFOsimulation1.simulatesFIFO();
/////////////////////////////////
//        SRT SRTsimulation2 = new SRT(test7);
//        SRTsimulation2.simulatesSRT(1000);
//
//        SJFSimulator SJFsimulation2 = new SJFSimulator(test8);
//        SJFsimulation2.SimulateSJF();
//
//        FIFO FIFOsimulation2 = new FIFO(test9);
//        FIFOsimulation2.simulatesFIFO();

    }

}
