import java.util.ArrayList;

public class SRT {
	
	private static ArrayList<Process> processes;

    public SRT(ArrayList<Process> processList){
    	processes = (ArrayList<Process>) processList.clone();
    }
	
	public void simulatesSRT()
    {
    	float ATT = 0;
    	int completedProcesses = 0;
    	int currentTime = 0;
    	int shortestTime = Integer.MAX_VALUE;
    	int shortestProcess = 0;
    	int currentRemainingTime = 0;
    	boolean checkProcess = false;
    	int[] activeTime = new int[processes.size()];
    	
    	// Traverses through loop until all processes are completed
    	while(completedProcesses != processes.size())
    	{
    		for(int i=0; i<processes.size(); i++)
    		{
    			if((processes.get(i).getArrivalTime() <= currentTime) && (processes.get(i).getRemCPUTime() < shortestTime) && (processes.get(i).getRemCPUTime() > 0))
    			{
    				//if process is not active
    			    if(processes.get(i).getActive()==0)
    				{
    				    //set the process to active
    					processes.get(i).setActive(1);
    					//active time of process is the current time
    					activeTime[i] = currentTime;
    				}

    				shortestTime = processes.get(i).getRemCPUTime();
                    //shortest time is the process we're currently at
    				shortestProcess = i;
    				checkProcess = true;
    			}
    		}
    		
    		if(checkProcess==false)
    		{
    			currentTime++;
    			continue;
    		}
    		
    		currentRemainingTime = processes.get(shortestProcess).getRemCPUTime();
    		
    		// Decrements CPU time of the shortest process
    		currentRemainingTime--;
    		processes.get(shortestProcess).setRemCPUTime(currentRemainingTime);
    		shortestTime = processes.get(shortestProcess).getRemCPUTime();
    		
    		// Once the shortest process is completed, update all values
    		if(shortestTime == 0)
    		{
    			shortestTime = Integer.MAX_VALUE;
    		}	
    		
    		if(processes.get(shortestProcess).getRemCPUTime()==0)
    		{
    			completedProcesses++;
    			checkProcess = false;
    			processes.get(shortestProcess).setTurnAround(1+currentTime-processes.get(shortestProcess).getArrivalTime());
    			processes.get(shortestProcess).setActive(0);
    			currentTime++;
    		}
    		else
    		currentTime++;
    	}
    	
    	for(int y=0; y<processes.size(); y++)
    	{
    	    ATT += processes.get(y).getTurnAround();
    	}
    	ATT = ATT/processes.size();
    	System.out.println(/*"SRT ATT IS: "*/ + ATT);
    }
}
