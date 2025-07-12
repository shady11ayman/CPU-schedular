/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package os;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author renal
 */
public class Algorithms {
    
   public static ArrayList<ResultProcess> fcfs(ArrayList<Process> p) {
        Process.setCompare_type(0);
        Collections.sort(p);
        int timer =p.get(0).getArriving_time();
        ArrayList<ResultProcess> jobs = new ArrayList<ResultProcess>();
        for(int i=0;i<p.size();i++)
        {
            ResultProcess j = new ResultProcess(p.get(i).getProsess_name(), timer, p.get(i).getBurst_time(), p.get(i));
            jobs.add(j);
            p.get(i).setFinish_time(timer + p.get(i).getBurst_time());
            timer += p.get(i).getBurst_time();
        }
        return jobs;
    }
    
    
     public static ArrayList<ResultProcess> RR(ArrayList<Process> p, int qt) {
        Process.setCompare_type(0);
        Collections.sort(p);
        Queue<Process> q = new LinkedList<>();
        ArrayList<ResultProcess> jobs = new ArrayList<ResultProcess>();
        int timer = p.get(0).getArriving_time();
        ResultProcess j;
        int i = 0;
        while (i < p.size() || !q.isEmpty()) {
            while (i < p.size() && timer >= p.get(i).getArriving_time()) {
                q.add(p.get(i));
                i++;
            }
              if (q.peek().getRemaining_burst_time()> qt) {
                    j = new ResultProcess(q.peek().getProsess_name(), timer, qt, q.peek());
                    jobs.add(j);
                    q.peek().setRemaining_burst_time(q.peek().getRemaining_burst_time()- qt);
                    timer += j.getBurst_time();
                    while (i < p.size() && timer >= p.get(i).getArriving_time()) {
                        q.add(p.get(i));
                        i++;
                    }
                    q.add(q.peek());
                }
              else {
                    j = new ResultProcess(q.peek().getProsess_name(), timer, q.peek().getRemaining_burst_time(), q.peek());
                    jobs.add(j);
                    timer += j.getBurst_time();
                }
                q.poll();                
            
        
    }
        return jobs ;
     }
    
    
}
