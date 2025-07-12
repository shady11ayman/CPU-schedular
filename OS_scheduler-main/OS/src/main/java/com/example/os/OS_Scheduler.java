/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.example.os;

import java.util.ArrayList;
/**
 *
 * @author Reda
 */
public class OS_Scheduler {

    public static void main(String[] args) {
        ArrayList<Process> myList = new ArrayList<Process>();

        Process p1 = new Process("p1", 0, 8, 2);
        Process p2 = new Process("p2", 2, 6, 4);
        Process p3 = new Process("p3", 4, 7, 1);
        Process p4 = new Process("p4", 5, 7, 3);

        myList.add(p1);
        myList.add(p2);
        myList.add(p3);
        myList.add(p4);
        Algo a = new Algo();
        a.priority(myList);


        while (Algo.checker2) {
            if (Algo.checker == false) {
                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getProsess_name());
                System.out.println(Algo.global_process_Priority_queue.get(Algo.global_process_Priority_queue.size() - 1).getRemaining_time());
                System.out.println("------------------------------");
                Algo.checker = true;
            }
//            long startTime = System.currentTimeMillis(); // get the current time in milliseconds
//            while (System.currentTimeMillis() < startTime + 2000) {
//            }
        }

        System.out.println("***********************************");
        for (int i = 0; i < Algo.global_process_Priority_queue.size(); i++) {
            System.out.println(Algo.global_process_Priority_queue.get(i).getProsess_name());
            System.out.println(Algo.global_process_Priority_queue.get(i).getRemaining_time());
        }
        System.out.println("#####################");

        for (int i = 0; i < Priority_queue.global_process_total_data.size(); i++) {
            System.out.println("!!!!!!!!!!!!!!");
            System.out.println(Priority_queue.global_process_total_data.get(i).getProsess_name());
            System.out.println(Priority_queue.global_process_total_data.get(i).getRemaining_time());
            System.out.println((int) Priority_queue.global_process_total_data.get(i).getTurnAround_time());
            System.out.println(Priority_queue.global_process_total_data.get(i).getWaiting_time());
            System.out.println("^^^^^^^^^");
            System.out.println("| | | | | | | | | | |");
        }


        System.out.println("#####################");


    }
}