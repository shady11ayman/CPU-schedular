/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

/**
 *
 * @author renal
 */
public class Process  implements Comparable<Process>
{
    private String prosess_name;
    private int arriving_time;
    private int remaining_burst_time;
    private int burst_time;
    private int priority;
    private int waiting_time;
    private float finish_time;
    private float turnAround_time;
    private static int number_of_process;
    private static int compare_type;
   
    public Process(String process_name, int arriving_time, int burst_time) {
        this.prosess_name= process_name;
        this.arriving_time = arriving_time;
        this.burst_time = burst_time;
        this.remaining_burst_time = burst_time;
        number_of_process++;
    }
    public Process(String prosess_name, int arrival_time, int burst_time, int priority) {
        this(prosess_name, arrival_time, burst_time);
        this.priority =priority;
    }

    public String getProsess_name() {
        return prosess_name;
    }

    public void setProsess_name(String prosess_name) {
        this.prosess_name = prosess_name;
    }

    public int getArriving_time() {
        return arriving_time;
    }

    public void setArriving_time(int arriving_time) {
        this.arriving_time = arriving_time;
    }

    public int getRemaining_burst_time() {
        return remaining_burst_time;
    }

    public void setRemaining_burst_time(int remaining_burst_time) {
        this.remaining_burst_time = remaining_burst_time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    } 

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }
    
    public float getBurst_time() {
        return burst_time;
    }

    
    public void setFinish_time(float finish_time) {
        this.finish_time = finish_time;
    }

    public void setTurnAround_time(float turnAround_time) {
        this.turnAround_time = turnAround_time;
    }
    
    public static void setCompare_type(int compare_type) {
        Process.compare_type = compare_type;
    }
 
    public float getFinish_time() {
        return finish_time;
    }
    
     public float getTurnAround_time() {
        return turnAround_time;
    }
    
    public static int getNumber_of_process() {
        return number_of_process;
    }

    public static int getCompare_type() {
        return compare_type;
    }
    
    @Override
    public int compareTo(Process p)
    {
        // compare with respect to arrival time ascendengly
        if(compare_type == 0)
            return (this.getArriving_time() - p.getArriving_time());
        
        // compare with respect to burst time ascendengly
        else if(compare_type == 1)
            return (int)(this.getBurst_time() - p.getBurst_time());
        
        // compare with respect to priority 
//        else if(compare_type1==0 && compare_type1==0)
            
        else 
            return (int)(this.getPriority() - p.getPriority()); 
       }
    
}
