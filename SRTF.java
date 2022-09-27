
// SRTF: Shortest Remaining Time First
 
class Process{
    int pId; // Process ID
    int burTime; // Burst Time
    int arrTime; // Arrival Time
     
    public Process(int pId, int burTime, int arrTime){

        this.pId = pId;
        this.burTime = burTime;
        this.arrTime = arrTime;
    }
}
 
public class GFG{


    static void findWaitingTime(Process pro[], int n, int waiTime[]){
        int rTime[] = new int[n];
      
        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rTime[i] = pro[i].burTime;
      
        int complete = 0, T = 0, MIN = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
      

        while (complete != n) {
      
            for (int j = 0; j < n; j++)
            {
                if ((pro[j].arrTime <= T) &&
                  (rTime[j] < MIN) && rTime[j] > 0) {
                    MIN = rTime[j];
                    shortest = j;
                    check = true;
                }
            }
      
            if (check == false) {
                T++;
                continue;
            }

            rTime[shortest]--;

            MIN = rTime[shortest];
            if (MIN == 0)
                MIN = Integer.MAX_VALUE;

            if (rTime[shortest] == 0) {

                complete++;
                check = false;
      
                finish_time = T + 1;
      
                waiTime[shortest] = finish_time -
                             pro[shortest].burTime -
                             pro[shortest].arrTime;
      
                if (waiTime[shortest] < 0)
                    waiTime[shortest] = 0;
            }
            T++;
        }
    }
      
    static void findTurnAroundTime(Process proc[], int n,
                            int wt[], int tat[])
    {
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].burTime + wt[i];
    }
      
    static void findavgTime(Process proc[], int n)
    {
        int WT[] = new int[n], TAT[] = new int[n];
        int  totWT = 0, totTT = 0;

        findWaitingTime(proc, n, WT);
      
        findTurnAroundTime(proc, n, WT, TAT);
      
        System.out.println("Processes " +
                           " Burst time " +
                           " Waiting time " +
                           " Turn around time");
      
        for (int i = 0; i < n; i++) {
            totWT += WT[i];
            totTT += TAT[i];
            System.out.println(" " + proc[i].pId + "\t\t"
                             + proc[i].burTime + "\t\t " + WT[i]
                             + "\t\t" + TAT[i]);
        }
      
        System.out.println("Average waiting time = " +
                          (float)totWT / (float)n);
        System.out.println("Average turn around time = " +
                           (float)totTT / (float)n);
    }
     
    public static void main(String[] args)
    {
         Process pro[] = { new Process(1, 6, 1),
                            new Process(2, 8, 1),
                            new Process(3, 7, 2),
                            new Process(4, 3, 3)};
         
         findavgTime(pro, pro.length);
    }
}