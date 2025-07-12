/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.os;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
/**
 *
 * @author Reda
 */

public class Algo
{
    public static volatile ArrayList<Process> myList= new ArrayList<Process>();
    public static ArrayList<ResultProcess> global_process_jobs = new ArrayList<ResultProcess>();
    public static ArrayList<Process> global_process_Priority_queue = new ArrayList<Process>();

    public static volatile boolean checker = true;

    public static volatile boolean checker2 = true;

    public static volatile int timer=0;

    public static volatile boolean live;

    public static volatile boolean adding =false;

    public static volatile boolean isPriority =false;

    public static volatile Process globalProcess=null;

    public static void priority(ArrayList<Process> myList) {
        try {
            Priority pq =new Priority(myList, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sjf(ArrayList<Process> myList)  {
        try {
            SJF pq =new SJF(myList,false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sjf_preem(ArrayList<Process> myList) {
        try {
            SJF pq =new SJF(myList,true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void priority_preem(ArrayList<Process> myList) {
        try {
            Priority pq =new Priority(myList,true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static  void fcfs(ArrayList<Process> p) {

        Thread processThread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                Process.setCompare_type(0);
                Collections.sort(p);
                timer = p.get(0).getArriving_time();
                int i = 0;
                while (i < p.size())
                {
                    System.out.println("*");
                    if (checker)
                    {
                        System.out.println("%");
                        ResultProcess j = new ResultProcess(p.get(i).getProsess_name(), timer, (int) p.get(i).getBurst_time(), p.get(i));
                        global_process_jobs.add(j);
                        p.get(i).setFinish_time((int)timer + p.get(i).getBurst_time());
                        timer += p.get(i).getBurst_time();
                        i++;
                        checker = false;
                    }
                }
                checker2 = false;
            }
        });
        processThread.start();
    }

}

    
    
    
    
    
    
    
    
//    public static synchronized  void  fcfs(ArrayList<Process> p) {
//          
//        Process.setCompare_type(0);
//        Collections.sort(p);
//        int timer =p.get(0).getArriving_time();
//        //ArrayList<ResultProcess> jobs = new ArrayList<ResultProcess>();
//        int i =0;
//        while(i<p.size())
//        {
//            System.out.println("*");
//            if(checker)
//            {
//                System.out.println("%");
//                ResultProcess j = new ResultProcess(p.get(i).getProsess_name(), timer, (int) p.get(i).getBurst_time(), p.get(i));
//                global_process_jobs.add(j);
//                p.get(i).setFinish_time((int)timer + p.get(i).getBurst_time());
//                timer += p.get(i).getBurst_time();
//                i++;
//                checker= false;
//        }    
//    }
//        checker2= false;
//      }
    

    
    
    
    
    
    
    
    
//    
//     public static ArrayList<ResultProcess> RR(ArrayList<Process> p, int qt) {
//        Process.setCompare_type(0);
//        Collections.sort(p);
//        Queue<Process> q = new LinkedList<>();
//        ArrayList<ResultProcess> jobs = new ArrayList<ResultProcess>();
//        int timer = p.get(0).getArriving_time();
//        ResultProcess j;
//        int i = 0;
//        while (i < p.size() || !q.isEmpty()) {
//            while (i < p.size() && timer >= p.get(i).getArriving_time()) {
//                q.add(p.get(i));
//                i++;
//            }
//              if (q.peek().getRemaining_time()> qt) { 
//                    j = new ResultProcess(q.peek().getProsess_name(), timer, qt, q.peek());
//                    
//                    jobs.add(j);
//                    q.peek().setRemaining_time((int)q.peek().getRemaining_time()- qt);
//                   // timer += j.getBurst_time();
//                    timer += qt;
//                    
//                  while (i < p.size() && timer >= p.get(i).getArriving_time()) {
//                            q.add(p.get(i));
//                            i++;
//                    }
//                   q.add(q.peek());
//                }
//              else {
//                    j = new ResultProcess(q.peek().getProsess_name(), timer, q.peek().getRemaining_time(), q.peek());
//                    jobs.add(j);
//                  timer+= q.peek().getRemaining_time();
//                }
//                q.poll();                
//            
//        
//    }
//        return jobs ;
//     }
    
//}

