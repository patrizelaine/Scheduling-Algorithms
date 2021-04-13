import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class FIFO
{
    private static ArrayList<Process> processes = new ArrayList<>();

    public FIFO(ArrayList<Process> ProcessList)
    {
        processes = (ArrayList<Process>) ProcessList.clone();
    }

    public static void simulatesFIFO()
    {
        int n = processes.size();

        int t = 0;//current
        int TT; //turn around time
        float ATT; // average turn around time
        int completedP = 0;

        // holds scheduled processes based on arrival time
        PriorityQueue<Process> scheduledProcesses = new PriorityQueue<>(n, new ProcessComparator());

        // holds turn around times for processes in the order they were executed
        ArrayList<Integer> TurnAroundTimes = new ArrayList<>();

        // adding in the processes to priority queue, sorting by arrival time
        for(Process p :processes)
        {
            scheduledProcesses.add(p);
        }
        System.out.println("number of processes to be scheduled: " + scheduledProcesses.size());

        while(!scheduledProcesses.isEmpty())
        {
            Process p = scheduledProcesses.peek();//copy next process in queue
            System.out.println("Current time: " + t +
                    "\nTotal CPU time: " + p.getTotCPUTime() +
                    "\nArrival time: " + p.getArrivalTime() +
                    "\nActive status: " + p.getActive());
            //if current process's arrival time is greater than current time,
            //no active processes so increment current time (t)
            if(p.getArrivalTime() > t)
            {
                System.out.println("@@@@There are no active processes@@@@");
                t++;
                continue;
                //break;
            }
            else
            {
                //setting the current process to active
                p.setActive(1);
            }

            //turnaround time = current time - arrival time + total CPU time
            TT = t - p.getArrivalTime() + p.getTotCPUTime();
            //adding current process' turnaround time to list for average turn around time calculation
            TurnAroundTimes.add(TT);
            System.out.println("Turn around time: " + TT);
            //choose active process to run next
            if(p.getActive() == 1)
            {
                System.out.println("*******choosing an active process*******");
                int RemCPUtime = p.getRemCPUTime();
                while(p.getRemCPUTime() != 0)
                {
                    System.out.println("remaining CPU time: " + RemCPUtime);
                    System.out.println("Current time: " + t);
                    //decrement remaining CPU time
                    RemCPUtime = RemCPUtime - 1;
                    p.setRemCPUTime(RemCPUtime);
                    //update current time
                    t++;
                }
                if(RemCPUtime == 0)
                {
                    /*System.out.println(p.getName() + " remaing cpu time is 0\n" +
                            "current time is: " + t); */
                    // set to not active
                    p.setActive(0);
                    // remove from list of processes to be scheduled
                    System.out.println("#######removing " + scheduledProcesses.peek());
                    scheduledProcesses.poll();
                    completedP++;
                }
            }

            //calculate Average turn around times
            int total = 0;
            if(scheduledProcesses.isEmpty())
            {
                for(Integer turntime: TurnAroundTimes)
                {
                    total+=turntime;
                }
                ATT = (float) total/ (float) TurnAroundTimes.size();
                System.out.println("Average turn around time for FIFO was: " + ATT);
                System.out.println("# completed processes: " + completedP);
            }
        }
    }
}
