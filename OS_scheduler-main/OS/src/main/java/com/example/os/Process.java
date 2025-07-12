/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.os;

/**
 *
 * @author Reda
 */

public class Process implements Comparable<Process>
{
    private String prosess_name;
    private int arriving_time;
    private int remaining_time;
    int priority;
    private int waiting_time;
    private int position;
    private int turnaround;
    private int first_arriving_time;
    private static int compare_type;
    private static int number_of_process;
    private int burst_time;
    private float finish_time;
    
   
    public int getFirst_arriving_time() {
        return first_arriving_time;
    }
    
    public Process(String prosess_name, int arriving_time, int remaining_time, int priority) {
        this.prosess_name = prosess_name;
        this.arriving_time = arriving_time;
        this.remaining_time = remaining_time;
        this.priority = priority;
        this.waiting_time=0; //??
        this.first_arriving_time=arriving_time;
    }
    
    public Process(String process_name, int arriving_time, int burst_time) {
        this.prosess_name= process_name;
        this.arriving_time = arriving_time;
        this.burst_time = burst_time;
         this.remaining_time = burst_time;
        number_of_process++;
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

    public int getRemaining_time() {
        return remaining_time;
    }
    public void setRemaining_time(int remaining_time) {
        this.remaining_time = remaining_time;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
   public void setPosition(int position) {
         this.position = position;
    }
   public int getPosition() {
        return position;
    }
   
    public int getWaiting_time() {
        return waiting_time;
    }
    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }

   public int getTurnaround() {
        return turnaround;
    }
   public void setTurnaround(int turnaround) {
        this.turnaround = turnaround;
    }

    
  public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }
    
    public int getBurst_time() {
        return burst_time;
    }

    
    public void setFinish_time(float finish_time) {
        this.finish_time = finish_time;
    }

    public void setTurnAround_time(int turnAround_time) {
        this.turnaround = turnAround_time;
    }
    
    public static void setCompare_type(int compare_type) {
        Process.compare_type = compare_type;
    }
 
    public float getFinish_time() {
        return finish_time;
    }
    
     public float getTurnAround_time() {
        return turnaround;
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