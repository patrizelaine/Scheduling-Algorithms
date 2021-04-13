import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SJF {
    private ArrayList<Process> processes;

    public SJF(ArrayList<Process> procList){
        processes = (ArrayList<Process>) procList.clone();
    }

    public void SimulateSJF(){
        PriorityQueue<Process> q_SJF; //priority queue for SJF
        int turnaround;
        float ATT; //average turnaround

        //hold active processes
        ArrayList<Process> activeProcesses = new ArrayList<>();

        //hold turnaround times
        ArrayList<Integer> turnTimes = new ArrayList<>();

        //add active processes to list
        for (Process p : processes) {
            if (p.getActive() == 1) {
                activeProcesses.add(p);
            }
        }
        int t=0; //time t = 0
        int counter=0; //completed process counter
        //until all processes have run
        while (!processes.isEmpty()) {
            q_SJF=SJF(activeProcesses); //get order to run active processes

            //simulate running active process
            while (!q_SJF.isEmpty()) {
                System.out.println("Active processes: " + activeProcesses.size());
                Process chosen = q_SJF.peek(); //chosen process @head of SJF queue
                System.out.println( "t: "+ t);
                System.out.println( "(Running process with total CPU time: "+ chosen.getTotCPUTime()+")\n");
                int remTemp = chosen.getRemCPUTime(); //get remaining CPU time
                int decRemTemp = remTemp - 1; //decrement remaining time
                chosen.setRemCPUTime(decRemTemp); //update remaining time
                System.out.println("Remaining time: " + chosen.getRemCPUTime());

                //if process has completed
                if (chosen.getRemCPUTime() == 0) {
                    counter++;
                    t++; //increment time
                    turnaround = t - chosen.getArrivalTime(); //current time - arrival time
                    System.out.println("endtime: " + t);
                    chosen.setActive(0); //process no longer active
                    System.out.println("Turnaround time: " + turnaround);
                    turnTimes.add(turnaround);
                    q_SJF.poll(); //remove completed process from SJF queue
                    processes.remove(chosen); //remove chosen from process list
                    activeProcesses.remove(chosen); //remove chosen from active processes
                }
                //remaining time !=0
                else
                    t++;
            }//end while for active processes

            //update active processes list
            for(Process p: processes){
                if(p.getArrivalTime()<=t){
                    p.setActive(1);
                    activeProcesses.add(p);
                }
            }
            //at this point we know which processes are active
            if(activeProcesses.isEmpty()){
                t++;
            }
        }//end while for entire simulation

        //simulation done -> calc average turnaround
        int sum = 0;
        for(Integer turnTime: turnTimes){
            sum+=turnTime;
        }
        ATT=(float) sum/ (float) counter;
        System.out.println("Average turnaround time for SJF: " + String.format("%.2f",ATT));
        System.out.println("\n"+counter+" processes completed");
    }

    //non pre-emptive
    private PriorityQueue<Process> SJF(ArrayList<Process> activeProcesses){
        PriorityQueue<Process> pq;
        Comparator<Process> comparator = Comparator.comparing((Process p)-> p.getTotCPUTime())
                .thenComparing(p ->
                        p.getArrivalTime());
        pq = new PriorityQueue<>(10000, comparator);
        pq.addAll(activeProcesses);
        return pq;
    }
}