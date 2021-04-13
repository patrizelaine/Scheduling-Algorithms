public class Process {
    private int active;
    private int arrivalTime; //Ai
    private int totCPUTime; //Ti
    private int remCPUTime; //Ri
    private int turnAround; //TTi

    public Process(int activeStatus, int arrTime, int totCPU){
        active = activeStatus;
        arrivalTime = arrTime;
        totCPUTime = totCPU;
        remCPUTime = totCPUTime; //originally Ti
        turnAround = 0; //new process, initially 0
    }

    //set active status
    public void setActive(int act){
        active = act;
    }

    //set arrival time, Ai
    public void setArrivalTime(int arr){
        arrivalTime = arr;
    }

    //set total CPU time, Ti
    public void setTotCPUTime(int tot){
        totCPUTime = tot;
    }

    //set remaining CPU time, Ri
    public void setRemCPUTime(int rem){
        remCPUTime = rem;
    }

    //set turnaorund time, TTi
    public void setTurnAround(int turn){
        turnAround = turn;
    }

    //get active status
    public int getActive() {
        return active;
    }

    //get arrival time, Ai
    public int getArrivalTime(){
        return arrivalTime;
    }

    //get total CPU time, Ti
    public int getTotCPUTime(){
        return totCPUTime;
    }

    //get remaining CPU time, Ri
    public int getRemCPUTime(){
        return remCPUTime;
    }

    //get turnaround time
    public int getTurnAround(){
        return turnAround;
    }
}