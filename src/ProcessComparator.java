import java.util.Comparator;
class ProcessComparator implements Comparator<Process>
{
    // Overriding compare()method of Comparator
    // for first arrival, first in line to be executed
    public int compare(Process p1, Process p2)
    {
        if (p1.getArrivalTime() > p2.getArrivalTime())
            return 1;
        else if (p1.getArrivalTime() < p2.getArrivalTime())
            return -1;
        return 0;
    }
}