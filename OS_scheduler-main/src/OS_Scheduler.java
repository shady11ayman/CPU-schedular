/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;
import java.util.ArrayList;

/**
 *
 * @author renal
 */
public class OS_Scheduler {
    public static void main(String[] args) 
    {
        ArrayList<Process> myList= new ArrayList<Process>();
          
         Process p0 = new Process(0, 5, 11);
         Process p1 = new Process(1, 0,10);
         Process p2 = new Process(2, 1,4);
         Process p3 = new Process(3, 2, 5);
         Process p4 = new Process(4, 3, 6);
         
           myList.add(p0);
           myList.add(p1);
           myList.add(p2);
           myList.add(p3);
           myList.add(p4);
    
           Algorithms a1= new Algorithms();
           ArrayList<Job> res1 = new ArrayList<Job>();
           res1 = a1.RR(myList,4);
            for (Job job : res1) {
            System.out.println(job);
            }
            a1.timing(res1,myList);
            float turnaroundTime = Algorithms.total_turnAround_time;
            float waitingTime = Algorithms.total_waiting_time;
            System.out.println("Turnaround time: " + turnaroundTime);
            System.out.println("Waiting time: " + waitingTime); 
            System.out.println("//*****************************");
            
            ArrayList<Job> res2 = new ArrayList<Job>();
            res2=a1.fcfs(myList);
            for (Job job : res2) {
            System.out.println(job);
            }
            a1.timing(res2,myList);
            float turnaroundTime2 = Algorithms.total_turnAround_time;
            float waitingTime2 = Algorithms.total_waiting_time;
            System.out.println("Turnaround time: " + turnaroundTime2);
            System.out.println("Waiting time: " + waitingTime2); 
            
    }
    }

